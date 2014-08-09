package wordhoard;

/*
 * A Fragment is a piece of a corpus, like a character or a word.
 * (It does not include provenance) 
 */
public interface Fragment {
	// The bare text of the fragment.
	String text();
}
