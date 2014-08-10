package wordhoard.analysis;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import wordhoard.Dissection;
import wordhoard.Fragment;

public class Histogram{
	// Inputs
	private Dissection dissection;
	// Recorded data
	private Map<Fragment, Integer> counts = new HashMap<>();

	public Histogram(Dissection dissection) {
		super();
		this.dissection = dissection;
	}
	
	private void analyze() {
		
	}
	
}
