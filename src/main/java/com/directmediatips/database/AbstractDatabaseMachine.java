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

import java.io.IOException;
import java.sql.SQLException;

/**
 * Abstract super class for all the machines that require database access.
 */
public abstract class AbstractDatabaseMachine {
	
	/** Our database connection. */
	protected DatabaseConnection connection;
	
	/**
	 * Constructs a DatabaseMachine and initializes the connection.
	 */
	protected AbstractDatabaseMachine() throws SQLException, IOException {
		connection = new DatabaseConnection();
	}
	
	/**
	 * Close the connection to the database.
	 */
	public void close() {
		connection.close();
	}
	
	/**
	 * Replaces every character that isn't an ASCII character with a space character.
	 * 
	 * @param 	s	the String
	 * @returns	a String consisting of only ASCII characters
	 */
	public String makeASCII(String s) {
		return s.replaceAll("[^\\x00-\\xFF]", " ");
	}
}
