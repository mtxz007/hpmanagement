package medicine.view;

import hpmanagement.Activator;

import java.util.ArrayList;
import java.util.HashMap;

import medicine.dialog.Add;
import medicine.dialog.Details;
import medicine.dialog.Query;
import medicine.dialog.Reduce;
import medicine.dialog.ReduceForSale;

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
import dao.Medicine;

public class ShowMedicine extends ViewPart {
	public ShowMedicine() {
	}

	public static final String ID = "medicine.view.ShowMedicine"; //$NON-NLS-1$
	private boolean orderValue = true;
	private String orderName;										//以orderName排序
	private String name;											//药品名称
	private String manufacturer;									//厂家
	private	String state_name;										//状态（已删除，正常）	
	private DbUtils db = new DbUtils();
	private Table table;
	private int currentPage = 1;
	private String kinds = "";										//药品种类（中西药）
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
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				TableItem tl = table.getItem(table.getSelectionIndex());
				String name = tl.getText(1);
				Medicine medicine = getDetail(name);
				Details detail = new Details(new Shell(),SWT.NONE);
				detail.setMedicine(medicine);
				detail.open();
			}
		});
		button = new Button(container, SWT.NONE);
		btnNewButton = new Button(container, SWT.NONE);
		button_1 = new Button(container, SWT.NONE);
		button_2 = new Button(container, SWT.NONE);
		
		final TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				orderName = "id";
				orderValue = !orderValue;
				getMedicine(currentPage, name, kinds, update, orderName, orderValue);
			}
		});
		tblclmnNewColumn.setWidth(70);
		tblclmnNewColumn.setText("\u836F\u54C1id");
		
		final TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				orderName = "name";
				orderValue = !orderValue;
				getMedicine(currentPage, name, kinds, update, orderName, orderValue);
			}
		});
		tblclmnNewColumn_1.setWidth(185);
		tblclmnNewColumn_1.setText("\u540D\u79F0");
		
		final TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				orderName = "manufacturer";
				orderValue = !orderValue;
				getMedicine(currentPage, name, kinds, update, orderName, orderValue);
			}
		});
		tblclmnNewColumn_2.setWidth(197);
		tblclmnNewColumn_2.setText("\u5236\u836F\u5382");
		
		final TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				orderName = "pd";
				orderValue = !orderValue;
				getMedicine(currentPage, name, kinds, update, orderName, orderValue);
			}
		});
		tblclmnNewColumn_3.setWidth(141);
		tblclmnNewColumn_3.setText("\u751F\u4EA7\u65E5\u671F");
		
		final TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				orderName = "exp";
				orderValue = !orderValue;
				getMedicine(currentPage, name, kinds, update, orderName, orderValue);
			}
		});
		tblclmnNewColumn_4.setWidth(148);
		tblclmnNewColumn_4.setText("\u8FC7\u671F\u65E5\u671F");
		
		final TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				orderName = "number";
				orderValue = !orderValue;
				getMedicine(currentPage, name, kinds, update, orderName, orderValue);
			}
		});
		tblclmnNewColumn_5.setWidth(82);
		tblclmnNewColumn_5.setText("\u6570\u91CF");
		
		final TableColumn tblclmnNewColumn_6 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				orderName = "price_stock";
				orderValue = !orderValue;
				getMedicine(currentPage, name, kinds, update, orderName, orderValue);
			}
		});
		tblclmnNewColumn_6.setWidth(108);
		tblclmnNewColumn_6.setText("\u8FDB\u4EF7");
		
		final TableColumn tblclmnNewColumn_7 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				orderName = "price_sale";
				orderValue = !orderValue;
				getMedicine(currentPage, name, kinds, update, orderName, orderValue);
			}
		});
		tblclmnNewColumn_7.setWidth(105);
		tblclmnNewColumn_7.setText("\u9500\u552E\u4EF7");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem tl = table.getItem(table.getSelectionIndex());
				String name = tl.getText(1);
				Medicine medicine = getDetail(name);
				Details detail = new Details(new Shell(),SWT.NONE);
				detail.setMedicine(medicine);
				detail.open();
			}
		});
		menuItem.setText("\u8BE6\u60C5");
		
		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int number = Integer.parseInt(table.getItem(table.getSelectionIndex()).getText(5).toString());
				Medicine medicine = new Medicine();
				medicine.setNumber(number);
				medicine.setPrice_sale(Double.parseDouble(table.getItem(table.getSelectionIndex()).getText(7).toString()));
				medicine.setPrice_stock(Double.parseDouble(table.getItem(table.getSelectionIndex()).getText(6).toString()));
				medicine.setName(table.getItem(table.getSelectionIndex()).getText(1).toString());
				medicine.setId(Integer.parseInt(table.getItem(table.getSelectionIndex()).getText(0).toString()));
				medicine.setName(table.getItem(table.getSelectionIndex()).getText(1).toString());
				medicine.setId(Integer.parseInt(table.getItem(table.getSelectionIndex()).getText(0).toString()));
				Add add = new Add(new Shell(), SWT.NONE);
				add.setMedicine(medicine);
				add.open();
				currentPage = 1;
				getMedicine(currentPage, name, kinds, update, orderName, orderValue);
			}
		});
		menuItem_1.setText("\u589E\u52A0\u6570\u91CF");
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int number = Integer.parseInt(table.getItem(table.getSelectionIndex()).getText(5).toString());
				Medicine medicine = new Medicine();
				medicine.setNumber(number);
				medicine.setName(table.getItem(table.getSelectionIndex()).getText(1).toString());
				medicine.setId(Integer.parseInt(table.getItem(table.getSelectionIndex()).getText(0).toString()));
				Reduce reduce = new Reduce(new Shell(), SWT.CLOSE);
				reduce.setMedicine(medicine);
				reduce.open();
				currentPage = 1;
				getMedicine(currentPage, name, kinds, update, orderName, orderValue);
			}
		});
		mntmNewItem.setText("\u8FD4\u5382");
		
		MenuItem menuItem_2 = new MenuItem(menu, SWT.NONE);
		menuItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int number = Integer.parseInt(table.getItem(table.getSelectionIndex()).getText(5).toString());
				Medicine medicine = new Medicine();
				medicine.setNumber(number);
				medicine.setMenufacturer(table.getItem(table.getSelectionIndex()).getText(2).toString());
				medicine.setPrice_sale(Double.parseDouble(table.getItem(table.getSelectionIndex()).getText(7).toString()));
				medicine.setPrice_stock(Double.parseDouble(table.getItem(table.getSelectionIndex()).getText(6).toString()));
				medicine.setName(table.getItem(table.getSelectionIndex()).getText(1).toString());
				medicine.setId(Integer.parseInt(table.getItem(table.getSelectionIndex()).getText(0).toString()));
				ReduceForSale sale = new ReduceForSale(new Shell(), SWT.CLOSE);
				sale.setMedicine(medicine);
				sale.open();
				currentPage = 1;
				getMedicine(currentPage, name, kinds, update, orderName, orderValue);
			}
		});
		menuItem_2.setText("\u9500\u552E");
		
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				currentPage = 1;
				getMedicine(currentPage, name, kinds, update, orderName, orderValue);
			}
		});
		
		button.setText("\u9996\u9875");
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				currentPage--;
				getMedicine(currentPage, name, kinds, update, orderName, orderValue);
			}
		});
		
		btnNewButton.setText("\u4E0A\u4E00\u9875");
		
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(currentPage < page.getPageCount()){
					currentPage++;
					getMedicine(currentPage, name, kinds, update, orderName, orderValue);
				}
			}
		});
		button_1.setText("\u4E0B\u4E00\u9875");
		
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				currentPage = page.getPageCount();
				getMedicine(currentPage, name, kinds, update, orderName, orderValue);
			}
		});
		button_2.setText("\u5C3E\u9875");
		
		
		Label label = new Label(container, SWT.NONE);
		label.setBounds(222, 655, 6, 20);
		label.setText("/");
		
		lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(234, 655, 22, 20);
		
		
		text = new Text(container, SWT.BORDER);
		
		getMedicine(currentPage, name, kinds, update, orderName, orderValue);
		
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
				getMedicine(currentPage, name, kinds, update, orderName, orderValue);
			}
		});
		btnNewButton_1.setBounds(266, 650, 32, 30);
		btnNewButton_1.setText("->");
		
		Button button_3 = new Button(container, SWT.NONE);
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Query query = new Query(new Shell(), SWT.NONE);
				query.open();
				kinds = query.getKinds();
				System.out.println(kinds);
				update = query.getQuery();
				System.out.println(update);
				getMedicine(currentPage, name, kinds, update, orderName, orderValue);
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
	
	public void getMedicine(int currentPage, String name ,String kinds, String query, String orderName, boolean orderValue){
		table.removeAll();
		page = GetAll.getMedicine(currentPage, name, kinds, query, orderName, orderValue);
		for(HashMap<String, Object> map : page.getList() ){
			tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(0, map.get("id").toString());
			tableItem.setText(1, map.get("name").toString());
			tableItem.setText(2, map.get("manufacturer").toString());
			tableItem.setText(3, map.get("pd").toString());
			tableItem.setText(4, map.get("exp").toString());
			tableItem.setText(5, map.get("number").toString());
			tableItem.setText(6, map.get("price_stock").toString());
			tableItem.setText(7, map.get("price_sale").toString());
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
	
	private ArrayList<HashMap<String, Object>> getAll(){
		ArrayList<HashMap<String,Object>> list = db.queryForList("SELECT distinct st.`id`,st.`name`, st.`manufacturer`, st.`pd`, st.`exp`, st.`number`, st.`price_stock`,st.`price_sale`, k.`NAME` AS kinds_name , sm.`name` AS kinds_state_name"
				+" FROM medicine_stock st, medicine_state s, medicine_kinds k, medicine_state sm WHERE st.`kind` = k.`id` AND k.`state`=s.`id` "
				+" AND s.`name` LIKE '"+state_name+"' AND st.`manufacturer` LIKE '"+manufacturer+"' AND st.`name` LIKE '"+name+"' AND k.`NAME` LIKE '"+kinds+"'"
				+" GROUP BY id"
				+" ORDER BY "+orderName+"");
		
		return list;
	}
	
	private Medicine getDetail(String name){
		Medicine medicine = new Medicine();
		ArrayList<HashMap<String,Object>> list = db.queryForList("SELECT distinct st.`id`,st.`name`, st.`manufacturer`, st.`batch_number`, st.`pd`, st.`exp`, st.`number`, st.`price_stock`,st.`price_sale`, k.`NAME` AS kinds_name , sm.`name` AS kinds_state_name"
				+" FROM medicine_stock st, medicine_state s, medicine_kinds k, medicine_state sm WHERE st.`kind` = k.`id` AND k.`state`=s.`id` "
				+" AND st.name = '"+name+"'");
		medicine.setExp(list.get(0).get("exp").toString());
		medicine.setPd(list.get(0).get("pd").toString());
		medicine.setId(Integer.parseInt(list.get(0).get("id").toString()));
		medicine.setMenufacturer(list.get(0).get("manufacturer").toString());
		medicine.setKind(list.get(0).get("kinds_name").toString());
		medicine.setName(list.get(0).get("name").toString());
		medicine.setNumber(Integer.parseInt(list.get(0).get("number").toString()));
		medicine.setPrice_stock(Double.parseDouble(list.get(0).get("price_stock").toString()));
		medicine.setPrice_sale(Double.parseDouble(list.get(0).get("price_sale").toString()));
		medicine.setBatch_number(list.get(0).get("batch_number").toString());
		return medicine;
	}
}