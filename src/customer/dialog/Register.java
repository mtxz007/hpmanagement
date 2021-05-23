package customer.dialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import utils.DbUtils;
import utils.OpenBox;
import dao.Patient;

public class Register extends Dialog {

	protected Object result;
	protected Shell shell;
	private Patient patient;
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	private ArrayList<HashMap<String, Object>> user;
	private ArrayList<HashMap<String, Object>> kinds;
	private ArrayList<HashMap<String, Object>> department;
	private int kinds_id;
	private int department_id;
	private String time;
	private int state_id;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Register(Shell parent, int style) {
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
		user = db.queryForList("SELECT u.`username`,u.`name`,u.`sexual`,u.`age`,u.`mailbox`,u.`phone_number`,u.`address`,d.`name` AS department_name,r.`name` AS root_name,s.`name` AS state_name "
				+"FROM USER u, department d, root r, state s WHERE u.`department` = d.`id` AND u.`root` = r.`id` AND u.`state` = s.`id` "
				+"ORDER BY u.name ");
		kinds = db.queryForList("select * from kinds");
		department = db.queryForList("select * from department");
		shell = new Shell(getParent(), getStyle());
		shell.setSize(450, 300);
		shell.setText("\u6302\u53F7");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(59, 44, 36, 20);
		lblNewLabel.setText("\u59D3\u540D");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(59, 97, 36, 20);
		label.setText("\u79D1\u5BA4");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u7C7B\u578B");
		label_1.setBounds(59, 161, 36, 20);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(243, 44, 50, 20);
		lblNewLabel_1.setText("\u7ECF\u529E\u4EBA");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(243, 161, 50, 20);
		lblNewLabel_2.setText("\u4EF7\u683C");
		
		final Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(116, 44, 76, 20);
		lblNewLabel_3.setText(patient.getName());
		
		final Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setBounds(311, 161, 76, 20);
		lblNewLabel_4.setText("10.00");
		
		final Combo combo = new Combo(shell, SWT.READ_ONLY);

		final Combo combo_1 = new Combo(shell, SWT.READ_ONLY);
		combo_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(combo_1.getText().equals("医保")){
					lblNewLabel_4.setText("5.00");
				}else{
					lblNewLabel_4.setText("10.00");
				}
			}
		});
		combo_1.setBounds(116, 153, 76, 28);
		for(HashMap<String, Object> map:kinds){
			combo_1.add(map.get("name").toString());
		};
		combo_1.select(0);
		
		final Combo combo_2 = new Combo(shell, SWT.READ_ONLY);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				combo_2.removeAll();	
				for(HashMap<String, Object> map:user){
					if(map.get("department_name").equals(combo.getText())){
						combo_2.add(map.get("name").toString());
					}
				}
			}
		});
		
		combo_2.setBounds(311, 44, 102, 28);
		combo_2.add("请先选择科室", 0);
		combo_2.select(0);
		
		combo.setBounds(116, 89, 76, 28);
		combo.add("请选择", 0);
		for(HashMap<String, Object> map:department){
			combo.add(map.get("name").toString());
		}
		combo.select(0);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
				time = date.format(new Date());
				if(combo_1.getText().equals("医保")){
					kinds_id = 2;
				}else if(combo_1.getText().equals("普通")){
					kinds_id = 1;
				}
				for(HashMap<String, Object> map:department){
					if(map.get("name").equals(combo.getText())){
						department_id = Integer.parseInt(map.get("id").toString());
						break;
					}
				}
				if(combo.getText().equals("请选择") || combo.getText().equals("请先选择科室") || combo_2.getText().equals("") || combo_2.getText() == null){
					OpenBox.Open("请确定选项选择完毕");
				}else if(db.updatePre("insert into register(patient_id, name,department,kinds,time,price,operator,state,result) values(?, ?, ?, ?, ?, ?, ?, 1, '')", 
					patient.getId_patient(), lblNewLabel_3.getText(),department_id, kinds_id, time, lblNewLabel_4.getText(), combo_2.getText())!=0 
					&& db.updatePre("insert into orders(patient_id,name) values(?, ?)", patient.getId_patient(), patient.getName())!=0
					&& db.update("insert into income values("+department_id+","+lblNewLabel_4.getText()+",'"+time+"','否','是')")!=0){
					OpenBox.Open("操作成功");
					shell.dispose();
				}else{
					OpenBox.Open("操作失败，您已完成挂号，若要重新挂号请先取消前置挂号单");
					shell.dispose();
				}
			}
		});
		btnNewButton.setBounds(110, 211, 70, 30);
		btnNewButton.setText("\u6302\u53F7");
		
		Button button = new Button(shell, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		button.setText("\u53D6\u6D88");
		button.setBounds(261, 211, 70, 30);

	}
}
