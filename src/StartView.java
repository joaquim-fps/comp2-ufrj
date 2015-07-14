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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setBounds(0, 0, 300, 300);
		frame.setVisible(true);
	}
	
	public void finish()
	{
		frame.dispose();
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

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			
			
			new Thread() {

		        @Override
		        public void run() {
		        	controller.startGame();

		        }
		    }.start();
		}
	}
}
