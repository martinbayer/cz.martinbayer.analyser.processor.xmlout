package cz.martinbayer.analyser.processor.xmlout.processor.gui;

import java.io.File;
import java.io.Serializable;

import cz.martinbayer.utils.model.ObservableModelObject;

public class XMLOutputProcessorConfigDialogModel extends ObservableModelObject
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4156294871923039534L;
	public static final String PROPERTY_DIRECTORY_PATH = "directoryPath";
	public static final String PROPERTY_SELECTED_FILE = "selectedFile";

	/** Directory where selected file is placed */
	private String directoryPath;
	private File selectedFile;

	public String getDirectoryPath() {
		return this.directoryPath;
	}

	public final void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}

	public void setSelectedFile(File selectedFile) {
		firePropertyChange(PROPERTY_SELECTED_FILE, this.selectedFile,
				this.selectedFile = selectedFile);
	}

	public final File getSelectedFile() {
		return selectedFile;
	}
}
