package fr.ujfgrenoble.PC.model.equipement;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import fr.ujfgrenoble.PC.model.Equipement;
import fr.ujfgrenoble.PC.utils.Params;

public class EquipementProxy implements Equipement {

	private String hostName;
	private String dcId;
	private int idx;

	public EquipementProxy(String hostName, String dcId, int idx) {

		this.dcId = dcId;
		this.idx = idx;
		this.hostName = hostName;
	}

	private URI getBaseURI() {

		return UriBuilder.fromUri("http://" + hostName + ":8080/DataCenter").build();
	}

	@Override
	public void demarre() {

		throw new RuntimeException("fonction demarre ne doit pas être appelée sur classe WSException");

	}

	@Override
	public void arrete() {

		throw new RuntimeException("fonction arrete ne doit pas être appelée sur classe WSException");

	}

	@Override
	public String getId() {

		// http://localhost:8080/Superviseur/jaxrs/EQ/EquipementStatus?dcId=DC1&idx=0&op=id

		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseURI());
		WebTarget intermediaire = target.path(Params.wsPath).path(Params.wsEQPath).path(Params.wsEquipementStatus)
				.queryParam("dcId", dcId).queryParam("idx", idx).queryParam("op", "id");
		return intermediaire.request().accept(MediaType.TEXT_PLAIN).get(String.class).toString();

	}

	@Override
	public String getStatus() {

		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseURI());
		WebTarget intermediaire = target.path(Params.wsPath).path(Params.wsEQPath).path(Params.wsEquipementStatus)
				.queryParam("dcId", dcId).queryParam("idx", idx).queryParam("op", "status");
		return intermediaire.request().accept(MediaType.TEXT_PLAIN).get(String.class).toString();

	}

	@Override
	public String getCpu() {

		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseURI());
		WebTarget intermediaire = target.path(Params.wsPath).path(Params.wsEQPath).path(Params.wsEquipementStatus)
				.queryParam("dcId", dcId).queryParam("idx", idx).queryParam("op", "cpu");
		return intermediaire.request().accept(MediaType.TEXT_PLAIN).get(String.class).toString();
	}

	@Override
	public String getSeuil() {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseURI());
		WebTarget intermediaire = target.path(Params.wsPath).path(Params.wsEQPath).path(Params.wsEquipementStatus)
				.queryParam("dcId", dcId).queryParam("idx", idx).queryParam("op", "seuil");
		return intermediaire.request().accept(MediaType.TEXT_PLAIN).get(String.class).toString();
	}

	@Override
	public void setSeuil(double seuil) {
		throw new RuntimeException("fonction setSeuil ne doit pas être appelée sur classe WSException");
	}

	@Override
	public String getAlert() {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseURI());
		WebTarget intermediaire = target.path(Params.wsPath).path(Params.wsEQPath).path(Params.wsEquipementStatus)
				.queryParam("dcId", dcId).queryParam("idx", idx).queryParam("op", "alert");
		return intermediaire.request().accept(MediaType.TEXT_PLAIN).get(String.class).toString();
	}

}
