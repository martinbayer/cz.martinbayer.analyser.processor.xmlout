package cz.martinbayer.analyser.processor.xmlout.processor;

import java.io.File;

import cz.martinbayer.analyser.impl.ConcreteE4LogsisLog;
import cz.martinbayer.analyser.processors.IProcessorLogic;
import cz.martinbayer.analyser.processors.types.LogProcessor;

public class XMLOutputProcLogic implements IProcessorLogic<ConcreteE4LogsisLog> {

	private XMLOutputProcessor processor;

	@Override
	public LogProcessor<ConcreteE4LogsisLog> getProcessor() {
		if (processor == null) {
			processor = new XMLOutputProcessor();
		}
		return processor;
	}

	public void setOutputFile(File selectedFile) {
		((XMLOutputProcessor) getProcessor()).setOutputFile(selectedFile);
	}

}
