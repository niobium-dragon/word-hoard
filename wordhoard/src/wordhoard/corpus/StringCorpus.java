package wordhoard.corpus;

import java.util.regex.Pattern;
import java.util.stream.Stream;

import wordhoard.Corpus;

/*
 * A corpus given by an in-memory string.  Easy for testing!
 */

public class StringCorpus implements Corpus {
	private String body;
	private String name;
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String name() {
		return this.name;
	}
	
	public StringCorpus(String name, String body) {
		super();
		this.name = name;
		this.body = body;
	}

	@Override
	public String toString() {
		return "StringCorpus [name=" + name + "; body=" + body + "]";
	}

	private static Pattern newlineRE = Pattern.compile("\n");
	
	@Override
	public Stream<String> lines() {
		return newlineRE.splitAsStream(this.getBody());
	}
	
	
	
}
