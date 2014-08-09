package wordhoard.fragment;

public class CharFragment extends AbstractFragment {

	// The only thing about CharFragments is, they are char-sized.
	public CharFragment(String text) throws Exception {
		super(text);
		if (text == null || text.length() != 1) {
			throw new Exception("CharFragments must be single characters, not “" + text + "”");
		}
	}

}
