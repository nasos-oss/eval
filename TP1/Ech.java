import java.util.LinkedList;
import java.util.Arrays;
import java.lang.Math;


class Ech{
    LinkedList<Evt> echeancier = new LinkedList<Evt>(); // liste chainée d'evenements
    private double derniereSortie;
    private double tempsDernierEvt = 0;

    public Ech(){
        Evt arr0 = new Evt(false, 0, 0);
        add(arr0);
    }


    /* Ajoute un evenement à l'écheancier selon l'ordre chronologique et actualise
    *  les valeurs de temps de l'echeancier
    */
    public void add(Evt evt){
        if(evt.type){
            derniereSortie = evt.temps;
        }
        int i=0;
        while(i<echeancier.size() && echeancier.get(i).temps < evt.temps){
            i++;
        }
        echeancier.add(i, evt);
    }

    public void traitement(){
        Evt evt;
        int i = 0;
        int k = 0;
        double dateArr = 0;
        /* Debut de la simulation, la simulation s'arrete quand le temps max est ecoulé
        *  on attends pas la fin de tous les departs
        */
        while(true){
            //On recupere l'évenement pour l'analyser
            evt = echeancier.get(i);
            int j;
            //Actualisation des stats avec l'évenement n
            Stats.stats_evt(evt, tempsDernierEvt);
            /* Si l'évenement n correspond à une arrivée d'un client, alors on a deux
            *  nouveaux évenements: l'arrivée future du client n+1 et
            *  la sortie future du client n
            *
            */
            if (!evt.type){
                // durée d'inter-arrivée
                double dateArrivee = Utils.loi_exponentielle(MM1.lambda, evt.temps);
                // Arret de la simulation quand le temps max est depassé
                if (dateArrivee > MM1.dureeMaxSimulation){
                    Stats.dureeSimulation = evt.temps;
                    return;
                }
                Evt arr = new Evt(false, dateArrivee, evt.number + 1);
                Evt depart;
                //si la file est vide
                if(Stats.nbClients == 0){
                    //durée de service + arrivée du client n
                    depart = new Evt(true, Utils.loi_exponentielle(MM1.mu, evt.temps), evt.number);
                }
                else{
                    depart = new Evt(true, Utils.loi_exponentielle(MM1.mu, Math.max(derniereSortie,evt.temps)), evt.number);
                }
                add(arr);
                add(depart);
                i++;
            }
            else{
                /* Enlève les événements de l'échéancier du début jusqu'au premier depart inclus (l'evenement analysé)
                * et les affiches (si debug)
                */
                for(j=0; j < echeancier.size(); j++,i--){
                    /*On supprime les arrivés dont on est sur que les departs ont déjà été effectués
                    * on supprime le premier car les arrivées sont dans l'odre croissant
                    */
                    if (Stats.getFirstlisteArr().number < evt.number){
                        Stats.removeFirstlisteArr();
                    }
                    if (echeancier.getFirst() == evt){
                        //on recupere la date d'arrivée correspondant au depart
                        for (k=0; k<Stats.getSizelisteArr(); k++){
                            if (Stats.getlisteArr(k).number == evt.number){
                                dateArr = Stats.getlisteArr(k).temps;
                            }
                        }
                        Stats.tpsSejour += evt.temps - dateArr;
                        if (MM1.debug){
                            System.out.println("Date=" + evt.temps + " Depart" + " client #" + evt.number + "      arrive a t=" + dateArr);
                        }
                        echeancier.removeFirst();
                        break;
                    }
                    Evt evt_affiche = echeancier.pollFirst();
                    if (!evt_affiche.type && MM1.debug){   //c'est une arrivée
                        System.out.println("Date=" + evt_affiche.temps + " Arrivee" + " client #" + evt_affiche.number);
                    }
                }
            }
            tempsDernierEvt = evt.temps;
        }
    }
}
