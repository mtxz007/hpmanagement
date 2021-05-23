package medicine.view;

import medicine.dialog.AddMedicine;
import medicine.dialog.AddMenufacturer;
import medicine.dialog.getManufacturerName;

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

import utils.OpenView;

public class Menu extends ViewPart {

	public static final String ID = "medicine.view.Menu"; //$NON-NLS-1$

	public Menu() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		ExpandBar expandBar_1 = new ExpandBar(container, SWT.NONE);
		{
			ExpandItem xpndtmNewExpanditem = new ExpandItem(expandBar_1, SWT.NONE);
			xpndtmNewExpanditem.setExpanded(true);
			xpndtmNewExpanditem.setText("\u836F\u623F\u5E93\u5B58\u7BA1\u7406");
			{
				Composite composite = new Composite(expandBar_1, SWT.NONE);
				xpndtmNewExpanditem.setControl(composite);
				composite.setLayout(new GridLayout(1, false));
				{
					Link link = new Link(composite, SWT.NONE);
					link.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent e) {
							OpenView.openView(ShowMedicine.ID);
						}
					});
					link.setText("<a>\u67E5\u770B\u836F\u54C1</a>");
				}
				{
					Link link = new Link(composite, SWT.NONE);
					link.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent e) {
							AddMedicine add = new AddMedicine(new Shell(), SWT.CLOSE);
							add.open();
						}
					});
					link.setText("<a>\u8FDB\u836F</a>");
				}
			}
			xpndtmNewExpanditem.setHeight(xpndtmNewExpanditem.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		}
		{
			ExpandItem xpndtmNewExpanditem_1 = new ExpandItem(expandBar_1, SWT.NONE);
			xpndtmNewExpanditem_1.setExpanded(true);
			xpndtmNewExpanditem_1.setText("\u836F\u5382\u7BA1\u7406");
			{
				Composite composite = new Composite(expandBar_1, SWT.NONE);
				xpndtmNewExpanditem_1.setControl(composite);
				composite.setLayout(new GridLayout(1, false));
				
				Link link = new Link(composite, SWT.NONE);
				link.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent e) {
						ShowManufacturer show = new ShowManufacturer(new Shell(), SWT.CLOSE);
						show.open();
					}
				});
				link.setText("<a>\u67E5\u770B\u836F\u5382</a>");
				
				Link link_1 = new Link(composite, SWT.NONE);
				link_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent e) {
						AddMenufacturer add = new AddMenufacturer(new Shell(), SWT.CLOSE);
						add.open();
					}
				});
				link_1.setText("<a>\u6DFB\u52A0\u836F\u5382</a>");
				{
					Link link_2 = new Link(composite, SWT.NONE);
					link_2.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent e) {
							getManufacturerName delete = new getManufacturerName(new Shell(), SWT.CLOSE);
							delete.open();
						}
					});
					link_2.setText("<a>\u5220\u9664\u836F\u5382</a>");
				}
			}
			xpndtmNewExpanditem_1.setHeight(xpndtmNewExpanditem_1.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		}
		{
			
			Composite composite = new Composite(expandBar_1, SWT.NONE);
			composite.setLayout(new GridLayout(1, false));
			{
				Link link = new Link(composite, SWT.NONE);
				link.setText("<a>\u5404\u79CD\u79D1\u5BA4\u836F\u54C1\u7EDF\u8BA1</a>");
			}
			{
				Link link = new Link(composite, SWT.NONE);
				link.setText("<a>\u5404\u79D1\u5BA4\u8BCA\u65AD\u5904\u7406\u72B6\u51B5</a>");
			}
			{
				Link link = new Link(composite, SWT.NONE);
				link.setText("<a>\u6536\u5165\u60C5\u51B5</a>");
			}
		}

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

}
