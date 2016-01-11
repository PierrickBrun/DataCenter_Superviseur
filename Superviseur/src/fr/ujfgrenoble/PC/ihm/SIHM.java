package fr.ujfgrenoble.PC.ihm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ujfgrenoble.PC.model.DataCenterModel;
import fr.ujfgrenoble.PC.model.Equipement;
import fr.ujfgrenoble.PC.model.equipement.EquipementProxy;
import fr.ujfgrenoble.PC.services.WSDataCenter;

/**
 * 
 * Servlet implementation class SIHM
 * 
 * 
 * 
 */
@WebServlet("/SIHM")
public class SIHM extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Iterator<Equipement> getListeEquipements(String idc) {

		WSDataCenter dc = new WSDataCenter("localhost", idc);
		int nb = dc.getNbEquipements();

		List<Equipement> lst = new LinkedList<Equipement>();

		for (int i = 0; i < nb; i++) {
			lst.add(new EquipementProxy("localhost", idc, i));
		}

		return lst.iterator();
	}

	private void genereListeEquipements(String dc, PrintWriter out) {
		out.println("<table>");

		Iterator<Equipement> it = getListeEquipements(dc);
		int idx = 0;
		while (it.hasNext()) {

			if (idx == 0) {
				out.println("<tr>");
				out.println("<th>Id</th>");
				out.println("<th colspan=\"2\">status</th>");
				out.println("<th colspan=\"4\">seuil</th>");
				out.println("<th colspan=\"2\">cpu</th>");
				out.println("<th>alert</th>");
				out.println("</tr>");
			}

			Equipement e = it.next();
			String status = e.getStatus();

			String paramScript = "(\"localhost\",\"" + dc + "\", \"" + idx + "\") ";

			out.println("<tr>");

			out.println("<td>" + e.getId() + "</td>");

			out.println("<td>" + status + "</td>");
			if (Equipement.statusDemarre.equalsIgnoreCase(status)) {
				out.println(
						"<td><img width='16' src='http://localhost:8080/Superviseur/images/stop-orange.png' onclick='javascript:stopEquipement"
								+ paramScript + ";'></img></td>");
			} else {
				out.println(
						"<td><img width='16'  src='http://localhost:8080/Superviseur/images/round_green_play_button_on.png' onclick='javascript:startEquipement"
								+ paramScript + ";'></img></td>");
			}

			out.println("<td><progress id=\"avancement_seuil" + idx + "\" value=\"" + e.getSeuil()
					+ "\" max=\"100\"></progress></td>");
			out.println("<td><span id=\"pourcentage" + idx + "\">" + e.getSeuil() + "%</span></td>");
			out.println("<td><input type=\"button\" onclick=\'javascript:modif(\"" + idx + "\",-10,\"localhost\",\""
					+ dc + "\", \"" + idx + "\")\' value=\"-\"></td>");
			out.println("<td><input type=\"button\" onclick=\'javascript:modif(\"" + idx + "\",10,\"localhost\",\"" + dc
					+ "\", \"" + idx + "\")\' value=\"+\"></td>");

			out.println("<td>" + e.getCpu() + "% </td>");
			out.println("<td><progress id=\"avancement_cpu" + idx + "\" value=\"" + e.getCpu()
					+ "\" max=\"100\"></progress></td>");

			out.println("<td>" + e.getAlert() + " </td>");

			out.println("</tr>");
			idx++;
		}

		if (idx == 0) {
			out.print("DataCenter " + dc + " stoppé!");
		}
		out.println("</table>");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		out.println(
				"<script  type='text/javascript' src='http://localhost:8080/Superviseur/scripts/utils.js' ></script>");
		out.println("<style>" + "table, th, td {" + "border: 1px solid black;" + "border-collapse: collapse;" + "}"
				+ "th, td {" + "padding: 8px;" + "}" + "</style>");

		// recuperation de la liste des data centers configurés en dur. Devrait
		// etre changé pour passage
		// en mode réellement distribué sur plusieurs machines.
		Iterator<String> dcs = DataCenterModel.dataCentersMonitores.keySet().iterator();

		while (dcs.hasNext()) {
			String dc = dcs.next();
			out.println("<H1><form action=\"DCIHM/" + dc + "\">Liste des équipements DataCenter " + dc
					+ "\t<input type=\"submit\" value=\"Créer\"></form></H1>");
			genereListeEquipements(dc, out);
		}

	}

}
