import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

public class GameModel
{
	private GameController gameController;
	private GameView gameView;
	private World world;
	private ArrayList<BoomBall> boomList;
	private ArrayList<MasterBall> masterList;
	private ArrayList<FishingBall> fishingList;
	private double raio = 30;
	private int width;
	private int height;
	private int quantTargets = 10;
	private Color background = Color.BLUE;
	private int chance = 50;//porcentagem de chance de uma bola alvo aparecer a cada frame do jogo
	private boolean explode = false;
	private boolean roda = true;
	
	public GameModel () {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int)screenSize.getWidth();
		height = (int)screenSize.getHeight()-35;
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
	
	public void createFishingBall()
	{
		if (((int)(Math.random()*100)) % (101-chance) == 0)
		{
			FishingBall auxBall = new FishingBall(world, raio, Color.GREEN);
			fishingList.add(auxBall);
		}
	}
	
	public void createBoomBall()
	{
		if (((int)(Math.random()*100)) % (101-chance) == 0)
		{
			BoomBall auxBall = new BoomBall(world, raio, Color.MAGENTA);
			boomList.add(auxBall);
		}
	}
	
	public void updateFishingBalls()
	{
		for (int i = 0; i < fishingList.size(); i++) {
			fishingList.get(i).move(world);
			if (fishingList.get(i).hasCollidedWithBottom(world))
			{
				fishingList.remove(fishingList.get(i));
			}
		}
	}
	
	public void updateBoomBalls()
	{
		for (int i = 0; i < boomList.size(); i++)
		{
			boomList.get(i).move(world);
			if (boomList.get(i).hasCollidedWithBottom(world))
			{
				boomList.remove(boomList.get(i));
			}
		}
	}
	
	public void updateMasterBalls()
	{
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
						aux.setDeltaX(masterList.get(0).getCenter().getPx() - aux.getCenter().getPx());
						aux.setDeltaY(masterList.get(0).getCenter().getPy() - aux.getCenter().getPy());
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
							if (masterList.size() == 1)
							{
								roda = false;
								break;
							}
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
	}
	
	public void blowUpBalls()
	{
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
	}
	
	public void update()
	{
		createFishingBall();
		createBoomBall();
		updateFishingBalls();
		updateBoomBalls();
		updateMasterBalls();
		blowUpBalls();
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
	
	public boolean isGameRunning()
	{
		return roda;
	}

	public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

	public GameView getGameView() {
		return gameView;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public ArrayList<BoomBall> getBoomList() {
		return boomList;
	}

	public void setBoomList(ArrayList<BoomBall> boomList) {
		this.boomList = boomList;
	}

	public ArrayList<MasterBall> getMasterList() {
		return masterList;
	}

	public void setMasterList(ArrayList<MasterBall> masterList) {
		this.masterList = masterList;
	}

	public ArrayList<FishingBall> getFishingList() {
		return fishingList;
	}

	public void setFishingList(ArrayList<FishingBall> fishingList) {
		this.fishingList = fishingList;
	}

	public double getRaio() {
		return raio;
	}

	public void setRaio(double raio) {
		this.raio = raio;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getQuantTargets() {
		return quantTargets;
	}

	public void setQuantTargets(int quantTargets) {
		this.quantTargets = quantTargets;
	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	public int getChance() {
		return chance;
	}

	public void setChance(int chance) {
		this.chance = chance;
	}
}
