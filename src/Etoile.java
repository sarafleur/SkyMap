import java.awt.Color;
import java.lang.Math;

public class Etoile {

	String nom;
	double ascensionDte, declinaison;
	String typeSpectral; double magnitude;
	int taille;
	
	public Etoile(String n, double a, double d, double m,String t){
		nom=n;
		ascensionDte=a;
		declinaison=d;
		typeSpectral=t;
		magnitude=m;
		taille=this.taille();
	}
	
	public boolean hauteur(Temps t, Lieu l){//vrai si hauteur positif
		double H= Math.toRadians(t.TempsSideral()*15)-this.ascensionDte-l.lonrad;
		double hauteur = Math.asin(Math.sin(this.declinaison)*Math.sin(l.latrad)+Math.cos(H)*Math.cos(l.latrad)*Math.cos(this.declinaison));
		return hauteur>0;
	}
	
	public double h(Temps t, Lieu l){//vrai si hauteur positif
		double H= Math.toRadians(t.TempsSideral()*15)-this.ascensionDte-l.lonrad;
		double hauteur = Math.asin(Math.sin(this.declinaison)*Math.sin(l.latrad)+Math.cos(H)*Math.cos(l.latrad)*Math.cos(this.declinaison));
		return hauteur;
	}
	
	public Point point(Temps t, Lieu l){
		double H= Math.toRadians(t.TempsSideral()*15)-this.ascensionDte-l.lonrad;
		double D = Math.cos(H)*Math.sin(l.latrad)-Math.tan(this.declinaison)*Math.cos(l.latrad);
		double hauteur = Math.asin(Math.sin(this.declinaison)*Math.sin(l.latrad)+Math.cos(H)*Math.cos(l.latrad)*Math.cos(this.declinaison));
		double azimut;
		if (D>0){
			azimut = Math.atan(Math.sin(H)/D);
		}
		else {
			azimut = Math.atan(Math.sin(H)/D)+Math.PI;
		}
		double r = Math.cos(hauteur);
		double x = r*Math.cos(-azimut-Math.PI/2);
		double y = r*Math.sin(-azimut-Math.PI/2);
		return new Point(x,y);
	}
	
	public int taille(){
		//int t1=(int)(Math.sqrt(this.luminosite())*4+0.5);//arrondi. 5px pour les étoiles les plus lumineuses  fonction log a revoir
		//int t2=(int)(this.luminosite()*this.luminosite()*5+0.5);
		int t=(int)(this.luminosite()*4.2+0.5);
		return Math.max(2, t);
	}
	
	public double luminosite(){
		double i=3*2.42/((2.46+magnitude)*2.42);
		if(i>=1) return 1;
		else return i;
	}
	
	public Color couleur(){ //return la couleur en fonction du type
		double r,v,b;
		switch(Character.toString(typeSpectral.charAt(0))){
		case "O" : r=0.8 ; v=0.8 ; b=1 ; break;
		case "B" : r=0.9 ; v=0.9 ; b=1 ; break;
		case "A" : r=1 ; v=1 ; b=1 ; break;
		case "F" : r=1 ; v=1 ; b=0.8 ; break;
		case "G" : r=1 ; v=1 ; b=0.7 ; break;
		case "K" : r=1 ; v=0.9 ; b=0.8; break;
		case "M" : r=1 ; v=0.6 ; b=0.6; break;
		case "C" : r=1 ; v=0.6 ; b=0.6; break;
		case "S" : r=1 ; v=0.6 ; b=0.6; break;
		case "W" : r=1 ; v=1 ; b=1 ; break;
		case "P" : r=1 ; v=1 ; b=1 ; break;
		default : r=0 ; v=1 ; b=0 ; break; //si pas de couleur (=error ?) renvoie une étoile verte
		}
		//return (new Color(255,255,255,255));
		return new Color((int)(r*255*this.luminosite()+0.5),(int)(v*255*this.luminosite()+0.5),(int)(b*255*this.luminosite()),255);
	}
	
	public Color couleurSelect(){
		double r,v,b;
		switch(Character.toString(typeSpectral.charAt(0))){
		case "O" : r=0.5 ; v=0.5 ; b=1 ; break;
		case "B" : r=0.6 ; v=0.6 ; b=1 ; break;
		case "A" : r=1 ; v=1 ; b=1 ; break;
		case "F" : r=1 ; v=1 ; b=0.5 ; break;
		case "G" : r=1 ; v=1 ; b=0.4 ; break;
		case "K" : r=1 ; v=0.6 ; b=0.5; break;
		case "M" : r=1 ; v=0.3 ; b=0.63; break;
		case "C" : r=1 ; v=0.3 ; b=0.3; break;
		case "S" : r=1 ; v=0.3 ; b=0.3; break;
		case "W" : r=1 ; v=1 ; b=1 ; break;
		case "P" : r=1 ; v=1 ; b=1 ; break;
		default : r=0 ; v=1 ; b=0 ; break; //si pas de couleur (=error ?) renvoie une étoile verte
		}
		//return (new Color(255,255,255,255));
		return new Color((int)(r*255+0.5),(int)(v*255+0.5),(int)(b*255),255);
	}
	
	public Color couleurSelectSuivi(){
		double r,v,b;
		switch(Character.toString(typeSpectral.charAt(0))){
		case "O" : r=0.5 ; v=0.5 ; b=1 ; break;
		case "B" : r=0.6 ; v=0.6 ; b=1 ; break;
		case "A" : r=1 ; v=1 ; b=1 ; break;
		case "F" : r=1 ; v=1 ; b=0.5 ; break;
		case "G" : r=1 ; v=1 ; b=0.4 ; break;
		case "K" : r=1 ; v=0.6 ; b=0.5; break;
		case "M" : r=1 ; v=0.3 ; b=0.63; break;
		case "C" : r=1 ; v=0.3 ; b=0.3; break;
		case "S" : r=1 ; v=0.3 ; b=0.3; break;
		case "W" : r=1 ; v=1 ; b=1 ; break;
		case "P" : r=1 ; v=1 ; b=1 ; break;
		default : r=0 ; v=1 ; b=0 ; break; //si pas de couleur (=error ?) renvoie une étoile verte
		}
		//return (new Color(255,255,255,255));
		return new Color((int)(r*255+0.5),(int)(v*255+0.5),(int)(b*255),100);
	}
	
	public String toString(){
		//System.out.println(this.luminosite());
		return "cette étoile : "+this.nom+" a pour couleur "+this.couleur();
	}
	
	
}
