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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class StartView
{
	private JFrame frame;
	private CardLayout c1;
	private JPanel 	panels,
					initPanel, 
					playPanel, 
					configPanel,
					instPanel;
	private JButton bPlay,
					iPlay,
					iConfig,
					iInst;
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
		private JButton easy,med,hard,sound;
		
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
			
			if (controller.HasSound())
			{
				sound = new JButton("Sound: On");
			}
			else
			{
				sound = new JButton("Sound: Off");
			}
			sound.addActionListener(new SoundButtonListener());
			sound.setFont(font);
			
			add(easy);
			add(med);
			add(hard);
			add(new JLabel("<html><body><br></body></html>"));
			add(sound);
		}
	}
	
	class InstPanel extends JPanel
	{
		private JTextArea inst;
		private JButton button;
		String txt = "\tINSTRUÇÕES\n Seu objetivo é capturar o maximo de bolas verdes e entregá-las às"
				+ " gentis bolas azuis que estarão por perto.\n Mas tome cuidado, porque dizem que bandos de bolas verdes estão saqueando"
				+ " nas redondezas.\n"
				+ "Faça o máximo de entregas, desvie dessas turmas da pesada e nos deixe orgulhosos, Bola Vermelha !!!";
		public InstPanel()
		{
			//setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
			
			inst = new JTextArea(10,20);
			inst.setEditable(false);
			inst.setLineWrap(true);
			inst.setWrapStyleWord(true);
			inst.setText(txt);
			
			add(inst,BorderLayout.NORTH);
			
			button = new JButton("Back");
			button.addActionListener(new ChangeLayoutListener());
			
			add(button,BorderLayout.SOUTH);
		}
	}
	
	public void setUp()
	{
		textField = new JTextField("Digite Seu nome", 20);
		textField.setFont(font);
		textField.addActionListener(new TextFieldListener());
		
		initPanel = new JPanel();
		initPanel.setBackground(Color.white);
		initPanel.add(new HeadPanel(),BorderLayout.NORTH);
		
		iPlay = new JButton("Play Now");
		iPlay.addActionListener(new ChangeLayoutListener());
		iConfig = new JButton("Config.");
		iConfig.addActionListener(new ChangeLayoutListener());
		iInst = new JButton("Instructions");
		iInst.addActionListener(new ChangeLayoutListener());
		
		initPanel.add(iPlay,BorderLayout.EAST);
		initPanel.add(iConfig,BorderLayout.WEST);
		initPanel.add(iInst,BorderLayout.CENTER);

		
		bPlay = new JButton("Play");
		bPlay.addActionListener(new ButtonListener());
		bPlay.setBackground(Color.LIGHT_GRAY);
		
		playPanel = new JPanel();
		playPanel.setBackground(Color.white);
		playPanel.setLayout( new BoxLayout( playPanel , BoxLayout.Y_AXIS ));
		playPanel.add(new HeadPanel(), BorderLayout.NORTH);
		playPanel.add(textField);
		playPanel.add(bPlay);
		
		
		configPanel = new JPanel();
		configPanel.setBackground(Color.WHITE);
		configPanel.add(new HeadPanel(), BorderLayout.NORTH);
		configPanel.add(new ConfigPanel());
		
		
		instPanel = new JPanel();
		instPanel.setBackground(Color.WHITE);
		instPanel.add(new HeadPanel(), BorderLayout.NORTH);
		instPanel.add(new InstPanel());
		
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
		panels.add(instPanel,"Instruções");
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
			if(button.getText().compareTo("Play Now") == 0)
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
			else if(button.getText().compareTo("Back") == 0)
			{
				c1.show(panels,"Inicio");
			}
			else
			{
				c1.show(panels, "Instruções");
			}
		}
	}
	
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
	
	class SoundButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton button = (JButton)e.getSource();
			
			if (button.getText().compareTo("Sound: On") == 0)
			{
				button.setText("Sound: Off");
			}
			else
			{
				button.setText("Sound: On");
			}
			controller.changeSoundConfig();
		}
	}
}
