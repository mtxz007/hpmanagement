package medicine.dialog;

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

public class AddMenufacturer extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AddMenufacturer(Shell parent, int style) {
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
		shell.setText("\u6DFB\u52A0");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(148, 116, 133, 26);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(148, 53, 133, 20);
		lblNewLabel.setText("\u8BF7\u8F93\u5165\u5236\u9020\u5382\u540D\u79F0");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DbUtils db = new DbUtils();
				String name = text.getText().trim();
				if(name.equals("")|| name==null){
					OpenBox.Open("不能为空");
				}else{
					if(db.queryForList("select * from manufacturer where name = '"+name+"'").size()!=0){
						OpenBox.Open("制造厂已存在");
					}else if(db.update("insert into manufacturer(name) values('"+name+"')")!=0){
						OpenBox.Open("操作成功");
						shell.dispose();
					}else
						OpenBox.Open("操作失败");
				}
			}
		});
		btnNewButton.setBounds(166, 182, 93, 30);
		btnNewButton.setText("\u786E\u5B9A");

	}
}
