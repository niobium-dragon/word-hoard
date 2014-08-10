package wordhoard.dissection;

import wordhoard.Dissection;
import wordhoard.Fragment;
import wordhoard.fragment.CharFragment;

import java.util.List;
import java.util.Optional;

import bard.BardUtil;

public class DissectToChars implements Dissection {

	public static String IgnoreThese = "\\s";
	public static String Boundary = ""; 
	
	@Override
	public List<String> dissectLine(String line) {
		return DissectUtils.splitAndFilter(line, Boundary, IgnoreThese);
	}

	@Override
	public Optional<Fragment> stringToFragment(String s) {
		try {
			return Optional.of(new CharFragment(s));
		} catch (Exception e) {
			System.err.printf("Oh dearie!  Exception in DissectToChars.stringToFragment(%s), exn=%s\n", s, e);
			return Optional.empty();
		}
	}
	
}
