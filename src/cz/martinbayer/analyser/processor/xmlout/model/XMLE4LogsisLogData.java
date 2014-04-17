package cz.martinbayer.analyser.processor.xmlout.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Martin
 * @version 1.0
 * @created 03-Dec-2013 12:28:44 AM
 */
@XmlRootElement(name = "logrecords")
public class XMLE4LogsisLogData<T extends XMLE4LogsisLog> {

	@XmlElement(name = "logrecord")
	private List<T> logRecords = new ArrayList<>();

	public XMLE4LogsisLogData() {
	}

	public void addLogRecord(T logRecord) {
		this.logRecords.add(logRecord);
	}
}