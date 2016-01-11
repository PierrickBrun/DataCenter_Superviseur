package fr.ujfgrenoble.PC.ihm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ujfgrenoble.PC.model.DataCenterModel;
import fr.ujfgrenoble.PC.services.WSDataCenter;

@WebServlet("/DCIHM/*")
public class DCIHM extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void genereFormulaireEquipement(String dc, PrintWriter out) {
		String res = "";

		res += "<form method=\"get\" action=\"#\"><label for=\"name\">Nom de l'équipement</label><input type=\"text\" id=\"name\" name=\"name\" /><br/><label for=\"evenementiel\">Supporte évènementiel</label><input type=\"checkbox\" id=\"evenementiel\" name=\"evenementiel\" /><br/><label for=\"demarre\">Etat initial démarré</label><input type=\"checkbox\" id=\"demarre\" name=\"demarre\" /><br/><input type=\"submit\" value=\"OK\" /><input type=\"reset\" value=\"Cancel\" /></form>";
		out.print(res);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = null;
		PrintWriter out = response.getWriter();

		url = (String) request.getAttribute("javax.servlet.forward.request_uri");
		url = url == null ? ((HttpServletRequest) request).getRequestURI() : url;
		String dc = url.substring(url.lastIndexOf("/") + 1);

		if (dc == null || dc.isEmpty()) {
			return;
		} else if (dc.equals("DCIHM")) {
			out.print("vous devez spécifier un DataCenter (...Superviseur/DCIHM/x)");
			return;
		}
		DataCenterModel mdl = DataCenterModel.dataCentersMonitores.get(dc);
		if (mdl == null) {
			out.print("Le DataCenter \"" + dc + "\" n'existe pas");
			return;
		}

		out.println(
				"<script  type='text/javascript' src='http://localhost:8080/Superviseur/scripts/utils.js' ></script>");
		out.println("<style>" + "label {" + "display: block;" + "width: 150px;" + "float: left;" + "}" + "</style>");

		if (request.getParameter("name") != null) {
			String name = request.getParameter("name");
			String demarre = request.getParameter("demarre");
			String evenementiel = request.getParameter("evenementiel");

			demarre = parseCheckboxBoolean(demarre);
			evenementiel = parseCheckboxBoolean(evenementiel);

			WSDataCenter dataCenter = new WSDataCenter("localhost", dc);
			dataCenter.nouvelEquipement(dc, name, demarre, evenementiel);
			response.sendRedirect("../SIHM");
		} else {
			genereFormulaireEquipement(dc, out);
		}
	}

	private String parseCheckboxBoolean(String checkbox) {
		if (checkbox != null && checkbox.equals("on")) {
			checkbox = "true";
		} else {
			checkbox = "false";
		}
		return checkbox;
	}

}
