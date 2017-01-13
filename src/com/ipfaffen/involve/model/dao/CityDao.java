package com.ipfaffen.involve.model.dao;

import com.ipfaffen.involve.model.datasource.DataSource;
import com.ipfaffen.involve.model.entity.City;

/**
 * @author Isaias Pfaffenseller
 */
public class CityDao extends ModelDao<City> {

	/**
	 * @param dataSource
	 */
	public CityDao(DataSource dataSource) {
		super(dataSource);
	}
}