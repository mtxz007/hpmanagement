package utils;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class OpenView {
	public static void openView(String id){
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().
			getActivePage().showView(id);
		} catch (PartInitException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
