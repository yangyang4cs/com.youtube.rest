package com.youtube.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.util.ToJSON;

public class Schema308tube extends Oracle308tube {

	/**
	 * 
	 * @param PC_PARTS_PK
	 * @param PC_PARTS_TITLE
	 * @param PC_PARTS_CODE
	 * @param PC_PARTS_MAKER
	 * @param PC_PARTS_AVAIL
	 * @param PC_PARTS_DESC
	 * @return
	 */
	public int insertINtoPC_PARTS(String PC_PARTS_PK, String PC_PARTS_TITLE, String PC_PARTS_CODE, String PC_PARTS_MAKER, String PC_PARTS_AVAIL, String PC_PARTS_DESC) {
		PreparedStatement query = null;
		Connection conn = null;

		try {
			String sql = "insert into PC_PARTS (PC_PARTS_PK, PC_PARTS_TITLE, PC_PARTS_CODE, PC_PARTS_MAKER, PC_PARTS_AVAIL, PC_PARTS_DESC) values (?,?,?,?,?,?)";
			/*
			 * If this was a real application, you should do data validation here before starting to insert data into the database.
			 */
			conn = oraclePcPartsConnection();
			query = conn.prepareStatement(sql);

			int pkInt = Integer.parseInt(PC_PARTS_PK);
			query.setInt(1, pkInt);

			query.setString(2, PC_PARTS_TITLE);
			query.setString(3, PC_PARTS_CODE);
			query.setString(4, PC_PARTS_MAKER);

			int avilInt = Integer.parseInt(PC_PARTS_AVAIL);
			query.setInt(5, avilInt);

			query.setString(6, PC_PARTS_DESC);

			query.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return 500; // internal server error

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 200; // success
	}

	/**
	 * 
	 * @param brand
	 * @return
	 */
	public static JSONArray returnBrandParts(String brand) {
		PreparedStatement query = null;
		Connection conn = null;
		JSONArray json = null;

		try {
			String sql = "select PC_PARTS_PK, PC_PARTS_TITLE, PC_PARTS_CODE, PC_PARTS_MAKER, PC_PARTS_AVAIL, PC_PARTS_DESC from PC_PARTS where UPPER(PC_PARTS_MAKER) = ?";

			conn = oraclePcPartsConnection();
			query = conn.prepareStatement(sql);
			query.setString(1, brand.toUpperCase());

			ResultSet rs = query.executeQuery();
			json = ToJSON.INSTANCE.toJSONArray(rs);
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (query != null) {
				try {
					query.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return json;
	}

	/**
	 * 
	 * @param brand
	 * @param item_number
	 * @return
	 */
	public static JSONArray returnBrandParts(String brand, int item_number) {
		PreparedStatement query = null;
		Connection conn = null;
		JSONArray json = null;

		try {
			String sql = "select PC_PARTS_PK, PC_PARTS_TITLE, PC_PARTS_CODE, PC_PARTS_MAKER, PC_PARTS_AVAIL, PC_PARTS_DESC from PC_PARTS where UPPER(PC_PARTS_MAKER) = ? and PC_PARTS_CODE = ?";

			conn = oraclePcPartsConnection();
			query = conn.prepareStatement(sql);
			query.setString(1, brand.toUpperCase());
			query.setInt(2, item_number);

			ResultSet rs = query.executeQuery();
			json = ToJSON.INSTANCE.toJSONArray(rs);
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (query != null) {
				try {
					query.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return json;
	}

}
