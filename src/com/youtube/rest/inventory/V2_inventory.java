package com.youtube.rest.inventory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.dao.Schema308tube;

@Path("v2/inventory/")
public class V2_inventory {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrandParts(@QueryParam("brand") String brand) {
		try {
			if (brand == null) {
				return Response.status(500)
						.entity("Error: please specify brand for this search")
						.build();
			}

			JSONArray json = Schema308tube.returnBrandParts(brand);
			if (json != null) {
				String returnString = json.toString();
				return Response.ok(returnString).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500)
					.entity("Server was not able to process your request")
					.build();
		}
		return null;
	}

	/*
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnErrorOnBrand() throws Exception {
		return Response.status(400).entity("Error: please specify brand for this search").build();
	}
	*/

	@Path("/{brand}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrand(@PathParam("brand") String brand) {
		try {
			if (brand == null) {
				return Response.status(500)
						.entity("Error: please specify brand for this search")
						.build();
			}

			JSONArray json = Schema308tube.returnBrandParts(brand);
			if (json != null) {
				String returnString = json.toString();
				return Response.ok(returnString).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500)
					.entity("Server was not able to process your request")
					.build();
		}
		return null;
	}

}
