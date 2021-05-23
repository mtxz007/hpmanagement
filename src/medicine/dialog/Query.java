package medicine.dialog;

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import utils.DbUtils;

public class Query extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private String kinds;
	private String manufacturer;
	private String state_name;
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getKinds() {
		return kinds;
	}

	public void setKinds(String kinds) {
		this.kinds = kinds;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	private String query;
	private Combo combo;
	private Combo combo_1;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Query(Shell parent, int style) {
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
		shell.setText("\u67E5\u8BE2");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(205, 110, 173, 26);
		
		combo = new Combo(shell, SWT.READ_ONLY);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				display(combo.getText());
			}
		});
		combo.setBounds(95, 110, 92, 28);
		combo.add("名称", 0);
		combo.add("制药厂");
		combo.add("药品种类");
		combo.add("批次号");
		combo.select(0);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				kinds = combo.getText();
				if(kinds.equals("名称")){
					query = text.getText(); 
				}else{
					query = combo_1.getText();
				}
				shell.dispose();
			}
		});
		btnNewButton.setBounds(120, 180, 79, 30);
		btnNewButton.setText("\u67E5\u8BE2");
		
		Label lblNewLabel = new Label(shell, SWT.CENTER);
		lblNewLabel.setBounds(108, 51, 233, 20);
		lblNewLabel.setText("\u8BF7\u8F93\u5165\u67E5\u8BE2\u5185\u5BB9\u3002\u53EF\u9009\u62E9\u67E5\u8BE2\u9879");
		
		combo_1 = new Combo(shell, SWT.READ_ONLY);
		
		Button button = new Button(shell, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		button.setText("\u53D6\u6D88");
		button.setBounds(235, 180, 79, 30);
		
		display(combo.getText());
	}
	
	public void display(String str){
		DbUtils db = new DbUtils();
		if(str.equals("名称")||str.equals("批次号")){
			if(combo_1 != null){
				combo_1.setVisible(false);
			}
			
			text.setVisible(true);
		}else if(str.equals("制药厂")){
			if(text != null){
				text.setVisible(false);
			}
			combo_1.removeAll();
			combo_1.setBounds(249, 110, 92, 28);
			combo_1.setVisible(true);
			for(HashMap map : db.queryForList("SELECT * FROM manufacturer")){
				combo_1.add(map.get("name").toString());
			}
		}else if(str.equals("药品种类")){
			if(text != null){
				text.setVisible(false);
			}
			combo_1.removeAll();
			combo_1.setBounds(249, 110, 92, 28);
			combo_1.setVisible(true);
			for(HashMap map : db.queryForList("SELECT * FROM medicine_kinds")){
				combo_1.add(map.get("NAME").toString());
			}
		}
	}
}
