import java.awt.Color;
import java.awt.MouseInfo;

public class MasterBall extends PokeBall
{
	private double deltaX, deltaY;
	
	public MasterBall(Point center, double radius, Color color) {
		super.setCenter(center);
		super.setRadius(radius);
		super.setColor(color);
	}
	
	public void move()
	{
		Point mousePos = new Point(MouseInfo.getPointerInfo().getLocation().getX(), MouseInfo.getPointerInfo().getLocation().getY());
		super.getCenter().setPx(mousePos.getPx()-deltaX);
		super.getCenter().setPy(mousePos.getPy()-deltaY);
	}

	public double getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(double deltaX) {
		this.deltaX = deltaX;
	}

	public double getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}
	
}
