package drawings;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class Point {
	int x;
	int y;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

abstract class Shape {
	String color;
}

class Circle extends Shape {
	Point centre;
	int radius;
	
	Circle(Point centre, int radius) {
		this.centre = centre;
		this.radius = radius;
	}
}

class Polygon extends Shape {
	Point[] vertices;
	
	Polygon(Point[] vertices) {
		this.vertices = vertices.clone();
	}
			
}

class Drawing {
	
	Shape[] shapes;
	
	String toSVG() {
		String result = "<svg xmlns='http://w3c.org/2000/svg'>";
		for (Shape shape : shapes) {
//			if (shape instanceof Circle) {
//				Point centre = ((Circle) shape).centre;
			
//			if (shape instanceof Circle circle) {
//				Point centre = circle.centre;
//				result += "<circle x='" + centre.x + "' y='" + centre.y + "' r='" + circle.radius + "' />";
//			}	else {
//				Polygon polygon = (Polygon)shape;
//				result += "<polygon points=";
//				for (Point point : polygon.vertices)
//					result += point.x + " " + point.y + " ";
//				result += "' />";
			
			switch (shape) {
			case Circle circle -> {
				Point centre = circle.centre;
				result += "<circle x='" + centre.x + "' y='" + centre.y + "' r='" + circle.radius + "' />";
			}
			default -> {
				Polygon polygon = (Polygon)shape;
				result += "<polygon points=";
				for (Point point : polygon.vertices)
					result += point.x + " " + point.y + " ";
				result += "' />";
			}
			}
		}
		return result + "</svg>";
	}

}

public class DrawingTest {
	
	@Test
	void testDrawing() {
		Drawing myDrawing = new Drawing();
		myDrawing.shapes = new Shape[] {
				new Circle(new Point(5, 5), 10),
				new Polygon(new Point[] {new Point(0,10), new Point(10,0), new Point(10,10)})
		};
		assertEquals("<svg xmlns='http://w3c.org/2000/svg'><circle x='5' y='5' r='10' /><polygon points=0 10 10 0 10 10 ' /></svg>", myDrawing.toSVG());
	}
}