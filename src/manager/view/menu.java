package manager.view;

import java.util.ArrayList;
import java.util.HashMap;

import manager.dialog.AddDepartment;
import manager.dialog.AddUser;
import manager.dialog.Alter_getUsername;
import manager.dialog.DeleteDepartment;
import manager.dialog.Delete_getUsername;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.part.ViewPart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import utils.DbUtils;
import utils.OpenView;
import org.eclipse.swt.widgets.Label;

public class menu extends ViewPart {

	public static final String ID = "manager.view.menu"; //$NON-NLS-1$

	public menu() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final DbUtils db = new DbUtils();
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		ExpandBar expandBar_1 = new ExpandBar(container, SWT.NONE);
		{
			ExpandItem xpndtmNewExpanditem = new ExpandItem(expandBar_1, SWT.NONE);
			xpndtmNewExpanditem.setExpanded(true);
			xpndtmNewExpanditem.setText("\u7528\u6237\u7BA1\u7406");
			{
				Composite composite = new Composite(expandBar_1, SWT.NONE);
				xpndtmNewExpanditem.setControl(composite);
				composite.setLayout(new GridLayout(1, false));
				{
					Link link = new Link(composite, SWT.NONE);
					link.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent e) {
							OpenView.openView(manager.view.showUser.ID);
						}
					});
					link.setText("<a>\u67E5\u770B\u7528\u6237</a>");
				}
				{
					Link link = new Link(composite, SWT.NONE);
					link.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent e) {
							AddUser add = new AddUser(new Shell(), SWT.CLOSE);
							add.open();
						}
					});
					link.setText("<a>\u6DFB\u52A0\u7528\u6237</a>");
				}
				{
					Link link = new Link(composite, SWT.NONE);
					link.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent e) {
							Alter_getUsername ag = new Alter_getUsername(new Shell(), SWT.CLOSE);
							ag.open();
						}
					});
					link.setText("<a>\u4FEE\u6539\u7528\u6237</a>");
				}
				{
					Link link = new Link(composite, SWT.NONE);
					link.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent e) {
							Delete_getUsername dg = new Delete_getUsername(new Shell(), SWT.CLOSE);
							dg.open();
						}
					});
					link.setText("<a>\u5220\u9664\u7528\u6237</a>");
				}
			}
			xpndtmNewExpanditem.setHeight(xpndtmNewExpanditem.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		}
		{
			ExpandItem xpndtmNewExpanditem_1 = new ExpandItem(expandBar_1, SWT.NONE);
			xpndtmNewExpanditem_1.setExpanded(true);
			xpndtmNewExpanditem_1.setText("\u79D1\u5BA4\u7BA1\u7406");
			{
				Composite composite = new Composite(expandBar_1, SWT.NONE);
				xpndtmNewExpanditem_1.setControl(composite);
				composite.setLayout(new GridLayout(1, false));
				
				Link link = new Link(composite, SWT.NONE);
				link.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent e) {
						OpenView.openView(showDepartment.ID);
					}
				});
				link.setText("<a>\u67E5\u770B\u79D1\u5BA4</a>");
				
				Link link_1 = new Link(composite, SWT.NONE);
				link_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent e) {
						AddDepartment ad = new AddDepartment(new Shell(), SWT.CLOSE);
						ad.open();
					}
				});
				link_1.setText("<a>\u6DFB\u52A0\u79D1\u5BA4</a>");
				{
					Link link_2 = new Link(composite, SWT.NONE);
					link_2.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent e) {
							DeleteDepartment delete = new DeleteDepartment(new Shell(), SWT.CLOSE);
							delete.open();
						}
					});
					link_2.setText("<a>\u5220\u9664\u79D1\u5BA4</a>");
				}
			}
			xpndtmNewExpanditem_1.setHeight(xpndtmNewExpanditem_1.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		}
		{
			ExpandItem xpndtmNewExpanditem_2 = new ExpandItem(expandBar_1, SWT.NONE);
			xpndtmNewExpanditem_2.setExpanded(true);
			xpndtmNewExpanditem_2.setText("\u7EDF\u8BA1");
			
			Composite composite = new Composite(expandBar_1, SWT.NONE);
			xpndtmNewExpanditem_2.setControl(composite);
			composite.setLayout(new GridLayout(1, false));
			{
				Link link = new Link(composite, SWT.NONE);
				link.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent e) {
						DefaultCategoryDataset data = new DefaultCategoryDataset();
						ArrayList<HashMap<String, Object>> medicine_kinds = db.queryForList("select * from medicine_kinds");
						//根据kinds逐个查询相应中类药品放入medicine_stock，因为只需要统计数量，用sum函数计算对应药品数量放入数据域中
						for(HashMap<String, Object> k:medicine_kinds){
							ArrayList<HashMap<String, Object>> medicine_stock = db.queryForList("select sum(number) as sum from medicine_stock where kind = "+k.get("id").toString()+"");
							data.addValue(Integer.parseInt(medicine_stock.get(0).get("sum").toString()),"", k.get("NAME").toString());
						}
						JFreeChart chart = ChartFactory.createBarChart3D("药品种类柱状图", "种类", "数量", data, PlotOrientation.VERTICAL, true, true, true);
						ChartFrame show = new ChartFrame("药品种类柱状图",chart);
						show.pack();
						show.setVisible(true);
					}
				});
				link.setText("<a>\u5404\u79CD\u7C7B\u836F\u54C1\u7EDF\u8BA1</a>");
			}
			{
				Link link = new Link(composite, SWT.NONE);
				link.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent e) {
						DefaultCategoryDataset data = new DefaultCategoryDataset();
						ArrayList<HashMap<String, Object>> department = db.queryForList("select * from department");
						ArrayList<HashMap<String, Object>> state = db.queryForList("select * from register_state");
						for(HashMap<String, Object> d:department){
							for(HashMap<String, Object> s:state){
								ArrayList<HashMap<String, Object>> registe = db.queryForList("select count(*) as num from register where department = "+d.get("id")+" AND state = "+s.get("id")+"");
								if(registe.size()!=0)
									data.addValue(Integer.parseInt(registe.get(0).get("num").toString()),d.get("name").toString(), s.get("name").toString());
							}
						}
						
						JFreeChart chart = ChartFactory.createBarChart3D("挂号/药品收入柱状图", "挂号/药品", "收入", data, PlotOrientation.VERTICAL, true, true, true);
						ChartFrame show = new ChartFrame("收入柱状图",chart);
						show.pack();
						show.setVisible(true);
					}
				});
				link.setText("<a>\u5404\u79D1\u5BA4\u8BCA\u65AD\u5904\u7406\u72B6\u51B5</a>");
			}
			{
				Link link = new Link(composite, SWT.NONE);
				link.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent e) {
						DefaultCategoryDataset data = new DefaultCategoryDataset();
						String[] source = {"挂号","开药"};
						ArrayList<HashMap<String, Object>> department = db.queryForList("select * from department");
						ArrayList<HashMap<String, Object>> manufacturer = db.queryForList("select * from manufacturer");
						ArrayList<HashMap<String, Object>> incomeForRegiste = db.queryForList("SELECT source,SUM(income) as income FROM income WHERE isregiste = '是' GROUP BY source");
						ArrayList<HashMap<String, Object>> incomeForMedicine = db.queryForList("SELECT source,SUM(income) as income FROM income WHERE ismedicine = '是' GROUP BY source");
						for(String s:source){
							if(s.equals("挂号"))
								for(HashMap<String, Object> d:department){
									for(HashMap<String, Object>  ig:incomeForRegiste)
										if(d.get("id").toString().equals(ig.get("source").toString()))
											data.addValue(Double.parseDouble(ig.get("income").toString()),"", s);
								}
							else
								for(HashMap<String, Object>  d:manufacturer)
									for(HashMap<String, Object>  ig:incomeForMedicine)
										if(d.get("id").toString().equals(ig.get("source").toString()))
											data.addValue(Double.parseDouble(ig.get("income").toString()), "", s);
						}
						JFreeChart chart = ChartFactory.createBarChart3D("挂号/药品收入柱状图", "挂号/药品", "收入", data, PlotOrientation.VERTICAL, true, true, true);
						ChartFrame show = new ChartFrame("收入柱状图",chart);
						show.pack();
						show.setVisible(true);
					}
				});
				link.setText("<a>\u6536\u5165\u60C5\u51B5</a>");
			}
			xpndtmNewExpanditem_2.setHeight(xpndtmNewExpanditem_2.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		}

		createActions();
		initializeToolBar();
		initializeMenu();
	}
	void createActions() {
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
}
