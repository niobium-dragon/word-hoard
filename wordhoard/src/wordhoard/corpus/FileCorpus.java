package wordhoard.corpus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import static bard.BardUtil.*;
import wordhoard.Corpus;

public class FileCorpus implements Corpus {

	private String name;
	private Path path;
	private BufferedReader reader;

	public FileCorpus(String name, Path path) {
		super();
		this.name = name;
		this.path = path;
		this.reader = null;
	}

	@Override
	public String name() {
		return this.name;
	}
	
	@Override
	public void close() throws Exception {
		this.reader.close();
	}

	@Override
	public Stream<String> lines() {
		try {
			this.reader = new BufferedReader(new FileReader(this.path.toString()));
			return reader.lines();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
