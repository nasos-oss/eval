class MM1{
    public static double lambda;
    public static double mu;
    public static double dureeMaxSimulation;
    public static boolean debug; //true:debug, false:pas de debug
    public static void main(String[] args){
        //on stocke les arguments de l'utilisateur
        lambda = Double.parseDouble(args[0]);
        mu = Double.parseDouble(args[1]);
        dureeMaxSimulation = Double.parseDouble(args[2]);
        int debugtemp = Integer.parseInt(args[3]);
        if(debugtemp == 0){
            debug = false;
        }
        else{
            debug = true;
        }
        double debut = System.currentTimeMillis();
        //Creation et initialisation de l'echeancier avec une arrivée
        Ech ech = new Ech();
        //Simulation de la file d'attente
        ech.traitement();
        //temps d'execution en secondes
        double tempsExec = (System.currentTimeMillis() - debut)/1000;
        //Statistiques
        Stats.stats_theoriques();
        Stats.stats_simulation();
        System.out.println();
        System.out.println("Temps d'execution : " + tempsExec + "s");
    }
}
