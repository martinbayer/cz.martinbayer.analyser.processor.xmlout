package cz.martinbayer.analyser.processor.xmlout.model;

import javax.xml.bind.annotation.XmlRootElement;

import cz.martinbayer.analyser.impl.ConcreteE4LogsisLog;

@XmlRootElement(name = "logrecord")
public class XMLE4LogsisLog extends ConcreteE4LogsisLog {

	private ConcreteE4LogsisLog log;

	public XMLE4LogsisLog() {
	}

	public XMLE4LogsisLog(ConcreteE4LogsisLog log) {
		this.log = log;
		initData(this.log);
	}

	private void initData(ConcreteE4LogsisLog log) {
		setErrorMessage(log.getErrorMessage());
		setEventDateTime(log.getEventDateTime());
		setFileName(log.getFileName());
		setLine(log.getLine());
		setLogLevel(log.getLogLevel());
		setMessage(log.getMessage());
		setRemoved(log.isRemoved());
		setThreadName(log.getThreadName());
	}
}
