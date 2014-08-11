package wordhoard.analysis;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.PrintWriter;

import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.rendersnake.HtmlCanvas;

public class GraphHistograms {

	public static void main(String[] args) throws IOException {
		SVGGraphics2D g2 = new SVGGraphics2D(300, 200);
		g2.setPaint(Color.RED);
		g2.draw(new Rectangle(10, 10, 280, 180));
		String svgElement = g2.getSVGElement();
		System.out.printf("∆ svgElement=\n" + svgElement);


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
