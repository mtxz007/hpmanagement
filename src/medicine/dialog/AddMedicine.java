package medicine.dialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import utils.DbUtils;
import utils.OpenBox;
import dao.Medicine;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.DateTime;

public class AddMedicine extends Dialog {

	protected Object result;
	protected Shell shell;
	private String batch_number;
	private Medicine medicine = new Medicine();
	private ArrayList<HashMap<String, Object>> manufacturer;
	private ArrayList<HashMap<String, Object>> medicine_kinds;
	private int kind_id;
	private int manufacturer_id;
	private Text text;
	private Text text_2;
	private Text text_1;
	private Text text_3;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AddMedicine(Shell parent, int style) {
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
		manufacturer = db.queryForList("select * from manufacturer");
		medicine_kinds = db.queryForList("select * from medicine_kinds");
		shell = new Shell(getParent(), getStyle());
		shell.setSize(583, 450);
		shell.setText("\u6DFB\u52A0");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(52, 55, 45, 20);
		lblNewLabel.setText("\u540D\u79F0");
		
		final Label lblNewLabel_6 = new Label(shell, SWT.NONE);
		lblNewLabel_6.setForeground(SWTResourceManager.getColor(255, 0, 0));
		lblNewLabel_6.setBounds(127, 77, 76, 20);
		lblNewLabel_6.setText("*\u4E0D\u80FD\u4E3A\u7A7A");
		lblNewLabel_6.setVisible(false);
		
		final Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("*\u4E0D\u80FD\u4E3A\u7A7A");
		label_2.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_2.setBounds(206, 103, 76, 20);
		label_2.setVisible(false);
		
		final Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("*\u4E0D\u80FD\u4E3A\u7A7A");
		label_3.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_3.setBounds(206, 159, 76, 20);
		label_3.setVisible(false);
		
		final Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("*\u4E0D\u80FD\u4E3A\u7A7A");
		label_4.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_4.setBounds(206, 208, 76, 20);
		label_4.setVisible(false);
		
		final Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("*\u4E0D\u80FD\u4E3A\u7A7A");
		label_5.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_5.setBounds(380, 179, 76, 20);
		label_5.setVisible(false);
		
		final Label label_6 = new Label(shell, SWT.NONE);
		label_6.setText("*\u4E0D\u80FD\u4E3A\u7A7A");
		label_6.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_6.setBounds(380, 130, 76, 20);
		label_6.setVisible(false);
		
		final Label label_7 = new Label(shell, SWT.NONE);
		label_7.setText("*\u4E0D\u80FD\u4E3A\u7A7A");
		label_7.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_7.setBounds(225, 267, 76, 20);
		label_7.setVisible(false);
		
		final Label label_8 = new Label(shell, SWT.NONE);
		label_8.setText("*\u4E0D\u80FD\u4E3A\u7A7A");
		label_8.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_8.setBounds(380, 239, 76, 20);
		label_8.setVisible(false);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(293, 208, 45, 20);
		lblNewLabel_1.setText("\u5236\u836F\u5382");
		
		final Combo combo = new Combo(shell, SWT.READ_ONLY);
		for(HashMap<String, Object> map : manufacturer){
			combo.add(map.get("name").toString());
		}
		combo.select(0);
		combo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				label_8.setVisible(false);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(combo.getText().equals("")||combo.getText()==null)
					label_8.setVisible(true);
			}
		});
		combo.setBounds(380, 205, 102, 57);
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(52, 103, 45, 20);
		lblNewLabel_2.setText("\u6570\u91CF");
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(52, 159, 45, 20);
		lblNewLabel_3.setText("\u8FDB\u4EF7");
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("\u9500\u552E\u4EF7");
		label.setBounds(52, 205, 45, 20);
		
		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setText("\u836F\u54C1\u79CD\u7C7B");
		lblNewLabel_4.setBounds(52, 267, 65, 20);
		
		final Combo combo_1 = new Combo(shell, SWT.READ_ONLY);
		for(HashMap<String, Object> map : medicine_kinds){
			combo_1.add(map.get("NAME").toString());
		}
		combo_1.select(0);
		combo_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				label_7.setVisible(false);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(combo_1.getText().equals("")||combo_1.getText()==null)
					label_7.setVisible(true);
			}
		});
		combo_1.setBounds(127, 264, 92, 13);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				label_3.setVisible(false);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(text_2.getText().trim().equals("")||text_2.getText().trim()==null)
					label_3.setVisible(true);
			}
		});
		text_2.setBounds(127, 159, 73, 26);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				label_4.setVisible(false);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(text_1.getText().trim().equals("")||text_1.getText().trim()==null)
					label_4.setVisible(true);
			}
		});
		text_1.setBounds(127, 207, 73, 26);
		
		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setBounds(293, 103, 65, 20);
		lblNewLabel_5.setText("\u751F\u4EA7\u65E5\u671F");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u8FC7\u671F\u65E5\u671F");
		label_1.setBounds(293, 159, 65, 20);
		
		text = new Text(shell, SWT.BORDER);
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lblNewLabel_6.setVisible(false);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(text.getText().equals("")||text.getText()==null)
					lblNewLabel_6.setVisible(true);
			}
		});
		text.setBounds(127, 52, 199, 26);
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				label_2.setVisible(false);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(text_3.getText().equals("")||text_3==null)
					label_2.setVisible(true);
			}
		});
		text_3.setBounds(127, 103, 73, 26);
		
		final DateTime dateTime = new DateTime(shell, SWT.BORDER);
		dateTime.setBounds(380, 96, 116, 28);
		
		
		final DateTime dateTime_1 = new DateTime(shell, SWT.BORDER);
		dateTime_1.setBounds(380, 159, 116, 28);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				medicine.setName(text.getText().trim());
				medicine.setBatch_number(batch_number);
				medicine.setPrice_stock(Double.parseDouble(text_2.getText().trim()));
				medicine.setPrice_sale(Double.parseDouble(text_1.getText().trim()));
				medicine.setPd(dateTime.getYear()+"-"+dateTime.getMonth()+"-"+dateTime.getDay());
				medicine.setExp(dateTime_1.getYear()+"-"+dateTime_1.getMonth()+"-"+dateTime_1.getDay());
				medicine.setMenufacturer(combo.getText());
				medicine.setKind(combo_1.getText());
				for(HashMap<String, Object> map : medicine_kinds){
					if(map.get("NAME").equals(medicine.getKind())){
						kind_id = Integer.parseInt(map.get("id").toString());
					}
				}
				for(HashMap<String, Object> map : manufacturer){
					if(map.get("name").equals(medicine.getMenufacturer())){
						manufacturer_id = Integer.parseInt(map.get("id").toString());
					}
				}
				if(db.updatePre("INSERT INTO medicine_stock(NAME,manufacturer,pd,EXP,number,batch_number,price_stock,price_sale,kind)"
						+" VALUES (?,?,?,?,?,?,?,?,?) ", medicine.getName(), manufacturer_id, medicine.getPd(), medicine.getExp(), medicine.getNumber(), medicine.getBatch_number(), medicine.getPrice_stock(), medicine.getPrice_sale(), kind_id)!=0){
					OpenBox.Open("操作成功");shell.dispose();
				}else{
					OpenBox.Open("操作失败");
					shell.dispose();
				}
			}
		});
		btnNewButton.setBounds(240, 311, 98, 30);
		btnNewButton.setText("\u6DFB\u52A0");
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		batch_number = date.format(new Date()).split("-")[0]+date.format(new Date()).split("-")[1]+date.format(new Date()).split("-")[2];
		
	}
}
