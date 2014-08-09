package wordhoard.dissection;

import wordhoard.Dissection;

import java.util.List;

public class DissectToChars implements Dissection {

	public static String IgnoreThese = "\\s";
	public static String Boundary = ""; 
	
	@Override
	public List<String> dissectLine(String line) {
		return DissectUtils.splitAndFilter(line, Boundary, IgnoreThese);
	}

}
