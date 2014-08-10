package wordhoard;

import java.util.stream.Stream;

public interface Corpus {
	Stream<String> lines();
	String name();
}
