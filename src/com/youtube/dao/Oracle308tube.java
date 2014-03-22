package com.youtube.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Oracle308tube {

	private static DataSource Oracle308tube = null;
	private static Context context = null;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public static DataSource Oracle308tubeConn() throws Exception {
		if (Oracle308tube != null) {
			return Oracle308tube;
		}

		try {
			if (context == null) {
				context = new InitialContext();
			}
			Oracle308tube = (DataSource) context.lookup("308tubeOracle");
		} catch (Exception e) {
			throw e;
		}
		return Oracle308tube;
	}

}
