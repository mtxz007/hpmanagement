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

public class DeleteDepartment extends Dialog {

	protected boolean result = false;
	protected Shell shell;
	private Text text;
	private Button button;
	private String department_name;
	private boolean style = false;
	private Label label;
	private Label lblNewLabel_1;

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public boolean isStyle() {
		return style;
	}

	public void setStyle(boolean style) {
		this.style = style;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DeleteDepartment(Shell parent, int style) {
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
		shell.setText("\u5220\u9664");
		
		if(style){
			label = new Label(shell, SWT.NONE);
			label.setBounds(248, 96, 89, 20);
			label.setText(department_name);
			
			Label lblNewLabel = new Label(shell, SWT.NONE);
			lblNewLabel.setBounds(121, 96, 99, 20);
			lblNewLabel.setText("\u786E\u8BA4\u5220\u9664\u79D1\u5BA4\uFF1A");
		}else{
			lblNewLabel_1 = new Label(shell, SWT.NONE);
			lblNewLabel_1.setAlignment(SWT.CENTER);
			lblNewLabel_1.setBounds(132, 59, 159, 20);
			lblNewLabel_1.setText("\u8BF7\u8F93\u5165\u5220\u9664\u79D1\u5BA4\u540D\u79F0");
			
			text = new Text(shell, SWT.BORDER);
			text.setBounds(133, 104, 158, 26);
		}
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DbUtils db = new DbUtils();
				Warning_2 w = new Warning_2(new Shell(), SWT.Close);
				if(style)
					department_name = label.getText();
				else
					department_name = text.getText();
				if(department_name == null || department_name.equals("")){
					OpenBox.Open("不能为空");
				}else if(db.queryForList("select * from department where name = '"+department_name+"'").size()==0){
					OpenBox.Open("不存在该科室");
				}else {
					w.open();
					if(w.getResult()){
						if(db.update("delete from department where name = '"+department_name+"'")!=0)
							OpenBox.Open("操作成功");
						else
							OpenBox.Open("操作失败");
					}
				}
			}
		});
		btnNewButton.setBounds(132, 166, 64, 30);
		btnNewButton.setText("\u786E\u5B9A");
		
		button = new Button(shell, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
				result = false;
			}
		});
		button.setText("\u53D6\u6D88");
		button.setBounds(247, 166, 64, 30);
	}
}
