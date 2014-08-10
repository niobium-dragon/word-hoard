package wordhoard;

import java.util.stream.Stream;

public class Dissection {
	private String name;
	private Stream<Fragment> fragStream;
	public Dissection(String name, Stream<Fragment> fragStream) {
		super();
		this.name = name;
		this.fragStream = fragStream;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Stream<Fragment> getFragStream() {
		return fragStream;
	}
	public void setFragStream(Stream<Fragment> fragStream) {
		this.fragStream = fragStream;
	}
	@Override
	public String toString() {
		return String.format("Dissection(%s)", name, fragStream);
	}
	
	
}
