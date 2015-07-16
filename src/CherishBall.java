import java.awt.Color;


public class CherishBall extends PokeBall
{
	public CherishBall(World world, double radius, Color color) {
super(radius, color);
		
		int sinal1, sinal2;
		double x = world.getWidth() * Math.random();
		
		x = (x + radius >= world.getWidth()) ? x - (radius+1) : x;
		x = (x - radius <= 0) ? x + (radius+1) : x;
		
		if ((int)(Math.random()*100) % 2 == 0)
		{
			sinal1 = 1;
		}
		else sinal1 = -1;
		
		if ((int)(Math.random()*100) % 2 == 0)
		{
			sinal2 = 1;
		}
		else sinal2 = -1;
		
		super.setCenter(new Point(x, radius+1));
		super.setV(new Velocity(sinal1 * 3 * Math.random(), sinal2 * 3 * Math.random()));
	}
	
	public boolean hasCollidedWithBottom(World world)
	{
		if (super.getCenter().getPy() + super.getRadius() >= world.getHeight()) {
			return true;
		}
		return false;
	}
}
