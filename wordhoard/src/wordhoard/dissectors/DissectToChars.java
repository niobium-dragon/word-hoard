package wordhoard.dissectors;

import wordhoard.Dissector;
import wordhoard.Fragment;
import wordhoard.fragment.CharFragment;

import java.util.List;
import java.util.Optional;

import bard.BardUtil;

public class DissectToChars implements Dissector {

	public static String IgnoreThese = "\\s";
	public static String Boundary = ""; 
	
	@Override
	public List<String> dissectLine(String line) {
		return DissectUtils.splitAndFilter(line, Boundary, IgnoreThese);
	}

	@Override
	public Fragment makeFragment(String s) {
		try {
		return new CharFragment(s);
		} catch (Exception ex) {
			System.err.println("Oh nose!  Couldn't make a CharFragment from “" + s + "” on account of " + ex);
			return null;
		}
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
