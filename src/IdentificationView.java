import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class IdentificationView
{
	private Controller controller;
	private JFrame frame;
	private JPanel panel;
	private JTextField textField;
	private JButton button;
	private JLabel label;
	
	public void setUp()
	{
		label = new JLabel("Enter your name:");
		
		textField = new JTextField(20);
		textField.addActionListener(new TextFieldListener());
		
		button = new JButton("Play");
		button.addActionListener(new ButtonListener());
		
		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.add(label);
		panel.add(textField);
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
		frame.setVisible(false);
	}
	
	class ButtonListener implements ActionListener
	{

		public void actionPerformed(ActionEvent ev)
		{
			controller.createPlayer(textField.getText());
			new Thread() {

		        @Override
		        public void run() {
		        	controller.createPlayer(textField.getText());
		        	controller.startGame();
		        }
		    }.start();
		}
		
	}
	
	class TextFieldListener implements ActionListener
	{

		public void actionPerformed(ActionEvent ev)
		{
			new Thread() {

		        @Override
		        public void run() {
		        	controller.createPlayer(textField.getText());
		        	controller.startGame();
		        }
		    }.start();
		}
		
	}
	
	public Controller getController() {
		return controller;
	}
	public void setController(Controller controller) {
		this.controller = controller;
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
	public JTextField getTextField() {
		return textField;
	}
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
	public JButton getButton() {
		return button;
	}
	public void setButton(JButton button) {
		this.button = button;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}
}
