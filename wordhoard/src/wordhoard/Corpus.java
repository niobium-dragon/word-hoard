package wordhoard;

import java.util.stream.Stream;

public interface Corpus extends AutoCloseable {
	Stream<String> lines();
	String name();
}
