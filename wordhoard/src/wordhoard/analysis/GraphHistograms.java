package wordhoard.analysis;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.PrintWriter;

import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.rendersnake.HtmlCanvas;

import wordhoard.Corpus;
import wordhoard.Dissector;
import wordhoard.corpus.StringCorpus;
import wordhoard.dissectors.DissectToChars;

public class GraphHistograms {

	public static void main(String[] args) throws IOException {
		hist1();
	}
	
	public static void hist1() throws IOException {
//		Dissector dissector = new DissectToChars();
//		Corpus alCorpus = new FileCorpus("Alice", alTextSample);
//		
//		Histogram hist = new Histogram(dissector.dissecting(alCorpus));
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
