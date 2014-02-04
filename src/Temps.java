import java.util.Calendar;


public class Temps {
	double fracM;
	int annee;
	int mois;
	int jour;
	int heure;
	int minute;
	int decalage;
	double FracJ;
	int y;
	int m;
	
	public Temps (int a, int mois, int j, int h, int min){
		int d=0;
		if (heureEte(a,mois,j,h)){
			d=-2;
		}
		else {
			d=-1;
		}
		this.decalage=d;
		this.minute=min;
		if (d==0){
			this.annee=a;
			this.mois=mois;
			this.jour=j;
			this.heure=h;
		}
		else {
			if (d>0){
				if (d+h<24){
					this.annee=a;
					this.mois=mois;
					this.jour=j;
					this.heure=h+d;
				}
				else{
					this.heure=d+h-24;
					if (j<nbJourMois(a,mois)){
						this.annee=a;
						this.mois=mois;
						this.jour=j+1;
					}
					else{
						this.jour=1;
						if (mois<12){
							this.annee=a;
							this.mois=mois+1;
						}
						else{
							this.annee=a+1;
							this.mois=1;
						}
					}
				}				
			}
			else {
				if (d+h>0){
					this.annee=a;
					this.mois=mois;
					this.jour=j;
					this.heure=h+d;
				}
				else{
					this.heure=24+d+h;
					if (j>1){
						this.annee=a;
						this.mois=mois;
						this.jour=j-1;
					}
					else{
						if (mois>1){
							this.annee=a;
							this.mois=mois-1;
							this.jour=nbJourMois(a,mois-1);
						}
						else{
							this.annee=a-1;
							this.mois=12;
							this.jour=31;
						}
					}
				}
			}
		}
		this.FracJ=this.jour+(double)(60*this.heure+this.minute)/1440;
		if (this.mois>2){
			this.y=this.annee;
			this.m=this.mois;
		}
		else {
			this.y=this.annee-1;
			this.m=this.mois+12;
		}

	}

	public static boolean estBissextile (int year){
		if (Temps.Gregorien(year, 1, 1)){
			return (year%4==0&&!(year%100==0))||(year%400==0);
		}
		else{
			return (year%4==0);
		}
		
	}

	//fonction qui renvoie le nombre de jour dans un mois donné (tient compte des années bissextiles pour le mois de février)
	public static int nbJourMois (int year, int month){
		if (month==1||month==3||month==5||month==7||month==8||month==10||month==12){
			return 31;
		}
		else{
			if (month==4||month==6||month==9||month==11){
				return 30;
			}
			else{
				if (estBissextile(year)){
					return 29;
				}
				else {
					return 28;
				}
			}
		}
	}
	
	//renvoie un numéro qui correspond au jour de la semaine (0 pour dimanche, 1 lundi,2 mardi...)(algorithme de Lewis Caroll)
	public static int jourSemaine (int a,int m,int j){
		int c = a/100;
		int C=0; //nombre séculaire
		if (a<1782){
			C=18-c;
		}
		else{
			C=2*(3-c%4);
		}
		int y = a%100;
		int Y = (y/12+y%12+(y%12)/4)%7; //nombre annuel
		int M=0; //nombre mensuel
		switch (m){
		case 1 : M=0;break;
		case 2 : M=3;break;
		case 3 : M=3;break;
		case 4 : M=6;break;
		case 5 : M=1;break;
		case 6 : M=4;break;
		case 7 : M=6;break;
		case 8 : M=2;break;
		case 9 : M=5;break;
		case 10 : M=0;break;
		case 11 : M=3;break;
		case 12 : M=5;break;
		}
		int D=0; //nombre journalier
		if ((m==1||m==2)&&(estBissextile(a))){
			D=(j-1)%7;
		}
		else{
			D=j%7;
		}
		return (C+Y+D+M)%7;
	}

	//renvoie le numéro du jour d'un mois donnée qui est le dernier dimanche de ce mois donné
	public static int dernierDimanche (int a, int m){
		int r =nbJourMois(a,m)-6;
		for (int i =26;i<nbJourMois(a,m)+1;i++){
			if (jourSemaine(a,m,i)==0){
				r=i;
			}
		}
		return r;
	}

	//renvoie true si la date donnée par l'utilisateur est à l'heure d'été (tient compte de son établissement en 1976 et du changement en 1995
	public static boolean heureEte (int a,int m,int j,int h){
		if (a>1995){
			if ((Temps.apres(a, m, j, h, 0, a, 3, dernierDimanche(a,3), 2, 0))&&(Temps.apres(a, 10, dernierDimanche(a,10), 3, 0, a, m, j, h, 0))){
				return true;
			}
			else {
				return false;
			}
		}
			else{
				if (a>1975){
					if ((Temps.apres(a, m, j, h, 0, a, 3, dernierDimanche(a,3), 2, 0))&&(Temps.apres(a, 10, dernierDimanche(a,9), 3, 0, a, m, j, h, 0))){
						return true;
					}
					else{
						return false;
					}
				}
				else {
					return false;
				}
			}
		}

	
	
