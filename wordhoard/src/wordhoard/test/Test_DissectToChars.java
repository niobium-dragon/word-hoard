package wordhoard.test;

import static org.junit.Assert.*;
import static bard.BardUtil.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import wordhoard.Dissection;
import wordhoard.dissection.DissectToChars;

@SuppressWarnings("unused")
public class Test_DissectToChars {

	@Test
	public void testDissectLine() {
		test1("", new ArrayList<String>());
		test1("abc", list("a", "b", "c"));
		test1("a b\t\t\t\tc", list("a", "b", "c"));
		test1("Ʊɲɨ", list("Ʊ", "ɲ", "ɨ"));
		test1("𐅮𨭎", list("𐅮", "𨭎")); // Astral characters — and this broke before DissectUtils.split
	}

	private void test1(String line, List<String> desired) {
		Dissection dis = new DissectToChars();
		List<String> actual = dis.dissectLine(line);
		assertEquals("From line=“" + line + "”.  ", desired, actual);
	}
	
}
