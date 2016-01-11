package fr.ujfgrenoble.PC.model;


public interface Equipement {

	public final static String statusDemarre = "started";
	public final static String statusArrete = "stopped";

	public final static String opDemarre = "start";
	public final static String opArrete = "stop";

	void demarre();

	void arrete();

	String getStatus();

	String getId();

	String getCpu();

	String getSeuil();

	String getAlert();

	void setSeuil(int seuil);
	
}
