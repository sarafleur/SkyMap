import java.util.*;
import java.text.DateFormat; 
import java.text.SimpleDateFormat; 


public class Maintenant {

	String annee,mois,jour,heure,minute;
	static Locale locale = Locale.getDefault();
    static Date actuelle = new Date();
    static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    String dat = dateFormat.format(actuelle);
	
    public Maintenant(){
    	DateFormat dateFormata = new SimpleDateFormat("yyyy");
    	this.annee=dateFormata.format(actuelle);
    	DateFormat dateFormatmo = new SimpleDateFormat("MM");
    	this.mois=dateFormatmo.format(actuelle);
    	DateFormat dateFormatj = new SimpleDateFormat("dd");
    	this.jour=dateFormatj.format(actuelle);
    	DateFormat dateFormath = new SimpleDateFormat("HH");
    	this.heure=dateFormath.format(actuelle);
    	DateFormat dateFormatmi = new SimpleDateFormat("mm");
    	this.minute=dateFormatmi.format(actuelle);
    }	
}
