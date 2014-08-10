package bard;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class BardUtil {
	private BardUtil(){}
	
	public static void gottaImplementThis() {
		throw new RuntimeException("Gotta implement this!");
	}
	
	// Print a message and die.
	public static void fatallet(String format, Object... args) {
		String s = String.format(format, args);
		System.err.println(s);
		throw new RuntimeException(s);
	}
	

	
	@SafeVarargs
	public static <T>  List<T> list(final T ... stuff) {
		// Can't use Arrays.toList(stuff) because it's not @SafeVarargs.
		List<T> L = new ArrayList<T>(stuff.length);
		for(T t : stuff) { L.add(t); }
		return L;
	}
	
	// Turn an Optional<T> into a Stream<T> containing 0 or 1 elements.
	// http://stackoverflow.com/questions/22725537/using-java-8s-optional-with-streamflatmap
	public static <T> Stream<T> streamopt(Optional<T> opt) {
	    return opt.map(Stream::of)
	              .orElseGet(Stream::empty);
	}
}
