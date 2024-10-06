package hemmouda.passwordgenerator;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;

public class Interface {
	
	private static JToggleButton upper_case;
	private static JToggleButton lower_case;
	private static JToggleButton numbers;
	private static JToggleButton special_char;
	private static JSlider slider;
	private static JButton generate;
	
	private static JTextField field;

	public static void initialize () {
		JFrame frame = new JFrame ();
		frame.setTitle("Password generator");
		frame.setSize(900, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container pane = frame.getContentPane();
		pane.setLayout(new BorderLayout ());
		
		JPanel panel = new JPanel ();
		panel.setLayout(new GridLayout (3, 2));
		
		upper_case = new JToggleButton ();
		upper_case.setFocusable(false);
		upper_case.setSelected(true);
		upper_case.setText("Upper case letters");
		upper_case.addActionListener((ActionEvent e) -> {
			Generator.generate();
		});
		panel.add(upper_case);
		
		lower_case = new JToggleButton ();
		lower_case.setFocusable(false);
		lower_case.setSelected(true);
		lower_case.setText("Lower case letters");
		lower_case.addActionListener((ActionEvent e) -> {
			Generator.generate();
		});
		panel.add(lower_case);
		
		numbers = new JToggleButton ();
		numbers.setFocusable(false);
		numbers.setSelected(true);
		numbers.setText("Numbers");
		numbers.addActionListener((ActionEvent e) -> {
			Generator.generate();
		});
		panel.add(numbers);
		
		special_char = new JToggleButton ();
		special_char.setFocusable(false);
		special_char.setSelected(true);
		special_char.setText("Special characters");
		special_char.addActionListener((ActionEvent e) -> {
			Generator.generate();
		});
		panel.add(special_char);
		
		slider = new JSlider (JSlider.HORIZONTAL, 1, 64, 16);
		slider.setFocusable(false);
		slider.setMajorTickSpacing(3);
		slider.setPaintTicks(true);
		slider.addChangeListener((ChangeEvent e) -> {
			Generator.generate();
		});
		panel.add(slider);
		
		generate = new JButton ();
		generate.setText("Generate");
		generate.setFocusable(false);
		generate.addActionListener((ActionEvent e) -> {
			Generator.generate();
		});
		panel.add(generate);
		
		pane.add(panel, BorderLayout.WEST);
		
		field = new JTextField (64);
		field.setEditable(false);
		field.setHorizontalAlignment(JTextField.LEFT);
		
		pane.add(field, BorderLayout.CENTER);
		
		frame.setVisible(true);
	}
	
	public static void display (String text) {
		field.setText(text);
	}
	
	public static int getLength () {
		return slider.getValue();
	}
	
	public static boolean isUpper () {
		return upper_case.isSelected();
	}
	
	public static boolean isLower () {
		return lower_case.isSelected();
	}
	
	public static boolean isNumbers () {
		return numbers.isSelected();
	}
	
	public static boolean isSpecial () {
		return special_char.isSelected();
	}
	
}
