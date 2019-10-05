import java.time.LocalDate;

class Evt{
    int type;     //0 : arrivée, 1: départ
    int number;   //n° de l'évenement
    static float dateActuelle = System.currentTimeMillis();
    float date = (System.currentTimeMillis - dateActuelle) / 1000000000 ;   //temps en secondes
    LocalDate tempsArrive = LocalDate.now();
    if (type){     // si c'est un départ

    }
    public Evt(int type){
        this.type = type;
        if (type){
            Stats.nbArrives++;
            number = Stats.nbArrives;
        }
        else{
            Stats.nbDeparts++;
            number = Stats.nbDeparts;
        }
    }
}
