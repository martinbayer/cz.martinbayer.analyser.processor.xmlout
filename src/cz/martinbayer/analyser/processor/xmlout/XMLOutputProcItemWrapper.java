package cz.martinbayer.analyser.processor.xmlout;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.events.MouseEvent;

import cz.martinbayer.analyser.impl.ConcreteE4LogsisLog;
import cz.martinbayer.analyser.processor.xmlout.paletteitem.XMLOutputProcPaletteItem;
import cz.martinbayer.analyser.processor.xmlout.processor.XMLOutputProcLogic;
import cz.martinbayer.analyser.processors.IProcessorItemWrapper;
import cz.martinbayer.analyser.processors.IProcessorLogic;
import cz.martinbayer.analyser.processors.IProcessorsPaletteItem;

public class XMLOutputProcItemWrapper implements
		IProcessorItemWrapper<ConcreteE4LogsisLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1866711599665054824L;
	private XMLOutputProcLogic logic;
	private XMLOutputProcPaletteItem paletteItem;

	@Override
	public IProcessorLogic<ConcreteE4LogsisLog> getProcessorLogic() {
		if (logic == null) {
			logic = new XMLOutputProcLogic();
		}
		return logic;
	}

	@Override
	public IProcessorsPaletteItem getProcessorPaletteItem() {
		if (paletteItem == null) {
			paletteItem = new XMLOutputProcPaletteItem();
		}
		return paletteItem;
	}

	@Override
	public void mouseDoubleClicked(MouseEvent e) {
		paletteItem.openDialog((XMLOutputProcLogic) getProcessorLogic());
	}

	@Override
	public IProcessorItemWrapper<ConcreteE4LogsisLog> getInstance() {
		return new XMLOutputProcItemWrapper();
	}

	@Override
	@Inject
	public void setContext(IEclipseContext ctx) {
		Activator.setEclipseContext(ctx);
	}
}
