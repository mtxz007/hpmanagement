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

public class AddDepartment extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private String department_name;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AddDepartment(Shell parent, int style) {
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
		shell.setText(getText());
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(137, 96, 150, 26);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(152, 29, 150, 20);
		lblNewLabel.setText("\u8BF7\u8F93\u5165\u79D1\u5BA4\u540D\u79F0");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DbUtils db = new DbUtils();
				department_name = text.getText();
				if(department_name == null || department_name.equals("")){
					OpenBox.Open("不能为空！");
				}else{
					if(db.queryForList("select * from department where name = '"+department_name+"'").size()!=0){
						OpenBox.Open("科室已存在");
					}else{
						if(db.update("insert into department(name) values('"+department_name+"')")!=0)
							OpenBox.Open("操作成功");
						else
							OpenBox.Open("操作失败");
					}
				}
				shell.dispose();
			}
		});
		btnNewButton.setBounds(172, 159, 79, 30);
		btnNewButton.setText("\u786E\u5B9A");

	}
}
