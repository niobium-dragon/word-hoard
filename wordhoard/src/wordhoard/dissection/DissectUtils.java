package wordhoard.dissection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DissectUtils {
	private DissectUtils() throws Exception {
		throw new Exception("This class is not for instantiating");
	}
	
	// String.split doesn't quite respect Unicode; in particular, if AB is a surrogate pair, "AB".split("") = ["A","B"]
	// rather than the ["AB"] which would be more useful.
	// So DissectUtils fixes up that particular bug.
	// (It's not even clear what to do in more complicated cases, so I don't try.)
	// Nor do I behave well on ill-formed strings, like those ending in a single surrogate, 
	// or with two high surrogates together.
	public static String[] split(String in, String boundaryRE) {
		String[] splut = in.split(boundaryRE);
		if (boundaryRE.equals("")) {
			List<String> L = new ArrayList<String>(splut.length);
			boolean hasSurrogate = false;
			String surrogate = null;
			for(String x : splut) {
				if (x.length() != 1) continue;
				char c = x.charAt(0); 
				if (Character.isSurrogate(c)) {
					if (hasSurrogate) {
						// We have both halves; put them together
						L.add(surrogate + x);
						hasSurrogate = false; surrogate = null;
					} else {
						hasSurrogate = true;
						surrogate = x;
					}
				} else {
					// Normal non-surrogate, just goes into L
					hasSurrogate = false;
					surrogate = null;
					L.add(x);
				}
			}
			splut = new String[L.size()];
			L.toArray(splut); // 	Pity we can't reuse it, but I don't want to deal with null end-markers.					
		}
		return splut;
	}
	
	
	public static List<String>  splitAndFilter(String in, String boundaryRE, String ignoreTheseRE) {
		return Arrays.stream(split(in, boundaryRE))
					.filter((s) -> (s != null && !s.equals("")))
					.filter((s) -> (!Pattern.matches(ignoreTheseRE, s)))
					.map(String::toLowerCase) 
					.collect(Collectors.toList());
	}
}
