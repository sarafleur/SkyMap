import java.awt.*;
import javax.swing.*;

public class PanneauRond extends JPanel{
	
	int taille;
	Color couleur;
	int x,y;
	
	public PanneauRond(int t, Color c,int x,int y){
		taille=t; couleur=c;
		this.x=x;
		this.y=y;
	}
	
	public void paintComponent(Graphics g) {
		super.setBounds(x,y,taille,taille);
		super.setSize(taille,taille);
		super.paintComponent(g);
		g.setColor(couleur);
		g.fillOval(0, 0, taille, taille);
	}
}
