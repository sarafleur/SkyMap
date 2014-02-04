
public class Manip {
	
public static int [] decalageMinMoins (int a, int mois, int j, int h, int min){
	int[] tab = new int[5];
     	if (min==00){
     		tab[4]=59;
     		if (h==0){
         		tab[3]=23;
     			if (j==1){
     				if (mois==1){
                 		tab[2]=Temps.nbJourMois(a, 12);
         				tab[0]=a-1;
                 		tab[1]=12;}
     				else{
                 		tab[2]=Temps.nbJourMois(a, mois-1);
         				tab[0]=a;
                 		tab[1]=mois-1;}}
     			else{
     				tab[0]=a;
             		tab[1]=mois;
             		tab[2]=j-1;}}
     		else {
         		tab[0]=a;
         		tab[1]=mois;
         		tab[2]=j;
         		tab[3]=h-1;
         		tab[4]=59;}}
     	else{
     		tab[0]=a;
     		tab[1]=mois;
     		tab[2]=j;
     		tab[3]=h;
     		tab[4]=min-1;}
	return tab;
}

public static int [] decalageMinPlus (int a, int mois, int j, int h, int min){
	int[] tab = new int[5];
     	if (min==59){
     		tab[4]=0;
     		if (h==23){
     			tab[3]=0;
     			if (j==Temps.nbJourMois(a,mois)){
             		tab[2]=1;
     				if (mois==12){
         				tab[0]=a+1;
                 		tab[1]=1;}
     				else{
         				tab[0]=a;
                 		tab[1]=mois+1;}}
     			else{
     				tab[0]=a;
             		tab[1]=mois;
             		tab[2]=j+1;}}
     		else {
         		tab[0]=a;
         		tab[1]=mois;
         		tab[2]=j;
         		tab[3]=h+1;}}
     	else{
     		tab[0]=a;
     		tab[1]=mois;
     		tab[2]=j;
     		tab[3]=h;
     		tab[4]=min+1;}
	return tab;
}

public static int[] decalageHMoins (int a, int mois, int j, int h, int min){
	int[] tab = new int[5];
	tab[4]=min;
 		if (h==0){
     		tab[3]=23;
 			if (j==1){
 				if (mois==1){
 	         		tab[2]=Temps.nbJourMois(a, 12);
     				tab[0]=a-1;
             		tab[1]=12;}
 				else{
 	         		tab[2]=Temps.nbJourMois(a, mois-1);
     				tab[0]=a;
             		tab[1]=mois-1;}}
 			else{
 				tab[0]=a;
         		tab[1]=mois;
         		tab[2]=j-1;}}
 		else {
     		tab[0]=a;
     		tab[1]=mois;
     		tab[2]=j;
     		tab[3]=h-1;}

	return tab;
}

public static int[] decalageHPlus (int a, int mois, int j, int h, int min){
	int[] tab = new int[5];
 		tab[4]=min;
 		if (h==23){
 			tab[3]=0;
 			if (j==Temps.nbJourMois(a,mois)){
         		tab[2]=1;
 				if (mois==12){
     				tab[0]=a+1;
             		tab[1]=1;}
 				else{
     				tab[0]=a;
             		tab[1]=mois+1;}}
 			else{
 				tab[0]=a;
         		tab[1]=mois;
         		tab[2]=j+1;}}
 		else {
     		tab[0]=a;
     		tab[1]=mois;
     		tab[2]=j;
     		tab[3]=h+1;}
return tab;
}
public static int[] decalageJMoins (int a, int mois, int j, int h, int min){
	int[] tab = new int[5];
	tab[4]=min;
	tab[3]=h;
 			if (j==1){
 				if (mois==1){
 	         		tab[2]=Temps.nbJourMois(a, 12);
     				tab[0]=a-1;
             		tab[1]=12;}
 				else{
 	         		tab[2]=Temps.nbJourMois(a, mois-1);
     				tab[0]=a;
             		tab[1]=mois-1;}}
 			else{
 				tab[0]=a;
         		tab[1]=mois;
         		tab[2]=j-1;}
	return tab;
}
public static int[] decalageJPlus (int a, int mois, int j, int h, int min){
	int[] tab = new int[5];
		tab[4]=min;
		if(h==24){tab[3]=1; }else{if(h==25){tab[3]=1;}else{tab[3]=h;}}
			//tab[3]=0;
			if (j==Temps.nbJourMois(a,mois)){
     		tab[2]=1;
				if (mois==12){
 				tab[0]=a+1;
         		tab[1]=1;}
				else{
 				tab[0]=a;
         		tab[1]=mois+1;}}
			else{
				tab[0]=a;
     		tab[1]=mois;
     		if(h==24||h==25){tab[2]=j+2;} else{	tab[2]=j+1;}
     		}
return tab;
}

public static int[] decalageMoiMoins (int a, int mois, int j, int h, int min){
	int[] tab = new int[5];
	tab[4]=min;
	tab[3]=h;
 				if (mois==1){
     				tab[0]=a-1;
             		tab[1]=12;
             		tab[2]=j;}
 				else{
	     		    tab[0]=a;
	             	tab[1]=mois-1;
 					if (j<=Temps.nbJourMois(a, mois-1)){
 	             		tab[2]=j;
 					}
 					else{
 						tab[2]=Temps.nbJourMois(a, mois-1);
 					}}
	return tab;
}
public static int[] decalageMoiPlus (int a, int mois, int j, int h, int min){
	int[] tab = new int[5];
	tab[4]=min;
	tab[3]=h;
 				if (mois==12){
     				tab[0]=a+1;
             		tab[1]=1;
             		tab[2]=j;}
 				else{
	     		    tab[0]=a;
	             	tab[1]=mois+1;
 					if (j<=Temps.nbJourMois(a, mois+1)){
 	             		tab[2]=j;
 					}
 					else{
 						tab[2]=Temps.nbJourMois(a, mois+1);
 					}}
	return tab;
}
public static int[] decalageAnnMoins (int a, int mois, int j, int h, int min){
	int[] tab = new int[5];
	tab[4]=min;
	tab[3]=h;
	tab[1]=mois;
	tab[0]=a-1;
    if (j==29&&mois==2){
    	tab[2]=28;}
    else {
    	tab[2]=j;}
	return tab;
}
public static int[] decalageAnnPlus (int a, int mois, int j, int h, int min){
	int[] tab = new int[5];
	tab[4]=min;
	tab[3]=h;
	tab[1]=mois;
	tab[0]=a+1;
    if (j==29&&mois==2){
    	tab[2]=28;}
    else {
    	tab[2]=j;}
	return tab;
}
}
