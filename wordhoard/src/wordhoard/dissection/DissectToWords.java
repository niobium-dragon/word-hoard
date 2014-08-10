package wordhoard.dissection;

import java.util.List;

import wordhoard.Dissection;

public class DissectToWords implements Dissection {

	public static String IgnoreThese = "";
	public static String Boundary = "\\b(\\s+\\b)?"; 
	
	@Override
	public List<String> dissectLine(String line) {
		return DissectUtils.splitAndFilter(line, Boundary, IgnoreThese);
	}
}
