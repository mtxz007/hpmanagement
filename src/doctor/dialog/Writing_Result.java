package doctor.dialog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import dao.Register;

public class Writing_Result extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	public Register getCurrentRegister() {
		return currentRegister;
	}

	public void setCurrentRegister(Register currentRegister) {
		this.currentRegister = currentRegister;
	}

	private Register currentRegister;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Writing_Result(Shell parent, int style) {
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
		shell.setSize(502, 364);
		shell.setText("\u8BCA\u65AD\u7ED3\u679C");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(98, 132, 299, 66);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DbUtils db = new DbUtils();
				try {
					File file = new File("F:\\workspace\\hpmanagement\\register_result\\"+currentRegister.getName()+".txt");
					if(!file.exists()){
						file.createNewFile();
					}
					Writer writer = new FileWriter(file);
					BufferedWriter bufferedWriter = new BufferedWriter(writer);
					SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
					String time = date.format(new Date());
					String medicine = "开药："+text_1.getText();
					String other = "备注："+text.getText();
					bufferedWriter.write("诊断时间："+time);
					bufferedWriter.newLine();
					bufferedWriter.write(medicine);
					bufferedWriter.newLine();
					bufferedWriter.write(other);
					bufferedWriter.newLine();
					bufferedWriter.close();
					writer.close();
					if(db.update("update register set result = '"+currentRegister.getName()+".txt' where name = '"+currentRegister.getName()+"'")!=0 &&
							db.update("update register set state = 3 where name = '"+currentRegister.getName()+"'")!=0){
						currentRegister.setState("诊断完成");
						OpenBox.Open("操作成功");
					}else
						OpenBox.Open("操作失败");
					
					shell.dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(145, 239, 70, 30);
		btnNewButton.setText("\u4FDD\u5B58");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		btnNewButton_1.setBounds(302, 239, 70, 30);
		btnNewButton_1.setText("\u53D6\u6D88");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(98, 33, 45, 20);
		lblNewLabel.setText("\u5F00\u836F\uFF1A");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(98, 94, 45, 20);
		lblNewLabel_1.setText("\u5907\u6CE8\uFF1A");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(145, 33, 252, 26);

	}
}
