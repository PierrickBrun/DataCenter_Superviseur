package fr.ujfgrenoble.PC.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import fr.ujfgrenoble.PC.model.DataCenterModel;
import fr.ujfgrenoble.PC.model.Equipement;
import fr.ujfgrenoble.PC.model.equipement.EquipementReel;

@Path("/DC")
public class DataCenterWS {

	// http://localhost:8080/Superviseur/jaxrs/DC
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getId() {
		return "test";
	}

	// http://localhost:8080/Superviseur/jaxrs/DC/count?dcId=0 --> nb
	// équipements gérés par le data center
	@GET
	@Path("/count")
	@Produces(MediaType.TEXT_PLAIN)
	public int getNombreEquipements(@QueryParam("dcId") String dcId) {

		int res = 0;
		if (dcId != null) {

			DataCenterModel mdl = DataCenterModel.dataCentersMonitores.get(dcId);
			if (mdl != null)
				return mdl.getNbEquipements();
		}

		return res;
	}

	// http://localhost:8080/Superviseur/jaxrs/DC/new
	@POST
	@Path("/new")
	public int nouvelEquipement(@FormParam("dc") String dcId, @FormParam("name") String name,
			@FormParam("evenementiel") String evenementiel, @FormParam("demarre") String demarre) {

		int res = 0;
		if (dcId != null) {
			DataCenterModel mdl = DataCenterModel.dataCentersMonitores.get(dcId);
			if (mdl != null) {
				Equipement eq = new EquipementReel(name, Boolean.getBoolean(demarre), Boolean.getBoolean(evenementiel));
				mdl.ajoutEquipement(eq);
			}
		}
		return res;
	}

}
