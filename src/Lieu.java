

public class Lieu {
	
	String nom;
	int lat;
	int latm;
	int lats;
	int lon;
	int lonm;
	int lons;
	double latrad;
	double lonrad;
	double latdeg;
	double londeg;
	
	public Lieu (String s,Lieu l){
		this.nom=s;
		this.lat=l.lat;
		this.latm=l.latm;
		this.lats=l.lats;
		this.lon=l.lon;
		this.lonm=l.lonm;
		this.lons=l.lons;
		this.latrad = Math.toRadians((double)(lat+(double)(latm)/60+(double)(lats)/3600));
		this.lonrad = Math.toRadians((double)(lon+(double)(lonm)/60+(double)(lons)/3600));
		this.londeg = (double)(lon+(double)(lonm)/60+(double)(lons)/3600);
		this.latdeg = (double)(lat+(double)(latm)/60+(double)(lats)/3600);
		
	}
	public Lieu (String s, int lo, int lom, int los,int l, int lm, int ls){
		this.nom=s;
		this.lat=l;
		this.latm=lm;
		this.lats=ls;
		this.lon=lo;
		this.lonm=lom;
		this.lons=los;
		this.latrad = Math.toRadians((double)(lat+(double)(latm)/60+(double)(lats)/3600));
		this.lonrad = Math.toRadians((double)(lon+(double)(lonm)/60+(double)(lons)/3600));
		this.londeg = (double)(lon+(double)(lonm)/60+(double)(lons)/3600);
		this.latdeg = (double)(lat+(double)(latm)/60+(double)(lats)/3600);
	}
	
	public String toString(){
		return nom+" latitude"+this.latrad+" longitude  "+this.lonrad;
	}
/*
 * coordonnée du lieu, faire aussi une liste de lieu prédéfinie
 * (une combo box pour choisir)
 * et les importer à l'aide d'un fichier ?
 * 
 * afficher etoile dans cette class ?
 * 
 * pouvoir enregisrter un lieu 
 * -> faire un fichier texte pour les lieu
 */
/**
 * Thread t = new Thread(){  //essai avec un nouveau threads
			        public void run() {
			        	System.out.println("coucou fixe");
			        	CardLayout c=(CardLayout)date().getLayout();
			        	c.next(date());
			        }
				};
				t.start();
 */

	/*
	fixe.addActionListener(new ActionListener(){
	@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			System.out.println("fixe");
			org.next(date);
		}
	});
	*/
	
}
