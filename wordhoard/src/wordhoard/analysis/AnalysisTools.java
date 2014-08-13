package wordhoard.analysis;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import wordhoard.Dissector;
import wordhoard.corpus.FileCorpus;

public class AnalysisTools {
	private AnalysisTools(){} // Module

	public static List<Histogram> histogramsOfCorpora(Dissector dissector,
			String... corporaNames) {
		List<Histogram> hists = Stream.of(corporaNames)	
					.map((name) -> new FileCorpus(name, Paths.get("corpora", name + ".txt")))
					.map((corpus) -> dissector.dissecting(corpus)) // Stream<Dissection>
					.map((dissection) -> new Histogram(dissection))  // Stream<Histogram>
					.collect(Collectors.toList());
		return hists;
	}
	

}
