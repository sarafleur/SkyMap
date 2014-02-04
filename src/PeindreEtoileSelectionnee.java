import java.awt.*;
import javax.swing.*;


public class PeindreEtoileSelectionnee extends JPanel{

	Temps t;
	Lieu l;
	Etoile e;
	
	PeindreEtoileSelectionnee(Temps t,Lieu l, Etoile e){
		this.t=t;
		this.l=l;
		this.e=e;
	}
	
	public void paintComponent(Graphics g){
		super.setBounds(0,0,500,500);
		super.setBackground(new Color(0,0,0,0));
		int x;
		int y;
		int taille;
		g.setColor(e.couleurSelect());
		x=e.point(t,l).x;
		y=e.point(t, l).y;
		taille=7;
		if(e.hauteur(t, l)){
			g.fillOval(x-(int)(taille/2),y-(int)(taille/2),taille, taille);
		}
		super.paintComponent(g);
	}
}
