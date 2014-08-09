package wordhoard.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import wordhoard.corpus.StringCorpus;

public class Test_StringCorpus {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	// Some String values that functions might well mess up on.
	// I could use a Theory here but that's kinda overkill.
	private static String[] testStrings = new String[]{
		null, // null often hurts. 
		"",  // the null string does too.
		"a",  // one character
		"something that is not too short", // many chars
		"ú", // Non-ASCII unicode
		"Ʊɲɨćǒᵭḗ ɨṩ ĵôʎ", // More exotic unicode
		"𐄳𐅮𨭎𠬠𝕂😂🀤🃕🄸", // Astral characters
		};
	
	@Test
	public void testGetBody() {
		for(String s : testStrings) {
			StringCorpus sc = new StringCorpus(s);
			assertEquals(sc.getBody(), s);
		}
	}

	@Test
	public void testSetBody() {
		StringCorpus sc = new StringCorpus("anything else");
		// I gather Hamcrest has better array methods but I don't feel like looking now.
		for(String s : testStrings) {
			assertThat(sc.getBody(), not(equalTo(s)));
		}
		for(String s : testStrings) {
			sc.setBody(s);
			assertThat(sc.getBody(), is(equalTo(s)));
		}
	}


	@Test
	public void testToString() {
		for(String s : testStrings) {
			StringCorpus sc = new StringCorpus(s);
			if (s != null) {
				assertThat(sc.toString(), containsString(s));
			}
		}
	}

}
