import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class StartView
{
	private JFrame frame;
	private CardLayout c1;
	private JPanel panels, headPanel, playPanel, configPanel;
	private JButton bPlay, bInstr , bConfig ;
	JTextField textField;
	private JLabel gamesName,group;
	private Controller controller;
	private String player;
	
	public StartView()
	{
		gamesName = new JLabel(" PokeBalls Drifting ");
		group = new JLabel("Bruno Ferraz \n Joaquim Ferreira \n Vitor Barcellos");
		Font font = new Font("serif",Font.BOLD,32);
		gamesName.setFont(font);
		
		textField = new JTextField("Digite Seu nome", 20);
		textField.addActionListener(new ButtonListener());
		
		headPanel = new JPanel();
		headPanel.setBackground(Color.white);
		BoxLayout box = new BoxLayout( headPanel , BoxLayout.Y_AXIS );
		headPanel.setLayout(box);
		headPanel.add(gamesName);
		headPanel.add(group);
		headPanel.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent m)
			{
				c1.show(panels, "Jogar");
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		bPlay = new JButton("Play");
		bPlay.addActionListener(new ButtonListener());
		bPlay.setBackground(Color.LIGHT_GRAY);
		
		playPanel = new JPanel();
		playPanel.setBackground(Color.white);
		box = new BoxLayout( playPanel , BoxLayout.Y_AXIS );
		playPanel.setLayout(box);
		playPanel.add(textField);
		playPanel.add(bPlay);
		
		bConfig = new JButton("Configurar");
		bConfig.addActionListener(new ButtonListener());
		bPlay.setBackground(Color.LIGHT_GRAY);
		
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				go();
			}
		});
	}
	
	public void go()
	{
		
		
//		panels = new JPanel(new CardLayout());
//		panels.add(headPanel, "Inicio");
//		panels.add(playPanel, "Jogar");
		
		c1 = new CardLayout();
		panels = new JPanel();
		panels.setLayout(c1);
		panels.add(headPanel, "Inicio");
		panels.add(playPanel, "Jogar");
		c1.show(panels,"Inicio");
		
		frame = new JFrame("Drifts");
		frame.getContentPane().add(panels);
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

	public JPanel getPanels() {
		return panels;
	}

	public void setPanels(JPanel panels) {
		this.panels = panels;
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
	
//	 Tentei Forçar uma Substituição no frame, quando o player quisesse começar o jogo. 
//	 Quando fosse dar play, apareceria um TextField para que o jogador digitasse o no-
//	 me para ser gravado nos recordes.
	
//	public void setPlayer()
//	{
//				System.out.println("Cheguei aqui");
//				frame.getContentPane().remove(bPlay);
//				
//				JTextField text = new JTextField("Digite seu nome para os recordes");
//				text.addActionListener(new ButtonListener());
//				frame.getContentPane().add(text);
//	}

	class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
		        	Object o = event.getSource();
		        	if(o instanceof JButton)
		        	{
			        	if(o.equals(bPlay))
			        	{
//			        		setPlayer();
			        		player = textField.getText();
				        	start(player);
			        	}
			        	else if(o.equals(bInstr))
			        	{
			        		//controller.showInstructions;
			        	}
			        	else if(o.equals(bConfig))
			        	{
			        		//controller.Config;
			        	}
			        }
			        else
			        {
			        	player = textField.getText();
			        	start(player);
			        }
		}
	}
	
	public void start(String player)
	{
		new Thread(){
			public void run()
			{
				controller.createPlayer(player);
				controller.startGame();
			}
		}.start();
	}
}
