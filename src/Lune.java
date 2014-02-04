
public class Lune {
	double L,M,D,F,O,lambda,B,O1,O2,beta,ascensionDroite, declinaison;
	
	public Lune (Temps T){
		double t = T.SiecleJulien();
		double t2 = Math.pow(t, 2);
		double t3 = Math.pow(t, 3);
		L = 270.434164 + 481267.8831*t - 0.001133*t2 + 0.0000019*t3;
		M = 296.104608 + 477198.8491*t + 0.009192*t2 + 0.0000144*t3;
		D = 350.737486 + 445267.1142*t - 0.001436*t2 + 0.0000019*t3;
		F = 11.250889 + 483202.0251*t - 0.003211*t2 - 0.0000003*t3;
		O = 259.183275 - 1934.1420*t + 0.002078*t2 + 0.0000022*t3;
		
		lambda = L + 6.288750*Math.sin(Math.toRadians(M))
				+ 1.274018*Math.sin(Math.toRadians(2*D-M))
				+ 0.658309*Math.sin(Math.toRadians(2*D))
				+ 0.213616*Math.sin(Math.toRadians(2*M));
		
		B = 5.128189*Math.sin(Math.toRadians(F))
				+ 0.280606*Math.sin(Math.toRadians(M+F))
				+0.277693*Math.sin(Math.toRadians(M-F))
				+0.173238*Math.sin(Math.toRadians(2D-F));
		
		O1 = 0.0004664*Math.cos(Math.toRadians(O));
		O2 = 0.00000754*Math.cos(Math.toRadians(O+275.05-2.30*t));
		beta = B*(1-O1-O2);
		double e = T.oblequite();
		declinaison = Math.asin(Math.sin(Math.toRadians(beta))*Math.cos(e)+Math.cos(Math.toRadians(beta))*Math.sin(e)*Math.sin(Math.toRadians(lambda)));
		if (Math.cos(Math.toRadians(lambda))>0){
			ascensionDroite = Math.atan((Math.sin(Math.toRadians(lambda))*Math.cos(e)-Math.tan(Math.toRadians(beta))*Math.sin(e))/Math.cos(Math.toRadians(lambda)));
		}
		else{
			ascensionDroite = Math.atan((Math.sin(Math.toRadians(lambda))*Math.cos(e)-Math.tan(Math.toRadians(beta))*Math.sin(e))/Math.cos(Math.toRadians(lambda)))+Math.PI;
		}
	}
}
