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
	private long N = 0;
	private long maxCount;
	private double maxFrac;

	public Histogram(Dissection dissection) {
		super();
		this.dissection = dissection;
		this.gobble();
	}
	
	
	public String name() {
		return this.dissection.getName();
	}

	public Dissection getDissection() {
		return dissection;
	}


	public Map<Fragment, Long> getCounts() {
		return counts;
	}
	
	
	public Set<String> keyStrings() {
		return counts.keySet().stream().map(Fragment::toString).collect(Collectors.toSet());
	}
	
	public long maxCount() {
		return this.maxCount;
	}
	public double maxFrac() {
		return this.maxFrac;
	}


	// Read the streams, and put their info into local data structures.
	private void gobble() {
		this.counts = this.dissection.getFragStream()
				.collect(
						Collectors.groupingBy(
								(frag)->frag, 
								Collectors.counting()));
		this.N = this.counts.values().stream().collect(Collectors.summingLong((x)->x));
		this.maxCount = -1;
		this.maxFrac = -1;
		// Phrasing this in streams was unduly awkward.
		for(Fragment f: this.counts.keySet()) {
			this.maxCount = Math.max(this.maxCount, this.countOf(f.text()));
			this.maxFrac = Math.max(this.maxFrac, this.fracOf(f.text()));
		}
	}
	
	public long countOf(String s) {
		return this.counts.getOrDefault(this.dissection.makeFragment(s), 0L);
	}
	
	public long N() {
		return this.N;
	}
	
	public double fracOf(String s) {
		return ((double) this.countOf(s)) / ((double) this.N);
	}

}
