package dialog;

import hpmanagement.Application;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import utils.DbUtils;
import utils.OpenBox;
import dao.User;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Login extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	public static User currentUser = new User();
	private int root;
	private boolean start;
	private Text text_2;
	
	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public int getRoot() {
		return root;
	}

	public void setRoot(int root) {
		this.root = root;
	}

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Login(Shell parent, int style) {
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
		shell.setSize(450, 300);
		shell.setText("\u767B\u5F55");
		
		final Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(87, 92, 49, 20);
		lblNewLabel.setText("\u7528\u6237\u540D");
		
		final Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(87, 134, 49, 20);
		lblNewLabel_1.setText("\u5BC6\u7801");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(87, 49, 49, 20);
		lblNewLabel_2.setText("\u60A8\u662F");
		
		final Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setForeground(SWTResourceManager.getColor(255, 0, 0));
		lblNewLabel_4.setBounds(260, 49, 76, 20);
		lblNewLabel_4.setText("*\u4E0D\u80FD\u4E3A\u7A7A");
		lblNewLabel_4.setVisible(false);
		
		final Combo combo = new Combo(shell, SWT.READ_ONLY);
		combo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(combo.getText().equals("请选择"))
					lblNewLabel_4.setVisible(true);
				else if(combo.getText().equals("管理员"))
					root = 1;
				else if(combo.getText().equals("门诊"))
					root = 2;
				else if(combo.getText().equals("药房"))
					root = 3;
				else if(combo.getText().equals("医生"))
					root = 4;
				
			}
		});
		combo.add("请选择", 0);
		combo.add("病人");
		combo.add("管理员");
		combo.add("门诊");
		combo.add("医生");
		combo.add("药房");
		combo.select(0);
		combo.setBounds(162, 46, 92, 28);
		
		final Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DbUtils db = new DbUtils();
				String username = text.getText().trim();
				String password = text_1.getText().trim();
				ArrayList<HashMap<String,Object>> list;
				if(combo.getText().equals("请选择") || text.getText().trim().equals("") || text_1.getText().trim().equals("")){
					OpenBox.Open("请填写用户名和密码");
				}else if((list = db.queryForList("SELECT u.id, u.`username`,u.`name`,u.`sexual`,u.`age`,u.`mailbox`,u.`phone_number`,u.`address`,d.`name` AS department_name,r.`name` AS root_name,s.`name` AS state_name "
						+"FROM USER u, department d, root r, state s WHERE u.`department` = d.`id` AND u.`root` = r.`id` AND u.`state` = s.`id` AND username = '"+username+"' AND password = '"+password+"' AND r.id = "+root+""
				)).size()==0){
					OpenBox.Open("用户名不存在或密码错误");
				}else{
					currentUser.setUsername(username);
					currentUser.setPassword(password);
					currentUser.setAddress(list.get(0).get("address").toString());
					currentUser.setAge(Integer.parseInt(list.get(0).get("age").toString()));
					currentUser.setDepartment(list.get(0).get("department_name").toString());
					currentUser.setId(list.get(0).get("id").toString());
					currentUser.setName(list.get(0).get("name").toString());
					currentUser.setMailBox(list.get(0).get("mailbox").toString());
					currentUser.setPhone_number(list.get(0).get("phone_number").toString());
					currentUser.setRoot(list.get(0).get("root_name").toString());
					currentUser.setSexual(list.get(0).get("sexual").toString());
					start = true;
					OpenBox.Open("登陆成功");
					shell.dispose();
				}
			}
		});
		btnNewButton.setBounds(98, 192, 67, 30);
		btnNewButton.setText("\u767B\u5F55");
		
		final Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Registe registe = new Registe(new Shell(), SWT.CLOSE);
				registe.open();
			}
		});
		btnNewButton_1.setBounds(260, 192, 67, 30);
		btnNewButton_1.setText("\u6CE8\u518C");
		
		final Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_3.setBounds(333, 92, 76, 20);
		lblNewLabel_3.setText("*\u4E0D\u80FD\u4E3A\u7A7A");
		lblNewLabel_3.setVisible(false);
		
		final Label label = new Label(shell, SWT.NONE);
		label.setText("*\u4E0D\u80FD\u4E3A\u7A7A");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setBounds(333, 134, 76, 20);
		label.setVisible(false);

		text = new Text(shell, SWT.BORDER);
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lblNewLabel_3.setVisible(false);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(text.getText().trim().equals(""))
					lblNewLabel_3.setVisible(false);
			}
		});
		text.setBounds(162, 89, 165, 26);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(162, 131, 165, 26);
		
		final Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setBounds(153, 116, 38, 20);
		lblNewLabel_5.setText("\u59D3\u540D");
		lblNewLabel_5.setVisible(false);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(194, 116, 92, 26);
		text_2.setVisible(false);
		
		final Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(text_2.getText().trim().equals(""))
					OpenBox.Open("不能为空");
				else{
					currentUser.setRoot("病人");
					currentUser.setName(text_2.getText().trim());
					start = true;
					shell.dispose();
				}
			}
		});
		btnNewButton_2.setBounds(194, 192, 54, 30);
		btnNewButton_2.setText("\u8FDB\u5165");
		btnNewButton_2.setVisible(false);
		text_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lblNewLabel_3.setVisible(false);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(text.getText().trim().equals(""))
					label.setVisible(false);
			}
		});
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblNewLabel_4.setVisible(false);
				if(combo.getText().equals("病人")){
					btnNewButton_2.setVisible(true);
					lblNewLabel_5.setVisible(true);
					text_2.setVisible(true);
					lblNewLabel.setVisible(false);
					lblNewLabel_1.setVisible(false);
					text.setVisible(false);
					text_1.setVisible(false);
					btnNewButton.setVisible(false);
					btnNewButton_1.setVisible(false);
				}else{
					btnNewButton_2.setVisible(false); 
					lblNewLabel_5.setVisible(false);
					text_2.setVisible(false);
					lblNewLabel.setVisible(true);
					lblNewLabel_1.setVisible(true);
					text.setVisible(true);
					text_1.setVisible(true);
					btnNewButton.setVisible(true);
					btnNewButton_1.setVisible(true);
				}
			}
		});
	}
}
