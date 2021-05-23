package doctor.dialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

import utils.DbUtils;
import dao.Register;

public class Details extends Dialog {

	protected Object result;
	protected Shell shell;
	private Register register;

	public Register getRegister() {
		return register;
	}

	public void setRegister(Register register) {
		this.register = register;
	}

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Details(Shell parent, int style) {
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
		shell.setSize(506, 436);
		shell.setText("\u8BE6\u60C5");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(77, 39, 37, 20);
		label.setText("\u59D3\u540D");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText(register.getName());
		label_1.setBounds(132, 39, 56, 20);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u7C7B\u578B");
		label_2.setBounds(77, 81, 37, 20);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText(register.getKinds());
		label_3.setBounds(132, 81, 37, 20);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(77, 125, 37, 20);
		lblNewLabel.setText("\u72B6\u6001");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(132, 125, 56, 20);
		lblNewLabel_1.setText(register.getState());
		
		Label lblNewLabel_2 = new Label(shell, SWT.BORDER | SWT.WRAP);
		lblNewLabel_2.setBounds(77, 195, 342, 91);
		if(register.getResult().equals("") || register.getResult() == null){
			lblNewLabel_2.setText("\u65E0");
		}else{
			File file = new File("F:\\workspace\\hpmanagement\\register_result\\"+register.getName()+".txt");
			FileReader in;
			try {
				in = new FileReader(file);
				SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
				String time = date.format(new Date());
				BufferedReader reader = new BufferedReader(in);
				String str = "";
				String context = "";
				while((str=reader.readLine())!=null)
					if(str.startsWith("诊断时间"))
						if(str.split("：")[1].equals(time)){
								context += str+"\n";
								for(int i=1; i<=2; i++){
									str=reader.readLine();
									context += str+"\n";
								}
						}
				lblNewLabel_2.setText(context);
				reader.close();
				in.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setBounds(77, 169, 76, 20);
		label_4.setText("\u8BCA\u65AD\u7ED3\u679C\uFF1A");
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setBounds(355, 39, 64, 20);
		label_5.setText(register.getOperator());
		
		Label label_7 = new Label(shell, SWT.NONE);
		label_7.setText(""+register.getPrice());
		label_7.setBounds(355, 125, 64, 20);
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(284, 39, 50, 20);
		lblNewLabel_3.setText("\u7ECF\u529E\u4EBA");
		
		Label label_9 = new Label(shell, SWT.NONE);
		label_9.setText("\u4EF7\u683C");
		label_9.setBounds(284, 125, 50, 20);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DbUtils db = new DbUtils();
				if(register.getState().equals("诊断完成")){
					shell.dispose();
				}else{
					db.update("update register set state = 2 where name = '"+register.getName()+"'");
					shell.dispose();
				}
			}
		});
		btnNewButton.setBounds(115, 318, 76, 30);
		btnNewButton.setText("\u786E\u5B9A");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DbUtils db = new DbUtils();
				Writing_Result writing = new Writing_Result(new Shell(), SWT.CLOSE);
				writing.setCurrentRegister(register);
				writing.open();
				db.update("delete from orders where patient_id = "+register.getPatient_id()+"");
			}
		});
		btnNewButton_1.setBounds(283, 318, 76, 30);
		btnNewButton_1.setText("\u8BCA\u65AD\u7ED3\u679C");
		
		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setBounds(209, 292, 76, 20);
		lblNewLabel_4.setText("\u6302\u53F7\u65F6\u95F4\uFF1A");
		
		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setBounds(283, 292, 104, 20);
		lblNewLabel_5.setText(register.getTime());
		
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setBounds(283, 81, 50, 20);
		label_6.setText("\u79D1\u5BA4");
		
		Label lblNewLabel_6 = new Label(shell, SWT.NONE);
		lblNewLabel_6.setBounds(355, 81, 76, 20);
		lblNewLabel_6.setText(register.getDepartment());
	}

}
