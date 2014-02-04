
public class Soleil {
	double L, M, C, O, longecl, ascensionDroite, declinaison;
	
	public Soleil (Temps T){
		L = 279.69668 + 36000.76892*T.SiecleJulien() + 0.0003025*Math.pow(T.SiecleJulien(),2);
		M = 358.47583 + 35999.04975*T.SiecleJulien() - 0.000150*Math.pow(T.SiecleJulien(), 2) - 0.0000033*Math.pow(T.SiecleJulien(),3);
		C = (1.919460 - 0.004789*T.SiecleJulien() - 0.000014*Math.pow(T.SiecleJulien(), 2))*Math.sin(Math.toRadians(M))
		    + (0.020094 + 0.000100*T.SiecleJulien())*Math.sin(Math.toRadians(2*M))
		    + 0.000293*Math.sin(Math.toRadians(3*M));
		O = 259.18 - 1934.142*T.SiecleJulien();
		longecl = Math.toRadians(L + C + 0.00569 - 0.00479*Math.sin(Math.toRadians(O)));
		//pour le soleil, beta vaut 0
		declinaison = Math.asin(Math.sin(longecl)*Math.sin(T.oblequite()));
		if (Math.cos(longecl)>0){
			ascensionDroite = Math.atan((Math.sin(longecl)*Math.cos(T.oblequite()))/Math.cos(longecl));
		}
		else{
			ascensionDroite = Math.atan((Math.sin(longecl)*Math.cos(T.oblequite()))/Math.cos(longecl))+Math.PI;
		}
	}
}
