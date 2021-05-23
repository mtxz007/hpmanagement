package dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import utils.DbUtils;
import utils.OpenBox;
import dao.User;

public class Tip extends Dialog {

	protected Object result;
	protected Shell shell;
	private User newUser;
	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}
	
	private boolean simple;

	private Text text;
	private Text text_1;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Tip(Shell parent, int style) {
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
		shell.setText("\u5BC6\u7801");
		
		Label lblNewLabel = new Label(shell, SWT.WRAP | SWT.CENTER);
		lblNewLabel.setBounds(78, 42, 314, 44);
		lblNewLabel.setText("\u5BC6\u7801\u9ED8\u8BA4\u4E3A123456\uFF0C\u8BF7\u66F4\u6539\u60A8\u7684\u5BC6\u7801\u3002\u82E5\u4E0D\u9700\u8981\uFF0C\u8BF7\u6309\u7EE7\u7EED");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(199, 105, 157, 26);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(94, 108, 80, 20);
		lblNewLabel_1.setText("\u8BF7\u8F93\u5165\u5BC6\u7801");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DbUtils db = new DbUtils();
				if(text.getText().equals("") || text_1.getText().equals("") || !text.getText().equals(text_1.getText())){
					OpenBox.Open("没有输入或两次密码不一致");
				}else{
					if(db.update("update user set password = '"+text.getText()+"' where username = '"+newUser.getUsername()+"'")!=0){
						OpenBox.Open("操作成功");
						shell.dispose();
					}else{
						OpenBox.Open("操作失败");
					}
				}
			}
		});
		btnNewButton.setBounds(128, 204, 69, 30);
		btnNewButton.setText("\u786E\u5B9A");
		
		Button button = new Button(shell, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		button.setText("\u7EE7\u7EED");
		button.setBounds(259, 204, 69, 30);
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("\u518D\u6B21\u8F93\u5165\u5BC6\u7801");
		label.setBounds(94, 152, 99, 20);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(199, 149, 157, 26);

	}
}
