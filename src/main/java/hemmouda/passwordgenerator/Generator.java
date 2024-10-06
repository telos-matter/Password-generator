package hemmouda.passwordgenerator;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Generator {

	private static final char [] UPPER_CASE_LETTERS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	private static final char [] LOWER_CASE_LETTERS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	private static final char [] NUMBERS = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
	private static final char [] SPECIAL_CHARS = {'!', '#', '$', '%', '&', '(', ')', '*', '+', '-', '.', ':', ';','<' ,'=' ,'>' ,'?' ,'@' ,'[', ']', '^', '_', '{', '|', '}', '~'};
	
	//private static final char [] SPECIAL_CHARS = {'`', '\\', '\"', '\'', ',', '/', '!', '#', '$', '%', '&', '(', ')', '*', '+', '-', '.', ':', ';','<' ,'=' ,'>' ,'?' ,'@' ,'[', ']', '^', '_', '{', '|', '}', '~'};
	
	private static final char [] [] CHARS = {UPPER_CASE_LETTERS, LOWER_CASE_LETTERS, NUMBERS, SPECIAL_CHARS};
	
	private static boolean [] settings = new boolean [4];
	
	private static final Clipboard CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();
	
	public static void generate () {
		update (); 
		
		
		if (settings [0] || settings [1] || settings [2] || settings [3]) {
			int length = Interface.getLength();
			
			StringBuilder builder = new StringBuilder (length);	
			for (int i = 0; i < length; i++) {
				builder.append(getRandom(getRandom()));
			}

			String password = builder.toString();
			Interface.display(password);
			CLIPBOARD.setContents(new StringSelection(password), null);
		} else {
			Interface.display("");
		}
	}
	
	private static void update () {
		settings [0] = Interface.isUpper();
		settings [1] = Interface.isLower();
		settings [2] = Interface.isNumbers();
		settings [3] = Interface.isSpecial();
	}
	
	private static char [] getRandom () {
		int index;
		
		do {
			index = randomInt(0, 3);
			if (settings [index]) {
				return CHARS[index];
			}
		} while (true);
	}
	
	private static char getRandom (char [] array) {
		return array [randomInt (0, array.length -1)];
	}
	
	private static int randomInt (int min, int max) {
		int interval = max -min +1;
		double delta = 1./interval;
		double value = Math.random();
		
		for (int i = 1; i <= interval; i++) {
			if (value <= delta * i) {
				return min +i -1;
			}
		}		
		
		return -1;
	}
	
}
