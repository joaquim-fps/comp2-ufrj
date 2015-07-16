import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartView
{
	private JFrame frame;
	private JPanel panel;
	private JButton bPlay, bInstr , bConfig ;
	private Controller controller;
	private JLabel gamesName,group;
	private String player;
	
	public StartView()
	{
		gamesName = new JLabel(" PokeBalls Drifting ");
		group = new JLabel("Bruno Ferraz \n Joaquim Ferreira \n Vitor Barcellos");
		Font font = new Font("serif",Font.BOLD,32);
		gamesName.setFont(font);
		
		
		bPlay = new JButton("Play");
		bPlay.addActionListener(new ButtonListener());
		bPlay.setBackground(Color.LIGHT_GRAY);
		
		panel = new JPanel();
		panel.setBackground(Color.white);
		BoxLayout box = new BoxLayout( panel , BoxLayout.Y_AXIS );
		panel.setLayout(box);
		panel.add(gamesName);
		panel.add(group);
		
		frame = new JFrame("Drifts");
		frame.getContentPane().add(BorderLayout.NORTH,panel);
		frame.getContentPane().add(bPlay);
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
		return bPlay;
	}

	public void setButton(JButton bPlay) {
		this.bPlay = bPlay;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public String getPlayer() {
		return player;
	}

	class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			controller.setUpIdentificationScreen();
		}
	}
}
