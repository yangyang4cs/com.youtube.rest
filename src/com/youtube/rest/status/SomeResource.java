package com.youtube.rest.status;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @Produce specify the media type that a method will produce and send back to the client.
 * 
 *          When you use @Produces at the class level, its treated as a default
 * 
 *          When used with methods, it becomes a require to access that method
 * 
 *          You can also define more than one for a specific method
 * 
 *          It does do some encoding but nothing extensive
 * 
 *          You define your own as seen in the example or you can use predefined MediaType. This is what the project will be using.
 * 
 * @Consumes Basically tells your application to expect data to be sent in the body of the HTTP message
 * 
 *           You will need to define what type of data sent up. Again this another way to limit what HTTP message can access specific
 *           methods.
 * 
 *           You can define your own or used the predefin MediaType.
 */
@Path("/resource")
@Produces("text/plain")
public class SomeResource {

	// by default, this method is executed
	@GET
	public String returnPlainText() {
		// code to return plain text
		return "";
	}

	// if specified, execute this method
	@GET
	@Produces("text/html")
	public String returnHtml() {
		// code to return HTML
		return "";
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void saveMessage(String message) {
		// Look at the HTTP Post body
		// The body will be brought into the java code as a String called message
		// void indicates not data is returned
	}

}
