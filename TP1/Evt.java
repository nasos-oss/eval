import java.time.LocalDate;

class Evt{
    boolean type;     //false : arrivée, true: départ
    int number;   //n° de l'évenement
    double temps;
    public Evt(boolean type, double tps, int num){
        this.type = type;
        temps = tps;
        number = num;
    }
}
