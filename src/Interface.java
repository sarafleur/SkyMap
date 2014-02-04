import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.swing.border.Border;
import javax.swing.event.*;
import javax.swing.*;

/**
 * 
 * 
 * @author Castor
 *
 *a modifier :
 *  [RESOLU]  Incrément (passer au jours (heure, minutes, jour, moi, anné) suivante ou précédente)
 *  [RESOLU]  prendre date et heure locale pour rentrer directement
 *  [RESOLU]  ajouter controle d'animation
 *  [RESOLU]  possibilité de suivre étoile ou toute les étoiles
 *  [RESOLU]  couleur du fond en fonction soleil
 *  [RESOLU]  ajouter soleil et lune au début de peindreEtoile mais prendre les données dans une classe a part
 *
 */

public class Interface {

	public static Maintenant mtnt=new Maintenant();
	
	public static Lieu endroit; 
	public static Temps temps;
	public static Temps tempsInt;
	public static Temps temps1;
	public static Temps temps2;
	public static int duree;
	
	public static JTextField jour;
	public static JTextField moi;
	public static JTextField annee;
	public static JTextField heure;
	public static JTextField minute;
	public static JTextField jour1;
	public static JTextField moi1;
	public static JTextField annee1;
	public static JTextField heure1;
	public static JTextField minute1;
	public static JTextField jour2;
	public static JTextField moi2;
	public static JTextField annee2;
	public static JTextField heure2;
	public static JTextField minute2;
	
	public static JTextField lon1;
	public static JTextField lon2;
	public static JTextField lon3;
	public static JTextField la1;
	public static JTextField la2;
	public static JTextField la3;
	
	public static boolean cadreTempsFix=true;
	public static boolean cadreLieuPred=true;
	
	public static JPanel time=new JPanel();
	public static JPanel date=new JPanel();
	public static CardLayout org=new CardLayout();
	public static JPanel panneaulieu=new JPanel();
	public static CardLayout org2=new CardLayout();
	public static JPanel informationEtoile=informationEtoile();
	public static CardLayout orgcontrole=new CardLayout();;
	public static JPanel controle=controle();
	public static JPanel controleFixe=new JPanel();
	public static JPanel controleAnim=new JPanel();
	
	public static Etoile[] etoile=listeEtoile();
	public static Lieu[] lieu=listeLieu();
	public static JComboBox<String> choix;
	public static JComboBox<String> gmt;
	public static Etoile etoileSelection;
	
	public static JButton go=new JButton("Afficher l'image / l'animation");
	public static JRadioButton liste;
	public static JRadioButton fixe;
	public static JButton reset=new JButton("reset");
	
	public static int sourisX;
	public static int sourisY;
	
	public static JFrame fenetre;
	public static JPanel cadre;
	public static CardLayout orgRondInt=new CardLayout();
	public static JPanel rondInt=new JPanel(orgRondInt);
	public static Panneau rond=new Panneau(500,50,8);
	
	final static Color haut=new Color(170,200,250,255);
	final static Color milieu=Color.white;
	
	public static Thread thread=new Thread();
	public static boolean pause;
	public static int vit=2;
	
	public static JCheckBox suivi1Etoile;
	public static JCheckBox suiviTteEtoile;
	
