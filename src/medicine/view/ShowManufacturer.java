package medicine.view;

import java.util.HashMap;

import medicine.dialog.Warning_2;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import utils.DbUtils;
import utils.GetAll;
import utils.OpenBox;
import utils.Page;

public class ShowManufacturer extends Dialog {

	protected Object result;
	protected Shell shell;
	private Table table;
	private Text text;
	private TableItem tableItem;
	private Button button;
	private Button button_1;
	private Button button_2;
	private Button btnNewButton_1;
	private Page page;
	private int currentPage = 1;
	private String name = "";
	private boolean orderValue = true;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ShowManufacturer(Shell parent, int style) {
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
		shell.setSize(515, 514);
		shell.setText("\u5382\u5546\u4FE1\u606F");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(155, 86, 199, 267);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				orderValue = !orderValue;
				getManufacturer(currentPage, name, orderValue);
			}
		});
		tblclmnNewColumn.setWidth(54);
		tblclmnNewColumn.setText("id");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(139);
		tblclmnNewColumn_1.setText("\u5236\u9020\u5546\u540D\u79F0");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DbUtils db = new DbUtils();
				Warning_2 warning = new Warning_2(new Shell(), SWT.CLOSE);
				warning.setName(table.getItem(table.getSelectionIndex()).getText(1));
				warning.open();
				if(warning.isResult())
					//因为外键约束，先删除medicine_stock表中此厂所有数据再删除manjufacturer中数据
					if(db.update("DELETE FROM medicine_stock WHERE manufacturer = (SELECT id FROM manufacturer WHERE NAME = '"+table.getItem(table.getSelectionIndex()).getText(1)+"' )")!=0 
							&& db.update( "delete from manufacturer where id = "+Integer.parseInt(table.getItem(table.getSelectionIndex()).getText(0))+"")!=0){
						OpenBox.Open("操作成功");
						getManufacturer(currentPage, name, orderValue);
					}else{
						OpenBox.Open("操作失败");
					}
			}
		});
		menuItem.setText("\u5220\u9664");
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.setText("\u4FEE\u6539");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(155, 54, 73, 26);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				name = text.getText().trim();
				currentPage = 1;
				getManufacturer(currentPage, name, orderValue);
			}
		});
		btnNewButton.setBounds(234, 54, 55, 30);
		btnNewButton.setText("\u67E5\u8BE2");
		
		btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				currentPage = page.getPageCount();
				getManufacturer(currentPage, name, orderValue);
			}
		});
		btnNewButton_1.setAlignment(SWT.RIGHT);
		btnNewButton_1.setBounds(315, 359, 39, 30);
		btnNewButton_1.setText("-\u300B");
		
		button = new Button(shell, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				currentPage = 1;
				getManufacturer(currentPage, name, orderValue);
			}
		});
		button.setAlignment(SWT.LEFT);
		button.setText("\u300A-");
		button.setBounds(155, 359, 39, 30);
		
		button_1 = new Button(shell, SWT.NONE);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				currentPage--;
				getManufacturer(currentPage, name, orderValue);
			}
		});
		button_1.setText("<-");
		button_1.setAlignment(SWT.LEFT);
		button_1.setBounds(200, 359, 39, 30);
		
		button_2 = new Button(shell, SWT.NONE);
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				currentPage++;
				getManufacturer(currentPage, name, orderValue);
			}
		});
		button_2.setText("->");
		button_2.setAlignment(SWT.RIGHT);
		button_2.setBounds(270, 359, 39, 30);
		
		getManufacturer(currentPage, name, orderValue);
	}
	
	private void getManufacturer(int currentPage, String name, boolean orderValue){
		table.removeAll();
		page = GetAll.getManufacturer(currentPage, name, orderValue);
		for(HashMap<String, Object> map : page.getList() ){
			tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(0, map.get("id").toString());
			tableItem.setText(1, map.get("name").toString());
		}
		
		if(currentPage == 1){
			button.setVisible(false);
			button_1.setVisible(false);
		}else{
			button.setVisible(true);
			button_1.setVisible(true);
		}
		
		if(currentPage == page.getPageCount()){
			button_2.setVisible(false);
			btnNewButton_1.setVisible(false);
		}else{
			button_2.setVisible(true);
			btnNewButton_1.setVisible(true);
		}
	}
}
