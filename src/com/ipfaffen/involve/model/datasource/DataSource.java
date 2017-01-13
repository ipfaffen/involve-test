package com.ipfaffen.involve.model.datasource;

import java.io.InputStream;

/**
 * @author Isaias Pfaffenseller
 */
public interface DataSource {
	
	/**
	 * @param table
	 * @return
	 */
	InputStream getDataFileAsStream(String table);
}