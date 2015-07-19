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
	private JPanel 	panels,
					initPanel, 
					playPanel, 
					configPanel;
	private JButton bPlay,
					iPlay,
					iConfig,
					bInstr,
					bConfig;
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
	
	class ConfigPanel extends JPanel
	{
		private JButton easy,med,hard;
		
		public ConfigPanel()
		{
			setBackground(Color.LIGHT_GRAY);
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			
			easy = new JButton("Easy");
			easy.addActionListener(new ConfigListener());
			easy.setFont(font);
			
			med = new JButton("Medium");
			med.addActionListener(new ConfigListener());
			med.setFont(font);
			
			hard = new JButton("Hard");
			hard.addActionListener(new ConfigListener());
			hard.setFont(font);
			
			add(easy);
			add(med);
			add(hard);
		}
	}
	
	public StartView()
	{
		textField = new JTextField("Digite Seu nome", 20);
		textField.setFont(font);
		textField.addActionListener(new TextFieldListener());
		
		initPanel = new JPanel();
		initPanel.setBackground(Color.white);
		initPanel.add(new HeadPanel(),BorderLayout.NORTH);
		
		iPlay = new JButton("Play");
		iPlay.addActionListener(new ChangeLayoutListener());
		iConfig = new JButton("Config.");
		iConfig.addActionListener(new ChangeLayoutListener());
		
		initPanel.add(iPlay,BorderLayout.EAST);
		initPanel.add(iConfig,BorderLayout.WEST);
//		initPanel.add(new JLabel("Clique para Iniciar"), BorderLayout.SOUTH);
//		initPanel.addMouseListener(new MyMouseListener());
		
		bPlay = new JButton("Play");
		bPlay.addActionListener(new ButtonListener());
		bPlay.setBackground(Color.LIGHT_GRAY);
		
		playPanel = new JPanel();
		playPanel.setBackground(Color.white);
		playPanel.setLayout( new BoxLayout( playPanel , BoxLayout.Y_AXIS ));
		playPanel.add(new HeadPanel(), BorderLayout.NORTH);
		playPanel.add(textField);
		playPanel.add(bPlay);
		
//		bConfig = new JButton("Configurar");
//		bConfig.addActionListener(new ButtonListener());
//		bConfig.setBackground(Color.LIGHT_GRAY);
		
		configPanel = new JPanel();
		configPanel.setBackground(Color.WHITE);
		configPanel.setLayout( new BoxLayout( configPanel , BoxLayout.Y_AXIS ));
		configPanel.add(new HeadPanel(), BorderLayout.NORTH);
		configPanel.add(new ConfigPanel());
		
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
		panels.add(configPanel,"Configurar");
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
	        player = textField.getText();
		    start(player);
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
	
	class ChangeLayoutListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton button = (JButton) e.getSource();
			if(button.getText().compareTo("Play") == 0)
			{
				c1.show(panels, "Jogar");
				textField.requestFocus();
				textField.setSelectedTextColor(Color.BLACK);
				textField.setSelectionColor(Color.LIGHT_GRAY);
				textField.selectAll();
				controller.setConfig(new ConfigModel("medium"));
			}
			else if(button.getText().compareTo("Config.") == 0)
			{
				c1.show(panels,"Configurar");
			}
		}
	}
	
//	class MyMouseListener implements MouseListener{
//		public void mouseClicked(MouseEvent m)
//		{
//			c1.show(panels, "Jogar");
//			textField.requestFocus();
//			textField.setSelectedTextColor(Color.BLACK);
//			textField.setSelectionColor(Color.LIGHT_GRAY);
//			textField.selectAll();
//		}
//
//		@Override
//		public void mouseEntered(MouseEvent arg0) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void mouseExited(MouseEvent arg0) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void mousePressed(MouseEvent arg0) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void mouseReleased(MouseEvent arg0) {
//			// TODO Auto-generated method stub
//			
//		}
//	}
	
	class ConfigListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton button = (JButton) e.getSource();
			if(button.getText().compareTo("Easy") == 0)
			{
				controller.setConfig(new ConfigModel("easy"));
			}
			else if(button.getText().compareTo("Easy") == 0)
			{
				controller.setConfig(new ConfigModel("medium"));
			}
			else
			{
				controller.setConfig(new ConfigModel("hard"));
			}
			c1.show(panels, "Jogar");
			textField.requestFocus();
			textField.setSelectedTextColor(Color.BLACK);
			textField.setSelectionColor(Color.LIGHT_GRAY);
			textField.selectAll();
		}
	}
}
