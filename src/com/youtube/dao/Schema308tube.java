package com.youtube.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.QueryParam;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.util.ToJSON;

public class Schema308tube extends Oracle308tube {

	/**
	 * 
	 * @param brand
	 * @return
	 */
	public static JSONArray returnBrandParts(@QueryParam("brand") String brand) {
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

}
