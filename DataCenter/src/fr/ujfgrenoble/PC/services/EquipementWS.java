package fr.ujfgrenoble.PC.services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fr.ujfgrenoble.PC.model.DataCenterModel;
import fr.ujfgrenoble.PC.model.Equipement;
import fr.ujfgrenoble.PC.utils.Params;

@Path("/EQ")
public class EquipementWS {

	private static final String sOpStatus = "status";
	private static final String sOpId = "id";
	private static final String sOpCpu = "cpu";
	private static final String sOpSeuil = "seuil";
	private static final String sOpAlert = "alert";

	@GET
	@Path("{EquipementStatus}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getIdEquipementStatus(@QueryParam("dcId") String dcId, @QueryParam("idx") String idx,
			@QueryParam("op") String op) {

		String res = "";

		Integer idxElem = Params.decodeInt(idx);
		if (dcId != null && idxElem != null) {

			DataCenterModel mdl = DataCenterModel.dataCentersMonitores.get(dcId);
			if (mdl == null)
				return "erreur";

			Equipement eq = mdl.getEquipement(idxElem);
			if (sOpStatus.equalsIgnoreCase(op)) {
				// renvoie le statut de l'équipement
				res = eq.getStatus();
			} else if (sOpId.equalsIgnoreCase(op)) {
				// renvoie l'id de l'équipement
				res = eq.getId();
			} else if (sOpCpu.equalsIgnoreCase(op)) {
				// renvoie le cpu de l'équipement
				res = eq.getCpu();
			} else if (sOpSeuil.equalsIgnoreCase(op)) {
				// renvoie le seuil de l'équipement
				res = eq.getSeuil();
			} else if (sOpAlert.equalsIgnoreCase(op)) {
				// renvoie le seuil de l'équipement
				res = eq.getAlert();
			}

		}

		return res;
	}

	@POST
	@Path("{EquipementOperation}")
	@Produces(MediaType.TEXT_PLAIN)
	public String operationEquipement(@QueryParam("dcId") String dcId, @QueryParam("idx") String idx,
			@QueryParam("op") String op, @QueryParam("seuil") int seuil) {

		Integer idxElem = Params.decodeInt(idx);

		if (dcId != null && idxElem != null && op != null) {
			DataCenterModel mdl = DataCenterModel.dataCentersMonitores.get(dcId);
			if (mdl == null)
				return "erreur";

			Equipement eq = mdl.getEquipement(idxElem);
			if (Equipement.opArrete.equalsIgnoreCase(op)) {
				eq.arrete();
			} else if (Equipement.opDemarre.equalsIgnoreCase(op)) {
				eq.demarre();
			} else if (sOpSeuil.equalsIgnoreCase(op)) {
				eq.setSeuil(seuil);
			}

		}

		String oldState = "";
		return oldState;
	}

}
