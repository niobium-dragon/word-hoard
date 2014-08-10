package wordhoard;

/*
 * A Fragment is a piece of a corpus, like a character or a word.
 * (It does not include provenance) 
 */
public interface Fragment {
	// The bare text of the fragment.
	String text();
	// And they need to be hashable, so please implement equals() and hashCode()
	// We can't do it from the interface, so here are static methods that do the right thing.
	static  public boolean equals(Fragment This, Object obj) {
		if (obj instanceof Fragment) {
			Fragment frag = (Fragment) (obj);
			return This.text().equals(frag.text());
		} else return false;
	}
	static public int hashCode(Fragment This) {
		return This.text().hashCode();
	}
}
