package hemmouda.passwordgenerator;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ignored) {}
		ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);

		Interface.initialize();
	}

}
