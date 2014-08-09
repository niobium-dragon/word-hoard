package wordhoard.test;

import static org.junit.Assert.*;

import org.junit.Test;

import wordhoard.fragment.CharFragment;

public class Test_CharFragment {

	@Test
	public void testSizeIsOne() {
		for(String s : Constants.strings){
			assertEquals("s=" + s,( s != null && s.length() == 1 ), canMakeCharFragmentFrom(s) );
		}
	}
	
	private static boolean canMakeCharFragmentFrom(String s) {
		try {
			CharFragment charFragment = new CharFragment(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
