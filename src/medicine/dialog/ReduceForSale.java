package medicine.dialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
import dao.Medicine;

public class ReduceForSale extends Dialog {

	protected Object result;
	protected Shell shell;
	private int number;
	private Medicine medicine;
	private Text text;
	private ArrayList<HashMap<String,Object>> manufacturer;
	private String department_id = null;
	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ReduceForSale(Shell parent, int style) {
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
		manufacturer  = db.queryForList("select * from manufacturer");
		
		shell = new Shell(getParent(), getStyle());
		shell.setSize(450, 300);
		shell.setText("\u589E\u52A0");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(108, 87, 113, 20);
		lblNewLabel.setText("\u8BF7\u8F93\u5165\u9500\u552E\u6570\u91CF");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(252, 84, 73, 26);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				ArrayList<HashMap<String, Object>> list;
				try{
					//获取当前时间
					SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
					String time = date.format(new Date());
					
					for(HashMap<String, Object> map:manufacturer){
						if(map.get("name").toString().equals(medicine.getMenufacturer()))
							department_id = map.get("id").toString();
					}
					number = Integer.parseInt(text.getText().trim().toString());
					if(medicine.getNumber()==0){
						OpenBox.Open("错误，无库存");
					}else if(medicine.getNumber()<number){
						OpenBox.Open("错误，库存不足");
					}else{
						if(text.getText().trim().equals("")||text.getText()==null)
							OpenBox.Open("请输入数量！");
						else{
							medicine.setNumber(medicine.getNumber()-number);
							if(number<0)
								number = 0;
							if((list = db.queryForList("select * from calculation where id = "+medicine.getId()+"")).size()==0){
								db.update("insert into calculation(id, reducenumber,addnumber) " +
										"values("+medicine.getId()+", 0, 0)");
							}
							list = db.queryForList("select * from calculation where id = "+medicine.getId()+"");
							if(db.update("update calculation set reducenumber = "+(Integer.parseInt(list.get(0).get("reducenumber").toString())+number)+", " +
									"result = '"+(medicine.getPrice_sale()*(Integer.parseInt(list.get(0).get("reducenumber").toString())+number)-medicine.getNumber()*medicine.getPrice_stock())+"'" +
									"where id = "+medicine.getId()+"")!=0
									&& db.update("update medicine_stock set number = "+medicine.getNumber()+" " +
											"where name = '"+medicine.getName()+"' AND id = "+medicine.getId()+"")!=0
									&& db.update("insert into income values("+department_id+","+medicine.getPrice_sale()+",'"+time+"','是','否')")!=0
									){
								OpenBox.Open("操作成功");
								shell.dispose();
							}else{
								OpenBox.Open("操作失败");
								shell.dispose();
							}
						}
					}
				}catch(NumberFormatException e1){
					OpenBox.Open("请输入数字");
				}
			}
		});
		btnNewButton.setBounds(183, 175, 78, 30);
		btnNewButton.setText("\u786E\u5B9A");
	}
}
