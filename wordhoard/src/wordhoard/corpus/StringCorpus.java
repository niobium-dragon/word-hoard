package wordhoard.corpus;

import java.util.regex.Pattern;
import java.util.stream.Stream;

import wordhoard.Corpus;

/*
 * A corpus given by an in-memory string.  Easy for testing!
 */

public class StringCorpus implements Corpus {
	private String body;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public StringCorpus(String body) {
		super();
		this.body = body;
	}

	@Override
	public String toString() {
		return "StringCorpus [body=" + body + "]";
	}

	private static Pattern newlineRE = Pattern.compile("\n");
	
	@Override
	public Stream<String> lines() {
		return newlineRE.splitAsStream(this.getBody());
	}
	
	
	
}
