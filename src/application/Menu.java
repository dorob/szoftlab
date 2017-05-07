package application;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.*;


public class Menu extends JFrame implements ActionListener{
	
private Engine jatek;
private JPanel pMenu;
private JPanel pMain;
private JPanel pScore;
private JButton bStart;
private JButton bScore;
private JButton bExit;


	public Menu(){
		super("Szoftlab");
		GlobalLogger.log("called: Menu constructor");
		Init();
	}

	public void Init(){
		GlobalLogger.log("called: engine -init");

		jatek= new Engine();
		
		
		pMenu= new JPanel();
		pMain=new JPanel();
		pScore = new JPanel();
		pMain.setLayout(new CardLayout());
		JLabel label= new JLabel();
		label.setText("Szoftech Jatek");
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		label.setFont(new Font(label.getFont().getName(),Font.BOLD ,60 ));
		bStart = new JButton();
		bStart.setAlignmentX(JButton.CENTER_ALIGNMENT);
		bStart.setText("Start");
		bStart.setActionCommand("start");
		bStart.addActionListener(this);
		
		bScore = new JButton();
		bScore.setAlignmentX(JButton.CENTER_ALIGNMENT);
		bScore.setText("ScoreBoard");
		bScore.setActionCommand("scores");
		bScore.addActionListener(this);
		
		bExit = new JButton();
		bExit.setAlignmentX(JButton.CENTER_ALIGNMENT);
		bExit.setText("Exit");
		bExit.setActionCommand("exit");
		bExit.addActionListener(this);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(960, 540);
        this.setLocationRelativeTo(null);
        
		pMenu.setLayout(new BoxLayout(pMenu, BoxLayout.PAGE_AXIS));
		pMenu.add(Box.createRigidArea(new Dimension(0, 100)));
		pMenu.add(label);
		pMenu.add(Box.createRigidArea(new Dimension(0, 100)));
		pMenu.add(bStart);
		pMenu.add(Box.createRigidArea(new Dimension(0, 10)));
		pMenu.add(bScore);
		pMenu.add(Box.createRigidArea(new Dimension(0, 10)));
		pMenu.add(bExit);
		
		pMain.add(pMenu, "menu");
		
		pScore.setLayout(new BoxLayout(pScore, BoxLayout.PAGE_AXIS));
		JLabel label1 = new JLabel("ScoreBoard");
		label1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		label1.setFont(new Font(label1.getFont().getName(), Font.BOLD, 30));
		JButton back = new JButton("Menu");
		back.setActionCommand("menu");
		back.setAlignmentX(JButton.CENTER_ALIGNMENT);
		back.addActionListener(this);
		pScore.add(label1, BorderLayout.NORTH);
		
		pScore.add(back, BorderLayout.SOUTH);
		
		pMain.add(pScore, "score");
		pMain.add(jatek, "engine");
		
        this.setContentPane(pMain);
        this.setResizable(false);
        this.setVisible(true);
		
		JScrollPane scroll = new JScrollPane(jatek);
		this.getContentPane().add(scroll);
		
		
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout cl = (CardLayout) pMain.getLayout();
		if(e.getActionCommand().equals("menu")) {
			cl.show(pMain, "menu");
			
		}
		if(e.getActionCommand().equals("start")) {
			cl.show(pMain, "engine");
			jatek.nextLevel();
			cl.show(pMain, "menu");
		}
		if(e.getActionCommand().equals("scores")) {
			ReInitScoreBoard();
			cl.show(pMain, "score");
		}
		if(e.getActionCommand().equals("exit")) {
			System.exit(0);
		}
	
		
	}
	

	private void ReInitScoreBoard(){
		
		Scoreboard tmp;
		tmp=jatek.getToplista();
		Collections.sort(tmp.getHelyezes());
		if(pScore.getComponentCount()==3) pScore.remove(2);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		for(Player p:tmp.getHelyezes()){
		    model.addElement(p.toString());
		}
		JList<String> lTmp = new JList<String>(model);
		JScrollPane sp = new JScrollPane(lTmp);
		
		pScore.add(sp, BorderLayout.CENTER);
		
	}

}
