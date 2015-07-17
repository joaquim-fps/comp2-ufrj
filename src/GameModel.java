import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameModel
{
	private GameController gameController;
	private GameView gameView;
	private Player player;
	private World world;
	private ArrayList<BoomBall> boomList;
	private ArrayList<MasterBall> masterList;
	private ArrayList<FishingBall> fishingList;
	private ArrayList<CherishBall> cherishList;
	private double raio = 30;
	private int width;
	private int height;
	private int quantTargets = 10;
	private Color background = Color.WHITE;
	private int chanceBoom = 50;//porcentagem de chance dessa bola aparecer a cada frame do jogo
	private int chanceFishing = 50;
	private int chanceCherish = 10;
	private boolean explode = false;
	private boolean roda = true;
	private boolean isPaused = false;
	private KeyboardFocusManager manager;
	private SoundPlayer soundPlayer;
	
	public GameModel () {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int)screenSize.getWidth();
		height = (int)screenSize.getHeight()-35;
		world = new World (width, height, background);
		fishingList = new ArrayList<FishingBall>();
		masterList = new ArrayList<MasterBall>();
		boomList = new ArrayList<BoomBall>();
		cherishList = new ArrayList<CherishBall>();
		BoomBall b = null;
		MasterBall c = null;
		FishingBall auxBall = null;
		soundPlayer = new SoundPlayer();
		
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
		
		manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
	}
	
	public void createFishingBall()
	{
		if (((int)(Math.random()*100)) % (101-chanceFishing) == 0)
		{
			FishingBall auxBall = new FishingBall(world, raio, Color.GREEN);
			fishingList.add(auxBall);
		}
	}
	
	public void createBoomBall()
	{
		if (((int)(Math.random()*100)) % (101-chanceBoom) == 0)
		{
			BoomBall auxBall = new BoomBall(world, raio, Color.MAGENTA);
			boomList.add(auxBall);
		}
	}
	
	public void createCherishBall()
	{
		if (((int)(Math.random()*100)) % (101-chanceCherish) == 0)
		{
			CherishBall auxBall = new CherishBall(world, raio, Color.BLUE);
			cherishList.add(auxBall);
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
	
	public void updateCherishBalls()
	{
		for (int i = 0; i < cherishList.size(); i++)
		{
			cherishList.get(i).move(world);
			if (cherishList.get(i).hasCollidedWithBottom(world))
			{
				cherishList.remove(cherishList.get(i));
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
						soundPlayer.playSound(1);
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
								soundPlayer.playSound(2);
								roda = false;
								break;
							}
							soundPlayer.playSound(3);
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
	
	public void collectPoints()
	{
		for (int i = 0; i < masterList.size(); i++) {
			if (!cherishList.isEmpty()) {
				for (int j = 0; j < cherishList.size(); j++) {
					if (masterList.get(i).hasCollided(cherishList.get(j))) 
					{
						soundPlayer.playSound(4);
						int points = 0;
						cherishList.remove(cherishList.get(j));
						
						if (masterList.size() > 1)
						{
							if (masterList.size() == 4)//se tiver 3 bolas verdes
							{
								points = 1;
							}
							else if (masterList.size() == 5)//se tiver 4 bolas verdes
							{
								points = 5;
							}
							else if (masterList.size() == 6)//se tiver 5 bolas verdes
							{
								points = 11;
							}
							else if(masterList.size() == 7)//se tiver 6 bolas verdes
							{
								points = 17;
							}
							else if(masterList.size() == 8)//se tiver 7 bolas verdes
							{
								points = 25;
							}
							else if(masterList.size() == 9)//se tiver 8 bolas verdes
							{
								points = 33;
							}
							else if(masterList.size() == 10)//se tiver 9 bolas verdes
							{
								points = 41;
							}
							else if(masterList.size() >= 11)//se tiver 10 ou mais bolas verdes
							{
								points = 51;
							}
							score(points);
							
							for (int k = masterList.size()-1; k >= 1; k--)
							{
								masterList.remove(masterList.get(k));
							}
						}
						
						break;
					}
				}
			}
		}
	}
	
	public void update()
	{
		if (!isPaused)
		{
			createFishingBall();
			createBoomBall();
			createCherishBall();
			updateFishingBalls();
			updateBoomBalls();
			updateCherishBalls();
			updateMasterBalls();
			blowUpBalls();
			collectPoints();
		}
	}
	
	public void score(int points)
	{
		player.score(points);
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
	
	class MyDispatcher implements KeyEventDispatcher
	{

		public boolean dispatchKeyEvent(KeyEvent e)
		{
			int id = e.getID();
			if (id == KeyEvent.KEY_TYPED)
	        {
	        	if (isPaused)
	        	{
	        		isPaused = false;
	        	}
	        	else
	        	{
	        		isPaused = true;
	        	}
	        }
			
			return false;
		}
		
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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
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

	public ArrayList<CherishBall> getCherishList() {
		return cherishList;
	}

	public void setCherishList(ArrayList<CherishBall> cherishList) {
		this.cherishList = cherishList;
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

	public int getChanceBoom() {
		return chanceBoom;
	}

	public void setChanceBoom(int chanceBoom) {
		this.chanceBoom = chanceBoom;
	}

	public int getChanceFishing() {
		return chanceFishing;
	}

	public void setChanceFishing(int chanceFishing) {
		this.chanceFishing = chanceFishing;
	}

	public int getChanceCherish() {
		return chanceCherish;
	}

	public void setChanceCherish(int chanceCherish) {
		this.chanceCherish = chanceCherish;
	}
}
