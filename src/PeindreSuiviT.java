import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class PeindreSuiviT extends JPanel{

	// peinds entre le temps t1 et me temps t2, imprime une etoile par 2minutes
	Temps t1;
	Temps t2;
	Lieu l;
	Etoile[] e;
	
	PeindreSuiviT(Temps t1, Temps t2,Lieu l, Etoile[] e){
		this.t1=t1;
		this.t2=t2;
		this.l=l;
		this.e=e;
	}
	
	public void paintComponent(Graphics g){
		super.setBounds(0,0,500,500);
		super.setBackground(new Color(0,0,0,0));
		g.setColor(new Color(0,10,75,255));
		g.fillOval(0, 0, 500, 500);
		int x;
		int y;
		int taille;
		for(int j=0;j<(int)(e.length);j++){
			g.setColor(e[j].couleurSelectSuivi());
			int n=(int)(long)Temps.nbMinute(t1, t2);
			Temps t=t1;
			for(int i=0;i<n;i+=2){
				x=e[j].point(t,l).x;
				y=e[j].point(t, l).y;
				taille=Math.max(1, (int)(e[j].taille()/5));
				if(e[j].hauteur(t, l)){
					g.fillOval(x-(int)(taille/2),y-(int)(taille/2),taille, taille);
				}
				//décalage de 2 minute
				int[] tab =Manip.decalageMinPlus(t.annee,t.mois,t.jour,t.heure-t.decalage,t.minute);
				t=new Temps(tab[0],tab[1],tab[2],tab[3],tab[4]);
				int[] ta =Manip.decalageMinPlus(t.annee,t.mois,t.jour,t.heure-t.decalage,t.minute);
				t=new Temps(ta[0],ta[1],ta[2],ta[3],ta[4]);
			}
		}
		
		super.paintComponent(g);
	}
}
