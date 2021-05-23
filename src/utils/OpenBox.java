package utils;

import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class OpenBox {
	public static void Open(String msg){
		MessageBox message = new MessageBox(new Shell());
		message.setMessage(msg);
		message.open();
	}
}
