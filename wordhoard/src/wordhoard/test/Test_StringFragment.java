package wordhoard.test;

import static org.junit.Assert.*;

import org.junit.Test;

import wordhoard.fragment.CharFragment;
import wordhoard.fragment.StringFragment;

public class Test_StringFragment {

	@Test
	public void testEqualsAndHashCode() throws Exception {
		// approximately the same as a similar test in Test_CharFragment
		int nYes = 0, nNo = 0;
		for (String s : Constants.strings) {
			for (String t : Constants.strings) {
				if (s == null || t == null) continue;
				StringFragment ss = new StringFragment(s);
				StringFragment tt = new StringFragment(t);
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
		assertTrue(nYes > 1);
		assertTrue(nNo > 1);
	}
}
