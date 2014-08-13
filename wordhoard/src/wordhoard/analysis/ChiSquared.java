package wordhoard.analysis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.math3.stat.inference.ChiSquareTest;

import wordhoard.Dissector;
import wordhoard.dissectors.DissectToChars;

public class ChiSquared {
	public static void main(String[] args) throws Exception {
		chisquare(
				"alice-in-wonderland", 
				"the-tempest", 
				"alice-looking-glass", 
				"can-you-forgive-her"
				);
	}
	
	private static void chisquare(String ... corporaNames) throws Exception {
		Dissector dissector = new DissectToChars();
		List<Histogram> hists = AnalysisTools.histogramsOfCorpora(dissector, corporaNames);
		
		Set<String> keyset = new HashSet<String>();
		hists.stream().forEach((hist) -> {keyset.addAll(hist.keyStrings());});
		List<String> keys = keyset.stream().sorted().collect(Collectors.toList());
		String keystr = keys.stream().collect(Collectors.joining());

		for (int i = 0; i < hists.size(); i++) {
			Histogram hi = hists.get(i);
			for (int j = i; j < hists.size(); j++) {
				// We are including j==i — better get 'same' when the corpora are the same! 
				Histogram hj = hists.get(j);
				chisq(keys, hi, hj);
			}
		}
	}
	
	private static void chisq(List<String> keys, Histogram h1, Histogram h2) {
		// We have to be a bit careful constructing our observed and expected sets.
		// We need expected[k] > 0 always.
		// (And best to have it > 5)
		List<Double> expected = new ArrayList<Double>(keys.size());
		List<Long> observed = new ArrayList<>(keys.size());
		// We'll put all the things with small e's into a "remnants" category. 
		double eRemnants = 0.0;
		long oRemnants = 0;
		List<String> sRemnants = new ArrayList<>();
		
		double small = 5.0;
		
		long No = h2.N();
		
		for (String k : keys) {
			double e = h1.fracOf(k) * No; 
			long o = h2.countOf(k);
			
			if (e >= small) {
				// Enough E in this category to use it.
				expected.add(e);
				observed.add(o);
			} else {
				// Remnant
				eRemnants += e;
				oRemnants += o;
				sRemnants.add(k); 
			}
		}
		
		if (eRemnants > 0) {
			expected.add(eRemnants);
			observed.add(oRemnants);
		}
		
		int n = expected.size();
		double[] aExpected = new double[n];
		long[] aObserved = new long[n];
		for(int i = 0; i < n; i++ ) {
			aExpected[i] = expected.get(i);
			aObserved[i] = observed.get(i);
		}
		
		ChiSquareTest x2 = new ChiSquareTest();
		double x = x2.chiSquare(aExpected, aObserved);
		double p = x2.chiSquareTest(aExpected, aObserved);
		
		System.out.printf("p(%s,%s) = %g from x=%g\n", h1.name(), h2.name(), p, x);
				
	}
	
	
}
