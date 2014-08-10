package wordhoard.dissectors;

import java.util.List;
import java.util.Optional;

import wordhoard.Dissector;
import wordhoard.Fragment;
import wordhoard.fragment.StringFragment;

public class DissectToWords implements Dissector {

	public static String IgnoreThese = "";
	public static String Boundary = "\\b(\\s+\\b)?"; 
	
	@Override
	public List<String> dissectLine(String line) {
		return DissectUtils.splitAndFilter(line, Boundary, IgnoreThese);
	}
	
	@Override
	public Fragment makeFragment(String s) {
		return new StringFragment(s);
	}
	
	
	@Override
	public Optional<Fragment> stringToFragment(String s) {
		return Optional.of(new StringFragment(s));
	}
}
