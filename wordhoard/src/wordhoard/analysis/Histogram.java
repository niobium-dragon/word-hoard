package wordhoard.analysis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import wordhoard.Dissection;
import wordhoard.Fragment;

public class Histogram{
	// Inputs
	private Dissection dissection;
	// Recorded data.  
	private Map<Fragment, Long> counts = new HashMap<>();

	public Histogram(Dissection dissection) {
		super();
		this.dissection = dissection;
		this.gobble();
	}
	

	public Dissection getDissection() {
		return dissection;
	}


	public Map<Fragment, Long> getCounts() {
		return counts;
	}


	// Read the streams, and put their info into local data structures.
	private void gobble() {
		this.counts = this.dissection.getFragStream()
				.collect(
						Collectors.groupingBy(
								(frag)->frag, 
								Collectors.counting()));
	}
	
	public long countOf(String s) {
		return this.counts.getOrDefault(this.dissection.makeFragment(s), 0L);
	}

}
