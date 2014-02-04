import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

class Dialog extends JDialog implements ActionListener{
	
	JLabel texte;
	JButton ok;
	JTextField ville;
	int a;
	String nom;
	Lieu l;
	
	public Dialog (JFrame fen,int a,Lieu l) {
		super(fen,"---  ^^  ---",true) ; 
		//setSize(500, 300) ;
		setBounds(450,50,400,100);
		this.a=a;
		this.l=l;
		
		Container contenu = getContentPane() ;
		contenu.setLayout(new FlowLayout()) ;
		ok=new JButton("ok");
		ok.setSize(50,30);
		ok.setCursor(new Cursor(12));
		ok.addActionListener(this);
		/*new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
			
		});*/
		switch(a){
		case 0:	texte=new JLabel("erreur dans le format de la date...    ");break;
		case 1: texte= new JLabel("erreur dans le format des coordonnées du lieu...    ");break;
		case 2 : texte=new JLabel("ville ?   "); ville=new JTextField(15);break;
		case 3 : texte=new JLabel("rentrer un nom de viller !!");break;
		default : texte=new JLabel("Erreur quelque part... Vérifie");break;
		}
		contenu.add(texte);
		if(ville!=null){contenu.add(ville);}
		contenu.add(ok);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(a!=2){
			setVisible(false);
		}
		else{
			Lieu lieu=new Lieu(ville.getText(),l);
			try{
				String chemin="lieu.dat";
				FileWriter fichier=new FileWriter(chemin, true);
				try{
					fichier.write(lieu.nom+","+lieu.lon+","+lieu.lonm+","+lieu.lons+","+
									lieu.lat+","+lieu.latm+","+lieu.lats);
					fichier.write(System.getProperty("line.separator" ));
				}
				finally{
					fichier.close();
				}
			}
			catch(IOException ioe){
				System.out.println("erreur dans dialog");
			}
			setVisible(false);
		
		}
		
	}
	
}