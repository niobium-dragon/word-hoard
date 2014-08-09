package wordhoard;

import java.util.List;

public interface Dissection {
	// Break a line of input text into suitable Fragments.
	List<String> dissectLine(String line);
}
