package fr.ujfgrenoble.PC.utils;



import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import fr.ujfgrenoble.PC.model.DataCenterModel;
import fr.ujfgrenoble.PC.model.equipement.EquipementReel;

public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		System.err.println ("End of application") ;
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		System.out.println ("Application DataCenter") ;

		
		// ici c'est la fonction qui est appelée au lancement de l'application web
		// j'ai mis le code pipo qui permet d'initialiser les listes d'équipements 
		// a monitorer. En vrai il faudrait se connecter à une base de données ou 
		// utiliser un service de recuperation.
		// 
		// initialisations : DataCenters, Superviseur, etc
		// 
		DataCenterModel md1 = new DataCenterModel ("DC1") ;
		DataCenterModel md2 = new DataCenterModel ("DC2") ;
		
	    // pour demo
		md1.ajoutEquipement ( new EquipementReel (  "e1" ) ) ;
		md1.ajoutEquipement ( new EquipementReel (  "e2" ) ) ;
		md1.ajoutEquipement ( new EquipementReel (  "e3" ) ) ;
		md2.ajoutEquipement ( new EquipementReel (  "e4" ) ) ;
		md1.ajoutEquipement ( new EquipementReel (  "e5" ) ) ;
		md2.ajoutEquipement ( new EquipementReel (  "e6" ) ) ;
		md2.ajoutEquipement ( new EquipementReel (  "e7" ) ) ;
		md2.ajoutEquipement ( new EquipementReel (  "e8" ) ) ;
		md2.ajoutEquipement ( new EquipementReel (  "e9" ) ) ;
		md2.ajoutEquipement ( new EquipementReel (  "e10" ) ) ;
		md2.ajoutEquipement ( new EquipementReel (  "e11" ) ) ;

		DataCenterModel.dataCentersMonitores.put ( md1.getId () , md1 ) ;
		DataCenterModel.dataCentersMonitores.put ( md2.getId () , md2 ) ;
		
	}

}
