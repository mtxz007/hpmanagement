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

public class getManufacturerName extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private String name;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public getManufacturerName(Shell parent, int style) {
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
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(125, 52, 198, 20);
		lblNewLabel.setText("\u8BF7\u8F93\u5165\u5220\u9664\u5236\u9020\u5382\u540D\u79F0");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(171, 110, 103, 26);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DbUtils db = new DbUtils();
				name = text.getText().trim();
				if(name.equals("")||name == null){
					OpenBox.Open("不能为空！");
				}else{
					Warning_2 warning = new Warning_2(new Shell(), SWT.CLOSE);
					warning.setName(name);
					warning.open();
					if(warning.isResult()){
						if(db.queryForList("select * from manufacturer where name = '"+name+"'").size()==0){
							OpenBox.Open("制造厂不存在！");
						}else if(db.update("DELETE FROM medicine_stock WHERE manufacturer = (SELECT id FROM manufacturer WHERE NAME = '"+name+"' )")!=0 
								&& db.update( "delete from manufacturer where name = '"+name+"'")!=0){
							OpenBox.Open("操作成功");
						}else{
							OpenBox.Open("操作失败");
						}
					}
				}
			}
		});
		btnNewButton.setBounds(191, 171, 67, 30);
		btnNewButton.setText("\u786E\u5B9A");
	}
}
