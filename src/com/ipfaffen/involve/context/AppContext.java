package com.ipfaffen.involve.context;

import java.io.InputStream;

import com.ipfaffen.involve.model.dao.CityDao;
import com.ipfaffen.involve.model.datasource.DataSource;

/**
 * @author Isaias Pfaffenseller
 */
public class AppContext {

	private static DataSource dataSource;
	private static CityDao cityDao;
	
	/**
	 * @return
	 */
	public static DataSource getDataSource() {
		if(dataSource == null) {
			dataSource = new DataSource() {
				@Override
				public InputStream getDataFileAsStream(String table) {
					return AppContext.class.getResourceAsStream(String.format("/resources/%s.csv", table)); 
				}
			};
		}
		return dataSource;
	}
	
	/**
	 * @return
	 */
	public static CityDao getCityDao() {
		if(cityDao == null) {
			cityDao = new CityDao(getDataSource());
		}
		return cityDao;
	}
}