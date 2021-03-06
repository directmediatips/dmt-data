package com.directmediatips.database;

/*
 * Copyright 2017, Bruno Lowagie, Wil-Low BVBA
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the  * specific language governing permissions and
 * limitations under the License.
 */

import java.io.FileReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Wrapper for the java.sql.Connection class.
 */
public class DatabaseConnection {
	
	/**  Path to the properties file to make the DB connection:. */
	protected static final String PROPS = "database/db.properties";
	/** The actual connection. */
	protected Connection connection;
	
	/**
	 * Creates a database connection by reading the connection details
	 * from a properties file.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SQLException the SQL exception
	 */
	public DatabaseConnection() throws IOException, SQLException {
		Properties properties = new Properties();
		properties.load(new FileReader(PROPS));
	    try {
		    Class.forName(properties.getProperty("driver"));
	    }
	    catch (ClassNotFoundException e) {
		    throw new RuntimeException(e);
	    }
	    connection = DriverManager.getConnection(
			properties.getProperty("database"),
			properties.getProperty("username"),
			properties.getProperty("password"));
	}
	
	/**
	 * Executes a simple SQL query and returns a result set.
	 *
	 * @param sql the SQL query
	 * @return a ResultSet
	 * @throws SQLException the SQL exception
	 */
	public ResultSet execute(String sql) throws SQLException {
		return connection.createStatement().executeQuery(sql);
	}
	
	/**
	 * Creates a prepared statement.
	 *
	 * @param sql the SQL template for the query
	 * @return a prepared statement
	 * @throws SQLException the SQL exception
	 */
	public PreparedStatement prepare(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}
	
	/**
	 * Closes the connection and throws a runtime exception if not successful.
	 */
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
