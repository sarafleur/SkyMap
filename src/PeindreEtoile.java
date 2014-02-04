import java.awt.*;
import java.util.GregorianCalendar;

import javax.swing.*;


public class PeindreEtoile extends JPanel{
	
	Temps t;
	Lieu l;
	Etoile[] listEtoile;
	Etoile soleil;
	Etoile lune;
	
	public PeindreEtoile(Etoile[] e,Temps t,Lieu l){
		listEtoile=new Etoile[e.length];
		for(int i=0;i<listEtoile.length;i++){
			listEtoile[i]=e[i];
		}
		this.t=t;
		this.l=l;
		Soleil s=new Soleil(t);
		Lune lune=new Lune(t);
		this.soleil=new Etoile("Soleil",s.ascensionDroite,s.declinaison,0,"Nouveau");
		this.lune=new Etoile("Lune",lune.ascensionDroite,lune.declinaison,0,"nouveau");
	}
	
	public void paintComponent(Graphics g) {
		super.setBounds(0,0,500,500);
		super.setBackground(new Color(0,0,0,0));
		//fonction couleur en fonction hauteur du soleil
		if(soleil.hauteur(t,l)){
			g.setColor(new Color((int)(10*soleil.h(t, l)),(int)(20*soleil.h(t, l)),(int)(90*(1+0.5*soleil.h(t, l))),255));
		}
		else{
			g.setColor(new Color(0,10,(int)(75*Math.abs(1-Math.abs(soleil.h(t, l)))),255));
		}
		g.fillOval(0, 0, 500, 500);
		int x;
		int y;
		int taille;
		for(int i=0;i<listEtoile.length;i++){
			if(listEtoile[i].hauteur(t,l)){
				g.setColor(listEtoile[i].couleur());
				x=listEtoile[i].point(t, l).x;
				y=listEtoile[i].point(t, l).y;
				taille=listEtoile[i].taille;
				g.fillOval(x-(int)(taille/2), y-(int)(taille/2), taille, taille);
			}
		}
		g.setColor(Color.yellow);
		x=soleil.point(t, l).x;
		y=soleil.point(t,l).y;
		taille=16;
		if(soleil.hauteur(t, l)){g.fillOval(x-(int)(taille/2), y-(int)(taille/2), taille, taille);}
		g.setColor(new Color(200,200,200,255));
		x=lune.point(t, l).x;
		y=lune.point(t,l).y;
		taille=14;
		if(lune.hauteur(t, l)){g.fillOval(x-(int)(taille/2), y-(int)(taille/2), taille, taille);}
		super.paintComponent(g);
	}

}


