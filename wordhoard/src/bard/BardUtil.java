package bard;

import java.util.Arrays;
import java.util.List;

public class BardUtil {
	private BardUtil(){}
	
	public static <T>  List<T> list(T ... stuff) {
		return Arrays.asList(stuff);
	}
}
