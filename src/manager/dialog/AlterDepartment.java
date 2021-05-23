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

public class AlterDepartment extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private String department_newName = "";
	private String department_oldName = "";
	
	public String getDepartment_newName() {
		return department_newName;
	}

	public void setDepartment_newName(String department_newName) {
		this.department_newName = department_newName;
	}

	public String getDepartment_oldName() {
		return department_oldName;
	}

	public void setDepartment_oldName(String department_oldName) {
		this.department_oldName = department_oldName;
	}

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AlterDepartment(Shell parent, int style) {
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
		shell.setText("\u4FEE\u6539");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(205, 76, 90, 26);
		text.setText(getDepartment_oldName());
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(139, 79, 60, 20);
		lblNewLabel.setText("\u8BF7\u4FEE\u6539\uFF1A");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DbUtils db = new DbUtils();
				department_newName = text.getText().trim();
				System.out.print(department_oldName);
				if(department_newName == null || department_newName.equals("")){
					OpenBox.Open("不能为空！");
				}else{
					if(db.queryForList("select * from department where name = '"+department_newName+"'").size()!=0){
						OpenBox.Open("该科室已存在");
					}else{
						if(db.update("update department set name = '"+department_newName+"' where name = '"+department_oldName+"'")!=0){
							OpenBox.Open("操作成功");
							shell.dispose();
						}else
							OpenBox.Open("操作失败");
					}
				}
			}
		});
		btnNewButton.setBounds(113, 154, 77, 30);
		btnNewButton.setText("\u786E\u5B9A");
		
		Button button = new Button(shell, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		
		button.setText("\u53D6\u6D88");
		button.setBounds(269, 154, 77, 30);

	}
}
