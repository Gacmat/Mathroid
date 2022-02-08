package SeriousGame.entity.terminal;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import SeriousGame.Game;
import SeriousGame.entity.mob.Player;
import SeriousGame.entity.spawner.ParticleSpawner;
import SeriousGame.graphics.Screen;
import SeriousGame.graphics.Sprite;
import SeriousGame.input.Keyboard;

public class Terminal extends Terminals{

	protected 	boolean 	solved = false;
	public 		boolean 	solving = false;
	protected 	Sprite 		sprite;
	protected 	boolean 	action;
	protected 	int 		playerX, playerY;
	private 	JFrame 		frame;
	public 		JTextField 	answer;
				String 		equation;
	public		String 		Zagadka;
	String answerText, rightAnswer;
	Font font = new Font("TimesRoman", Font.PLAIN, 20);
	public Terminal(int x, int y, String Zagadka, String rightAnswer){
		this.x = x << 5;
		this.y = y << 5;
		this.Zagadka = Zagadka;
		this.rightAnswer = rightAnswer;
	
	}
	
	public void update() {
	}
	
	public void PlayerAction(){
		
		
		if(action && Math.abs(playerX-x)<32 && Math.abs(playerY-y)<35){
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
				answer = new JTextField();
				secretText.setEditable(false);
				
				secretText.setFont(font);
				answer.setFont(font);
				accept.setFont(font);
				gbc.fill = GridBagConstraints.BOTH;
				gbc.gridx=0;
				gbc.gridy=0;
				panel.add(secretText, gbc);
				gbc.gridy = 1;
				panel.add(answer, gbc);
				
				accept.addActionListener(new Check());
				gbc.gridy=2;
				panel.add(accept, gbc);
				
				answerText = secretText.getText();
				
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
		level.add(new ParticleSpawner((int) x-2, (int) y-2, 60, 40, level, true, true));
	}
	
	public boolean isSolved(){
		return solved;
	}
	public void render(Screen screen) {
		screen.renderMob(x, y, sprite, 0);
	}

	private class Check implements ActionListener{
		
		public void actionPerformed(ActionEvent AE) {
			if(answer.getText().equals(rightAnswer)){
				windowClose();
			}else
				answer.setText("OdpowiedŸ nieprawid³owa");
			
		}
	}
}
