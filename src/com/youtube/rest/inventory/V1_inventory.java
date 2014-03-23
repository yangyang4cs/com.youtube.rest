package com.youtube.rest.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.dao.Oracle308tube;
import com.youtube.util.ToJSON;

/**
 * http
 * 
 * json
 * 
 * html and js for clients later expisodes
 * 
 * In order to format the database rows to JSON we need to write our own method.
 * 
 * This is what that ToJSON java class is going to do. The process is pretty
 * simple.
 * 
 * The Objective.
 * 
 * 1. Get a connection to the database
 * 
 * 2. Send the Query
 * 
 * 3. Call our utility and ask it to format the data
 * 
 * 4. Send the data to the requestor
 * 
 */
@Path("v1/inventory/")
public class V1_inventory {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String returnAllPcParts() {
		PreparedStatement query = null;
		Connection conn = null;
		String returnString = null;

		try {
			conn = Oracle308tube.Oracle308tubeConn().getConnection();
			query = conn.prepareStatement("select * from PC_PARTS");
			ResultSet rs = query.executeQuery();

			JSONArray json = ToJSON.INSTANCE.toJSONArray(rs);
			if (json != null) {
				returnString = json.toString();
			}

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

		return returnString;
	}

}
