package wordhoard.analysis;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.rendersnake.HtmlCanvas;

import wordhoard.Corpus;
import wordhoard.Dissector;
import wordhoard.corpus.FileCorpus;
import wordhoard.corpus.StringCorpus;
import wordhoard.dissectors.DissectToChars;

public class GraphHistograms {

	public static void main(String[] args) throws Exception {
		hist2(
				"alice-in-wonderland", 
				//"the-tempest", 
				//"alice-looking-glass", 
				"can-you-forgive-her"
				);
	}
	
	public static void hist1() throws IOException {
		Dissector dissector = new DissectToChars();
		Corpus alCorpus = new FileCorpus("Alice", Paths.get("corpora/alice-in-wonderland.txt"));
		
		Histogram hist = new Histogram(dissector.dissecting(alCorpus));
		System.out.printf("∆ counts=%s\n", hist.getCounts());
	}
	
	// Canvas size
	private static int mx = 500, my = 200;
	
	// rx between 0 and 1
	private static int rx2x(double rx) {
		return (int) Math.floor(mx * rx);
	}
	
	private static int ry2y(double ry) {
		return (int) Math.floor(my * (ry));
	}
	
	private static void hist2(String ... corporaNames) throws Exception {
		Dissector dissector = new DissectToChars();
		List<Histogram> hists = AnalysisTools.histogramsOfCorpora(dissector, corporaNames);
		
		Set<String> keyset = new HashSet<String>();
		hists.stream().forEach((hist) -> {keyset.addAll(hist.keyStrings());});
		System.out.printf("∆%s\n", keyset);
		
		List<String> keys = keyset.stream().sorted().collect(Collectors.toList());
		String keystr = keys.stream().collect(Collectors.joining());
		System.out.printf("∆keystr=%s\n", keystr);
		
		
		SVGGraphics2D g2 = new SVGGraphics2D(mx, my);
		Color[] colors = new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE};
		if (corporaNames.length > colors.length) {
			throw new RuntimeException(String.format("Crappity, I can't handle %s corpora just now.  Add more colors.", colors.length));
		}
		
		double maxFrac = -1;
		for(Histogram hist : hists) maxFrac = Math.max(maxFrac, hist.maxFrac());
		
		// We need indices here!
		int Nj = keys.size();
		for(int i = 0; i < corporaNames.length; i++ ) {
			double sumFreq = 0;
			g2.setPaint(colors[i]);
			Histogram hist = hists.get(i);
			int lastX = 0, lastY = 0;
			for(int j = 0; j < Nj; j++) {
				// x coords in [0,1] evenly-spaced space
				// y coords in [0,1] fraction-of-max-count space.
				double x1 = ( (double) j) / (double) Nj;
				double w2 = ( (double) (1)) / (double) Nj;
				String s = keys.get(j);
				double h2 = hist.fracOf(s) / maxFrac;
				double y1 = 1.0 - h2;
				int thisX = rx2x(x1);
				int thisY = ry2y(y1);
				sumFreq += hist.fracOf(s);
				int ih2 =  ry2y(h2);
//				g2.draw(new Rectangle(rx2x(x1), ry2y(y1), rx2x(w2), ih2));
				g2.drawLine(lastX, lastY, thisX, thisY);
				g2.drawString(s, thisX, my-4);
				lastX = thisX; lastY = thisY;
			}
			System.out.printf("∆sumFreq=%g\n", sumFreq);
		}
		
		String svgElement = g2.getSVGElement();
		System.out.printf("∆ svgElement=\n" + svgElement);

		// renderSnake stuff
		HtmlCanvas html = new HtmlCanvas();
		html
		.html()
		.body()
		.h1()
			.content("renderSnake it")
		    .write(svgElement, false)
		._body()
		._html();
		
		
		try (PrintWriter pw = new PrintWriter("/tmp/la.html")) {
			pw.println(html.toHtml());
		}
			
	}

	public static void exemplar() throws IOException {
		// SVG stuff
		SVGGraphics2D g2 = new SVGGraphics2D(300, 200);
		g2.setPaint(Color.RED);
		g2.draw(new Rectangle(10, 10, 280, 180));
		String svgElement = g2.getSVGElement();
		System.out.printf("∆ svgElement=\n" + svgElement);

		// renderSnake stuff
		HtmlCanvas html = new HtmlCanvas();
		html
		.html()
		.body()
		.h1()
			.content("renderSnake it")
		    .write(svgElement, false)
		._body()
		._html();
		
		System.out.printf("∆ La!\n%s\n", html.toHtml());
		
		try (PrintWriter pw = new PrintWriter("/tmp/la.html")) {
			pw.println(html.toHtml());
		}
	}

}
