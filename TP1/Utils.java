import java.util.Random;
import java.lang.Math;
class Utils{
    public float loi_exponentielle(int lambda){
        Random rand = new Random();
        int x = rand.nextFloat();
        return tArrive - ((Math.log(1 - x))/lambda);
    }
}
