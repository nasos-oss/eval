import java.util.LinkedList;

class Stats{
    static int nbClientsTotal = 0;
    static int nbClients = 0;
    static int nbClientsSansAttente = 0;
    static int nbClientsAvecAttente;
    static double nbClientsMoyen = 0;
    static double tpsSejour = 0;
    static double debit;
    static public double dureeSimulation;
    static public LinkedList<Evt> listeArr = new LinkedList<Evt>(); //Liste des arrivées utile pour l'affichage

    public static void stats_theoriques(){
        double ro = MM1.lambda / MM1.mu;
        System.out.println();
        System.out.println("--------------------");
        System.out.println("RESULTATS THEORIQUES");
        System.out.println("--------------------");
        if (MM1.lambda < MM1.mu){
            System.out.println("lambda<mu : file stable");
        }
        else{
            System.out.println("lambda>mu : file instable");
        }
        System.out.println("ro (lambda/mu) = " + ro);
        System.out.println("nombre de clients attendus (lambda * duree) = " + MM1.lambda*MM1.dureeMaxSimulation);
        System.out.println("prob de service sans attente (1-ro) = " + (1 - ro));
        System.out.println("prob de file occupee (ro) = " + ro);
        System.out.println("debit (lambda) = " + MM1.lambda);
        System.out.println("esperance nb clients (ro/1-ro) = " + ro/(1-ro));
        System.out.println("tps moyen de sejour (1/mu(1-ro)) = " + (1/(MM1.mu*(1-ro))));
    }

    //Actualisation de certaines statistiques à chaque nouvel evenement
    public static void stats_evt(Evt evt, double tempsDernierEvt){
        if (!evt.type){     //arrivée
            nbClientsTotal++;
            if (nbClients == 0){
                nbClientsSansAttente++;
            }
            nbClients++;
            nbClientsMoyen += nbClients * (evt.temps - tempsDernierEvt);
            listeArr.add(evt);
            return;
        }
        nbClients--;
    }

    public static void removeFirstlisteArr(){
        listeArr.removeFirst();
    }

    public static Evt getFirstlisteArr(){
        return listeArr.getFirst();
    }

    public static Evt getlisteArr(int i){
        return listeArr.get(i);
    }

    public static int getSizelisteArr(){
        return listeArr.size();
    }


    public static void stats_simulation(){
        double proportionSansAtt = (double)nbClientsSansAttente/nbClientsTotal;
        double proportionAvecAtt = 1 - proportionSansAtt;
        double tpsMoyenSejour = tpsSejour/nbClientsTotal;
        nbClientsMoyen = nbClientsMoyen/dureeSimulation;
        System.out.println();
        System.out.println("--------------------");
        System.out.println("RESULTATS SIMULATION");
        System.out.println("--------------------");
        System.out.println("nombre total de clients = " + nbClientsTotal);
        System.out.println("proportion clients sans attente = " + proportionSansAtt);
        System.out.println("proportion clients avec attente = " + proportionAvecAtt);
        System.out.println("debit = " + nbClientsTotal/dureeSimulation);
        System.out.println("nb moyen de clients dans systeme = " + nbClientsMoyen);
        System.out.println("temps moyen de sejour = " + tpsMoyenSejour);
    }
}