	//Vérifie si un temps fait partie du calendrier grégorien (elle est statique car utilisée egalement dans le temps relatif
	public static boolean Gregorien (int annee, int mois, int jour){
		if(apres(annee,mois,jour,0,0,1582,10,15,0,0)){
			return true;
		}
		else {return false;}
	}
	
	//renvoie true si la date 1 est après la date 2 ou égale à la date 2
	public static boolean apresT(Temps t1,Temps t2){
		return apres(t1.annee,t1.mois,t1.jour,t1.heure,t1.minute,t2.annee,t2.mois,t2.jour,t2.heure,t2.minute);
	}
	
	public static boolean apres (int a1, int mois1, int j1, int h1, int min1,int a2, int mois2, int j2, int h2, int min2){
		if (a1>a2){
			return true;
		}
		else{
			if (a1==a2){
				if (mois1>mois2){
					return true;}
				else{
					if (mois1==mois2){
						if (j1>j2){
							return true;}
						else{
							if (j1==j2){
								if (h1>h2){
									return true;}
								else{
									if (h1==h2){
										if (min1<min2){
											return false;}
										else{
											return true;}}
									else {
										return false;}}}
							else {
								return false;}}}
					else{
						return false;}}}
			else {
				return false;}}
	}
	
	public double JourJulien (){
		if (Gregorien(annee,mois,jour)){
			int A = (int)((double)(y)/100);
			return (double) (FracJ+(int)(365.25*y)+(int)(30.6001*(m+1))+1720994.5+2-A+(int)((double)(A)/4));
		}
		else {
			return (double) (FracJ+(int)(365.25*y)+(int)(30.6001*(m+1))+1720994.5);
		}
	}
	
	public double SiecleJulien (){
		return (double)(JourJulien()-2415020)/36525;
	}
	
	//permet de calculer le jour julien à 0h
	public double JourJulien0(){
		if (Gregorien(annee,mois,jour)){
			int A = (int)((double)(y)/100);
			return (double) (jour+(int)(365.25*y)+(int)(30.6001*(m+1))+1720994.5+2-A+(int)((double)(A)/4));
		}
		else {
			return (double) (jour+(int)(365.25*y)+(int)(30.6001*(m+1))+1720994.5);
		}
	}
	
	//permet de calculer le siècle julien à 0h
	public double SiecleJulien0 (){
		return (double)(JourJulien0()-2415020)/36525;
	}
	
	//on utilise la formule du temps sidérale et on prend le reste de la division par 24
	public double TempsSideral (){
		double f = heure+(double)(minute)/60;
		return ((double)(6.6460656+2400.051*SiecleJulien0()+0.00002581*SiecleJulien0()*SiecleJulien0()+1.002737908*f))%24;
	}
	
	public double oblequite (){
		double T = this.SiecleJulien();
		return Math.toRadians(23.452294-0.0130125*T-0.000000164*T+0.000000503*T*T*T);
	}	
	
	public static boolean comparer(Temps t1,Temps t2){
		Calendar date1=Calendar.getInstance();
		Calendar date2=Calendar.getInstance();
		date1.set(t1.annee, t1.mois, t1.jour, t1.heure, t1.minute);
		date2.set(t2.annee, t2.mois, t2.jour, t2.heure, t2.minute);
		return date2.after(date1);
	}
	
	public static long nbMinute(Temps t1, Temps t2){
		Calendar date1=Calendar.getInstance();
		Calendar date2=Calendar.getInstance();
		date1.set(t1.annee, t1.mois, t1.jour, t1.heure, t1.minute);
		date2.set(t2.annee, t2.mois, t2.jour, t2.heure, t2.minute);
		long s1=date1.getTimeInMillis();
		long s2=date2.getTimeInMillis();
		return (Math.abs(s2-s1)/(1000*60));
	}
	
	public static int nbMin(Temps t1, Temps t2){
		int j=0;
		Temps t0=t1;
		while(!(apresT(t0,t2))){
			j++;
			int[] t =Manip.decalageMinPlus(t0.annee,t0.mois,t0.jour,t0.heure-t0.decalage,t0.minute);
			t0=new Temps(t[0],t[1],t[2],t[3],t[4]);
		}
		return j;
	}

	public static void main(String[] args){
		System.out.println((int)(long)Temps.nbMinute(new Temps(2013,05,31,23,0),new Temps(2013,06,01,01,0)));
	}

	public String toString(){
		return this.annee+" "+this.mois+" "+this.jour+" "+this.heure+" "+this.minute;
	}
}
