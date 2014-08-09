package bard;

import java.util.ArrayList;
import java.util.List;

public class BardUtil {
	private BardUtil(){}
	
	@SafeVarargs
	public static <T>  List<T> list(final T ... stuff) {
		// Can't use Arrays.toList(stuff) because it's not @SafeVarargs.
		List<T> L = new ArrayList<T>(stuff.length);
		for(T t : stuff) { L.add(t); }
		return L;
	}
}
