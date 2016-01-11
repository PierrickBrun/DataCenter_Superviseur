package fr.ujfgrenoble.PC.logs;

import java.util.Date;

public class StdoutLogger extends Logger {

	public StdoutLogger(int level) {
		super(level);
	}

	@Override
	protected void writeMessage(String msg) {
		System.out.println(new Date() + "|Stdout: " + msg);
	}

}
