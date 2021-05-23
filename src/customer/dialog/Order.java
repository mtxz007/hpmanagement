package customer.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import utils.DbUtils;
import utils.OpenBox;
import dao.Patient;

public class Order extends Dialog {

	protected Object result;
	protected Shell shell;
	private Patient patient;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Order(Shell parent, int style) {
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
		final DbUtils db = new DbUtils();
		shell = new Shell(getParent(), getStyle());
		shell.setSize(450, 300);
		shell.setText("\u67E5\u770B");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(110, 68, 76, 20);
		lblNewLabel.setText("\u60A8\u524D\u9762\u8FD8\u6709");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		String number = db.queryForList("SELECT COUNT(*) AS number FROM orders WHERE patient_id != "+patient.getId_patient()+" AND ordernumber < (SELECT ordernumber FROM orders WHERE patient_id = "+patient.getId_patient()+")").get(0).get("number").toString();
		lblNewLabel_1.setBounds(192, 68, 21, 20);
		lblNewLabel_1.setText(number);
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(219, 68, 105, 20);
		lblNewLabel_2.setText("\u4EBA\uFF0C\u8BF7\u8010\u5FC3\u7B49\u5F85");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		btnNewButton.setBounds(110, 166, 91, 30);
		btnNewButton.setText("\u786E\u5B9A");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Warning_1 warning = new Warning_1(new Shell(), SWT.CLOSE);
				warning.open();
				if(warning.isResult()){
					if(db.queryForList("select * from register where patient_id = "+patient.getId_patient()+"").size()!=0){
						if(db.update("delete from register where patient_id = "+patient.getId_patient()+"")!=0 && 
								db.update("delete from orders where patient_id = "+patient.getId_patient()+"")!=0){
							OpenBox.Open("操作成功");
						}else{
							OpenBox.Open("操作失败。您可能还未进行挂号。");
						}
					}else{
						OpenBox.Open("操作失败。您可能还未进行挂号。");
					}
				}
			}
		});
		btnNewButton_1.setBounds(250, 166, 86, 30);
		btnNewButton_1.setText("\u53D6\u6D88\u6302\u53F7");

	}

}
