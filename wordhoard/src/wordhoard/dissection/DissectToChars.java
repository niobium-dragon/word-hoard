package wordhoard.dissection;

import wordhoard.Dissection;

import java.util.List;
import java.util.regex.*;

public class DissectToChars implements Dissection {

	public static String IgnoreThese = "\\s";
	public static String Boundary = ""; 
	
	@Override
	public List<String> dissectLine(String line) {
		return DissectUtils.splitAndFilter(line, Boundary, IgnoreThese);
	}

}
