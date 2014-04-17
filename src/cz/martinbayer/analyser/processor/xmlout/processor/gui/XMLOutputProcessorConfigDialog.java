package cz.martinbayer.analyser.processor.xmlout.processor.gui;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import cz.martinbayer.analyser.processor.xmlout.Activator;
import cz.martinbayer.analyser.processor.xmlout.processor.XMLOutputProcLogic;
import cz.martinbayer.utils.StringUtils;
import cz.martinbayer.utils.gui.SWTUtils;

public class XMLOutputProcessorConfigDialog extends TitleAreaDialog {

	private static Logger logger = Activator.getLogger();
	private XMLOutputProcLogic logic;
	private XMLOutputProcessorConfigDialogModel dialogModel;
	private Text chooseFileText;
	private Button chooseFileBtn;

	public XMLOutputProcessorConfigDialog(Shell parentShell,
			XMLOutputProcLogic logic,
			XMLOutputProcessorConfigDialogModel dialogModel) {
		super(parentShell);
		this.logic = logic;
		this.dialogModel = dialogModel;
	}

	@Override
	public void create() {
		super.create();
		setTitle("XML export");
		setMessage("Choose a file to export the analysis results",
				IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(final Composite parent) {
		Composite parentComposite = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(parentComposite, SWT.None);
		container.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, true));
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 10;
		container.setLayout(layout);

		GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = GridData.FILL;
		chooseFileText = new Text(container, SWT.BORDER);
		chooseFileText.setLayoutData(data);

		data = new GridData();
		chooseFileBtn = new Button(container, SWT.NONE);
		chooseFileBtn.setImage(SWTUtils.getImage("images", "file.png",
				getClass()));
		chooseFileBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(parent.getShell(),
						SWT.SAVE);
				fileDialog.setOverwrite(true);
				fileDialog.setFilterExtensions(new String[] { "*.xml" });
				if (dialogModel.getDirectoryPath() != null) {
					fileDialog.setFilterPath(dialogModel.getDirectoryPath());
				}
				fileDialog.open();
				String selectedFile = fileDialog.getFileName();
				if (StringUtils.isNotEmpty(selectedFile)) {
					fileSelected(fileDialog.getFilterPath(), selectedFile);
				}
			}
		});
		initBinding();
		return container;
	}

	private void initBinding() {
		// create new Context
		DataBindingContext ctx = new DataBindingContext();

		/* create binding for root directory text field */
		IObservableValue target = WidgetProperties.text(SWT.Modify).observe(
				chooseFileText);
		IObservableValue model = BeanProperties.value(
				XMLOutputProcessorConfigDialogModel.class,
				XMLOutputProcessorConfigDialogModel.PROPERTY_SELECTED_FILE)
				.observe(dialogModel);
		ctx.bindValue(target, model, new UpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				if (value == null || value instanceof String
						|| StringUtils.isEmtpy((String) value)) {
					return null;
				}
				return super.convert(value);
			}
		}, null);
	}

	protected void fileSelected(String directoryPath, String selectedFile) {
		try {
			dialogModel.setDirectoryPath(directoryPath);
			File f = new File(directoryPath + File.separator + selectedFile);
			if (f.exists() || (!f.exists() && f.createNewFile())) {
				dialogModel.setSelectedFile(f);
			}
		} catch (IOException e) {
			logger.error(e, "Exception occured when creating file");
		}
	}

	@Override
	protected void okPressed() {
		if (validateSelectedFolder()) {
			this.logic.setOutputFile(dialogModel.getSelectedFile());
		}
		super.okPressed();
	}

	private boolean validateSelectedFolder() {
		try {
			if (dialogModel.getSelectedFile() == null
					|| dialogModel.getSelectedFile().exists()
					|| dialogModel.getSelectedFile().createNewFile()) {
				return true;
			}
			return false;
		} catch (IOException e) {
			logger.error(e, "Error occured during file creation");
			return false;
		}
	}
}
