package SeriousGame.entity.terminal;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import SeriousGame.entity.Entity;
import SeriousGame.entity.mob.Player;
import SeriousGame.graphics.Screen;
import SeriousGame.graphics.Sprite;

public class MainTerminal extends Entity {
	protected 	boolean 	solved = false;
	public 		boolean 	solving = false;
	public 		boolean 	out = false;
	protected 	Sprite 		sprite1;
	protected 	Sprite 		sprite2;
	protected 	boolean 	action;
	protected 	int 		playerX, playerY;
	private 	JFrame 		frame;
				String 		equation;
	public		String 		Zagadka;
	public boolean win = false;
	
	public MainTerminal(int x, int y, String Zagadka, String rightAnswer){
		this.x = x << 5;
		this.y = y << 5;
		this.Zagadka = Zagadka;
		sprite1 = Sprite.MainTerminal1;
		sprite2 = Sprite.MainTerminal2;
	}
	
	public void update() {
		List<Player> players = level.getPlayers();
		action = players.get(0).getAction();
		playerX = players.get(0).getX();
		playerY = players.get(0).getY();
		PlayerAction();
	}
	
	public void PlayerAction(){
		if(action && Math.abs(playerX-x)<256 && Math.abs(playerY-y)<128){
			if(!solved){
				
				solving = true;
				frame = new JFrame();
				setWindow();
				JPanel panel = new JPanel();
				panel.setSize(640, 480);

				GridBagLayout layout = new GridBagLayout();
				GridBagConstraints gbc = new GridBagConstraints();
				
				panel.setLayout(layout);
				frame.setLayout(layout);
				      
				JButton accept = new JButton("ZatwierdŸ");
				JTextField secretText = new JTextField(Zagadka);

				secretText.setEditable(false);
				
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.weightx = 0.5;
				gbc.gridx=0;
				gbc.gridy=0;
				panel.add(secretText, gbc);
				
				gbc.gridx = 0;
				accept.addActionListener(new Check());
				
				gbc.gridy=1;
				panel.add(accept, gbc);
				
				frame.add(panel);	
				frame.setVisible(true);
			}
		}
	}
	
	public void setWindow(){
		frame.setUndecorated(true);
		frame.setBounds(0,0,200,200);
		frame.setSize(1280, 1024);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}
	
	
	public void windowClose(){
		solved = true;
		solving = false;
		level.getClientPlayer().input.action=false;
		frame.setVisible(false);
		frame.dispose();
		win = true;
		
	}
	public boolean isSolved(){
		return solved;
	}
	public void render(Screen screen) {
		screen.renderSprite(x, y, sprite1, true, true);
		screen.renderSprite(x + 128, y, sprite2, true, true);
	}

	private class Check implements ActionListener{
		public void actionPerformed(ActionEvent AE) {
			windowClose();
		}
	}
}
