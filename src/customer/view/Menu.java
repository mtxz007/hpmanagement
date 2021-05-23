package customer.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.part.ViewPart;

import utils.DbUtils;
import utils.OpenBox;
import customer.dialog.Optimize;
import customer.dialog.Order;
import customer.dialog.Register;
import customer.dialog.Warning_1;
import dao.Patient;
import dialog.Login;

public class Menu extends ViewPart {

	public static final String ID = "customer.view.Menu"; //$NON-NLS-1$
	public Patient patient = new Patient();

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Menu() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final DbUtils db = new DbUtils();
		Composite container = new Composite(parent, SWT.BORDER);
		//若数据库已有病人信息直接进入，否则先添加病人信息
		if(db.queryForList("select * from patients where name = '"+Login.currentUser.getName()+"'").size()==0){
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			String time = date.format(new Date());
			if(db.update("insert into patients( name, sexual, age, photo, phone_number, time_create) values( '"+Login.currentUser.getName()+"','', 0,'', '','"+time+"')")!=0)
				OpenBox.Open("检测到您没有记录。请之后完善信息");
		}
		
		ArrayList<HashMap<String, Object>> list = db.queryForList("select * from patients where name = '"+Login.currentUser.getName()+"'");
		patient.setId_patient(Integer.parseInt((list.get(0).get("id_patients").toString())));
		patient.setName(Login.currentUser.getName());
		patient.setSexual(list.get(0).get("sexual").toString());
		patient.setAge(Integer.parseInt(list.get(0).get("age").toString()));
		patient.setTime_create(list.get(0).get("time_create").toString());
		patient.setPhone_number(list.get(0).get("phone_number").toString());
		patient.setPhoto(list.get(0).get("photo").toString());
		
		Button btnNewButton = new Button(container, SWT.BORDER);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Register register = new Register(new Shell(), SWT.CLOSE);
				System.out.println(patient.getName());
				register.setPatient(patient);
				register.open();
			}
		});
		btnNewButton.setBounds(57, 127, 162, 47);
		btnNewButton.setText("挂号");
		
		Button button = new Button(container, SWT.BORDER);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Order order = new Order(new Shell(), SWT.CLOSE);
				order.setPatient(patient);
				order.open();
			}
		});
		button.setText("查看排队情况");
		button.setBounds(57, 180, 162, 47);
		
		Button button_1 = new Button(container, SWT.BORDER);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Warning_1 warning = new Warning_1(new Shell(), SWT.CLOSE);
				warning.open();
				if(warning.isResult()){
					if(db.queryForList("select * from register where patient_id = "+patient.getId_patient()+"").size()!=0){
						if(db.update("delete from register where patient_id = "+patient.getId_patient()+"")!=0 && 
								db.update("delete from orders where patient_id = "+patient.getId_patient()+"")!=0){
							OpenBox.Open("操作成功");
							db.close();
						}else{
							OpenBox.Open("操作失败。您可能还未进行挂号。");
						}
					}else{
						OpenBox.Open("操作失败。您可能还未进行挂号。");
					}
				}
			}
		});
		button_1.setText("取消挂号");
		button_1.setBounds(57, 233, 162, 47);
		
		Button button_2 = new Button(container, SWT.BORDER);
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Optimize optimize = new Optimize(new Shell(), SWT.CLOSE);
				optimize.setPatient(patient);
				optimize.open();
				patient = optimize.getPatient();
			}
		});
		button_2.setText("\u5B8C\u5584\u4FE1\u606F");
		button_2.setBounds(57, 286, 162, 47);

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
