import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameView
{
	private GameModel gameModel;
	private Player player;
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
		frame.setSize(gameModel.getWorld().getWidth(), gameModel.getWorld().getHeight());
		frame.setExtendedState( frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		frame.setVisible(true);
	}
	
	public void update()
	{
		drawPanel.repaint();
		
		try
		{
			Thread.sleep(10);
		}
		catch(Exception ex){}
	}
	
	public void finish()
	{
		frame.dispose();
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
			
			for (CherishBall ball : gameModel.getCherishList())
			{
				draw(ball, g);
			}
			
			for (MasterBall ball : gameModel.getMasterList())
			{
				draw(ball, g);
			}
			
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("Score: " + Integer.toString(player.getScore()), 15, 20);
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.drawString("Pokeballs Drifting", 500, 35);
		}
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public MyDrawPanel getDrawPanel() {
		return drawPanel;
	}

	public void setDrawPanel(MyDrawPanel drawPanel) {
		this.drawPanel = drawPanel;
	}

	public GameModel getGameModel() {
		return gameModel;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
