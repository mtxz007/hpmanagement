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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import dao.User;

import utils.DbUtils;
import utils.OpenBox;

public class Details_forDelete extends Dialog {

	protected Object result;
	protected Shell shell;
	private String username;
	private User currentUser = new User();
	
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Details_forDelete(Shell parent, int style) {
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
		ArrayList<HashMap<String,Object>> user = db.queryForList("SELECT u.photo, u.`username`,u.`name`,u.`sexual`,u.`age`,u.`mailbox`,u.`phone_number`,u.`address`,d.`name` AS department_name,r.`name` AS root_name,s.`name` AS state_name "
				+"FROM USER u, department d, root r, state s WHERE u.`department` = d.`id` AND u.`root` = r.`id` AND u.`state` = s.`id` AND u.`username` = '"+getUsername()+"'");
		shell = new Shell(getParent(), getStyle());
		shell.setSize(611, 448);
		shell.setText("\u8BE6\u60C5");
		
		currentUser.setDepartment(user.get(0).get("department_name").toString());
		currentUser.setMailBox(user.get(0).get("mailbox").toString());
		currentUser.setName(user.get(0).get("username").toString());
		currentUser.setSexual(user.get(0).get("sexual").toString());
		currentUser.setAge(Integer.parseInt(user.get(0).get("age").toString()));
		currentUser.setUsername(user.get(0).get("username").toString());
		currentUser.setAddress(user.get(0).get("address").toString());
		currentUser.setState(user.get(0).get("state_name").toString());
		currentUser.setPhone_number(user.get(0).get("phone_number").toString());
		currentUser.setRoot(user.get(0).get("root_name").toString());
		currentUser.setPhoto(user.get(0).get("photo").toString());
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(70, 48, 50, 20);
		label.setText("\u7528\u6237\u540D");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u59D3\u540D");
		label_1.setBounds(70, 93, 50, 20);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u5E74\u9F84");
		label_2.setBounds(70, 137, 50, 20);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(70, 185, 50, 20);
		lblNewLabel.setText("\u4F4F\u5740");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(70, 268, 56, 20);
		lblNewLabel_1.setText("\u79D1\u5BA4");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(260, 48, 56, 20);
		lblNewLabel_2.setText("\u6027\u522B");
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(260, 93, 60, 20);
		lblNewLabel_3.setText("\u7535\u8BDD\u53F7\u7801");
		
		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setBounds(260, 137, 56, 20);
		lblNewLabel_4.setText("\u6743\u9650");
		
		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setBounds(260, 268, 56, 20);
		lblNewLabel_5.setText("\u72B6\u6001");
		
		Label lblNewLabel_6 = new Label(shell, SWT.NONE);
		lblNewLabel_6.setBounds(136, 48, 76, 20);
		lblNewLabel_6.setText(user.get(0).get("username").toString());
		
		Label lblNewLabel_7 = new Label(shell, SWT.NONE);
		lblNewLabel_7.setBounds(331, 48, 76, 20);
		lblNewLabel_7.setText(user.get(0).get("sexual").toString());
		
		Label lblNewLabel_8 = new Label(shell, SWT.NONE);
		lblNewLabel_8.setBounds(136, 93, 76, 20);
		lblNewLabel_8.setText(user.get(0).get("username").toString());
		
		
		Label lblNewLabel_9 = new Label(shell, SWT.NONE);
		lblNewLabel_9.setBounds(136, 137, 43, 20);
		lblNewLabel_9.setText(user.get(0).get("age").toString());
		
		Label lblNewLabel_10 = new Label(shell, SWT.WRAP);
		lblNewLabel_10.setBounds(136, 185, 180, 39);
		lblNewLabel_10.setText(user.get(0).get("address").toString());
		
		Label lblNewLabel_11 = new Label(shell, SWT.NONE);
		lblNewLabel_11.setBounds(331, 268, 76, 20);
		lblNewLabel_11.setText(user.get(0).get("state_name").toString());
		
		Label lblNewLabel_12 = new Label(shell, SWT.NONE);
		lblNewLabel_12.setBounds(331, 93, 118, 20);
		lblNewLabel_12.setText(user.get(0).get("phone_number").toString());
		
		Label lblNewLabel_13 = new Label(shell, SWT.NONE);
		lblNewLabel_13.setBounds(331, 137, 76, 20);
		lblNewLabel_13.setText(user.get(0).get("root_name").toString());
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Warning_2 warning = new Warning_2(new Shell(), SWT.CLOSE);
				warning.open();
				if(warning.getResult()){
					if(db.queryForList("select * from user where username = '"+currentUser.getUsername()+"'")!=null){
						if(db.update("delete from user where username = '"+currentUser.getUsername()+"'")!=0){
							OpenBox.Open("操作成功");
						}else{
							OpenBox.Open("操作失败");
						}
					}else{
						OpenBox.Open("无该用户");
					}
				}
			}
		});
		btnNewButton.setBounds(143, 339, 98, 30);
		btnNewButton.setText("\u786E\u5B9A\u5220\u9664");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.close();
			}
		});
		btnNewButton_1.setBounds(368, 339, 98, 30);
		btnNewButton_1.setText("\u53D6\u6D88");
		
		Label lblNewLabel_14 = new Label(shell, SWT.NONE);
		lblNewLabel_14.setBounds(260, 229, 50, 20);
		lblNewLabel_14.setText("\u90AE\u7BB1");
		
		Label lblNewLabel_15 = new Label(shell, SWT.NONE);
		lblNewLabel_15.setBounds(331, 229, 168, 20);
		lblNewLabel_15.setText(user.get(0).get("mailbox").toString());
		
		Composite composite = new Composite(shell, SWT.BORDER);
		composite.setBounds(455, 48, 104, 137);
		
		Label lblNewLabel_16 = new Label(shell, SWT.NONE);
		lblNewLabel_16.setBounds(136, 268, 98, 20);
		lblNewLabel_16.setText(user.get(0).get("department_name").toString());
		if(!currentUser.getPhoto().equals(""))
			lblNewLabel_16.setImage(SWTResourceManager.getImage("F:\\workspace\\hpmanagement"+File.separator+currentUser.getPhoto()));
	}

}
