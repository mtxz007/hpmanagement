package medicine.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import dao.Medicine;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Details extends Dialog {

	protected Object result;
	protected Shell shell;
	private Medicine medicine;

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Details(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(439, 411);
		shell.setText("\u8BE6\u60C5");
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("\u540D\u79F0");
		label.setBounds(34, 57, 50, 20);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u5382\u5BB6");
		label_1.setBounds(34, 113, 50, 20);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u8FC7\u671F\u65E5\u671F");
		label_2.setBounds(233, 217, 73, 20);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("\u751F\u4EA7\u65E5\u671F");
		label_3.setBounds(34, 217, 73, 20);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("\u5E93\u5B58");
		label_4.setBounds(34, 171, 50, 20);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText(""+medicine.getNumber());
		label_5.setBounds(100, 171, 32, 20);
		
		Label label_10 = new Label(shell, SWT.WRAP);
		label_10.setText(medicine.getExp());
		label_10.setBounds(312, 217, 92, 20);
		
		Label label_11 = new Label(shell, SWT.NONE);
		label_11.setText(medicine.getPd());
		label_11.setBounds(113, 217, 92, 20);
		
		Label label_12 = new Label(shell, SWT.NONE);
		label_12.setText(medicine.getName());
		label_12.setBounds(100, 57, 127, 20);
		
		Label label_13 = new Label(shell, SWT.NONE);
		label_13.setText(medicine.getMenufacturer());
		label_13.setBounds(100, 113, 127, 20);
		
		Label label_14 = new Label(shell, SWT.NONE);
		label_14.setText(""+medicine.getPrice_stock());
		label_14.setBounds(304, 57, 32, 20);
		
		Label label_15 = new Label(shell, SWT.NONE);
		label_15.setText("\u8FDB\u4EF7");
		label_15.setBounds(233, 57, 56, 20);
		
		Label label_16 = new Label(shell, SWT.NONE);
		label_16.setText("\u9500\u552E\u4EF7");
		label_16.setBounds(233, 113, 60, 20);
		
		Label label_17 = new Label(shell, SWT.NONE);
		label_17.setText(""+medicine.getPrice_sale());
		label_17.setBounds(304, 113, 32, 20);
		
		Button button = new Button(shell, SWT.NONE);
	
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		button.setText("\u786E\u5B9A");
		button.setBounds(160, 288, 98, 30);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(233, 171, 56, 20);
		lblNewLabel.setText("\u6279\u6B21\u53F7");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(312, 171, 92, 20);
		lblNewLabel_2.setText(medicine.getBatch_number());

	}

	public void setUsername(String username) {
		// TODO Auto-generated method stub
		
	}

}
