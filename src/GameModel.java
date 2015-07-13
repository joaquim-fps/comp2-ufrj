import java.awt.Color;
import java.util.ArrayList;

public class GameModel
{
	private GameView gameView;
	private World world;
	private ArrayList<BoomBall> boomList;
	private ArrayList<MasterBall> masterList;
	private ArrayList<FishingBall> fishingList;
	private double raio = 30;
	private int width = 600;
	private int height = 600;
	private int quantTargets = 10;
	private Color background = Color.BLUE;
	private int chance = 50;//porcentagem de chance de uma bola alvo aparecer a cada frame do jogo
	
	public GameModel () {
		world = new World (width, height, background);
		fishingList = new ArrayList<FishingBall>();
		masterList = new ArrayList<MasterBall>();
		boomList = new ArrayList<BoomBall>();
		BoomBall b = null;
		MasterBall c = null;
		FishingBall auxBall = null;
		
		for (int i = 0; i < quantTargets; i ++) {
			auxBall = new FishingBall (world, raio, Color.GREEN);
			
			fishingList.add(auxBall);
		}
		
		b = new BoomBall (geraPonto(raio), 
	              new Velocity (-3 * Math.random(), 3 * Math.random()),
	              raio, 
	              Color.MAGENTA);
		
		boomList.add(b);
		
		c = new MasterBall (geraPonto(raio), raio, Color.RED);
		c.setDeltaX(0);
		c.setDeltaY(0);
		masterList.add(c);
	}
	
	public void go() {
		
		boolean explode = false;
		boolean roda = true;
		while (roda == true) {
			
			//Este bloco serve para criar as bolas verdes
			if (((int)(Math.random()*100)) % (101-chance) == 0)
			{
				FishingBall auxBall = new FishingBall(world, raio, Color.GREEN);
				fishingList.add(auxBall);
			}
			
			for (int i = 0; i < fishingList.size(); i++) {
				fishingList.get(i).move(world);
				if ((!fishingList.get(i).isAttached()) && (fishingList.get(i).hasCollidedWithBottom(world)))
				{
					fishingList.remove(fishingList.get(i));
				}
			}
			
			for (int i = 0; i < boomList.size(); i++)
			{
				boomList.get(i).move(world);
			}
			
			for (int i = 0; i < masterList.size(); i++)
			{
				masterList.get(i).move();
			}
		
			boolean colidiu = false;
			for (MasterBall chief : masterList) {
				if (!fishingList.isEmpty()) {
					for (int i = 0; i < fishingList.size(); i++) {
						if (chief.hasCollided(fishingList.get(i))) 
						{
							MasterBall aux = new MasterBall(fishingList.get(i).getCenter(), fishingList.get(i).getRadius(), fishingList.get(i).getColor());
							fishingList.remove(fishingList.get(i));
							aux.setDeltaX(chief.getCenter().getPx() - aux.getCenter().getPx());
							aux.setDeltaY(chief.getCenter().getPy() - aux.getCenter().getPy());
							masterList.add(aux);
							
							colidiu = true;
							break;
						}
					}
					if(!boomList.isEmpty())
					{
						for(int i = 0; i < boomList.size(); i++)
						{
							if(chief.hasCollided(boomList.get(i)))
							{
								boomList.remove(boomList.get(i));
								
								explode = true;
								colidiu = true;
								break;
							}
						}
					}
					
					if (colidiu) { break; }
				}
				else {
					roda = false;
					break;
				}
				
				
			}
			
			if(explode)
			{
			
				for(int i = 0; i < masterList.size(); i++)
				{
					if(i != 0)
					{
						masterList.remove(masterList.get(i));
					}
				}
				
				MasterBall aux = masterList.get(0);
				masterList.clear();
				masterList.add(aux);
				
				explode = false;
			}
			
			gameView.update();
		}
	}
	
	private Point geraPonto(double raio)
	{
		double x = world.getWidth() * Math.random();
		double y = world.getHeight() * Math.random();
		
		x = (x + raio >= world.getWidth()) ? x - (raio+0.01) : x;
		x = (x - raio <= 0) ? x + (raio+0.01) : x;
		
		y = (y + raio >= world.getHeight()) ? y - (raio+0.01) : y;
		y = (y - raio <= 0) ? y + (raio+0.01) : y;
		
		return new Point(x,y);
	}
	
	public void setGameView (GameView gameView)
	{
		this.gameView = gameView;
	}
	
	public World getWorld()
	{
		return world;
	}
	
	public ArrayList<FishingBall> getFishingList()
	{
		return fishingList;
	}
	
	public ArrayList<BoomBall> getBoomList()
	{
		return boomList;
	}
	
	public ArrayList<MasterBall> getMasterList()
	{
		return masterList;
	}
}
