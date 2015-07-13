import java.awt.*;
import java.util.ArrayList;

public class Game {
	private World world;
	private ArrayList<Ball> balls, chiefs,pop;
	private ArrayList<FishingBall> fishList;
	private int width = 2;
	private int height = 2;
	private int quantTargets = 10;
	private Color background = StdDraw.BLUE;
	private int chance = 50;//porcentagem de chance de uma bola alvo aparecer a cada frame do jogo
	
	public Game () {
		world = new World (width, height, background);
		//Bolas alvo
		fishList = new ArrayList<FishingBall>();
		//Bolas  controladas
		chiefs = new ArrayList<Ball>();
		//Bolas estouradoras
		pop = new ArrayList<Ball>();
		//Todas as bolas
		balls = new ArrayList<Ball>();
		double raio = 0.05;
		Ball b = null;
		FishingBall auxBall = null;
		
		for (int i = 0; i < quantTargets; i ++) {
			auxBall = new FishingBall (world, raio, StdDraw.GREEN);
			
			fishList.add(auxBall);
			balls.add(auxBall);
		}
		
		b = new Ball (geraPonto(raio), 
	              new Velocity (-0.01 * Math.random(), 0.01 * Math.random()),
	              raio, 
	              StdDraw.MAGENTA);
		
		pop.add(b);
		balls.add(b);
		
		b = new Ball (geraPonto(raio), 
	              new Velocity (-0.01 * Math.random(), 0.01 * Math.random()),
	              raio, 
	              StdDraw.RED);
		
		chiefs.add(b);
		balls.add(b);
	}
	
	public void go() {
		StdDraw.setScale(-world.getWidth()/2, world.getWidth()/2);
		
		boolean explode = false;
		boolean roda = true;
		while (roda == true) {
			StdDraw.clear(world.getBackground());
			
			//Este bloco serve para criar as bolas verdes
			if (((int)(Math.random()*100)) % (101-chance) == 0)
			{
				FishingBall auxBall = new FishingBall(world, 0.05, StdDraw.GREEN);
				fishList.add(auxBall);
				balls.add(auxBall);
			}
			
			for (int i = 0; i < balls.size(); i++) {
				balls.get(i).move(world);
				balls.get(i).draw();
				if (balls.get(i) instanceof FishingBall)
				{
					FishingBall aux = (FishingBall) balls.get(i);
					if ((!aux.isAttached()) && (aux.hasCollidedWithBottom(world)))
					{
						fishList.remove(balls.get(i));
						balls.remove(i);
					}
				}
			}
		
			boolean colidiu = false;
			for (Ball chief : chiefs) {
				if (!fishList.isEmpty()) {
					for (FishingBall target : fishList) {
						if (chief.hasCollided(target)) 
						{
							//target.setColor(StdDraw.YELLOW); Uncomment it if wish to switch color when crush
							
							fishList.remove(target);
							
							//target.getV().setVx(chief.getV().getVx()); //Delete
							//target.getV().setVy(chief.getV().getVy()); //Delete
							
							target.attach(chief);
							
							chiefs.add(target);
							
							colidiu = true;
							break;
						}
					}
					if(!pop.isEmpty())
					{
						for( Ball burst : pop)
						{
							//System.out.println("teste ");
							if(chief.hasCollided(burst)) // PLX OTIMIZAR ISSO
							{
								//System.out.println("Teste2 ");
								
								/*
								Ball aux = chiefs.get(0);
								chiefs.clear();
								chiefs.add(aux);
								*/
								pop.remove(burst);
								balls.remove(burst);
								
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
			
				for(Ball remove : chiefs)
				{
					if(chiefs.indexOf(remove) != 0)
					{
						//chiefs.remove(remove);
						balls.remove(remove);
					}
				}
				
				Ball aux = chiefs.get(0);
				chiefs.clear();
				chiefs.add(aux);
				
				explode = false;
			}
		
			StdDraw.show(10);
		}
	}
	
	private static Point geraPonto(double raio) {
		double x = 2 * Math.random() - 1;
		double y = 2 * Math.random() - 1;
		
		x = (x + raio >= 1) ? x - (raio+0.01) : x;
		x = (x - raio <= -1) ? x + (raio+0.01) : x;
		
		y = (y + raio >= 1) ? y - (raio+0.01) : y;
		y = (y - raio <= -1) ? y + (raio+0.01) : y;
		
		return new Point(x,y);
	}
}