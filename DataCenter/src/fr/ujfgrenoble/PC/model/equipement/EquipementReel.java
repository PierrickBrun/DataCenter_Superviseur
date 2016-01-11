package fr.ujfgrenoble.PC.model.equipement;

import fr.ujfgrenoble.PC.logs.Logger;
import fr.ujfgrenoble.PC.logs.StderrLogger;
import fr.ujfgrenoble.PC.logs.StdoutLogger;
import fr.ujfgrenoble.PC.model.Equipement;

public class EquipementReel implements Equipement {

	String id;
	String etat = Equipement.statusArrete;
	int seuil = 100;
	int cpu = 0;
	boolean evenementiel = true;
	Logger l;

	public EquipementReel(String id) {
		this.id = id;
		l = new StdoutLogger(Logger.INFO);
		l.setNextLogger(new StderrLogger(Logger.ERROR));
	}

	public EquipementReel(String id, boolean demarre) {
		this.id = id;
		if (demarre) {
			demarre();
		}
	}

	public EquipementReel(String id, boolean demarre, boolean evenementiel) {
		this.id = id;
		if (demarre) {
			demarre();
		}
		this.evenementiel = evenementiel;
	}

	@Override
	public void demarre() {
		this.etat = Equipement.statusDemarre;
		l.message("Equipement " + id + " a demarre", Logger.INFO);
		randomizeCpu();
	}

	private void randomizeCpu() {
		this.cpu = (int) Math.round(100 * Math.random());
	}

	@Override
	public void arrete() {
		this.etat = Equipement.statusArrete;
		this.cpu = 0;
		l.message("Equipement " + id + " arrete", Logger.INFO);
	}

	@Override
	public String getStatus() {
		return etat;
	}

	@Override
	public String getId() {

		return id;
	}

	@Override
	public String getCpu() {
		return String.valueOf(cpu);
	}

	@Override
	public String getSeuil() {
		return String.valueOf(seuil);
	}

	@Override
	public String getAlert() {
		if (cpu > seuil && this.evenementiel) {
			return "true";
		}
		return "false";
	}

	@Override
	public void setSeuil(int seuil) {
		this.seuil = seuil;
	}
}
