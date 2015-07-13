import java.awt.Graphics;
//import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameView
{
	private GameModel gameModel;
	private JFrame frame;
	private MyDrawPanel drawPanel;
	
	public void setGameModel(GameModel gameModel)
	{
		this.gameModel = gameModel;
	}
	
	public void setUp()
	{
		drawPanel = new MyDrawPanel();
		frame = new JFrame("Drifts");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(drawPanel);
//		frame.getContentPane().validate();
		frame.setSize(gameModel.getWorld().getWidth(), gameModel.getWorld().getHeight());
		frame.setExtendedState( frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		frame.setVisible(true);
	}
	
	public void update()
	{
//		drawPanel.revalidate();
		System.out.println("repaint");
		drawPanel.repaint();
//		drawPanel.paintImmediately(new Rectangle(0, 0, drawPanel.getWidth(), drawPanel.getHeight()));
		
		try
		{
			Thread.sleep(10);
		}
		catch(Exception ex){}
	}
	
	public void finish()
	{
		frame.setVisible(false);
	}
	
	public void draw(PokeBall ball, Graphics g)
	{
		g.setColor(ball.getColor());
		g.fillOval((int)(ball.getCenter().getPx()-ball.getRadius()), (int)(ball.getCenter().getPy()-ball.getRadius()), (int)ball.getRadius(), (int)ball.getRadius());
	}
	
	@SuppressWarnings("serial")
	class MyDrawPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			System.out.println("paintComponent");
			g.setColor(gameModel.getWorld().getBackground());
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			for (FishingBall ball : gameModel.getFishingList())
			{
				draw(ball, g);
			}
			
			for (BoomBall ball : gameModel.getBoomList())
			{
				draw(ball, g);
			}
			
			for (MasterBall ball : gameModel.getMasterList())
			{
				draw(ball, g);
			}
		}
	}
}
