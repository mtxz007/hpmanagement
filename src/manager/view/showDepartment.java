package manager.view;

import java.util.HashMap;

import manager.dialog.AddDepartment;
import manager.dialog.AlterDepartment;
import manager.dialog.DeleteDepartment;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import utils.DbUtils;
import utils.GetAll;
import utils.OpenBox;
import utils.Page;

public class showDepartment extends ViewPart {

	public static final String ID = "manager.view.showDepartment"; //$NON-NLS-1$
	private Table table;
	private Text text;
	private String department_name = null;
	private TableItem tableItem;

	public showDepartment() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(88, 95, 173, 234);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(48);
		tblclmnNewColumn.setText("\u7F16\u53F7");
	
	
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("\u79D1\u5BA4");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AlterDepartment ad = new AlterDepartment(new Shell(), SWT.CLOSE);
				TableItem tableItem = table.getItem(table.getSelectionIndex());
				System.out.print(tableItem.getText(1));
				ad.setDepartment_oldName(tableItem.getText(1));
				ad.open();
				getDepartment(department_name);
			}
		});
		menuItem.setText("\u4FEE\u6539");
		
		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DeleteDepartment delete = new DeleteDepartment(new Shell(), SWT.CLOSE);
				delete.setStyle(true);
				delete.setDepartment_name(table.getItem(table.getSelectionIndex()).getText(1));
				delete.open();
			}
		});
		menuItem_1.setText("\u5220\u9664");
		
		text = new Text(container, SWT.BORDER);
		text.setBounds(317, 95, 97, 26);
		
		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				department_name = text.getText();
				getDepartment(department_name);
			}
		});
		btnNewButton.setBounds(432, 95, 54, 30);
		btnNewButton.setText("\u67E5\u627E");
		
		Button btnNewButton_1 = new Button(container, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				AddDepartment ad = new AddDepartment(new Shell(), SWT.CLOSE);
				ad.open();
				getDepartment(department_name);
			}
		});
		btnNewButton_1.setBounds(317, 157, 98, 30);
		btnNewButton_1.setText("\u65B0\u589E");

		getDepartment(department_name);
		
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
	
	public void getDepartment(String department_name){
		table.removeAll();
		if(department_name == null || department_name.equals("")){
			department_name = "%%";
		}
		Page page = GetAll.getDepartment(department_name);
		for(HashMap<String, Object> map : page.getList()){
			tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(0,map.get("id").toString());
			tableItem.setText(1, map.get("name").toString());
		}
	}
}
