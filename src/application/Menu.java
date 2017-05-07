package application;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;


public class Menu {
	
private Engine jatek;
private JFrame frame;
private JPanel menuPanel;
private JButton start;
private JButton score;
private JButton exit;


	public Menu(){
		GlobalLogger.log("called: Menu constructor");
		Init();
	}

	public void Init(){
		GlobalLogger.log("called: engine -init");
		frame = new JFrame("SzoftLab");
		jatek = new Engine();
		menuPanel= new JPanel();
		
		JLabel label= new JLabel();
		label.setText("Szoftech Jatek");
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		label.setFont(new Font(label.getFont().getName(),Font.BOLD ,60 ));
		start = new JButton();
		start.setAlignmentX(JButton.CENTER_ALIGNMENT);
		start.setText("Start");
		score = new JButton();
		score.setAlignmentX(JButton.CENTER_ALIGNMENT);
		score.setText("ScoreBoard");
		exit = new JButton();
		exit.setAlignmentX(JButton.CENTER_ALIGNMENT);
		exit.setText("Exit");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(960, 540);
        frame.setLocationRelativeTo(null);
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));
		menuPanel.add(Box.createRigidArea(new Dimension(0, 100)));
		menuPanel.add(label);
		menuPanel.add(Box.createRigidArea(new Dimension(0, 100)));
		menuPanel.add(start);
		menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		menuPanel.add(score);
		menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		menuPanel.add(exit);
		frame.add(menuPanel, BorderLayout.CENTER);
		
        
        
        frame.setResizable(false);
        frame.setVisible(true);
		
		JScrollPane scroll = new JScrollPane(jatek);
		frame.getContentPane().add(scroll);
		
		
		
	}
	public Engine getJatek() {
		return jatek;
	}
	public void setJatek(Engine jatek) {
		this.jatek = jatek;
	}
	public static void main(String args[]){
		Menu menu = new Menu();
		
	}
}
