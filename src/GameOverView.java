import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameOverView
{
	private JFrame frame;
	private JPanel panel;
	private JButton exitButton, playAgainButton;
	private Controller controller;
	
	public void setUp()
	{
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ExitButtonListener());
		
		playAgainButton = new JButton("Play Again");
		playAgainButton.addActionListener(new PlayAgainButtonListener());
		
		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.add(exitButton);
		panel.add(playAgainButton);
		
		frame = new JFrame("Drifts");
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	public void finish()
	{
		frame.dispose();
	}
	
	class ExitButtonListener implements ActionListener
	{

		public void actionPerformed(ActionEvent ev)
		{
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
		
	}
	
	class PlayAgainButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			controller.playAgain();
		}
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JButton getExitButton() {
		return exitButton;
	}

	public void setExitButton(JButton exitButton) {
		this.exitButton = exitButton;
	}

	public JButton getPlayAgainButton() {
		return playAgainButton;
	}

	public void setPlayAgainButton(JButton playAgainButton) {
		this.playAgainButton = playAgainButton;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
}