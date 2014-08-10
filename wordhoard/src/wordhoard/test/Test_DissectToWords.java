package wordhoard.test;

import static bard.BardUtil.list;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import wordhoard.Dissection;
import wordhoard.dissection.DissectToChars;
import wordhoard.dissection.DissectToWords;

public class Test_DissectToWords {

	@Test
	public void testDissectLine() {
		test1("", new ArrayList<String>());
		test1("abc", list("abc"));
		test1("a b\t\t\t\tc", list("a", "b", "c"));
		test1("Ʊɲɨ", list("ʊɲɨ"));
		test1("𐅮𨭎", list("𐅮","𨭎")); // Astral characters — Not too worried about how they parse, as long as they get there intact
		test1("You have a swallow.", list("you","have","a", "swallow", "."));
		test1("You have a swallow...", list("you","have","a", "swallow", "..."));
		
		
	}
	
	// DISABLED @Test
	public void testImprovements() throws Exception {
		// These are things that would be nice to have in the word dissection, but 
		// not today.
		
		// English contractions should be treated as words. 
		test1("hot, bubbly fish", list("hot", ",", "bubbly",  "fish"));
		test1("Isn't \"won't\" great?", list("isn't", "\"", "won't", "\"", "great", "?"));
		test1("Just sayin'", list("Just", "sayin'"));
		// Emoticons 
		test1(":)", list(":)"));
		test1("Joke:)", list("joke", ":)"));
		
	}
	
	private static String show(List<String> L) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for(String s : L) {
			if (!first) sb.append(",  ");
			sb.append("“" + s + "”");
			first = false;
		}
		return sb.toString();
	}

	private void test1(String line, List<String> desired) {
		Dissection dis = new DissectToWords();
		List<String> actual = dis.dissectLine(line);
		assertEquals("From line=“" + line + "”.  ", desired, actual);
	}
}
