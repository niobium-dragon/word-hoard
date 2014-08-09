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

	
	
	@Test
	public void testGetBody() {
		for(String s : Constants.strings) {
			StringCorpus sc = new StringCorpus(s);
			assertEquals(sc.getBody(), s);
		}
	}

	@Test
	public void testSetBody() {
		StringCorpus sc = new StringCorpus("anything else");
		// I gather Hamcrest has better array methods but I don't feel like looking now.
		for(String s : Constants.strings) {
			assertThat(sc.getBody(), not(equalTo(s)));
		}
		for(String s : Constants.strings) {
			sc.setBody(s);
			assertThat(sc.getBody(), is(equalTo(s)));
		}
	}


	@Test
	public void testToString() {
		for(String s : Constants.strings) {
			StringCorpus sc = new StringCorpus(s);
			if (s != null) {
				assertThat(sc.toString(), containsString(s));
			}
		}
	}

}
