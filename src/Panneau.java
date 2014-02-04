import java.awt.*;
import javax.swing.*;

public class Panneau extends JPanel{
	
	int taille;
	int x,y;
	
	public Panneau(int t,int x,int y){
		taille=t;
		this.x=x;
		this.y=y;
	}
	
	public void paintComponent(Graphics g) {
		super.setBounds(x,y,taille,taille);
		super.setSize(taille,taille);
		
		
		//super.paintComponent(g);
		//g.setColor(couleur);
		//g.fillOval(0, 0, taille, taille);
	}
}
