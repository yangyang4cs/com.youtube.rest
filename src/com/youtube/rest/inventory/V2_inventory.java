package com.youtube.rest.inventory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;

import com.youtube.dao.Schema308tube;

@Path("v2/inventory/")
public class V2_inventory {

	public static boolean DEBUG = true;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrandParts(@QueryParam("brand") String brand) {
		try {
			if (brand == null) {
				return Response.status(500).entity("Error: please specify brand for this search").build();
			}

			JSONArray json = Schema308tube.returnBrandParts(brand);
			if (json != null) {
				String returnString = json.toString();
				return Response.ok(returnString).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		return null;
	}

	/*
	 * @GET
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Response returnErrorOnBrand() throws Exception { return
	 * Response.status(400).entity("Error: please specify brand for this search").build(); }
	 */
	@Path("/{brand}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrand(@PathParam("brand") String brand) {
		try {
			if (brand == null) {
				return Response.status(500).entity("Error: please specify brand for this search").build();
			}

			JSONArray json = Schema308tube.returnBrandParts(brand);
			if (json != null) {
				String returnString = json.toString();
				return Response.ok(returnString).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		return null;
	}

	@Path("/{brand}/{item_number}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrand(@PathParam("brand") String brand, @PathParam("item_number") int item_number) {
		try {
			if (brand == null) {
				return Response.status(500).entity("Error: please specify brand for this search").build();
			}

			JSONArray json = Schema308tube.returnBrandParts(brand, item_number);
			if (json != null) {
				String returnString = json.toString();
				return Response.ok(returnString).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		return null;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPcParts(String incomingData) {
		String returnString = null;
		// JSONArray jsonArray = new JSONArray();
		Schema308tube dao = new Schema308tube();

		try {
			println("incomingData: " + incomingData);

			ObjectMapper mapper = new ObjectMapper();
			ItemEntry itemEntry = mapper.readValue(incomingData, ItemEntry.class);

			int http_code = dao.insertINtoPC_PARTS(itemEntry.PC_PARTS_PK, itemEntry.PC_PARTS_TITLE, itemEntry.PC_PARTS_CODE, itemEntry.PC_PARTS_MAKER, itemEntry.PC_PARTS_AVAIL, itemEntry.PC_PARTS_DESC);

			if (http_code == 200) {
				// returnString = jsonArray.toString();
				returnString = "Item inserted";
			} else {
				return Response.status(500).entity("Unable to process Item").build();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		return Response.ok(returnString).build();
	}

	protected void println(String string) {
		if (DEBUG) {
			System.out.println(string);
		}
	}

}

class ItemEntry {

	public String PC_PARTS_PK;
	public String PC_PARTS_TITLE;
	public String PC_PARTS_CODE;
	public String PC_PARTS_MAKER;
	public String PC_PARTS_AVAIL;
	public String PC_PARTS_DESC;

//	public ItemEntry() {
//
//	}
//
//	public ItemEntry(String PC_PARTS_PK, String PC_PARTS_TITLE, String PC_PARTS_CODE, String PC_PARTS_MAKER, String PC_PARTS_AVAIL, String PC_PARTS_DESC) {
//		this.PC_PARTS_PK = PC_PARTS_PK;
//		this.PC_PARTS_TITLE = PC_PARTS_TITLE;
//		this.PC_PARTS_CODE = PC_PARTS_CODE;
//		this.PC_PARTS_MAKER = PC_PARTS_MAKER;
//		this.PC_PARTS_AVAIL = PC_PARTS_AVAIL;
//		this.PC_PARTS_DESC = PC_PARTS_DESC;
//	}

}
