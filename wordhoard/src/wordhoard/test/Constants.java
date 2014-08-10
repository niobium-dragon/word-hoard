package wordhoard.test;

public class Constants {
	private Constants() throws Exception {
		throw new Exception("Please don't implement this namespace");
	}
	
	public final static String[] strings = new String[]{
		null, // null often hurts. 
		"",  // the null string does too.
		"a",  // one character
		"b", // another character
		"something that is not too short", // many chars
		"ú", // Non-ASCII unicode
		"Ʊɲɨćǒᵭḗ ɨṩ ĵôʎ", // More exotic unicode
		"𐄳𐅮𨭎𠬠𝕂😂🀤🃕🄸", // Astral characters
		};
}
