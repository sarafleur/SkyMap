import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import java.lang.Math;

public class Point {
	
	int x;
	int y;
	
	public Point (double x, double y){
		int scale=250;
		this.x= (int)(scale*(x+1)+0.5);
		this.y=(int)(-scale*(y-1)+0.5);
		this.x=2*scale-this.x;
		//plus tard, transformer directement
		//en utilisable 
		//(ie x et y en int, et enlever
		//la moitié du grand cercle noir)
		
		// ET TAILLE
	}
	
	public String toString(){
			return "x "+x+" y "+y;
	}
}

