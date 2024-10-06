package hemmouda.passwordgenerator;

import hemmouda.util.structures.Pair;

import java.util.List;
import java.util.Random;

public class Generator {

	private static final char [] UPPER_CASE_LETTERS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	private static final char [] LOWER_CASE_LETTERS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	private static final char [] NUMBERS = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
	private static final char [] SPECIAL_CHARS = {'!', '#', '$', '%', '&', '(', ')', '*', '+', '-', '.', ':', ';','<' ,'=' ,'>' ,'?' ,'@' ,'[', ']', '^', '_', '{', '|', '}', '~'};

	// Full special chars list
	//private static final char [] SPECIAL_CHARS = {'`', '\\', '\"', '\'', ',', '/', '!', '#', '$', '%', '&', '(', ')', '*', '+', '-', '.', ':', ';','<' ,'=' ,'>' ,'?' ,'@' ,'[', ']', '^', '_', '{', '|', '}', '~'};
	
	public static String generate (int length, boolean allowUpper, boolean allowLower, boolean allowNumbers, boolean allowSpecial) {
		// Check if we can generate something
		if (allowUpper || allowLower || allowNumbers || allowSpecial) {

			StringBuilder builder = new StringBuilder (length);
			for (int i = 0; i < length; i++) {
				builder.append(getRandomChar(allowUpper, allowLower, allowNumbers, allowSpecial));
			}

			return builder.toString();
		} else {
			return "";
		}
	}

	/**
	 * @return a random char from a random allowed charset
	 */
	private static char getRandomChar (boolean allowUpper, boolean allowLower, boolean allowNumbers, boolean allowSpecial) {
		char [] charset = getRandomCharset(allowUpper, allowLower, allowNumbers, allowSpecial);

		Random random = new Random();
		return charset[random.nextInt(charset.length)];
	}

	/**
	 * @return a random allowed charset
	 */
	private static char [] getRandomCharset (boolean allowUpper, boolean allowLower, boolean allowNumbers, boolean allowSpecial) {
		List<Pair<char[], Boolean>> allowed = List.of(
				new Pair<>(UPPER_CASE_LETTERS, allowUpper),
				new Pair<>(LOWER_CASE_LETTERS, allowLower),
				new Pair<>(NUMBERS, allowNumbers),
				new Pair<>(SPECIAL_CHARS, allowSpecial)
		);

		Random random = new Random();
		while (true) {
			int index = random.nextInt(allowed.size());
			var pair = allowed.get(index);
			if (pair.second) {
				return pair.first;
			}
		}
	}

}
