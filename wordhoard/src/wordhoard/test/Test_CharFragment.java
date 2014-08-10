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
			new CharFragment(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Test
	public void testEqualsAndHashCode() throws Exception {
		int nYes = 0, nNo = 0;
		for (String s : Constants.strings) {
			for (String t : Constants.strings) {
				if (canMakeCharFragmentFrom(s) && canMakeCharFragmentFrom(t)) {
					CharFragment ss = new CharFragment(s);
					CharFragment tt = new CharFragment(t);
					boolean eq = s.equals(t);
					if (eq) {
						nYes ++;
					} else {
						nNo ++;
					}
					boolean eeqq = ss.equals(tt);
					assertEquals(eq, eeqq);
					assertEquals(ss.hashCode(), s.hashCode());
				}
			}
		}
		assertTrue(nYes > 1);
		assertTrue(nNo > 1);
	}
	
}