	/**-----------------------------FIN DSE VARIABLES--------------------------------------------*/
	
	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                fenetrePrincipale();}});
    }

	public static void fenetrePrincipale(){
		// création de la fenetre qui éteint le programme à la fermeture
		fenetre = new JFrame();
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//titre
		fenetre.setTitle("Carte du ciel");
		//organisation de la fenetre
		BorderLayout orgfenetre = new BorderLayout();
		fenetre.getContentPane().setLayout(orgfenetre);
		//Dimension
		//fenetre.setExtendedState(fenetre.MAXIMIZED_BOTH);
		fenetre.setMinimumSize(new Dimension(1200,730));
		fenetre.setLocation(75, 0);
		//rendre la fenetre visible
		fenetre.setVisible(true);
		
		//Ajout des éléments
		//barre des taches
		fenetre.getContentPane().add(barreDesTaches(),"North");
		fenetre.getContentPane().add(centreFenetre(), "Center");
		fenetre.getContentPane().add(informationBas(),"South");
	}

	/**-----------------------------DEBUT REGION NORD--------------------------------------------*/
	
	public static JPanel barreDesTaches(){//barre supérieures contenant les opération possibles
		JPanel barre=new JPanel();
		GridLayout orgbarre=new GridLayout(1,3,0,0);
		barre.setLayout(orgbarre);
		barre.add(barreDesTachesGauche());
		barre.add(barreDesTachesCentre());	
		barre.add(barreDesTachesDroite());
		return barre;
	}
	
	public static JPanel barreDesTachesGauche(){
		JPanel barre=new JPanel();
		GridLayout orgbarre=new GridLayout(2,1,0,0);
		barre.setLayout(orgbarre);
		barre.add(boutonRadioAnim());
		barre.add(boutonRadioChang());
		return barre;
	}
	
	public static JPanel barreDesTachesCentre(){
		JPanel barre=new JPanel();
		GridLayout orgbarre=new GridLayout(2,1,0,0);
		barre.setLayout(orgbarre);
		barre.add(date);
		barre.add(panneaulieu);
		return barre;
	}
	
	public static JPanel barreDesTachesDroite(){
		JPanel barre=new JPanel();
		GridLayout orgbarre=new GridLayout(2,1,0,0);
		barre.setLayout(orgbarre);
		boutonAnimation();
		barre.add(panneauAnimation());
		barre.add(fuseau());
		return barre;
	}
		
	public static JPanel boutonRadioAnim(){ // choisir animation ou fixe
		/*essai date() dans ce panneau*/
		date.setLayout(org);
		date.add(dateFixe());
		date.add(dateAnimation());
		/**/
		JPanel barre=new JPanel();
		ButtonGroup bouton=new ButtonGroup();
		JRadioButton animation=new JRadioButton("animation");
		fixe=new JRadioButton("fixe");
		bouton.add(animation);bouton.add(fixe);
		fixe.setSelected(true);
		barre.add(new JLabel("Choisir type :"));
		animation.setBackground(haut);
		fixe.setBackground(haut);
		barre.add(animation);
		barre.add(fixe);
		//listener
		fixe.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("fixe");
				org.first(date); // affiche le panneau fix
				cadreTempsFix=true;
				orgcontrole.next(controle);
				//System.out.println(cadreTempsFix);
			}
		});
		animation.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("anim");
				org.last(date); // afiche le panneau animation
				cadreTempsFix=false;
				orgcontrole.next(controle);
				//System.out.println(cadreTempsFix);
			}
		});
		barre.setBackground(haut);
		return barre;
	}
	
	public static JPanel dateAnimation(){ // animation
		Temps mtntPlus=new Temps(c(mtnt.annee),c(mtnt.mois),c(mtnt.jour),c(mtnt.heure),c(mtnt.minute));
		int[] mP=jourP(mtntPlus.annee,mtntPlus.mois,mtntPlus.jour,mtntPlus.heure-mtntPlus.decalage,mtntPlus.minute);
		JPanel barre=new JPanel();
		JPanel barre1=new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel barre2=new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel du =new JLabel("du : ");
		jour1=new JTextField(mtnt.jour,2);
		moi1=new JTextField(mtnt.mois,2);
		annee1=new JTextField(mtnt.annee,4);
		JLabel a=new JLabel("à");
		JLabel a2=new JLabel("à");
		heure1=new JTextField(mtnt.heure,2);
		JLabel sep=new JLabel(":");
		JLabel sep2=new JLabel(":");
		minute1=new JTextField(mtnt.minute,2);
		JLabel au = new JLabel(" au : ");
		jour2=new JTextField(String.valueOf(mP[2]),2);
		moi2=new JTextField(String.valueOf(mP[1]),2);
		annee2=new JTextField(String.valueOf(mP[0]),4);
		heure2=new JTextField(String.valueOf(mP[3]),2);
		minute2=new JTextField(String.valueOf(mP[4]),2);
		barre1.add(du);
		barre1.add(jour1);
		barre1.add(moi1);
		barre1.add(annee1);
		barre1.add(a2);
		barre1.add(heure1);
		barre1.add(sep);
		barre1.add(minute1);
		barre2.add(au);
		barre2.add(jour2);
		barre2.add(moi2);
		barre2.add(annee2);
		barre2.add(a);
		barre2.add(heure2);
		barre2.add(sep2);
		barre2.add(minute2);
		barre.setBackground(haut);
		barre1.setBackground(haut);
		barre2.setBackground(haut);		
		barre.add(barre1);
		barre.add(barre2);
		return barre;
	}
	public static JPanel dateFixe(){ // fixe
		JPanel barre=new JPanel();
		JLabel le =new JLabel("Le : ");
		jour=new JTextField(mtnt.jour,2);
		moi=new JTextField(mtnt.mois,2);
		annee=new JTextField(mtnt.annee,4);
		JLabel a =new JLabel(" à : ");
		heure=new JTextField(mtnt.heure,2);
		JLabel m =new JLabel(":");
		minute=new JTextField(mtnt.minute,2);
		barre.add(le);
		barre.add(jour);
		barre.add(moi);
		barre.add(annee);
		barre.add(a);
		barre.add(heure);
		barre.add(m);
		barre.add(minute);
		barre.setBackground(haut);
		return barre;
	}
		
	public static JPanel boutonRadioChang(){ //choisir lieu prédéfini ou manuel
		/**/
		panneaulieu.setLayout(org2);
		panneaulieu.add(changerLieuPredef());
		panneaulieu.add(changerLieuMan());
		/**/
		JPanel barre=new JPanel();
		ButtonGroup bouton=new ButtonGroup();
		liste=new JRadioButton("liste prédéfinie");
		JRadioButton man=new JRadioButton("manuellement");
		liste.setBackground(haut);
		man.setBackground(haut);
		bouton.add(liste);bouton.add(man);
		liste.setSelected(true);
		barre.add(new JLabel("choisir lieu :"));
		barre.add(liste);
		barre.add(man);
		//listener
		liste.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				org2.next(panneaulieu);
				cadreLieuPred=true;
			}
		});
		man.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				org2.next(panneaulieu);
				cadreLieuPred=false;
			}
		});
		barre.setBackground(haut);
		return barre;
	}
	
	public static JPanel changerLieuPredef(){ // lieu prédéfinis
		JPanel barre2=new JPanel();
		/*
		String[] villes = {"Bordeaux","Lyon","Marseille","Montpellier","Nantes","Nices","Palaiseau","Paris","Rennes","Strasbourg","Toulouse"};
		choix = new JComboBox<String>(villes) ;
		choix.setSelectedIndex(1) ;
		*/
		listeDefilante();
		choix.setSelectedItem("Palaiseau");
		choix.setMaximumRowCount(7);
		choix.setBackground(haut);
		barre2.add(choix);
		barre2.setBackground(haut);
		return barre2;
	}
	public static JPanel changerLieuMan(){ // manuel
		JPanel barre=new JPanel();
		barre.setBackground(haut);
		barre.setLayout(new BoxLayout(barre, BoxLayout.Y_AXIS));
		JPanel barre1=new JPanel();//panneau longitude
		JLabel longi=new JLabel("Longitude Est : ");
		lon1=new JTextField(2);
		JLabel long1=new JLabel("°");
		lon2=new JTextField(2);
		JLabel long2=new JLabel("'");
		lon3=new JTextField(2);
		JLabel long3=new JLabel("\" ");		
		barre1.add(longi);
		barre1.add(lon1);
		barre1.add(long1);
		barre1.add(lon2);
		barre1.add(long2);
		barre1.add(lon3);
		barre1.add(long3);	
		barre1.setBackground(haut);
		barre.add(barre1);
		JPanel barre2=new JPanel();//panneau latitude
		JLabel lat=new JLabel("  Latitude Nord :");
		la1=new JTextField(2);
		JLabel lat1=new JLabel("°");
		la2=new JTextField(2);
		JLabel lat2=new JLabel("'");
		la3=new JTextField(2);
		JLabel lat3=new JLabel("\" ");
		barre2.add(lat);
		barre2.add(la1);
		barre2.add(lat1);
		barre2.add(la2);
		barre2.add(lat2);
		barre2.add(la3);
		barre2.add(lat3);
		barre2.setBackground(haut);
		barre.add(barre2);
		JPanel fin=new JPanel();
		fin.setLayout(new FlowLayout());
		fin.add(barre);
		JButton enr=new JButton("Save");
		enr.setCursor(new Cursor(12));
		enr.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				enregistrer();
			}
		});
		fin.add(enr);
		fin.setBackground(haut);
		return fin;
	}
	
	public static JPanel panneauAnimation(){
		JPanel gop=new JPanel();
		gop.add(go);
		gop.setBackground(haut);
		return gop;
	}
	
	public static void boutonAnimation(){
		go.setPreferredSize(new Dimension(250,35));
		go.setCursor(new Cursor(12));
		go.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(thread.isAlive()){
					thread.stop();
					maj();
				}
				if(cadreTempsFix){
					maj();
				}
				else{
					try {
						animation();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
			
		});
	}
	
	public static JPanel fuseau(){ // panneau vide, en dessous du bouton
		JPanel gop=new JPanel();
		gop.setBackground(haut);
		/*String[] a=new String[24];
		for(int i=-11;i<13;i++){
			a[i+11]=String.valueOf(i);
		}
		gmt=new JComboBox<String>(a);
		gmt.setBackground(haut);
		gmt.setSelectedItem("1");
		gop.add(new JLabel("Choix du fuseau horaire : GMT+"));
		gop.add(gmt);
		gop.add(new JLabel("h."));*/
		JLabel texte=new JLabel("prendre l'heure française...");
		gop.add(texte);
		return gop;
	}


	/**-------------------------------FIN REGION NORD--------------------------------------------*/
	
	/**-----------------------------DEBUT REGION CENTRE------------------------------------------*/

	public static JPanel centreFenetre(){ // region centre de la Jrame
		cadre=new JPanel();
		cadre.setLayout(new GridLayout(1,2,0,0));
		cadre.setBackground(milieu);
		listenerRond();
		boutonReset();
		rondInt.setBackground(milieu);
		rondInt.add(rond);
		cadre.add(rondInt);
		/*-----------*/
		JPanel partieDroite = new JPanel();
		partieDroite.setBackground(haut);
		partieDroite.setLayout(new GridLayout(3,1));
		partieDroite.setBounds(1050,300,10,100);
		
		partieDroite.add(controle);
		partieDroite.add(informationEtoile);
		partieDroite.add(panneauSuivi());
		cadre.add(partieDroite);
		return cadre;
	}

	public static void boutonReset(){
		reset.setCursor(new Cursor(12));
		reset.setPreferredSize(new Dimension(70,30));
		reset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				rond.removeAll();
				rond.add(new PeindreEtoile(etoile,temps,endroit));
				rond.revalidate();
				etoileSelection=null;
				informationEtoile.removeAll();
				JPanel pan=new JPanel();pan.setBackground(milieu);
				informationEtoile.add(pan);
				JPanel pan2=new JPanel();pan2.setBackground(milieu);
				informationEtoile.add(pan2);
				informationEtoile.revalidate();
			}
		});
	}
	
	public static void listenerRond(){ 
		maj();//premier affichage, a l'ouverture du programme
		
		rond.setCursor(new Cursor(1));
			
		/*----------fonciton recherche, listener sur le panel concerné----------*/
		rond.addMouseListener(new MouseInputAdapter(){
			public void mousePressed(MouseEvent event){
				pause=true;
			}
		});
		rond.addMouseListener(new MouseInputAdapter(){
			@Override
	        public void mouseClicked(MouseEvent event) {
				if(pause==false){}
				else{
					sourisX=event.getX();
					sourisY=event.getY();
					informationEtoile.removeAll();
					GridLayout grille=new GridLayout(2,1,0,2);
					informationEtoile.setLayout(grille);
					JPanel h=new JPanel();
					h.setLayout(new GridLayout(2,1));
					JPanel h1=new JPanel();h1.setBackground(milieu);
					h1.setLayout(new FlowLayout(FlowLayout.CENTER));
					h1.add(reset);
					h.add(h1);
					JPanel h2=new JPanel();h2.setBackground(milieu);
					h2.setLayout(new FlowLayout(FlowLayout.CENTER));
					JLabel clic=new JLabel("Informations sur l'étoile selectionnée (la couleur est accentuée exprès) :");
					h2.add(clic);
					h.add(h2);
					informationEtoile.add(h);
					JLabel info;
					if(Liste.distance(sourisX, sourisY, 250, 250)<=251){
						etoileSelection=Liste.plusProche(sourisX,sourisY,etoile,temps,endroit);
						info=new JLabel("Nom : "+etoileSelection.nom+" ;   Type spectrale : "+etoileSelection.typeSpectral+" ;   Magnitude : "+etoileSelection.magnitude+"."); // a modifier, affichage dans panneau
						//mettre l'etoile en plus gros
						PeindreEtoileSelectionnee et=new PeindreEtoileSelectionnee(temps,endroit,etoileSelection);
						PeindreEtoile etc=new PeindreEtoile(etoile,temps,endroit);
						rond.removeAll();
						rond.add(et);
						rond.add(etc);
						rond.revalidate();
					}
					else{
						info=new JLabel("vous n'avez pas cliquez sur la carte du ciel...");
						PeindreEtoile etc=new PeindreEtoile(etoile,temps,endroit);
						rond.removeAll();
						rond.add(etc);
						rond.revalidate();
					}
					JPanel b=new JPanel();b.setBackground(milieu);
					b.setLayout(new FlowLayout(FlowLayout.CENTER));
					b.add(info);
					informationEtoile.add(b);
					informationEtoile.revalidate();
		        }
			}
		});
	}
	
	public static JPanel informationEtoile(){
		JPanel p=new JPanel();
		p.setBackground(Color.white);
		return p;
	}
	
	public static JPanel controle(){
		JPanel panneau=new JPanel();
		panneau.setLayout(orgcontrole);
		controleFixe=controleFixe();
		controleAnim=controleAnim();
		panneau.add(controleFixe);
		panneau.add(controleAnim);
		return panneau;
	}
	
	public static JPanel controleFixe(){
		JPanel pan=new JPanel(new GridLayout(1,3));
		JPanel gauche=new JPanel();gauche.setBackground(Color.white);
		JPanel centre=new JPanel(new GridLayout(5,3));
		JPanel droite=new JPanel();droite.setBackground(Color.white);
		JPanel mil1=new JPanel(new FlowLayout(FlowLayout.CENTER));mil1.setBackground(Color.white);
		JPanel mil2=new JPanel(new FlowLayout(FlowLayout.CENTER));mil2.setBackground(Color.white);
		JPanel mil3=new JPanel(new FlowLayout(FlowLayout.CENTER));mil3.setBackground(Color.white);
		JPanel mil4=new JPanel(new FlowLayout(FlowLayout.CENTER));mil4.setBackground(Color.white);
		JPanel mil5=new JPanel(new FlowLayout(FlowLayout.CENTER));mil5.setBackground(Color.white);
		JLabel text1=new JLabel("année");
		JLabel text2=new JLabel("mois");
		JLabel text3=new JLabel("jour");
		JLabel text4=new JLabel("heure");
		JLabel text5=new JLabel("minute");
		mil1.add(text1);
		mil2.add(text2);
		mil3.add(text3);
		mil4.add(text4);
		mil5.add(text5);
		JButton[] bouton=boutonIncrement();
		centre.add(bouton[0]);
		centre.add(mil1);
		centre.add(bouton[1]);
		centre.add(bouton[2]);
		centre.add(mil2);
		centre.add(bouton[3]);
		centre.add(bouton[4]);
		centre.add(mil3);
		centre.add(bouton[5]);
		centre.add(bouton[6]);
		centre.add(mil4);
		centre.add(bouton[7]);
		centre.add(bouton[8]);
		centre.add(mil5);
		centre.add(bouton[9]);
		pan.add(gauche);
		pan.add(centre);
		pan.add(droite);
		return pan;
	}
	
	public static JPanel controleAnim(){
		JPanel panneau=new JPanel(new GridLayout(2,1,0,0));
		JPanel haut=new JPanel(new GridLayout(2,1));
		JPanel h1=new JPanel();h1.setBackground(Color.white);
		haut.add(h1);
		/*JLabel text=new JLabel("timelaps, barre stylé genre VLC ?");
		time.add(text);*/
		time.setBackground(Color.white);
		haut.add(time);
		JPanel bas=new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel bas2=new JPanel(new GridLayout(1,5,1,1));bas2.setBorder(BorderFactory.createLineBorder(Color.black));
		bas2.setBounds(10,10,100,20);
		bas.setBackground(Color.white);
		JButton[] bouton=boutonControle();
		for(int i=0;i<5;i++){ bas2.add(bouton[i]);}
		//JLabel text2=new JLabel("controle de l'animation, play, pause...");
		//bas.add(text2);
		panneau.add(haut);
		bas.add(bas2);
		panneau.add(bas);
		return panneau;
	}
	
	public static JButton[] boutonIncrement(){
		JButton[] bouton=new JButton[10];
		JButton anneem=new JButton("-");
		anneem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				int[] n={c(annee.getText()),c(moi.getText()),c(jour.getText()),c(heure.getText()),c(minute.getText())};
				int[] r=anneeM(n[0],n[1],n[2],n[3],n[4]);
				annee.setText(String.valueOf(r[0]));
				moi.setText(String.valueOf(r[1]));
				jour.setText(String.valueOf(r[2]));
				heure.setText(String.valueOf(r[3]));
				minute.setText(String.valueOf(r[4]));
				maj();
			}
		});
		JButton anneep=new JButton("+");
		anneep.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				int[] n={c(annee.getText()),c(moi.getText()),c(jour.getText()),c(heure.getText()),c(minute.getText())};
				int[] r=anneeP(n[0],n[1],n[2],n[3],n[4]);
				annee.setText(String.valueOf(r[0]));
				moi.setText(String.valueOf(r[1]));
				jour.setText(String.valueOf(r[2]));
				heure.setText(String.valueOf(r[3]));
				minute.setText(String.valueOf(r[4]));
				maj();
			}
		});
		JButton moim=new JButton("-");
		moim.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				int[] n={c(annee.getText()),c(moi.getText()),c(jour.getText()),c(heure.getText()),c(minute.getText())};
				int[] r=moiM(n[0],n[1],n[2],n[3],n[4]);
				annee.setText(String.valueOf(r[0]));
				moi.setText(String.valueOf(r[1]));
				jour.setText(String.valueOf(r[2]));
				heure.setText(String.valueOf(r[3]));
				minute.setText(String.valueOf(r[4]));
				maj();
			}
		});
		JButton moip=new JButton("+");
		moip.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				int[] n={c(annee.getText()),c(moi.getText()),c(jour.getText()),c(heure.getText()),c(minute.getText())};
				int[] r=moiP(n[0],n[1],n[2],n[3],n[4]);
				annee.setText(String.valueOf(r[0]));
				moi.setText(String.valueOf(r[1]));
				jour.setText(String.valueOf(r[2]));
				heure.setText(String.valueOf(r[3]));
				minute.setText(String.valueOf(r[4]));
				maj();
			}
		});
		JButton jourm=new JButton("-");
		jourm.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				int[] n={c(annee.getText()),c(moi.getText()),c(jour.getText()),c(heure.getText()),c(minute.getText())};
				int[] r=jourM(n[0],n[1],n[2],n[3],n[4]);
				annee.setText(String.valueOf(r[0]));
				moi.setText(String.valueOf(r[1]));
				jour.setText(String.valueOf(r[2]));
				heure.setText(String.valueOf(r[3]));
				minute.setText(String.valueOf(r[4]));
				maj();
			}
		});
		JButton jourp=new JButton("+");
		jourp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				int[] n={c(annee.getText()),c(moi.getText()),c(jour.getText()),c(heure.getText()),c(minute.getText())};
				int[] r=jourP(n[0],n[1],n[2],n[3],n[4]);
				annee.setText(String.valueOf(r[0]));
				moi.setText(String.valueOf(r[1]));
				jour.setText(String.valueOf(r[2]));
				heure.setText(String.valueOf(r[3]));
				minute.setText(String.valueOf(r[4]));
				maj();
			}
		});
		JButton heurem=new JButton("-");
		heurem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				int[] n={c(annee.getText()),c(moi.getText()),c(jour.getText()),c(heure.getText()),c(minute.getText())};
				int[] r=heureM(n[0],n[1],n[2],n[3],n[4]);
				annee.setText(String.valueOf(r[0]));
				moi.setText(String.valueOf(r[1]));
				jour.setText(String.valueOf(r[2]));
				heure.setText(String.valueOf(r[3]));
				minute.setText(String.valueOf(r[4]));
				maj();
			}
		});
		JButton heurep=new JButton("+");
		heurep.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				int[] n={c(annee.getText()),c(moi.getText()),c(jour.getText()),c(heure.getText()),c(minute.getText())};
				int[] r=heureP(n[0],n[1],n[2],n[3],n[4]);
				annee.setText(String.valueOf(r[0]));
				moi.setText(String.valueOf(r[1]));
				jour.setText(String.valueOf(r[2]));
				heure.setText(String.valueOf(r[3]));
				minute.setText(String.valueOf(r[4]));
				maj();
			}
		});
		JButton minutem=new JButton("-");
		minutem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				int[] n={c(annee.getText()),c(moi.getText()),c(jour.getText()),c(heure.getText()),c(minute.getText())};
				int[] r=minM(n[0],n[1],n[2],n[3],n[4]);
				annee.setText(String.valueOf(r[0]));
				moi.setText(String.valueOf(r[1]));
				jour.setText(String.valueOf(r[2]));
				heure.setText(String.valueOf(r[3]));
				minute.setText(String.valueOf(r[4]));
				maj();
			}
		});
		JButton minutep=new JButton("+");
		minutep.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				int[] n={c(annee.getText()),c(moi.getText()),c(jour.getText()),c(heure.getText()),c(minute.getText())};
				int[] r=minP(n[0],n[1],n[2],n[3],n[4]);
				annee.setText(String.valueOf(r[0]));
				moi.setText(String.valueOf(r[1]));
				jour.setText(String.valueOf(r[2]));
				heure.setText(String.valueOf(r[3]));
				minute.setText(String.valueOf(r[4]));
				maj();
			}
		});
		bouton[0]=anneem;
		bouton[1]=anneep;
		bouton[2]=moim;
		bouton[3]=moip;
		bouton[4]=jourm;
		bouton[5]=jourp;
		bouton[6]=heurem;
		bouton[7]=heurep;
		bouton[8]=minutem;
		bouton[9]=minutep;
		for(int i=0;i<10;i++){
			bouton[i].setCursor(new Cursor(12));
		}
		return bouton;	
	}
	
	public static JButton[] boutonControle(){
		JButton[] bouton=new JButton[5];
		ImageIcon a=new ImageIcon("vitesseM.png");
		ImageIcon b=new ImageIcon("play.png");
		ImageIcon c=new ImageIcon("pause.png");
		ImageIcon d=new ImageIcon("stop.png");
		ImageIcon e=new ImageIcon("vitesseP.png");
		JButton b1=new JButton(a);
		JButton b2=new JButton(b);
		JButton b3=new JButton(c);
		JButton b4=new JButton(d);
		JButton b5=new JButton(e);
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				plusMoins(false);
			}
		});
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(thread.isAlive()){pause=false;}
				else{try {
					animation();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}
			}
		});
		b3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				pause=!pause;
			}
		});
		b4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				thread.stop();
				maj();
			}
		});
		b5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				plusMoins(true);
			}
		});
		bouton[0]=b1;
		bouton[1]=b2;
		bouton[2]=b3;
		bouton[3]=b4;
		bouton[4]=b5;
		for(int i=0;i<5;i++){
			//bouton[i].createImage(20, 20);
			bouton[i].setCursor(new Cursor(12));
			bouton[i].setFocusPainted(false);
			bouton[i].setPreferredSize(new Dimension(30,30));
		}
		return bouton;
	}
	
	public static JPanel panneauSuivi(){
		JPanel panneau=new JPanel(new GridLayout(5,1));
		JPanel haut=new JPanel(new FlowLayout(FlowLayout.CENTER));haut.setBackground(Color.white);
		JPanel milieu=new JPanel(new FlowLayout(FlowLayout.CENTER));milieu.setBackground(Color.white);
		JPanel bas=new JPanel(new FlowLayout(FlowLayout.CENTER));bas.setBackground(Color.white);
		JPanel bas2=new JPanel(new FlowLayout(FlowLayout.CENTER));bas2.setBackground(Color.white);
		JPanel bas3=new JPanel(new FlowLayout(FlowLayout.CENTER));bas3.setBackground(Color.white);
		JLabel text1=new JLabel("suivi de l'étoile sélectionnée.");
		JLabel text2=new JLabel("suivi de toutes les étoiles.");
		JLabel text30=new JLabel("Choisir la date de début et de fin dans le mode animation, et lancer celle-ci.");
		JLabel text3=new JLabel("(Pour suivre une seule étoile, la sélectionner préalablement.)");
		JLabel text4=new JLabel("Pour le suivi de toutes les étoiles, choisir un intervalle de temps raisonnable (moins d'une heure).");
		suivi1Etoile=new JCheckBox();suivi1Etoile.setBackground(Color.white);
		suiviTteEtoile=new JCheckBox();suiviTteEtoile.setBackground(Color.white);
		suivi1Etoile.setSelected(false);
		suiviTteEtoile.setSelected(false);
		haut.add(suivi1Etoile);
		haut.add(text1);
		milieu.add(suiviTteEtoile);
		milieu.add(text2);
		bas.add(text30);
		bas2.add(text3);
		bas3.add(text4);
		panneau.add(haut);
		panneau.add(milieu);
		panneau.add(bas);
		panneau.add(bas2);
		panneau.add(bas3);
		return panneau;
	}
	
	/**-------------------------------FIN REGION CENTRE------------------------------------------*/
	
	/**-----------------------------DEBUT REGION SUD---------------------------------------------*/
	
	public static JPanel informationBas(){ // barre d'état en bas
		JPanel essa=new JPanel();
		essa.setBackground(milieu);
		GridLayout org=new GridLayout(1,2);
		essa.setLayout(org);
		JPanel b1=new JPanel(new FlowLayout(FlowLayout.CENTER));b1.setBackground(milieu);
		JPanel b2=new JPanel(new FlowLayout(FlowLayout.RIGHT));b2.setBackground(milieu);
		JLabel inf1=new JLabel("Cliquez sur la carte pour connaitre le nom d'une étoile");
		JLabel inf2=new JLabel("Créé par Sara-Fleur SULTAN et Adrien TANTIN.");
		b1.add(inf1);
		b2.add(inf2);
		essa.add(b1);
		essa.add(b2);
		return essa;
	}

	/**-------------------------------FIN REGION SUD---------------------------------------------*/
	
	/**-----------------------------DEBUTS FONCTION UTILES---------------------------------------*/
	
	public static Etoile[] listeEtoile(){
		Liste liste=new Liste();
		int n=liste.listeEtoile.size();
		Etoile[] a=new Etoile[n];
		for(int i=0;i<n;i++){
			a[i]=liste.listeEtoile.get(i);
			//System.out.println(a[i].taille);
		}
		return a;
	}
	
	public static Lieu[] listeLieu(){
		LinkedList<Lieu> list=new LinkedList<Lieu>();
		Lieu[] tab;
		String chemin="lieu.dat";
		String s="#";
		char c=s.charAt(0);
		try{
			BufferedReader buff = new BufferedReader(new FileReader(chemin));
			try{
				String ligne;
				while((ligne=buff.readLine())!=null){
					if(ligne.charAt(0)!=c){
						String[] tabligne=ligne.split(",");
						String ville=tabligne[0];
						int longitude1=Integer.parseInt(tabligne[1]);
						int longitude2=Integer.parseInt(tabligne[2]);
						int longitude3=Integer.parseInt(tabligne[3]);
						int latitude1=Integer.parseInt(tabligne[4]);
						int latitude2=Integer.parseInt(tabligne[5]);
						int latitude3=Integer.parseInt(tabligne[6]);
						list.add(new Lieu(ville, longitude1,longitude2,longitude3,latitude1,latitude2,latitude3));
					}
				}
				
			}
			finally{
				buff.close();
			}
		}
		catch(IOException ioe){
			System.out.println("erreur --"+ioe.toString());
		}
		tab=new Lieu[list.size()];
		for(int i=0;i<list.size();i++){
			tab[i]=list.get(i);
			//System.out.println(tab[i]);
		}
		return tab;
	}
	
	public static void listeDefilante(){
		String[] villes=new String[lieu.length];
		for(int i=0;i<lieu.length;i++){
			villes[i]=lieu[i].nom;
		}
		choix = new JComboBox<String>(villes) ;
	}
	
	public static void enregistrer(){
		try {
			prendreLieu();
			Dialog ville=new Dialog(fenetre,2,endroit);//dans la class, ecriture dans fichier
			ville.setVisible(true);
			maj();
			lieu=listeLieu();//mise a jour tableau des lieu
			choix.addItem(lieu[lieu.length-1].nom);//mise a jour comboBox
			choix.setSelectedItem(lieu[lieu.length-1].nom);//afichage avec nouvel item selectionné
			cadreLieuPred=true;
			org2.next(panneaulieu);
			liste.setSelected(true);
		} catch (Exception e) {
			Dialog erreur=new Dialog(fenetre,1,null);
			erreur.setVisible(true);
		}
	}

	public static int c(String a){// conversion : pour eviter qu'un int avec un 0 soit lu en base 8
		String i="0";
		char car0=i.charAt(0);
		char ac=a.charAt(0);
		if(Integer.parseInt(a)==0){return 0;}
		if(car0==ac){
			//if(a.charAt(1)==i.charAt(1)){System.out.println("rrrrr");}
			
			char n=a.charAt(1);
			String ns=String.valueOf(n);
			return Integer.parseInt(ns);
		}
		else return Integer.parseInt(a);
	}

	public static void prendreTemps() throws Exception{
		if(cadreTempsFix){
			int a=c(annee.getText());
			int mo=c(moi.getText());
			int j=c(jour.getText());
			int h=c(heure.getText());
			int mi=c(minute.getText());
			if(mo<=0 || mo>12){throw new Exception();}
			if(j<=0 || j>Temps.nbJourMois(a, mo)){throw new Exception();}
			if(h<0 || h>23){throw new Exception();}
			if(mi<0 || mi>59){throw new Exception();}
			if(a<-9999 || a>9999){throw new Exception();}
			temps=new Temps(a,mo,j,h,mi);
		}
		else{
			int a1=c(annee1.getText());
			int mo1=c(moi1.getText());
			int j1=c(jour1.getText());
			int h1=c(heure1.getText());
			int mi1=c(minute1.getText());
			if(mo1<=0 || mo1>12){throw new Exception();}
			if(j1<=0 || j1>Temps.nbJourMois(a1, mo1)){throw new Exception();}
			if(h1<0 || h1>23){throw new Exception();}
			if(mi1<0 || mi1>59){throw new Exception();}
			if(a1<-9999 || a1>9999){throw new Exception();}
			temps1=new Temps(a1,mo1,j1,h1,mi1);
			int a2=c(annee2.getText());
			int mo2=c(moi2.getText());
			int j2=c(jour2.getText());
			int h2=c(heure2.getText());
			int mi2=c(minute2.getText());
			if(mo2<=0 || mo2>12){throw new Exception();}
			if(j2<=0 || j2>Temps.nbJourMois(a2, mo2)){throw new Exception();}
			if(h2<0 || h2>23){throw new Exception();}
			if(mi2<0 || mi2>59){throw new Exception();}
			if(a2<-9999 || a2>9999){throw new Exception();}
			temps2=new Temps(a2,mo2,j2,h2,mi2);
			if(Temps.apresT(temps1, temps2)){throw new Exception();}
		}
	}
	public static void prendreLieu() throws Exception{
		if(cadreLieuPred){
			int n=choix.getSelectedIndex();
			endroit=lieu[n];
		}
		else{
			int l=c(lon1.getText());
			int l2=c(lon2.getText());
			int l3=c(lon3.getText());
			int l4=c(la1.getText());
			int l5=c(la2.getText());
			int l6=c(la3.getText());
			if(l<-180 || l>180){throw new Exception();}
			if(l4<-90 || l4>90){throw new Exception();}
			if( l2<0 || l3<0 || l5<0 || l6<0){throw new Exception();}
			if( l2>60 || l3>60 ||  l5>60 || l6>60){throw new Exception();}
			endroit=new Lieu(null,l,l2,l3,l4,l5,l6);
		}
	}
	
	public static int[] minM(int a, int mois, int j, int h, int min){
		return Manip.decalageMinMoins(a, mois, j, h, min);
	}
	public static int[] minP(int a, int mois, int j, int h, int min){
		return Manip.decalageMinPlus(a, mois, j, h, min);
	}
	public static int[] heureM(int a, int mois, int j, int h, int min){
		return Manip.decalageHMoins(a, mois, j, h, min);
	}
	public static int[] heureP(int a, int mois, int j, int h, int min){
		return Manip.decalageHPlus(a, mois, j, h, min);
	}
	public static int[] jourM(int a, int mois, int j, int h, int min){
		return Manip.decalageJMoins(a, mois, j, h, min);
	}
	public static int[] jourP(int a, int mois, int j, int h, int min){
		return Manip.decalageJPlus(a, mois, j, h, min);
	}
	public static int[] moiM(int a, int mois, int j, int h, int min){
		return Manip.decalageMoiMoins(a, mois, j, h, min);
	}
	public static int[] moiP(int a, int mois, int j, int h, int min){
		return Manip.decalageMoiPlus(a, mois, j, h, min);
	}
	public static int[] anneeM(int a, int mois, int j, int h, int min){
		return Manip.decalageAnnMoins(a, mois, j, h, min);
	}
	public static int[] anneeP(int a, int mois, int j, int h, int min){
		return Manip.decalageAnnPlus(a, mois, j, h, min);
	}
	
	public static void maj(){		
		try{
			//System.out.println(endroit);
			prendreLieu();
			//System.out.println(endroit);
		}
		catch(Exception ex){
			//System.out.println(ex);
			Dialog erreur=new Dialog(fenetre,1,null);
			erreur.setVisible(true);
		}
		try {
			//System.out.println(temps);
			prendreTemps();
			//System.out.println(temps);
		} 
		catch(Exception ex){
			//System.out.println(ex);
			Dialog erreur=new Dialog(fenetre,0,null);
			erreur.setVisible(true);
		}
		PeindreEtoileSelectionnee ets = null;
		PeindreEtoile peintureEtoile=new PeindreEtoile(etoile,temps,endroit);
		if(etoileSelection!=null){ets=new PeindreEtoileSelectionnee(temps,endroit,etoileSelection);}
		rond.removeAll();
		if(etoileSelection!=null){rond.add(ets);}
		rond.add(peintureEtoile);
		rond.revalidate();
	}
	
	public static void animation() throws InterruptedException{
		try{
			//System.out.println(endroit);
			prendreLieu();
			//System.out.println(endroit);
		}
		catch(Exception ex){
			//System.out.println(ex);
			Dialog erreur=new Dialog(fenetre,1,null);
			erreur.setVisible(true);
		}
		try {
			//System.out.println(temps);
			prendreTemps();
			//System.out.println(temps);
		} 
		catch(Exception ex){
			//System.out.println(ex);
			Dialog erreur=new Dialog(fenetre,0,null);
			erreur.setVisible(true);
		}
		vit=2;
		tempsInt=temps1;
		thread=new Thread(){
			public void run(){
				pause=false;
				int[] t0 =Manip.decalageMinMoins(tempsInt.annee,tempsInt.mois,tempsInt.jour,tempsInt.heure-tempsInt.decalage,tempsInt.minute);
				Temps temps0=new Temps(t0[0],t0[1],t0[2],t0[3],t0[4]);
				int moment=0;
				int total=(int)(long)Temps.nbMinute(temps1, temps2);
				JPanel fond=new JPanel(new FlowLayout(FlowLayout.LEFT));fond.setBorder(BorderFactory.createLineBorder(Color.black));
				fond.setPreferredSize(new Dimension(450,22));
				JPanel barre=new JPanel();barre.setBackground(Color.red);
				time.removeAll();
				time.add(fond);
				fond.removeAll();
				PeindreEtoileSelectionnee ets = null;
				PeindreSuivi1Etoile suivi1=null;
				PeindreSuiviT suiviT=null;
				while( (Temps.comparer(tempsInt, temps2)) && (Temps.comparer(temps0, tempsInt))){
					PeindreEtoile peintureEtoile=new PeindreEtoile(etoile,tempsInt,endroit);
					if(etoileSelection!=null){ets=new PeindreEtoileSelectionnee(tempsInt,endroit,etoileSelection);}
					if(suiviTteEtoile.isSelected()){suiviT=new PeindreSuiviT(temps1,tempsInt,endroit,etoile);}
					if(etoileSelection!=null && suivi1Etoile.isSelected()){suivi1=new PeindreSuivi1Etoile(temps1,tempsInt,endroit,etoileSelection);}
					//pause modifiable, pour la vitesse de défilement
					long a=System.currentTimeMillis();
					if(suiviTteEtoile.isSelected()){while(System.currentTimeMillis()<(a+1000)){}}
					else{while(System.currentTimeMillis()<(a+120/vit)){}}
					rond.removeAll();
					if(etoileSelection!=null){
						rond.add(ets);
					}
					if(suivi1Etoile.isSelected() && suiviTteEtoile.isSelected()){
						suiviTteEtoile.setSelected(false);
					}
					if(suivi1Etoile.isSelected()){
						try{rond.add(suivi1);}
						catch(NullPointerException e){suivi1Etoile.setSelected(false);}
					}
					if(suiviTteEtoile.isSelected()){
						try{rond.add(suiviT);}
						catch(NullPointerException e){suiviTteEtoile.setSelected(false);}
					}
					else{rond.add(peintureEtoile);}
					rond.revalidate();
					//incrémnet de la date, tempsInt
					int[] t =Manip.decalageMinPlus(tempsInt.annee,tempsInt.mois,tempsInt.jour,tempsInt.heure-tempsInt.decalage,tempsInt.minute);
					tempsInt=new Temps(t[0],t[1],t[2],t[3],t[4]);
					temps=tempsInt;
					//action du bouton pause
					while(pause){try {
						thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}/*if(pause){System.out.println(pause);}*/};
					//gestion de la loadbarre
					moment++;
					barre.setPreferredSize(new Dimension((int)(440*moment/(total)),11));
					fond.add(barre);
					
				}	
			}
		};
		thread.start();
	}
	
	public static void plusMoins(boolean a){
		if(a){
			if(vit==6){}
			else{
				if(vit==-1){
					vit=1;
				}
				else{
					vit=vit+1;
				}
			}
		}
		else{
			if(vit==1){}
			else{
				
					vit=vit-1;
				
			}
		}
	}

}


