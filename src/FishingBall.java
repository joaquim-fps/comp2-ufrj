import java.awt.Color;
//import java.util.ArrayList;

public class FishingBall extends Ball
{
	private boolean isAttached = false;

	public FishingBall(World world, double radius, Color color) {
		super(radius, color);
		
		double x = world.getWidth() * Math.random() - 1;
		
		x = (x + radius >= 1) ? x - (radius+0.01) : x;
		x = (x - radius <= -1) ? x + (radius+0.01) : x;
		
		super.setCenter(new Point(x, (world.getHeight()/2)-radius-0.01));
		super.setV(new Velocity(0.01 * Math.random(), -0.01 * Math.random()));
	}
	
	public boolean hasCollidedWithBottom(World world)
	{
		if (super.getCenter().getPy() - super.getRadius() <= -world.getHeight()/2) {
			return true;
		}
		return false;
	}
	
	
	
/*	public void move(MasterBall masterBall, World world) {
		super.getCenter().setPx(super.getCenter().getPx() + super.getV().getVx());
		super.getCenter().setPy(super.getCenter().getPy() + super.getV().getVy());
		
		if (!isAttached){
			if (Math.abs(super.getCenter().getPx()) + super.getRadius() >= world.getWidth()/2) {
				super.getV().setVx(-super.getV().getVx());
			}
			
			if (super.getCenter().getPy() + super.getRadius() >= world.getHeight()/2) {
				balls.remove(index);
			}
		}
		else
		{
			if (Math.abs(super.getCenter().getPx()) + super.getRadius() >= world.getWidth()/2) {
				super.getV().setVx(-super.getV().getVx());
			}
			
			if (Math.abs(super.getCenter().getPy()) + super.getRadius() >= world.getHeight()/2) {
				super.getV().setVy(-super.getV().getVy());
			}
		}
	}
*/	
	public boolean isAttached()
	{
		return isAttached;
	}
	
	public void attach(Ball ball)
	{
		isAttached = true;
		this.setV(ball.getV());
		
		
	}
	
}
