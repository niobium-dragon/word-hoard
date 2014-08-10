package wordhoard.corpus;

import java.util.stream.Stream;

import static bard.BardUtil.*;
import wordhoard.Corpus;

public class FileCorpus implements Corpus {
	
	String name;

	public FileCorpus(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public String name() {
		return this.name;
	}

	@Override
	public Stream<String> lines() {
		// TODO
			gottaImplementThis();
			return null;
	}
	
}
