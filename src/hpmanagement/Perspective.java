package hpmanagement;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import dialog.Login;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		if(Login.currentUser.getRoot().equals("管理员")){
			layout.addView(manager.view.menu.ID, IPageLayout.LEFT, 0.2f, layout.getEditorArea());
			layout.addView(manager.view.main.ID, IPageLayout.RIGHT, 0.8f, layout.getEditorArea());
		}
		if(Login.currentUser.getRoot().equals("医生") || Login.currentUser.getRoot().equals("门诊")){
			layout.addView(doctor.view.Menu.ID, IPageLayout.LEFT, 0.2f, layout.getEditorArea());
	  		layout.addView(manager.view.main.ID, IPageLayout.RIGHT, 0.8f, layout.getEditorArea());
		}
		if(Login.currentUser.getRoot().equals("病人")){
			layout.addView(customer.view.Menu.ID, IPageLayout.LEFT, 0.2f, layout.getEditorArea());
			layout.addView(manager.view.main.ID, IPageLayout.RIGHT, 0.8f, layout.getEditorArea());
		}
		if(Login.currentUser.getRoot().equals("药房")){
			layout.addView(medicine.view.Menu.ID, IPageLayout.LEFT, 0.2f, layout.getEditorArea());
			layout.addView(manager.view.main.ID, IPageLayout.RIGHT, 0.8f, layout.getEditorArea());
		}
	}
}
