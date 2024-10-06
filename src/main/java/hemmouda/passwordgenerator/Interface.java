package hemmouda.passwordgenerator;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;

public class Interface {

	private static final Clipboard CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();

	private static JToggleButton UPPER_CASE;
	private static JToggleButton LOWER_CASE;
	private static JToggleButton NUMBERS;
	private static JToggleButton SPECIAL_CHARS;
	private static JSlider SLIDER;

	private static JButton GENERATE_BUTTON;
	private static final String GENERATE_BUTTON_TEXT = "Generate a new %d char long password";

	private static JTextField TEXT_FIELD;

	public static void initialize () {
		JFrame frame = new JFrame ();
		frame.setTitle("Password generator - automatically copied");
		frame.setSize(900, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container pane = frame.getContentPane();
		pane.setLayout(new BorderLayout ());
		
		JPanel panel = new JPanel ();
		panel.setLayout(new GridLayout (3, 2));
		
		UPPER_CASE = createJToggleButton("Upper case letters");
		panel.add(UPPER_CASE);
		
		LOWER_CASE = createJToggleButton("Lower case letters");
		panel.add(LOWER_CASE);
		
		NUMBERS = createJToggleButton("Numbers");
		panel.add(NUMBERS);
		
		SPECIAL_CHARS = createJToggleButton("Special characters");
		panel.add(SPECIAL_CHARS);
		
		SLIDER = new JSlider (JSlider.HORIZONTAL, 1, 64, 16);
		SLIDER.setFocusable(false);
		SLIDER.setMajorTickSpacing(4);
		SLIDER.setPaintTicks(true);
		SLIDER.addChangeListener((ChangeEvent e) -> {
			generatePassword();
		});
		panel.add(SLIDER);
		
		GENERATE_BUTTON = new JButton ();
		GENERATE_BUTTON.setText(GENERATE_BUTTON_TEXT.formatted(SLIDER.getValue()));
		GENERATE_BUTTON.setFocusable(false);
		GENERATE_BUTTON.addActionListener((ActionEvent e) -> {
			generatePassword();
		});
		panel.add(GENERATE_BUTTON);
		
		pane.add(panel, BorderLayout.WEST);
		
		TEXT_FIELD = new JTextField (64);
		TEXT_FIELD.setEditable(false);
		TEXT_FIELD.setHorizontalAlignment(JTextField.LEFT);
		pane.add(TEXT_FIELD, BorderLayout.CENTER);
		
		frame.setVisible(true);
	}

	private static JToggleButton createJToggleButton (String label) {
		JToggleButton toggleButton = new JToggleButton ();
		toggleButton.setFocusable(false);
		toggleButton.setSelected(true);
		toggleButton.setText(label);
		toggleButton.addActionListener((ActionEvent e) -> {
			generatePassword();
		});

		return toggleButton;
	}

	private static void generatePassword () {
		int length = SLIDER.getValue();

		GENERATE_BUTTON.setText(GENERATE_BUTTON_TEXT.formatted(length));

		String password = Generator.generate(length,
				UPPER_CASE.isSelected(),
				LOWER_CASE.isSelected(),
				NUMBERS.isSelected(),
				SPECIAL_CHARS.isSelected());

		TEXT_FIELD.setText(password);
		CLIPBOARD.setContents(new StringSelection(password), null);
	}
	
}
