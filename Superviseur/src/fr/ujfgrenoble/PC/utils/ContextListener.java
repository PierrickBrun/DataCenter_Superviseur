package fr.ujfgrenoble.PC.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import fr.ujfgrenoble.PC.logs.Logger;
import fr.ujfgrenoble.PC.logs.StderrLogger;
import fr.ujfgrenoble.PC.logs.StdoutLogger;
import fr.ujfgrenoble.PC.model.DataCenterModel;

public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		Logger l = new StdoutLogger(Logger.INFO);
		l.setNextLogger(new StderrLogger(Logger.ERROR));

		System.err.println("End of application");
		l.message("End of application", Logger.INFO);
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		Logger l = new StdoutLogger(Logger.INFO);
		l.setNextLogger(new StderrLogger(Logger.ERROR));

		l.message("Application de Supervision", Logger.INFO);

		// ici c'est la fonction qui est appelée au lancement de l'application
		// web
		// j'ai mis le code pipo qui permet d'initialiser les data centers.
		// En vrai il faudrait se connecter à une base de données ou
		// utiliser un service de recuperation.
		//
		// initialisations : DataCenters, Superviseur, etc
		//
		DataCenterModel md1 = new DataCenterModel("DC1");
		DataCenterModel md2 = new DataCenterModel("DC2");

		DataCenterModel.dataCentersMonitores.put(md1.getId(), md1);
		DataCenterModel.dataCentersMonitores.put(md2.getId(), md2);

	}

}
