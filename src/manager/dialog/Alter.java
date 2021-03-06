package manager.dialog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import utils.DbUtils;
import utils.OpenBox;
import utils.Picture;
import dao.User;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class Alter extends Dialog {

	protected Object result;
	protected Shell shell;
	private User currentUser = new User();
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private boolean check_username = false;
	private boolean check_name = false;
	private boolean check_age = false;
	private boolean check_mailbox = false;
	private boolean check_phone_number = false;
	private boolean check_photo = false;
	private boolean check_department = false;
	private boolean check_state = false;
	private boolean check_root = false;
	private boolean check_address = false;
	private ArrayList<HashMap<String,Object>> department;
	private ArrayList<HashMap<String,Object>> root;
	private ArrayList<HashMap<String,Object>> state;
	private Label lblNewLabel_6;
	private Label label_3;
	private Label label_4;
	private Label label_5;
	private Label label_6;
	private Label lblNewLabel_7;
	private String name;
	private String filePath;
	private boolean alterPhoto = false;

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Alter(Shell parent, int style) {
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
		shell.setSize(611, 448);
		shell.setText("\u4FEE\u6539\u7528\u6237");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(71, 48, 56, 20);
		label.setText("\u7528\u6237\u540D*");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u59D3\u540D*");
		label_1.setBounds(71, 93, 50, 20);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u5E74\u9F84*");
		label_2.setBounds(71, 137, 50, 20);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(71, 303, 50, 20);
		lblNewLabel.setText("\u4F4F\u5740*");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(71, 191, 50, 20);
		lblNewLabel_1.setText("\u79D1\u5BA4*");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(260, 48, 56, 20);
		lblNewLabel_2.setText("\u6027\u522B*");
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(260, 93, 39, 20);
		lblNewLabel_3.setText("\u7535\u8BDD*");
		
		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setBounds(260, 137, 56, 20);
		lblNewLabel_4.setText("\u6743\u9650*");
		
		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setBounds(260, 191, 50, 20);
		lblNewLabel_5.setText("\u72B6\u6001*");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		btnNewButton.setBounds(352, 339, 98, 30);
		btnNewButton.setText("\u53D6\u6D88");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setBounds(144, 339, 98, 30);
		btnNewButton_1.setText("\u4FEE\u6539");
		
		Label lblNewLabel_14 = new Label(shell, SWT.NONE);
		lblNewLabel_14.setBounds(71, 257, 50, 20);
		lblNewLabel_14.setText("\u90AE\u7BB1*");
		
		label_4 = new Label(shell, SWT.NONE);
		label_4.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_4.setText("*\u7528\u6237\u540D\u4E0D\u80FD\u4E3A\u7A7A");
		label_4.setBounds(126, 74, 115, 20);
		label_4.setVisible(false);
		
		text = new Text(shell, SWT.BORDER);
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(text.getText() == null || text.getText().equals("")){
					check_username = false;
					label_4.setVisible(true);
				}else{
					check_username = true;
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				label_4.setVisible(false);
			}
		});
		text.setBounds(127, 48, 115, 26);
		text.setText(currentUser.getUsername());
		
		label_3 = new Label(shell, SWT.NONE);
		label_3.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_3.setBounds(126, 119, 115, 20);
		label_3.setText("*\u59D3\u540D\u4E0D\u80FD\u4E3A\u7A7A");
		label_3.setVisible(false);
		
		text_1 = new Text(shell, SWT.BORDER);
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
		text_1.setBounds(127, 93, 115, 26);
		text_1.setText(currentUser.getName());
		
		label_6 = new Label(shell, SWT.NONE);
		label_6.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_6.setVisible(false);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(text_2.getText() == null || text_1.getText().equals("")){
					check_age = false;
					label_6.setText("*\u5E74\u9F84\u4E0D\u80FD\u4E3A\u7A7A");
					label_6.setVisible(true);
					label_6.setBounds(126, 165, 115, 20);
				}else {
					try{
						Integer.parseInt(text_2.getText());
						check_age = true;
					}catch(NumberFormatException e1){
						label_6.setText("*??????????");
						label_6.setBounds(126, 165, 115, 20);
						label_6.setVisible(false);
					}
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				label_6.setVisible(false);
			}
		});
		text_2.setBounds(127, 137, 39, 26);
		text_2.setText(""+currentUser.getAge());
		
		lblNewLabel_6 = new Label(shell, SWT.NONE);
		lblNewLabel_6.setForeground(SWTResourceManager.getColor(255, 0, 0));
		lblNewLabel_6.setVisible(false);
		
		text_3 = new Text(shell, SWT.BORDER | SWT.WRAP);
		text_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(text_3.getText() == null || text_3.getText().equals("")){
					check_address = false;
					lblNewLabel_6.setBounds(402, 303, 115, 20);
					lblNewLabel_6.setText("*\u4F4F\u5740\u4E0D\u80FD\u4E3A\u7A7A");
					lblNewLabel_6.setVisible(true);
				}else{
					check_address = true;
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				lblNewLabel_6.setVisible(false);
			}
		});
		text_3.setBounds(125, 303, 272, 30);
		text_3.setText(currentUser.getAddress());
		
		lblNewLabel_7 = new Label(shell, SWT.NONE);
		lblNewLabel_7.setVisible(false);
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(text_4.getText() == null || text_4.getText().equals("")){
					check_mailbox = false;
					lblNewLabel_7.setForeground(SWTResourceManager.getColor(255, 0, 0));
					lblNewLabel_7.setBounds(296, 257, 109, 20);
					lblNewLabel_7.setText("*\u90AE\u7BB1\u4E0D\u80FD\u4E3A\u7A7A");
					lblNewLabel_7.setVisible(true);
				}else{
					check_mailbox = true;
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				lblNewLabel_7.setVisible(false);
			}
		});
		text_4.setBounds(127, 257, 164, 26);
		text_4.setText(currentUser.getMailBox());
		
		label_5 = new Label(shell, SWT.NONE);
		
		label_5.setVisible(false);
		text_5 = new Text(shell, SWT.BORDER);
		text_5.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(text_5.getText() == null || text_5.getText().equals("")){
					check_phone_number = false;
					label_5.setForeground(SWTResourceManager.getColor(255, 0, 0));
					label_5.setText("*\u7528\u6237\u540D\u4E0D\u80FD\u4E3A\u7A7A");
					label_5.setBounds(322, 119, 115, 20);
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
		text_5.setBounds(327, 93, 123, 26);
		text_5.setText(currentUser.getPhone_number());
		
		final Combo combo = new Combo(shell, SWT.READ_ONLY);
		combo.setBounds(127, 191, 92, 28);
		department = db.queryForList("SELECT id,NAME FROM department");
		for(HashMap<String, Object> map : department){
			if(map.get("NAME").toString().equals(currentUser.getDepartment())){
				combo.setText(currentUser.getDepartment());
				combo.add(map.get("NAME").toString(), 0);
				combo.select(0);
			}else {
				combo.add(map.get("NAME").toString());
			}
		}
		final Combo combo_1 = new Combo(shell, SWT.READ_ONLY);
		combo_1.setBounds(327, 191, 92, 28);
		state = db.queryForList("SELECT id,NAME FROM state");
		for(HashMap<String, Object> map : state){
			if(map.get("NAME").toString().equals(currentUser.getState())){
				combo_1.setText(currentUser.getState());
				combo_1.add(map.get("NAME").toString(), 0);
				combo_1.select(0);
			}else {
				combo_1.add(map.get("NAME").toString());
			}
		}
		final Combo combo_2 = new Combo(shell, SWT.READ_ONLY);
		combo_2.setBounds(327, 137, 92, 28);
		root = db.queryForList("SELECT id,NAME FROM root");
		for(HashMap<String, Object> map : root){
			if(map.get("NAME").toString().equals(currentUser.getRoot())){
				combo_2.setText(currentUser.getRoot());
				combo_2.add(map.get("NAME").toString(),0);
				combo_2.select(0);
			}else {
				combo_2.add(map.get("NAME").toString());
			}
		}
		
		final Button button = new Button(shell, SWT.RADIO);
		button.setBounds(323, 48, 39, 20);
		button.setText("\u7537");
		
		Button button_1 = new Button(shell, SWT.RADIO);
		button_1.setText("\u5973");
		button_1.setBounds(370, 48, 39, 20);
		
		final Label lblNewLabel_8 = new Label(shell, SWT.NONE);
		lblNewLabel_8.setForeground(SWTResourceManager.getColor(255, 0, 0));
		lblNewLabel_8.setBounds(326, 165, 76, 20);
		lblNewLabel_8.setText("*\u8BF7\u9009\u62E9");
		lblNewLabel_8.setVisible(false);
		
		final Label lblNewLabel_9 = new Label(shell, SWT.BORDER);
		lblNewLabel_9.setBounds(471, 48, 115, 151);
		if(!currentUser.getPhoto().equals(""))
			lblNewLabel_9.setImage(SWTResourceManager.getImage("F:\\workspace\\hpmanagement"+File.separator+currentUser.getPhoto()));
		
		Button btnNewButton_3 = new Button(shell, SWT.NONE);
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				FileDialog file = new FileDialog(new Shell());
				file.open();
				
				String path = file.getFilterPath();
				name = file.getFileName();
				
				filePath = path + File.separator + name;
				lblNewLabel_9.setImage(SWTResourceManager.getImage(filePath));
				
				alterPhoto = true;
			}
		});
		btnNewButton_3.setBounds(497, 205, 72, 30);
		btnNewButton_3.setText("\u4E0A\u4F20\u7167\u7247");
		
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				User alterUser = new User();
				if(text.getText() == null || text.getText().equals("")){
					check_username = false;
				}else{
					check_username = true;
				}
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
				if(text_3.getText() == null || text_3.getText().equals("")){
					check_address = false;
				}else{
					check_address = true;
				}
				if(text_2.getText() == null || text_2.getText().equals("")){
					check_age = false;
				}else{
					check_age = true;
				}
				if(text_4.getText() == null || text_4.getText().equals("")){
					check_mailbox = false;
				}else{
					check_mailbox = true;
				}
				if(combo_1.getText() == null || combo_1.getText().equals("")){
					check_state = false;
					label_5.setText("*\u8BF7\u9009\u62E9");
					label_5.setForeground(SWTResourceManager.getColor(255, 0, 0));
					label_5.setBounds(326, 218, 76, 20);
					label_5.setVisible(true);
				}else{
					check_state = true;
				}
				if(combo_2.getText() == null || combo_2.getText().equals("")){
					check_root = false;
					lblNewLabel_8.setVisible(true);
				}else{
					check_root = true;
				}
				if(combo.getText() == null || combo.getText().equals("")){
					check_department = false;
					label_3.setText("*\u8BF7\u9009\u62E9");
					label_3.setForeground(SWTResourceManager.getColor(255, 0, 0));
					label_3.setBounds(126, 218, 76, 20);
					label_3.setVisible(true);
				}else{
					check_department = true;
				}
				if(check_username && check_name && check_age && check_address && check_mailbox 
						&& check_phone_number && check_state && check_root && check_department){
					
					alterUser.setUsername(text.getText());
					alterUser.setName(text_1.getText());
					alterUser.setAge(Integer.parseInt(text_2.getText()));
					alterUser.setAddress(text_3.getText());
					alterUser.setPhone_number(text_5.getText());
					alterUser.setMailBox(text_4.getText());
					alterUser.setSexual((button.getSelection() ? "??" : "??"));
					for(HashMap<String, Object> map : department){
						if(map.get("NAME").toString().equals(combo.getText())){
							alterUser.setDepartment(map.get("id").toString());
						}
					}
					for(HashMap<String, Object> map : state){
						if(map.get("NAME").toString().equals(combo_1.getText())){
							alterUser.setState(map.get("id").toString());
						}
					}
					for(HashMap<String, Object> map : root){
						if(map.get("NAME").toString().equals(combo_2.getText())){
							alterUser.setRoot(map.get("id").toString());
						}
					}
					String newName = "";
					if(alterPhoto){
						newName = Picture.rename(name);
						alterUser.setPhoto(newName);
						Picture.upload(new File(filePath), newName);
					}else
						alterUser.setPhoto("");
					if(db.updatePre("UPDATE USER SET username = ?, NAME = ?, sexual = ?, age = ?, mailbox = ?, phone_number = ?, address = ?, department = ?, root = ?, state = ? ,photo = ? WHERE username = ?", alterUser.getUsername(), alterUser.getName(), alterUser.getSexual(), alterUser.getAge(), 
							alterUser.getMailBox(), alterUser.getPhone_number(), alterUser.getAddress(), alterUser.getDepartment(), alterUser.getRoot(), 
							alterUser.getState(), alterUser.getPhoto(), currentUser.getUsername()) != 0){
						OpenBox.Open("????????");
						shell.dispose();
					}else{
						OpenBox.Open("????????");
					}
				}else{
					OpenBox.Open("??????????????????");
				}
			}
		});
		
		if(currentUser.getSexual().equals("??")){
			button.setSelection(true);
		}else{
			button_1.setSelection(true);
		}

	}
}
