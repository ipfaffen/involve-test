package com.ipfaffen.involve.model.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ipfaffen.involve.model.annotation.Column;
import com.ipfaffen.involve.model.annotation.Table;
import com.ipfaffen.involve.model.datasource.DataSource;
import com.ipfaffen.involve.model.entity.ModelEntity;
import com.ipfaffen.involve.util.Reflection;

/**
 * @author Isaias Pfaffenseller
 */
public abstract class ModelDao<T extends ModelEntity<T>> {

	private static final String COLUMN_DELIMTER = ",";
	
	private DataSource dataSource;
	private Class<T> entityClass;
	private List<Field> entityFields;
	private String tableName;
	private String header;
	private Map<String, Integer> colIndexMap; 
	
	/**
	 * @param dataSource
	 */
	@SuppressWarnings("unchecked")
	public ModelDao(DataSource dataSource) {
		this.dataSource = dataSource;
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.tableName = ((Table) entityClass.getAnnotation(Table.class)).value(); 
	}
	
	/**
	 * @return
	 */
	public Long countAll() {
		try(BufferedReader reader = openReader()) {
			return reader
					.lines()
					.skip(1)
					.count();
		}
		catch(IOException e) {
			throw new UncheckedIOException(e);
		}
	}
	
	/**
	 * @param col
	 * @return
	 */
	public Long countBy(String col) {
		try(BufferedReader reader = openReader()) {
			return reader
					.lines()
					.skip(1)
					.map(row -> row.split(COLUMN_DELIMTER)[getColIndex(col)])
					.distinct()
					.count();
		}
		catch(IOException e) {
			throw new UncheckedIOException(e);
		}
	}
	
	/**
	 * @param col - filter column
	 * @param val - filter value
	 * @return
	 */
	public List<T> findBy(String col, String val) {
		try(BufferedReader reader = openReader()) {
			return reader
					.lines()
					.skip(1)
					.map(row -> row.split(COLUMN_DELIMTER))
					.filter(cols -> cols[getColIndex(col)].equalsIgnoreCase(val))
					.map(cols -> mapObject(cols))
					.collect(Collectors.toList());
		}
		catch(IOException e) {
			throw new UncheckedIOException(e);
		}
	}
	
	/**
	 * @param col - filter column
	 * @param val - filter value
	 * @return
	 */
	public List<String> getRowsBy(String col, String val) {
		List<String> content = new ArrayList<String>();
		try(BufferedReader reader = openReader()) {
			content.addAll(reader
					.lines()
					.skip(1)
					.filter(cols -> cols.split(COLUMN_DELIMTER)[getColIndex(col)].equalsIgnoreCase(val))
					.collect(Collectors.toList()));
		}
		catch(IOException e) {
			throw new UncheckedIOException(e);
		}
		return content;
	}	
	
	/**
	 * @return
	 */
	public String getHeader() {
		if(header == null) {
			try(BufferedReader reader = openReader()) {
				header = reader.lines().findFirst().get();
			}
			catch(IOException e) {
				throw new UncheckedIOException(e);
			}
		}
		return header;
	}
	
	/**
	 * @return
	 */
	public List<T> findAll() {
		try(BufferedReader reader = openReader()) {
			return reader
					.lines()
					.skip(1)
					.map(row -> mapObject(row.split(COLUMN_DELIMTER)))
					.collect(Collectors.toList());
		}
		catch(IOException e) {
			throw new UncheckedIOException(e);
		}
	}
	
	/**
	 * @param rowCols
	 * @return
	 */
	private T mapObject(String[] rowCols) {
		T record = buildObject();
    	for(Field entityField: getEntityFields()) {
    		String colName = getColName(entityField);
    		Integer colIndex = getColIndexMap().get(colName);
    		Object colValue = castValue(rowCols[colIndex], entityField.getType());
    		try {
				entityField.set(record, colValue);
			}
			catch(IllegalAccessException e) {
				throw new RuntimeException(e);
			}
    	}
    	return record;
	}

	/**
	 * @param value
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <X> X castValue(String value, Class<X> type) {
		if(type == Long.class) {
			return (X) new Long(value);
		}
		return (X) value;
	}

	/**
	 * @param field
	 * @return
	 */
	private String getColName(Field field) {
		String colName = field.getAnnotation(Column.class).value();
		if(colName.equals("")) {
			return field.getName();
		}
		return colName;
	}
	
	/**
	 * @param col
	 * @return
	 */
	private Integer getColIndex(String col) {
		Integer index = getColIndexMap().get(col);
		if(index == null) {
			throw new RuntimeException("Invalid column.");
		}
		return index;
	}
	
	/**
	 * @return
	 */
	public List<Field> getEntityFields() {
		if(entityFields == null) {
			entityFields = new ArrayList<>();
			for(Field field: entityClass.getDeclaredFields()) {
				field.setAccessible(true);
				if(!field.isAnnotationPresent(Column.class)) {
					continue;
				}
				entityFields.add(field);
			}
		}
		return entityFields;
	}
	
	/**
	 * @return
	 */
	private Map<String, Integer> getColIndexMap() {
		if(colIndexMap == null) {
			colIndexMap = new LinkedHashMap<>();
			int i = 0;
			for(String col: getHeader().split(COLUMN_DELIMTER)) {
				colIndexMap.put(col, i++);
			}
		}
		return colIndexMap;
	}
	
	/**
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private BufferedReader openReader() throws UnsupportedEncodingException {
		return new BufferedReader(new InputStreamReader(getDataFileAsStream(), "UTF8"));
	}
	
	/**
	 * @return
	 */
	private InputStream getDataFileAsStream() {
		return dataSource.getDataFileAsStream(tableName);
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private T buildObject() {
		return (T) Reflection.newInstance(entityClass);
	}
}