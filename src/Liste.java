import java.io.*;
import java.util.LinkedList;

public class Liste {
	
	LinkedList<Etoile> listeEtoile;
	
	public Liste(){ //Lecture dans fichier  --  crée une liste avec toute les étoile en linkedList<Etoile>
		listeEtoile=new LinkedList<Etoile>();
		String chemin="stars.dat";
		String s="#";
		char c=s.charAt(0);
		try{
			BufferedReader buff = new BufferedReader(new FileReader(chemin));
			try{
				String ligne;
				while((ligne=buff.readLine())!=null){
					if(ligne.charAt(0)!=c){
						String[] tabligne=ligne.split(",");
						String n=tabligne[0];
						double a=Double.parseDouble(tabligne[1]);
						double d=Double.parseDouble(tabligne[2]);
						double m=Double.parseDouble(tabligne[3]);
						String t=tabligne[4];
						listeEtoile.add(new Etoile(n,a,d,m,t));
					}
				}
			}
			finally{
				buff.close();
			}
		}
		catch(IOException ioe){
			System.out.println("Erreur --" + ioe.toString());
		}
	}
	
	public static double distance (int x1,int y1,int x2,int y2){
		return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
	}
	
	public static Etoile plusProche (int x,int y, Etoile[] tab, Temps t, Lieu l){
		int n = tab.length;
		Etoile e = tab[0];
		double d = 500;
		for (int i=0;i<n;i++){
			if(tab[i].hauteur(t, l)){
			if (distance(x,y,tab[i].point(t, l).x,tab[i].point(t, l).y)<d){
				d=distance(x,y,tab[i].point(t, l).x,tab[i].point(t, l).y);
				e =tab[i];
			}
		}
		}
		return e;
	}
	
		
}
	
