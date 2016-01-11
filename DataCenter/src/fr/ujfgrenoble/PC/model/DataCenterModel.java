package fr.ujfgrenoble.PC.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DataCenterModel {
	
	static public Map<String,DataCenterModel> dataCentersMonitores = new HashMap<String,DataCenterModel> () ;

	
	private String dcId ; 
	
	
	private List<Equipement> equipements = new ArrayList<Equipement> () ; 
	
	
	public DataCenterModel ( String id ) {
		this.dcId = id ;
	}
	
	
	public Iterator<Equipement> getEquipements () {
		return equipements.iterator () ;
	}
	
	public void ajoutEquipement ( Equipement eq ) {
		equipements.add ( eq ) ;
	}
	
	public String getId () {
		return dcId ;
	}
	
	public int getNbEquipements () {
		return equipements.size () ;
	}


	public Equipement getEquipement(Integer idxElem) {
		
		return equipements.get (idxElem);
	}

}
