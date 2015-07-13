import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartView
{
	private JFrame frame;
	private JPanel panel;
	private JButton button;
	private Controller controller;
	
	public StartView()
	{
		button = new JButton("Play");
		button.addActionListener(new ButtonListener());
		
		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.add(button);
		
		frame = new JFrame("Drifts");
		frame.getContentPane().add(panel);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	public void finish()
	{
		frame.dispose();
	}
	
	public void setController(Controller controller)
	{
		this.controller = controller;
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
	
	class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			controller.startGame();
		}
	}
}
