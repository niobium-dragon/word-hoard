package wordhoard.test;

import static org.junit.Assert.*;
import static bard.BardUtil.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import wordhoard.Dissector;
import wordhoard.dissectors.DissectToChars;

@SuppressWarnings("unused")
public class Test_DissectToChars {

	@Test
	public void testDissectLine() {
		test1("", new ArrayList<String>());
		test1("abc", list("a", "b", "c"));
		test1("Abc", list("a", "b", "c"));
		test1("a b\t\t\t\tc", list("a", "b", "c"));
		test1("Ʊɲɨ", list("ʊ", "ɲ", "ɨ"));
		test1("𐅮𨭎", list("𐅮", "𨭎")); // Astral characters — and this broke before DissectUtils.split
	}

	private void test1(String line, List<String> desired) {
		Dissector dis = new DissectToChars();
		List<String> actual = dis.dissectLine(line);
		assertEquals("From line=“" + line + "”.  ", desired, actual);
	}
	
}
