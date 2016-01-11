package fr.ujfgrenoble.PC.utils;

public class Params {

	public static final String wsPath = "jaxrs" ;
	public static final String wsDCPath = "DC" ;
	public static final String wsEQPath = "EQ" ;
	public static final String wsEquipementStatus = "EquipementStatus" ;
	public static final String wsCount = "count" ;
	public static final String wsNew = "new" ;
	

	static public Integer decodeInt ( String param )  {
		Integer i = null ;
		try {
			 i = Integer.parseInt( param ) ;
		} catch ( Throwable t ) {}
		
		return i ;
	}
}
