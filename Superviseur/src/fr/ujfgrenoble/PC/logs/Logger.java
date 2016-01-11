package fr.ujfgrenoble.PC.logs;

public abstract class Logger {
	//Implementation du pattern Chain Of Responsibility

	public static final int INFO = 1;
	public static final int DEBUG = 2;
	public static final int ERROR = 3;
	protected int level;
	protected Logger nextLogger;

	public Logger(int level) {
		this.level = level;
	}

	public void setNextLogger(Logger nextLogger) {
		this.nextLogger = nextLogger;
	}

	public void message(String msg, int priority) {
		if (priority >= level) {
			writeMessage(msg);
		}
		if (nextLogger != null) {
			nextLogger.message(msg, priority);
		}
	}

	abstract protected void writeMessage(String msg);
}
