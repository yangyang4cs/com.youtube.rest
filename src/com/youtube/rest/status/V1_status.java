package com.youtube.rest.status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.youtube.dao.Oracle308tube;

/**
 * This is the root path for our restful api service In the web.xml file, we specified that /api/* need to be in the URL to get to this
 * class.
 * 
 * We are versioning the class in the URL path. This is the first version v1. Example how to get to the root of this api resource:
 * http://localhost:7001/com.youtube.rest/api/v1/status
 * 
 * 
 * Readme: http://localhost:7001/com.youtube.rest/
 * 
 * API Title: http://localhost:7001/com.youtube.rest/api/v1/status
 * 
 * API Version: http://localhost:7001/com.youtube.rest/api/v1/status/version
 * 
 * GET, POST, PUT, DELETE, HEAD are defined in the HTTP/1.1 protocal. In the Jersey framework, we will convering the following:
 * 
 * @GET - most used, read-only and public access method
 * @POST - used to insert/add data OR submitting data like Login pages. With HTTPS you can protect the data
 * @PUT - used mainly for updating data but can be used fro inserting/adding data
 * @DELETE - used to delete data
 * @HEAD - used to return meta-data of the resource
 */
@Path("v1/status/")
// route to java class
public class V1_status {

	// version of the api
	private static final String api_version = "00.02.00"; // version of the api

	/**
	 * This method sits at the root of the api. It will return the name of this api.
	 * 
	 * @return String - Title of the api
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Web Service</p> ";
	}

	/**
	 * This method will return the version number of the api Note: this is nested one down from the root. You will need to add version intro
	 * the URL Path.
	 * 
	 * Example: http://localhost:7001/com.youtube.rest/api/v1/status/version
	 * 
	 * @return String - version number of the api
	 */
	@Path("/version")
	// route to specific method
	@GET
	// HTTP verb required to access method
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version:</p> " + api_version;
	}

	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() {
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;

		try {
			conn = Oracle308tube.Oracle308tubeConn().getConnection();
			query = conn.prepareStatement("SELECT to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') DATETIME FROM sys.dual");
			ResultSet rs = query.executeQuery();

			while (rs.next()) {
				myString = rs.getString("DATETIME");
			}

			query.close(); // statement
			returnString = "<p>Database Status</p> <p>Database Date/Time return: " + myString + "</p> ";

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return returnString;
	}

}
