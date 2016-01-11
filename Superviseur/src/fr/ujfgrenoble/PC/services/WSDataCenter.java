package fr.ujfgrenoble.PC.services;

import java.net.URI;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import fr.ujfgrenoble.PC.utils.Params;

public class WSDataCenter {

	private String hostName;
	private String dcId;

	public WSDataCenter(String hostName, String dcId) {

		this.dcId = dcId;
		this.hostName = hostName;
	}

	private URI getBaseURI() {

		return UriBuilder.fromUri("http://" + hostName + ":8080/DataCenter").build();
	}

	public int getNbEquipements() {

		// http://localhost:8080/Superviseur/jaxrs/DC/count

		String res = "0";

		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseURI());
		WebTarget intermediaire = target.path(Params.wsPath).path(Params.wsDCPath).path(Params.wsCount)
				.queryParam("dcId", dcId);
		try {
			res = intermediaire.request().accept(MediaType.TEXT_PLAIN).get(String.class).toString();
		} catch (NotFoundException nfe) {
			System.out.println("DataCenter non démarré");
		}

		return Integer.parseInt(res);
	}

	public void nouvelEquipement(String dcId, String name, String demarre, String evenementiel) {

		// http://localhost:8080/Superviseur/jaxrs/DC/new

		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseURI());
		WebTarget intermediaire = target.path(Params.wsPath).path(Params.wsDCPath).path(Params.wsNew);
		try {
			Form form = new Form();
			form.param("dc", dcId);
			form.param("name", name);
			form.param("demarre", demarre);
			form.param("evenementiel", evenementiel);
			Entity<Form> entity = Entity.form(form);
			intermediaire.request().post(entity);
		} catch (NotFoundException nfe) {
			System.out.println("DataCenter non démarré");
		}

	}

}
