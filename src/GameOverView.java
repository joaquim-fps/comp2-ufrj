import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOverView
{
	private JFrame frame;
	private JPanel panel;
	private JButton exitButton, playAgainButton;
	private JLabel label;
	private Controller controller;
	private Record record;
	
	public void setUp()
	{
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ExitButtonListener());
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		playAgainButton = new JButton("Play Again");
		playAgainButton.addActionListener(new PlayAgainButtonListener());
		playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		label = new JLabel("High Scores:");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(new JLabel("<html><body><br></body></html>"));
		panel.add(label);
		panel.add(new JLabel("<html><body><br></body></html>"));
		
		for (int i = 0; i < record.getPlayers().size(); i++)
		{
			JLabel l = new JLabel(Integer.toString(i+1) + ".  " + record.getPlayers().get(i).getName() + " -- " +
					Integer.toString(record.getPlayers().get(i).getScore()));
			l.setAlignmentX(Component.CENTER_ALIGNMENT);
			panel.add(l);
		}
		
		panel.add(new JLabel("<html><body><br></body></html>"));
		panel.add(playAgainButton);
		panel.add(new JLabel("<html><body><br></body></html>"));
		panel.add(exitButton);
		
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

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}
}
