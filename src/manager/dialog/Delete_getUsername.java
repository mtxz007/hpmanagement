package manager.dialog;

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

public class Delete_getUsername extends Dialog {

	protected Object result;
	protected Shell shell;
	private String username;
	private Text text;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Delete_getUsername(Shell parent, int style) {
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
		shell.setText("\u63D0\u793A");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(115, 66, 204, 20);
		lblNewLabel.setText("\u8BF7\u8F93\u5165\u60A8\u8981\u5220\u9664\u7528\u6237\u7684\u7528\u6237\u540D");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(163, 117, 120, 26);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(text.getText()==null||text.getText().equals("")){
					OpenBox.Open("请输入用户名");
				}else{
					username = text.getText().trim();
					DbUtils db = new DbUtils();
					if(db.queryForList("select * from user where username = '"+username+"'").size()!=0){
						Details_forDelete detail = new Details_forDelete(new Shell(),SWT.CLOSE);
						detail.setUsername(username);
						shell.dispose();
						detail.open();
					}else{
						OpenBox.Open("查无此人");
						shell.dispose();
					}
				}
			}
		});
		btnNewButton.setBounds(173, 177, 98, 30);
		btnNewButton.setText("\u786E\u5B9A");

	}

}
