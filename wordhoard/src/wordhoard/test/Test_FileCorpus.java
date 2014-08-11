package wordhoard.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static bard.BardUtil.*;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bard.BardUtil;
import wordhoard.Corpus;
import wordhoard.Dissector;
import wordhoard.Fragment;
import wordhoard.analysis.Histogram;
import wordhoard.corpus.FileCorpus;
import wordhoard.corpus.StringCorpus;
import wordhoard.dissectors.DissectToChars;

public class Test_FileCorpus {

	private Path tmpFile;

	@Before
	public void setUp() throws Exception {
		this.tmpFile = Files.createTempFile("test-file-corpus", ".txt");
		PrintWriter pw = new PrintWriter(this.tmpFile.toFile());
		pw.println("Alice was beginning to get very tired of sitting by her sister on the");
		pw.println("bank, and of having nothing to do: once or twice she had peeped into the");
		pw.println("book her sister was reading, but it had no pictures or conversations in");
		pw.println("it, 'and what is the use of a book,' thought Alice 'without pictures or");
		pw.println("conversations?'");
		pw.close();

	}

	@After
	public void tearDown() throws Exception {
		Files.delete(this.tmpFile);
	}

	@Test
	public void testLines() throws Exception {
		try (FileCorpus fc = new FileCorpus("Alice", this.tmpFile)) {
			long nLines = fc.lines()
//					.peek((x) -> {System.out.printf("∆2 %s\n", x);})
					.count();
			;
			assertEquals(nLines, 5);
		}
	}
	
	@Test
	public void testDissection() throws Exception {
		Dissector dissector = new DissectToChars();
		Corpus alCorpus = new FileCorpus("Alice", this.tmpFile);
		Histogram hist = new Histogram(dissector.dissecting(alCorpus));
		assertEquals(hist.countOf("a"), 15);
		assertEquals(hist.countOf("'"), 4);
		assertEquals(hist.countOf(":"), 1);
	}




}
