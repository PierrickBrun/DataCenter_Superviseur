package fr.ujfgrenoble.PC.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;




/**
 * 
 * @author Laurent Testard
 *
 * URL d'accès : http://localhost:8080/Superviseur/jaxrs/Id
 *
 */
@Path("/Id")
public class SuperviseurWS {
	

	@GET 
	@Produces(MediaType.TEXT_PLAIN)
	public String getId () {
		
		return "Superviseur "   ;
	}

}
