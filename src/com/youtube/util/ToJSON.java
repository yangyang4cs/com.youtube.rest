package com.youtube.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 * ToJSON will be the first utility java class. Take database data and convert
 * it into JSON format.
 * 
 * This utility will convert a database data into JSON format.
 * 
 */
public class ToJSON {

	public static boolean DEBUG = true;

	public static ToJSON INSTANCE = new ToJSON();

	/**
	 * This will convert database records into a JSON Array. Simply pass in a
	 * ResultSet from a database connection and it loop through and return a
	 * JSON array.
	 * 
	 * @param rs
	 *            - database ResultSet
	 * @return - JSON array
	 * @throws Exception
	 */
	public JSONArray toJSONArray(ResultSet rs) throws Exception {
		JSONArray json = new JSONArray(); // JSON array that will be returned

		try {
			// we will need the column names, this will save the table meta-data
			// like column name.
			ResultSetMetaData rsmd = rs.getMetaData();

			// loop through the ResultSet
			while (rs.next()) {
				// figure out how many columns there are
				int numColumns = rsmd.getColumnCount();
				// each row in the ResultSet will be converted to a JSON Object
				JSONObject obj = new JSONObject();

				// loop through all the columns and place them into the JSON
				// Object
				for (int i = 1; i < numColumns + 1; i++) {
					String colName = rsmd.getColumnName(i);
					int colType = rsmd.getColumnType(i);

					if (Types.ARRAY == colType) {
						obj.put(colName, rs.getArray(colName));
						println("ToJSON: ARRAY");

					} else if (Types.BIGINT == colType) {
						obj.put(colName, rs.getInt(colName));
						println("ToJSON: BIGINT");

					} else if (Types.BOOLEAN == colType) {
						obj.put(colName, rs.getBoolean(colName));
						println("ToJSON: BOOLEAN");

					} else if (Types.BLOB == colType) {
						obj.put(colName, rs.getBlob(colName));
						println("ToJSON: BLOB");

					} else if (Types.DOUBLE == colType) {
						obj.put(colName, rs.getDouble(colName));
						println("ToJSON: DOUBLE");

					} else if (Types.FLOAT == colType) {
						obj.put(colName, rs.getFloat(colName));
						println("ToJSON: FLOAT");

					} else if (Types.INTEGER == colType) {
						obj.put(colName, rs.getInt(colName));
						println("ToJSON: INTEGER");

					} else if (Types.NVARCHAR == colType) {
						obj.put(colName, rs.getNString(colName));
						println("ToJSON: NVARCHAR");

					} else if (Types.VARCHAR == colType) {
						obj.put(colName, rs.getString(colName));
						println("ToJSON: VARCHAR");

					} else if (Types.TINYINT == colType) {
						obj.put(colName, rs.getInt(colName));
						println("ToJSON: TINYINT");

					} else if (Types.SMALLINT == colType) {
						obj.put(colName, rs.getInt(colName));
						println("ToJSON: SMALLINT");

					} else if (Types.DATE == colType) {
						obj.put(colName, rs.getDate(colName));
						println("ToJSON: DATE");

					} else if (Types.TIMESTAMP == colType) {
						obj.put(colName, rs.getTimestamp(colName));
						println("ToJSON: TIMESTAMP");

					} else if (Types.NUMERIC == colType) {
						obj.put(colName, rs.getBigDecimal(colName));
						println("ToJSON: NUMERIC");

					} else {
						obj.put(colName, rs.getObject(colName));
						println("ToJSON: Object " + colName);
					}
				} // end for

				// each JSON object represents one row of data
				// JSON array is a collection of JSON objects
				json.put(obj);

			} // end while
		} catch (Exception e) {
			e.printStackTrace();
		}

		// returns the JSON array
		return json;
	}

	protected void println(String string) {
		if (DEBUG) {
			System.out.println(string);
		}
	}

}
