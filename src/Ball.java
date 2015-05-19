import java.awt.Color;

public class Ball {
	private Point center;
	private Velocity v;
	private double radius;
	private Color color;
	
	public Ball(Point center, Velocity v, double radius, Color color) {
		this.center = center;
		this.v = v;
		this.radius = radius;
		this.color = color;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public Velocity getV() {
		return v;
	}

	public void setV(Velocity v) {
		this.v = v;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void move(World world) {
		center.setPx(center.getPx() + v.getVx());
		center.setPy(center.getPy() + v.getVy());
		
		if (Math.abs(center.getPx()) + radius >= world.getWidth()/2) {
			v.setVx(-v.getVx());
		}
		
		if (Math.abs(center.getPy()) + radius >= world.getHeight()/2) {
			v.setVy(-v.getVy());
		}
	}
	
	public void draw() {
		StdDraw.setPenColor(color);
		StdDraw.filledCircle(center.getPx(), center.getPy(), radius);
	}
	
	public boolean hasCollided(Ball b) {
		double deltaX = this.center.getPx()-b.center.getPx();
		double deltaY = this.center.getPy()-b.center.getPy();
		
		if (Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)) <= this.radius + b.getRadius()) {
			return true;
		}
		
		return false;
	}
	
	public String toString() {
		return "Ball:" + 
				"\n center=(" + center.getPx() + "," + center.getPy() + ");" + 
				"\n radius=" + radius + ";" + 
				"\n velocity=(" + v.getVx() + "," + v.getVy() + ")";
	}
}