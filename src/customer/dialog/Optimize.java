package customer.dialog;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import utils.DbUtils;
import utils.OpenBox;
import utils.Picture;
import dao.Patient;

public class Optimize extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text_1;
	private Text text_2;
	private Text text_5;
	private boolean check_name = false;
	private boolean check_age = false;
	private boolean check_phone_number = false;
	private Label label_3;
	private Label label_5;
	private Label label_6;
	private Patient patient;
	private boolean alterPhoto = false;
	private String filePath;
	private String name;
	private String newName;

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
	public Optimize(Shell parent, int style) {
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
		shell.setSize(490, 493);
		shell.setText("\u533B\u52A1\u6CE8\u518C");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u59D3\u540D*");
		label_1.setBounds(64, 83, 50, 20);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u5E74\u9F84*");
		label_2.setBounds(64, 194, 50, 20);
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(64, 238, 56, 20);
		lblNewLabel_2.setText("\u6027\u522B*");
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(64, 136, 39, 20);
		lblNewLabel_3.setText("\u7535\u8BDD*");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		btnNewButton.setBounds(298, 359, 98, 30);
		btnNewButton.setText("\u53D6\u6D88");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setBounds(120, 359, 98, 30);
		btnNewButton_1.setText("\u6CE8\u518C");
		
		
		
		label_3 = new Label(shell, SWT.NONE);
		label_3.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_3.setBounds(120, 112, 115, 20);
		label_3.setText("*\u59D3\u540D\u4E0D\u80FD\u4E3A\u7A7A");
		label_3.setVisible(false);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setText(patient.getName());
		text_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(text_1.getText() == null || text_1.getText().equals("")){
					check_name = false;
					label_3.setVisible(true);
				}else{
					check_name = true;
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				label_3.setVisible(false);
			}
		});
		text_1.setBounds(120, 83, 115, 26);
		
		label_6 = new Label(shell, SWT.NONE);
		label_6.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_6.setVisible(false);
		
		final Label label_8 = new Label(shell, SWT.NONE);
		label_8.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_8.setVisible(false);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setText(""+patient.getAge());
		text_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(text_2.getText() == null || text_1.getText().equals("")){
					check_age = false;
					label_8.setText("*\u5E74\u9F84\u4E0D\u80FD\u4E3A\u7A7A");
					label_8.setBounds(126, 165, 115, 20);
					label_8.setVisible(true);
				}else {
					try{
						Integer.parseInt(text_2.getText());
						check_age = true;
					}catch(NumberFormatException e1){
						label_8.setText("*请输入数字");
						label_8.setVisible(true);
					}
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				label_8.setVisible(false);
			}
		});
		text_2.setBounds(119, 191, 39, 26);
		
		label_5 = new Label(shell, SWT.NONE);
		label_5.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_5.setText("*\u7535\u8BDD\u53F7\u4E0D\u80FD\u4E3A\u7A7A");
		label_5.setBounds(124, 165, 115, 20);
		label_5.setVisible(false);
		
		text_5 = new Text(shell, SWT.BORDER);
		text_5.setText(patient.getPhone_number());
		text_5.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(text_5.getText() == null || text_5.getText().equals("")){
					check_phone_number = false;
					label_5.setVisible(true);
				}else{
					check_phone_number = true;
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				label_5.setVisible(false);
			}
		});
		text_5.setBounds(120, 133, 130, 26);
		
		final Label label_7 = new Label(shell, SWT.NONE);
		label_7.setVisible(false);
		label_7.setText("*\u8BF7\u9009\u62E9");
		label_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_7.setBounds(127, 225, 92, 20);
		label_7.setVisible(false);
		
		final Button button = new Button(shell, SWT.RADIO);
		button.setBounds(127, 238, 39, 20);
		button.setText("\u7537");
		
		final Button button_1 = new Button(shell, SWT.RADIO);
		button_1.setText("\u5973");
		button_1.setBounds(127, 264, 39, 20);
		
		if(patient.getSexual().equals("男"))
			button.setSelection(true);
		else
			button_1.setSelection(true);
		
		final Label lblNewLabel = new Label(shell, SWT.BORDER);
		lblNewLabel.setBounds(280, 57, 138, 175);
		if(!patient.getPhoto().equals("")){
			lblNewLabel.setImage(SWTResourceManager.getImage("F:\\workspace\\hpmanagement"+File.separator+patient.getPhoto()));
		}
		
		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				FileDialog file = new FileDialog(new Shell());
				file.open();
				
				String path = file.getFilterPath();
				name = file.getFileName();
				
				filePath = path + File.separator + name;
				lblNewLabel.setImage(SWTResourceManager.getImage(filePath));
				
				alterPhoto = true;
			}
		});
		btnNewButton_2.setBounds(298, 254, 98, 30);
		btnNewButton_2.setText("\u4E0A\u4F20\u7167\u7247");
		
		if(patient.getPhoto()!=null || !patient.getPhoto().equals("")){
			lblNewLabel.setImage(SWTResourceManager.getImage("F:\\workspace\\hpmanagement\\icons"+File.separator+patient.getPhoto()));
		}
		
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(text_1.getText() == null || text_1.getText().equals("")){
					check_name = false;
				}else{
					check_name = true;
				}
				if(text_5.getText() == null || text_5.getText().equals("")){
					check_phone_number = false;
				}else{
					check_phone_number = true;
				}
				if(text_2.getText() == null || text_2.getText().equals("")){
					check_age = false;
				}else{
					check_age = true;
				}
				if( check_name && check_age
					&& check_phone_number ){
					patient.setName(text_1.getText());
					patient.setAge(Integer.parseInt(text_2.getText()));
					patient.setPhone_number(text_5.getText());
					patient.setSexual((button.getSelection() ? "男" : "女"));
					
					if(alterPhoto){
						newName = Picture.rename(name);
						patient.setPhoto(newName);
						Picture.upload(new File(filePath), newName);
					}
					if(db.updatePre("update patients set name = ?, sexual = ?, age = ?, photo = ?, phone_number = ? where id_patients = ?",patient.getName(), patient.getSexual(), patient.getAge(),patient.getPhoto(), 
							 patient.getPhone_number(), patient.getId_patient()  
							) != 0){
						OpenBox.Open("操作成功");	
						shell.dispose();
					}
				}else{
					OpenBox.Open("请确保信息填写完毕");
				}
			}
		});
	}
}
