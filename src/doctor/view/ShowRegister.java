package doctor.view;

import java.util.HashMap;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;

import utils.DbUtils;
import utils.GetAll;
import utils.OpenBox;
import utils.Page;
import dao.Register;
import doctor.dialog.Details;
import doctor.dialog.Warning_1;

public class ShowRegister extends ViewPart {

	public static final String ID = "doctor.view.ShowRegister"; //$NON-NLS-1$
	private Table table;
	private Table table_1;
	private Table table_2;
	private int currentPage = 1;
	private String name = "%%";
	private Page page = new Page();;
	private TableItem tableItem;
	private Text text;
	private Button button_1;
	private Button button_2;
	private Button btnNewButton;
	private Button button;
	private Label label_1;
	private Text text_1;
	private Register register;
	private TabItem tabItem;
	private TabItem tabItem_1;
	private TabItem tabItem_2;
	private TabFolder tabFolder;

	public ShowRegister() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				currentPage = 1;
				getRegister(currentPage, name, tabFolder.getSelectionIndex());
			}
		});
		btnNewButton.setBounds(286, 513, 75, 30);
		btnNewButton.setText("\u9996\u9875");
		
		button = new Button(container, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				currentPage--;
				getRegister(currentPage, name, tabFolder.getSelectionIndex());
			}
		});
		button.setText("\u4E0A\u4E00\u9875");
		button.setBounds(367, 513, 75, 30);
		
		button_1 = new Button(container, SWT.NONE);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				currentPage = page.getPageCount();
				getRegister(currentPage, name, tabFolder.getSelectionIndex());
			}
		});
		button_1.setText("\u5C3E\u9875");
		button_1.setBounds(652, 513, 75, 30);
		
		button_2 = new Button(container, SWT.NONE);
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				currentPage++;
				getRegister(currentPage, name, tabFolder.getSelectionIndex());
			}
		});
		button_2.setText("\u4E0B\u4E00\u9875");
		button_2.setBounds(571, 513, 75, 30);
		
		text = new Text(container, SWT.BORDER);
		text.setText(""+currentPage);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text.setBounds(445, 515, 32, 26);
		
		Label label = new Label(container, SWT.NONE);
		label.setText("/");
		label.setBounds(483, 518, 6, 20);
		
		label_1 = new Label(container, SWT.NONE);
		label_1.setText("2");
		label_1.setBounds(495, 518, 22, 20);
		
		Button button_3 = new Button(container, SWT.NONE);
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				int want = Integer.parseInt(text.getText());
				if(want<1){
					want = 1;
				}else if(want>page.getCurrentPage()){
					want = page.getPageCount();
				}
				
				currentPage = want;
				getRegister(currentPage, name, tabFolder.getSelectionIndex());
			}
		});
		button_3.setText("->");
		button_3.setBounds(527, 513, 32, 30);
		
		text_1 = new Text(container, SWT.BORDER);
		text_1.setBounds(286, 134, 73, 26);
		
		Button btnNewButton_1 = new Button(container, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				name = text_1.getText();
				currentPage = 1;
				getRegister(currentPage, name, tabFolder.getSelectionIndex());
			}
		});
		btnNewButton_1.setBounds(367, 134, 55, 30);
		btnNewButton_1.setText("\u67E5\u627E");
		
		tabFolder = new TabFolder(container, SWT.NONE);
		tabFolder.setBounds(281, 184, 456, 323);
		
		tabItem_2 = new TabItem(tabFolder, SWT.NONE);
		tabItem_2.setText("\u672A\u8BCA\u65AD");
		
		table_2 = new Table(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				DbUtils db = new DbUtils();
				Details detail = new Details(new Shell(), SWT.CLOSE);
				for(HashMap<String, Object> map:page.getList()){
					if(map.get("name").equals(table_2.getItem(table_2.getSelectionIndex()).getText(0))){
						register = new Register();
						register.setPatient_id(Integer.parseInt(map.get("patient_id").toString()));
						register.setName(map.get("name").toString());
						register.setDepartment(map.get("department_name").toString());
						register.setKinds(map.get("kinds_name").toString());
						register.setOperator(map.get("operator").toString());
						register.setTime(map.get("time").toString());
						register.setPrice(Double.parseDouble(map.get("price").toString()));
						register.setResult((map.get("result").toString().equals("") ? "":map.get("result").toString()));
						register.setState(map.get("state_name").toString());
					}
				}
				db.update("update register set state = 2 where name = '"+register.getName()+"'");
				register.setState("诊断中");
				detail.setRegister(register);
				detail.open();
				currentPage = 1;
				getRegister(currentPage, name, tabFolder.getSelectionIndex());
			}
		});
		table_2.setLinesVisible(true);
		table_2.setHeaderVisible(true);
		tabItem_2.setControl(table_2);
		
		TableColumn tableColumn_4 = new TableColumn(table_2, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("\u59D3\u540D");
		
		TableColumn tableColumn_5 = new TableColumn(table_2, SWT.NONE);
		tableColumn_5.setWidth(115);
		tableColumn_5.setText("\u7ECF\u529E\u4EBA");
		
		TableColumn tableColumn_6 = new TableColumn(table_2, SWT.NONE);
		tableColumn_6.setWidth(118);
		tableColumn_6.setText("\u7C7B\u578B");
		
		TableColumn tableColumn_7 = new TableColumn(table_2, SWT.NONE);
		tableColumn_7.setWidth(108);
		tableColumn_7.setText("\u4EF7\u683C");
		
		Menu menu_2 = new Menu(table_2);
		table_2.setMenu(menu_2);
		
		MenuItem menuItem_4 = new MenuItem(menu_2, SWT.NONE);
		menuItem_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DbUtils db = new DbUtils();
				Details detail = new Details(new Shell(), SWT.NONE);
				for(HashMap<String, Object> map:page.getList()){
					if(map.get("name").equals(table_2.getItem(table_2.getSelectionIndex()).getText(0))){
						register = new Register();
						register.setPatient_id(Integer.parseInt(map.get("patient_id").toString()));
						register.setName(map.get("name").toString());
						register.setDepartment(map.get("department_name").toString());
						register.setKinds(map.get("kinds_name").toString());
						register.setOperator(map.get("operator").toString());
						register.setTime(map.get("time").toString());
						register.setPrice(Double.parseDouble(map.get("price").toString()));
						register.setResult((map.get("result").toString().equals("") ? "无":map.get("result").toString()));
						register.setState(map.get("state_name").toString());
					}
				}
				detail.setRegister(register);
				detail.open();
			}
		});
		menuItem_4.setText("\u6253\u5F00");
		
		MenuItem menuItem_5 = new MenuItem(menu_2, SWT.NONE);
		menuItem_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DbUtils db = new DbUtils();
				name = table_2.getItem(table_2.getSelectionIndex()).getText(0);
				Warning_1 warning = new Warning_1(new Shell(), SWT.NONE);
				warning.open();
				if(warning.isResult())
					if(db.update("delete from register where name = '"+name+"'")!=0){
						OpenBox.Open("操作成功");
						currentPage = 1;
						getRegister(currentPage, name, tabFolder.getSelectionIndex());
					}
					else
						OpenBox.Open("操作失败");
				currentPage = 1;
				getRegister(currentPage, name, tabFolder.getSelectionIndex());
			}
		});
		menuItem_5.setText("\u5220\u9664");
		
		tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("\u8BCA\u65AD\u4E2D");
		
		table_1 = new Table(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				DbUtils db = new DbUtils();
				Details detail = new Details(new Shell(), SWT.CLOSE);
				for(HashMap<String, Object> map:page.getList()){
					if(map.get("name").equals(table_1.getItem(table_1.getSelectionIndex()).getText(0))){
						register = new Register();
						register.setPatient_id(Integer.parseInt(map.get("patient_id").toString()));
						register.setName(map.get("name").toString());
						register.setDepartment(map.get("department_name").toString());
						register.setKinds(map.get("kinds_name").toString());
						register.setOperator(map.get("operator").toString());
						register.setTime(map.get("time").toString());
						register.setPrice(Double.parseDouble(map.get("price").toString()));
						register.setResult((map.get("result").toString().equals("") ? "":map.get("result").toString()));
						register.setState(map.get("state_name").toString());
					}
				}
				db.update("update register set state = 2 where name = '"+register.getName()+"'");
				detail.setRegister(register);
				detail.open();
				currentPage = 1;
				getRegister(currentPage, name, tabFolder.getSelectionIndex());
			}
		});
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		tabItem_1.setControl(table_1);
		
		TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("\u59D3\u540D");
		
		TableColumn tableColumn_1 = new TableColumn(table_1, SWT.NONE);
		tableColumn_1.setWidth(115);
		tableColumn_1.setText("\u7ECF\u529E\u4EBA");
		
		TableColumn tableColumn_2 = new TableColumn(table_1, SWT.NONE);
		tableColumn_2.setWidth(118);
		tableColumn_2.setText("\u7C7B\u578B");
		
		TableColumn tableColumn_3 = new TableColumn(table_1, SWT.NONE);
		tableColumn_3.setWidth(108);
		tableColumn_3.setText("\u4EF7\u683C");
		
		Menu menu_1 = new Menu(table_1);
		table_1.setMenu(menu_1);
		
		MenuItem menuItem_2 = new MenuItem(menu_1, SWT.NONE);
		menuItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Details detail = new Details(new Shell(), SWT.CLOSE);
				for(HashMap<String, Object> map:page.getList()){
					if(map.get("name").equals(table_1.getItem(table_1.getSelectionIndex()).getText(0))){
						register = new Register();
						register.setPatient_id(Integer.parseInt(map.get("patient_id").toString()));
						register.setName(map.get("name").toString());
						register.setDepartment(map.get("department_name").toString());
						register.setKinds(map.get("kinds_name").toString());
						register.setOperator(map.get("operator").toString());
						register.setTime(map.get("time").toString());
						register.setPrice(Double.parseDouble(map.get("price").toString()));
						register.setResult((map.get("result").toString().equals("") ? "无":map.get("result").toString()));
						register.setState(map.get("state_name").toString());
					}
				}
				detail.setRegister(register);
				detail.open();
				getRegister(currentPage, name, tabFolder.getSelectionIndex());
			}
		});
		menuItem_2.setText("\u6253\u5F00");
		
		MenuItem menuItem_3 = new MenuItem(menu_1, SWT.NONE);
		menuItem_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DbUtils db = new DbUtils();
				name = table_1.getItem(table_1.getSelectionIndex()).getText(0);
				Warning_1 warning = new Warning_1(new Shell(), SWT.CLOSE);
				warning.open();
				if(warning.isResult())
					if(db.update("delete from register where name = '"+name+"'")!=0){
						OpenBox.Open("操作成功");
						currentPage = 1;
						getRegister(currentPage, name, tabFolder.getSelectionIndex());
					}
					else
						OpenBox.Open("操作失败");
			}
		});
		menuItem_3.setText("\u5220\u9664");
		
		tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("\u5DF2\u8BCA\u65AD");
		
		table = new Table(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		tabItem.setControl(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				DbUtils db = new DbUtils();
				Details detail = new Details(new Shell(), SWT.NONE);
				for(HashMap<String, Object> map:page.getList()){
					if(map.get("name").equals(table.getItem(table.getSelectionIndex()).getText(0))){
						register = new Register();
						register.setPatient_id(Integer.parseInt(map.get("patient_id").toString()));
						register.setName(map.get("name").toString());
						register.setDepartment(map.get("department_name").toString());
						register.setKinds(map.get("kinds_name").toString());
						register.setOperator(map.get("operator").toString());
						register.setTime(map.get("time").toString());
						register.setPrice(Double.parseDouble(map.get("price").toString()));
						register.setResult((map.get("result").toString().equals("") ? "":map.get("result").toString()));
						register.setState(map.get("state_name").toString());
					}
				}
				detail.setRegister(register);
				detail.open();
				currentPage = 1;
				getRegister(currentPage, name, tabFolder.getSelectionIndex());
			}
		});
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("\u59D3\u540D");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(115);
		tblclmnNewColumn_1.setText("\u7ECF\u529E\u4EBA");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setText("\u7C7B\u578B");
		tblclmnNewColumn_2.setWidth(118);
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(108);
		tblclmnNewColumn_3.setText("\u4EF7\u683C");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Details detail = new Details(new Shell(), SWT.NONE);
				for(HashMap<String, Object> map:page.getList()){
					if(map.get("name").equals(table.getItem(table.getSelectionIndex()).getText(0))){
						register = new Register();
						register.setPatient_id(Integer.parseInt(map.get("patient_id").toString()));
						register.setName(map.get("name").toString());
						register.setDepartment(map.get("department_name").toString());
						register.setKinds(map.get("kinds_name").toString());
						register.setOperator(map.get("operator").toString());
						register.setTime(map.get("time").toString());
						register.setPrice(Double.parseDouble(map.get("price").toString()));
						register.setResult((map.get("result").toString().equals("") ? "无":map.get("result").toString()));
						register.setState(map.get("state_name").toString());
					}
				}
				detail.setRegister(register);
				detail.open();
			}
		});
		menuItem.setText("\u6253\u5F00");
		
		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DbUtils db = new DbUtils();
				name = table.getItem(table.getSelectionIndex()).getText(0);
				Warning_1 warning = new Warning_1(new Shell(), SWT.NONE);
				warning.open();
				if(warning.isResult())
					if(db.update("delete from register where name = '"+name+"'")!=0){
						OpenBox.Open("操作成功");
						currentPage = 1;
						getRegister(currentPage, name, tabFolder.getSelectionIndex());
					}
					else
						OpenBox.Open("操作失败");
			}
		});
		menuItem_1.setText("\u5220\u9664");
		
		getRegister(currentPage, name, tabFolder.getSelectionIndex());

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
	//显示挂号信息，根据选用table不同显示不同数据
	void getRegister(int currentPage, String name, int state_id){
		String state = "挂号";
		if(state_id==0){
			state = "挂号";
		}else if(state_id==1){
			state = "诊断中";
		}else if(state_id==2){
			state = "诊断完成";
		}
		page = GetAll.getRegister(currentPage, name);
		table.removeAll();
		table_1.removeAll();
		table_2.removeAll();
		//挂号
		for(HashMap<String, Object> map : page.getList()){
			if(map.get("state_name").equals("挂号")){
				tableItem = new TableItem(table_2, SWT.NONE);
				tableItem.setText(0, map.get("name").toString());
				tableItem.setText(1, map.get("operator").toString());
				tableItem.setText(2, map.get("state_name").toString());
				tableItem.setText(3, map.get("price").toString());
			}
		}
		//诊断中
		for(HashMap<String, Object> map : page.getList()){
			if(map.get("state_name").equals("诊断中")){
				tableItem = new TableItem(table_1, SWT.NONE);
				tableItem.setText(0, map.get("name").toString());
				tableItem.setText(1, map.get("operator").toString());
				tableItem.setText(2, map.get("state_name").toString());
				tableItem.setText(3, map.get("price").toString());
			}
		}
		//诊断完成
		for(HashMap<String, Object> map : page.getList()){
			if(map.get("state_name").equals("诊断完成")){
				tableItem = new TableItem(table, SWT.NONE);
				tableItem.setText(0, map.get("name").toString());
				tableItem.setText(1, map.get("operator").toString());
				tableItem.setText(2, map.get("state_name").toString());
				tableItem.setText(3, map.get("price").toString());
			}
		}
		text.setText(""+currentPage);
		
		if(currentPage == 1){
			button.setVisible(false);
			btnNewButton.setVisible(false);
		}else{
			button.setVisible(true);
			btnNewButton.setVisible(true);
		}
		
		if(currentPage == page.getPageCount()){
			button_1.setVisible(false);
			button_2.setVisible(false);
		}else{
			button_1.setVisible(true);
			button_2.setVisible(true);
		}
		
		label_1.setText(""+page.getPageCount());
	}
}
