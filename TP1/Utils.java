import java.util.Random;
import java.lang.Math;
class Utils{
    public static double loi_exponentielle(double param, double tArrive){
        Random rand = new Random();
        double x = rand.nextDouble();
        return tArrive - ((Math.log(1 - x))/param);
    }
}
