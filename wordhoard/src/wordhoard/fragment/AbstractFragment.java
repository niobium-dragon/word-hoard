package wordhoard.fragment;

import wordhoard.Fragment;

public class AbstractFragment implements Fragment {

	private String text;

	public AbstractFragment(String text) {
		super();
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}



	@Override
	public String text() {
		return this.text;
	}

}
