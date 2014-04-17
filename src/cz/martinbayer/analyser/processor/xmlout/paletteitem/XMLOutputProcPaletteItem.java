package cz.martinbayer.analyser.processor.xmlout.paletteitem;

import org.eclipse.swt.widgets.Display;

import cz.martinbayer.analyser.processor.xmlout.processor.XMLOutputProcLogic;
import cz.martinbayer.analyser.processor.xmlout.processor.gui.XMLOutputProcessorConfigDialog;
import cz.martinbayer.analyser.processor.xmlout.processor.gui.XMLOutputProcessorConfigDialogModel;
import cz.martinbayer.analyser.processors.BasicProcessorPaletteItem;

public class XMLOutputProcPaletteItem extends BasicProcessorPaletteItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3645758629361883292L;
	private static final String LABEL = "XML output processor";
	private XMLOutputProcessorConfigDialogModel model = new XMLOutputProcessorConfigDialogModel();

	public XMLOutputProcPaletteItem() {
		imagePath = "images/icon.png";
		disabledImagePath = "images/icon_dis.png";
	}

	@Override
	public String getLabel() {
		return LABEL;
	}

	public void openDialog(XMLOutputProcLogic logic) {
		XMLOutputProcessorConfigDialog dialog = new XMLOutputProcessorConfigDialog(
				Display.getDefault().getActiveShell(), logic, model);
		dialog.open();
	}
}
