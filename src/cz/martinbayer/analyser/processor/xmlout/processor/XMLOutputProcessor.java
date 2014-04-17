package cz.martinbayer.analyser.processor.xmlout.processor;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.e4.core.services.log.Logger;

import cz.martinbayer.analyser.impl.ConcreteE4LogsisLog;
import cz.martinbayer.analyser.processor.xmlout.Activator;
import cz.martinbayer.analyser.processor.xmlout.model.XMLE4LogsisLog;
import cz.martinbayer.analyser.processor.xmlout.model.XMLE4LogsisLogData;
import cz.martinbayer.analyser.processors.types.OutputProcessor;

public class XMLOutputProcessor extends OutputProcessor<ConcreteE4LogsisLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3253139226567433220L;

	private Logger log = Activator.getLogger();
	private XMLE4LogsisLogData<XMLE4LogsisLog> data;
	private File selectedFile;

	@Override
	protected void process() {
		data = new XMLE4LogsisLogData<>();
		if (logData != null) {
			for (ConcreteE4LogsisLog l : logData.getLogRecords()) {
				if (!l.isRemoved()) {
					data.addLogRecord(new XMLE4LogsisLog(l));
				}
			}
		}
	}

	@Override
	protected void createOutput() {
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(XMLE4LogsisLog.class,
					XMLE4LogsisLogData.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(data, this.selectedFile);
			log.info("Results saved to XML file "
					+ this.selectedFile.getAbsolutePath());
		} catch (JAXBException e) {
			log.error(e, "Unable to save result as XML file "
					+ this.selectedFile.getAbsolutePath());
		}
	}

	@Override
	public void init() {
		// nothing needed to be initialized
	}

	public void setOutputFile(File selectedFile) {
		this.selectedFile = selectedFile;
	}

	@Override
	protected StringBuffer isSubProcessorValid() {
		if (this.selectedFile == null || !this.selectedFile.exists()) {
			return new StringBuffer("No output file selected for processor: ")
					.append(getName());
		}
		return null;
	}
}