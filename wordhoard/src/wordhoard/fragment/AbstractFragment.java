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
	
	@Override
	public boolean equals(Object obj) {
		return Fragment.equals(this, obj);
	}

	@Override
	public int hashCode() {
		return Fragment.hashCode(this);
	}
	
}
