package wordhoard.test;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import wordhoard.Corpus;
import wordhoard.Dissector;
import wordhoard.Fragment;
import wordhoard.analysis.Histogram;
import wordhoard.corpus.StringCorpus;
import wordhoard.dissectors.DissectToChars;
import wordhoard.fragment.CharFragment;

public class Test_Histogram {
	
	private static String bbTextSample = "I woke up and ate nine rabbits and flew to the Tumult Sands to clean up.  The Tumult Sands are a little scrap of desert where indigo sand boils around. It's never still.  It's like it's being stirred by a huge wooden spoon from the sky, and like someone with about seven heads is underneath it puffing it up.  It used to be sacred to the mhelvul's gods, before my parents chased them off and gave the mhelvul something more worthwhile to serve.  The mhelvul used to tie a spare child of the noble classes upside-down on a heavy iron scaffold there every spring and fall, and let the sands scourge them to death.  The desert gods would gather around and lap up the child's blood and spirit as he died.  What good that did the mhelvul, I don't know.  Kept the desert gods from eating them more, I guess. Only, the mhelvul don't go into the desert, and the desert gods didn't leave it.  There's nothing there but stirred-up indigo sand and that horrid iron scaffold.";
	private static String alTextSample = "Alice was beginning to get very tired of sitting by her sister on the bank, and of having nothing to do: once or twice she had peeped into the book her sister was reading, but it had no pictures or conversations in it, 'and what is the use of a book,' thought Alice 'without pictures or conversations?'  So she was considering in her own mind (as well as she could, for the hot day made her feel very sleepy and stupid), whether the pleasure of making a daisy-chain would be worth the trouble of getting up and picking the daisies, when suddenly a White Rabbit with pink eyes ran close by her. There was nothing so very remarkable in that; nor did Alice think it so very much out of the way to hear the Rabbit say to itself, 'Oh dear! Oh dear! I shall be late!' (when she thought it over afterwards, it occurred to her that she ought to have wondered at this, but at the time it all seemed quite natural); but when the Rabbit actually took a watch out of its waistcoat-pocket, and looked at it, and then hurried on, Alice started to her feet, for it flashed across her mind that she had never before seen a rabbit with either a waistcoat-pocket, or a watch to take out of it, and burning with curiosity, she ran across the field after it, and fortunately was just in time to see it pop down a large rabbit-hole under the hedge.";

	@Test
	public void test() {
		Dissector dissector = new DissectToChars();
		Corpus alCorpus = new StringCorpus("Alice", alTextSample);
		
		Histogram hist = new Histogram(dissector.dissecting(alCorpus));
		assertEquals(alCorpus.name(), "Alice");
		Map<Fragment, Long> counts = hist.getCounts();
		assertEquals(hist.countOf("a"), 87L); // I counted
		assertEquals(hist.countOf("-"), 4L); // I counted
		assertEquals(hist.countOf("…"), 0L); // I counted
		// Check N().  Count the characters, except for the spaces.
		String alSquish = alTextSample.replace(" ", "");
		assertEquals(hist.N(), alSquish.length());
	}

}
