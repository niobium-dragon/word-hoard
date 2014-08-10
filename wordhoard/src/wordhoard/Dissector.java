package wordhoard;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;
import static bard.BardUtil.*;

public interface Dissector {
	// Break a line of input text into suitable Fragments.
	List<String> dissectLine(String line);


	Optional<Fragment> stringToFragment(String s);

	// new DissectionException(String.format("Can't dissect ‘%s’ as a %s", s, this));

	// Stream the fragments from a corpus
	default Dissection dissecting(Corpus corpus)  {
		Stream<String> lines = corpus.lines();
		Function<String, Stream<Fragment>> fragmentizeLine = (line) -> {
			return this.dissectLine(line)
					.stream()
					.flatMap((x) -> streamopt(stringToFragment(x)));
		};
		Stream<Fragment> fragStream = lines.flatMap(fragmentizeLine);
		return new Dissection(corpus.name(), fragStream);
	}
}

