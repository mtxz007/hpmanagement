package dialog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
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
import dao.User;

public class Registe extends Dialog {

	protected Object result;
	protected Shell shell;
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
	private boolean check_address = false;
	private boolean check_id = false;
	private ArrayList<HashMap<String,Object>> department;
	private Label lblNewLabel_6;
	private Label label_3;
	private Label label_4;
	private Label label_5;
	private Label label_6;
	private Label lblNewLabel_7;
	private Label lblNewLabel_8;
	private Text text_6;
	private String filePath;
	private String name;
	private String newName;
	private boolean alterPhoto = false;


	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Registe(Shell parent, int style) {
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
		shell.setSize(611, 518);
		shell.setText("\u533B\u52A1\u6CE8\u518C");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(71, 33, 56, 20);
		label.setText("\u7528\u6237\u540D*");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u59D3\u540D*");
		label_1.setBounds(71, 82, 50, 20);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u5E74\u9F84*");
		label_2.setBounds(71, 137, 50, 20);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(71, 374, 50, 20);
		lblNewLabel.setText("\u4F4F\u5740*");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(71, 191, 50, 20);
		lblNewLabel_1.setText("\u79D1\u5BA4*");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(260, 33, 56, 20);
		lblNewLabel_2.setText("\u6027\u522B*");
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(260, 137, 39, 20);
		lblNewLabel_3.setText("\u7535\u8BDD*");
		
		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setBounds(260, 191, 56, 20);
		lblNewLabel_4.setText("\u6743\u9650");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		btnNewButton.setBounds(352, 423, 98, 30);
		btnNewButton.setText("\u53D6\u6D88");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setBounds(144, 423, 98, 30);
		btnNewButton_1.setText("\u6CE8\u518C");
		
		Label lblNewLabel_14 = new Label(shell, SWT.NONE);
		lblNewLabel_14.setBounds(71, 328, 50, 20);
		lblNewLabel_14.setText("\u90AE\u7BB1*");
		
		label_4 = new Label(shell, SWT.NONE);
		label_4.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_4.setBounds(127, 56, 115, 20);
		label_4.setVisible(false);
		
		text = new Text(shell, SWT.BORDER);
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(text.getText() == null || text.getText().equals("")){
					check_username = false;
					label_4.setText("*\u7528\u6237\u540D\u4E0D\u80FD\u4E3A\u7A7A");
					label_4.setVisible(true);
				}else if(db.queryForList("select * from user where username = '"+text.getText()+"'").size()!=0){
					label_4.setText("*用户名已存在");
					label_4.setVisible(true);
					check_username = false;
				}else{
					check_username = true;
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				label_4.setVisible(false);
			}
		});
		text.setBounds(127, 30, 115, 26);
		
		label_3 = new Label(shell, SWT.NONE);
		label_3.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_3.setBounds(127, 111, 115, 20);
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
		text_1.setBounds(127, 82, 115, 26);
		
		label_6 = new Label(shell, SWT.NONE);
		label_6.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_6.setVisible(false);
		
		final Label label_8 = new Label(shell, SWT.NONE);
		label_8.setText("*\u8BF7\u9009\u62E9");
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_8.setBounds(127, 165, 76, 20);
		label_8.setVisible(false);
		
		text_2 = new Text(shell, SWT.BORDER);
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
		text_2.setBounds(126, 134, 39, 26);
		
		lblNewLabel_7 = new Label(shell, SWT.NONE);
		lblNewLabel_7.setForeground(SWTResourceManager.getColor(255, 0, 0));
		lblNewLabel_7.setBounds(403, 374, 104, 20);
		lblNewLabel_7.setText("*\u4F4F\u5740\u4E0D\u80FD\u4E3A\u7A7A");
		lblNewLabel_7.setVisible(false);
		
		text_3 = new Text(shell, SWT.BORDER | SWT.WRAP);
		text_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(text_3.getText() == null || text_3.getText().equals("")){
					check_address = false;
					lblNewLabel_7.setVisible(true);
				}else{
					check_address = true;
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				lblNewLabel_7.setVisible(false);
			}
		});
		text_3.setBounds(125, 374, 272, 30);
		
		lblNewLabel_8 = new Label(shell, SWT.NONE);
		lblNewLabel_8.setForeground(SWTResourceManager.getColor(255, 0, 0));
		lblNewLabel_8.setBounds(297, 328, 109, 20);
		lblNewLabel_8.setText("*\u90AE\u7BB1\u4E0D\u80FD\u4E3A\u7A7A");
		lblNewLabel_8.setVisible(false);
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(text_4.getText() == null || text_4.getText().equals("")){
					check_mailbox = false;
					lblNewLabel_8.setVisible(true);
				}else{
					check_mailbox = true;
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				lblNewLabel_8.setVisible(false);
			}
		});
		text_4.setBounds(127, 328, 164, 26);
		
		label_5 = new Label(shell, SWT.NONE);
		label_5.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_5.setText("*\u7535\u8BDD\u53F7\u4E0D\u80FD\u4E3A\u7A7A");
		label_5.setBounds(327, 165, 115, 20);
		label_5.setVisible(false);
		
		text_5 = new Text(shell, SWT.BORDER);
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
		text_5.setBounds(327, 137, 123, 26);
		
		final Label label_7 = new Label(shell, SWT.NONE);
		label_7.setVisible(false);
		label_7.setText("*\u8BF7\u9009\u62E9");
		label_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_7.setBounds(127, 225, 92, 20);
		label_7.setVisible(false);
		
		final Combo combo = new Combo(shell, SWT.READ_ONLY);
		combo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				label_7.setVisible(false);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(combo.getText().equals("请选择"))
					label_7.setVisible(true);
			}
		});
		combo.setBounds(127, 191, 92, 28);
		department = db.queryForList("SELECT id,NAME FROM department");
		combo.add("请选择", 0);
		for(HashMap<String, Object> map : department){
			combo.add(map.get("NAME").toString());
		}
		combo.select(0);
		
		final Button button = new Button(shell, SWT.RADIO);
		button.setBounds(323, 33, 39, 20);
		button.setText("\u7537");
		
		Button button_1 = new Button(shell, SWT.RADIO);
		button_1.setText("\u5973");
		button_1.setBounds(323, 59, 39, 20);
		
		final Label lblNewLabel_11 = new Label(shell, SWT.BORDER);
		lblNewLabel_11.setBounds(456, 56, 115, 141);
		
		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				FileDialog file = new FileDialog(new Shell());
				file.open();
				
				String path = file.getFilterPath();
				name = file.getFileName();
				
				filePath = path + File.separator + name;
				lblNewLabel_11.setImage(SWTResourceManager.getImage(filePath));
				
				alterPhoto = true;
			}
		});
		btnNewButton_2.setBounds(467, 220, 76, 30);
		btnNewButton_2.setText("\u4E0A\u4F20\u7167\u7247");
		
		Label lblNewLabel_5 = new Label(shell, SWT.WRAP);
		lblNewLabel_5.setBounds(327, 191, 98, 86);
		lblNewLabel_5.setText("\u9ED8\u8BA4\u4E3A\u533B\u52A1\u4EBA\u5458\uFF0C\u82E5\u4E3A\u7BA1\u7406\u5458\u8BF7\u8054\u7CFB\u5DF2\u6709\u7BA1\u7406\u8C03\u6574\u3002");
		
		Label lblNewLabel_9 = new Label(shell, SWT.NONE);
		lblNewLabel_9.setBounds(71, 280, 56, 20);
		lblNewLabel_9.setText("\u8EAB\u4EFD\u8BC1*");
		
		final Label lblNewLabel_10 = new Label(shell, SWT.NONE);
		lblNewLabel_10.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_10.setBounds(297, 280, 115, 20);
		lblNewLabel_10.setText("*\u8EAB\u4EFD\u8BC1\u4E0D\u80FD\u4E3A\u7A7A");
		lblNewLabel_10.setVisible(false);
		
		text_6 = new Text(shell, SWT.BORDER);
		text_6.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lblNewLabel_10.setVisible(false);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(text_6.getText().equals("")|| text_6.getText()==null){
					check_id = false;
					lblNewLabel_10.setVisible(true);
				}else{
					check_id = true;
				}
			}
		});
		text_6.setBounds(127, 277, 164, 26);
		
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				User newUser = new User();
				String department_id = null;
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

				if(combo.getText() == null || combo.getText().equals("")){
					check_department = false;
					
				}else{
					for(HashMap<String, Object> m:department)
						if(m.get("NAME").toString().equals(combo.getText())){
							department_id = m.get("id").toString();
							break;
						}
					check_department = true;
				}
				if(check_username && check_name && check_age && check_address && check_mailbox 
						&& check_phone_number && check_department && !combo.getText().equals("请输入")){
					
					newUser.setUsername(text.getText().trim());
					newUser.setName(text_1.getText().trim());
					newUser.setAge(Integer.parseInt(text_2.getText().trim()));
					newUser.setAddress(text_3.getText().trim());
					newUser.setId(text_6.getText().trim());
					newUser.setPhone_number(text_5.getText().trim());
					newUser.setMailBox(text_4.getText().trim());
					newUser.setSexual((button.getSelection() ? "男" : "女"));
					for(HashMap<String, Object> map : department){
						if(map.get("NAME").toString().equals(combo.getText())){
							newUser.setDepartment(map.get("id").toString());
						}
					}
					
					newUser.setState("正常");
					
					if(alterPhoto){
						newName = Picture.rename(name);
						newUser.setPhoto(newName);
						Picture.upload(new File(filePath), newName);
					}else
						newUser.setPhoto("");
					if(db.updatePre("INSERT INTO USER VALUES(?, '123456', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", newUser.getUsername(), newUser.getName(), newUser.getSexual(), newUser.getAge(), 
							newUser.getId(), newUser.getMailBox(), newUser.getPhone_number(), newUser.getAddress(), department_id, 4, 
							1, newUser.getPhoto()) != 0){
						OpenBox.Open("操作成功");
						Tip tip = new Tip(new Shell(), SWT.CLOSE);
						tip.setNewUser(newUser);
						shell.dispose();
						tip.open();
					}
				}else{
					OpenBox.Open("请确保信息填写完毕");
				}
			}
		});
	}
}
