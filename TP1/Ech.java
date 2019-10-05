class Ech{
    LinkedList<Evt> echeancier = new LinkedList<Evt>(); // liste chainée d'evenements
    //ajoute un evenement à l'écheancier selon l'ordre chronologique
    public void add(Evt evt){
        if (evt.date > )
        for(int num=0; num < echeancier.size(); num++){
            if (echeancier[num].date > evt.date){
                echeancier.add(num+1, evt);
            }
        }
    }
    public void remove(Evt evt){
        echeancier.remove(evt);
    }

}
