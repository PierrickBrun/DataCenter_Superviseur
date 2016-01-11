package fr.ujfgrenoble.PC.logs;

import java.util.Date;

public class StderrLogger extends Logger {

	public StderrLogger(int level) {
		super(level);
	}

	@Override
	protected void writeMessage(String msg) {
		System.err.println(new Date() + "|Stderr: " + msg);
	}

}
