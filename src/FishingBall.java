import java.awt.Color;
<<<<<<< HEAD

public class FishingBall extends PokeBall
{
	
=======
//import java.util.ArrayList;

public class FishingBall extends Ball
{
>>>>>>> origin/master
	private boolean isAttached = false;

	public FishingBall(World world, double radius, Color color) {
		super(radius, color);
		
<<<<<<< HEAD
		double x = world.getWidth() * Math.random();
		
		x = (x + radius >= world.getWidth()) ? x - (radius+1) : x;
		x = (x - radius <= 0) ? x + (radius+1) : x;
		
		super.setCenter(new Point(x, radius+1));
		super.setV(new Velocity(3 * Math.random(), -3 * Math.random()));
=======
		double x = world.getWidth() * Math.random() - 1;
		
		x = (x + radius >= 1) ? x - (radius+0.01) : x;
		x = (x - radius <= -1) ? x + (radius+0.01) : x;
		
		super.setCenter(new Point(x, (world.getHeight()/2)-radius-0.01));
		super.setV(new Velocity(0.01 * Math.random(), -0.01 * Math.random()));
>>>>>>> origin/master
	}
	
	public boolean hasCollidedWithBottom(World world)
	{
<<<<<<< HEAD
		if (super.getCenter().getPy() + super.getRadius() >= world.getHeight()) {
=======
		if (super.getCenter().getPy() - super.getRadius() <= -world.getHeight()/2) {
>>>>>>> origin/master
			return true;
		}
		return false;
	}
	
<<<<<<< HEAD
=======
	
	
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
>>>>>>> origin/master
	public boolean isAttached()
	{
		return isAttached;
	}
	
<<<<<<< HEAD
	public void attach(PokeBall ball)
	{
		isAttached = true;
//		super.setV(ball.getV());
=======
	public void attach(Ball ball)
	{
		isAttached = true;
		this.setV(ball.getV());
		
		
>>>>>>> origin/master
	}
	
}
