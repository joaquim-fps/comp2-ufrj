import java.awt.BorderLayout;
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
	private JPanel panels, initPanel, headPanel, playPanel, configPanel;
	private JButton bPlay, bInstr , bConfig ;
	JTextField textField;
	Font font = new Font("serif",Font.BOLD,13);
	
	private Controller controller;
	private String player;
	
	class HeadPanel extends JPanel
	{
		private JLabel gamesName,group;
		HeadPanel()
		{
			setBackground(Color.LIGHT_GRAY); 
			setLayout(new BoxLayout( this , BoxLayout.Y_AXIS ));
			
			gamesName = new JLabel(" PokeBalls Drifting ");
			group = new JLabel("Bruno Ferraz \n Joaquim Ferreira \n Vitor Barcellos");
			
			Font fontTitle = new Font("serif",Font.BOLD,32);
			gamesName.setFont(fontTitle);
			group.setFont(font);
			
			add(gamesName);
			add(group);
		}
	}
	
	public StartView()
	{
	
		textField = new JTextField("Digite Seu nome", 20);
		textField.setFont(font);
		textField.addActionListener(new ButtonListener());
		
		initPanel = new JPanel();
		initPanel.setBackground(Color.white);
		
		
		initPanel.add(new HeadPanel(),BorderLayout.NORTH);
		initPanel.add(new JLabel("Clique para Iniciar"), BorderLayout.SOUTH);
		initPanel.addMouseListener(new MyMouseListener());
		
		bPlay = new JButton("Play");
		bPlay.addActionListener(new ButtonListener());
		bPlay.setBackground(Color.LIGHT_GRAY);
		
		playPanel = new JPanel();
		playPanel.setBackground(Color.white);
		playPanel.setLayout( new BoxLayout( playPanel , BoxLayout.Y_AXIS ));
		playPanel.add(new HeadPanel(), BorderLayout.NORTH);
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
		c1 = new CardLayout();
		panels = new JPanel();
		panels.setLayout(c1);
		panels.add(initPanel, "Inicio");
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

	class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
        	Object o = event.getSource();
        	if(o instanceof JButton)
        	{
	        	if(o.equals(bPlay))
	        	{
//			        
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
		}
	}
	class TextFieldListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
        	player = textField.getText();
        	start(player);
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
	
	class MyMouseListener implements MouseListener{
		public void mouseClicked(MouseEvent m)
		{
			c1.show(panels, "Jogar");
			textField.requestFocus();
			textField.setSelectedTextColor(Color.BLACK);
			textField.setSelectionColor(Color.LIGHT_GRAY);
			textField.selectAll();
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
	}
}
