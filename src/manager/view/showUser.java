package manager.view;

import hpmanagement.Activator;

import java.util.HashMap;

import manager.dialog.Details;
import manager.dialog.Query;
import medicine.dialog.Warning_1;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;

import utils.DbUtils;
import utils.GetAll;
import utils.Page;

public class showUser extends ViewPart {

	public static final String ID = "manager.view.showUser"; //$NON-NLS-1$
	private DbUtils db = new DbUtils();
	private Table table;
	private int currentPage = 1;
	private String username;
	private String kinds = "";
	private String update = "";
	private Text text;
	private Page page;
	private Label lblNewLabel;
	private Button button;
	private Button btnNewButton;
	private Button button_1;
	private Button button_2;
	public String getKinds() {
		return kinds;
	}

	public void setKinds(String kinds) {
		this.kinds = kinds;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	private TableItem tableItem;

	public showUser() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		Image image = Activator.getImageDescriptor("icons/yuanshen_3.jpg").createImage();
		container.setBackgroundImage(image);
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		button = new Button(container, SWT.NONE);
		btnNewButton = new Button(container, SWT.NONE);
		button_1 = new Button(container, SWT.NONE);
		button_2 = new Button(container, SWT.NONE);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(93);
		tblclmnNewColumn.setText("\u7528\u6237\u540D");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(95);
		tblclmnNewColumn_1.setText("\u59D3\u540D");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(57);
		tblclmnNewColumn_2.setText("\u6027\u522B");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_3.setWidth(59);
		tblclmnNewColumn_3.setText("\u5E74\u9F84");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_4.setWidth(244);
		tblclmnNewColumn_4.setText("\u7535\u8BDD\u53F7\u7801");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_5.setWidth(163);
		tblclmnNewColumn_5.setText("\u79D1\u5BA4");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_6.setWidth(151);
		tblclmnNewColumn_6.setText("\u6743\u9650");
		
		TableColumn tblclmnNewColumn_7 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_7.setWidth(176);
		tblclmnNewColumn_7.setText("\u72B6\u6001");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem tl = table.getItem(table.getSelectionIndex());
				String username = tl.getText(0);
				Details detail = new Details(new Shell(),SWT.NONE);
				detail.setUsername(username);
				detail.open();
			}
		});
		menuItem.setText("\u8BE6\u60C5");
		
		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		menuItem_1.setText("\u4FEE\u6539");
		
		MenuItem menuItem_2 = new MenuItem(menu, SWT.NONE);
		menuItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem tl = table.getItem(table.getSelectionIndex());
				username = tl.getText(0);
				Warning_1 warn = new Warning_1(new Shell(), SWT.Close);
				warn.open();
				if(warn.getResult() != null){
					db.update("DELETE FROM USER WHERE username = '"+username+"'");
					getUser(currentPage, kinds, update);
				}
			}
		});
		menuItem_2.setText("\u5220\u9664");
		
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				currentPage = 1;
				getUser(currentPage, kinds, update);
			}
		});
		
		button.setText("\u9996\u9875");
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				currentPage--;
				getUser(currentPage, kinds, update);
			}
		});
		
		btnNewButton.setText("\u4E0A\u4E00\u9875");
		
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(currentPage < page.getPageCount()){
					currentPage++;
					getUser(currentPage, kinds, update);
				}
			}
		});
		button_1.setText("\u4E0B\u4E00\u9875");
		
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				currentPage = page.getPageCount();
				getUser(currentPage, kinds, update);
			}
		});
		button_2.setText("\u5C3E\u9875");
		
		
		Label label = new Label(container, SWT.NONE);
		label.setBounds(222, 655, 6, 20);
		label.setText("/");
		
		lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(234, 655, 22, 20);
		
		
		text = new Text(container, SWT.BORDER);
		
		getUser(currentPage, kinds, update);
		
		table.setBounds(20, 34, 1044, 584);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		button.setBounds(20, 650, 62, 30);
		button_2.setBounds(372, 650, 62, 30);
		button_1.setBounds(304, 650, 62, 30);
		btnNewButton.setBounds(88, 650, 62, 30);
		
		Button btnNewButton_1 = new Button(container, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				int want = Integer.parseInt(text.getText());
				if(want<1){
					want = 1;
				}else if(want>page.getCurrentPage()){
					want = page.getPageCount();
				}
				
				currentPage = want;
				getUser(currentPage, kinds, update);
			}
		});
		btnNewButton_1.setBounds(266, 650, 32, 30);
		btnNewButton_1.setText("->");
		
		Button button_3 = new Button(container, SWT.NONE);
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Query query = new Query(new Shell(), SWT.CLOSE);
				query.open();
				kinds = query.getKinds();
				update = query.getUpdate();
				getUser(currentPage, kinds, update);
			}
		});
		button_3.setBounds(20, 703, 74, 30);
		button_3.setText("\u67E5\u8BE2");
		
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
	
	public void getUser(int currentPage, String kinds, String update){
		table.removeAll();
		page = GetAll.get(currentPage, kinds, update);
		for(HashMap<String, Object> map : page.getList() ){
			tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(0, map.get("username").toString());
			tableItem.setText(1, map.get("name").toString());
			tableItem.setText(2, map.get("sexual").toString());
			tableItem.setText(3, map.get("age").toString());
			tableItem.setText(4, map.get("phone_number").toString());
			tableItem.setText(5, map.get("department_name").toString());
			tableItem.setText(6, map.get("root_name").toString());
			tableItem.setText(7, map.get("state_name").toString());
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
		
		lblNewLabel.setText(""+page.getPageCount());
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text.setBounds(187, 654, 32, 26);
	}
}
