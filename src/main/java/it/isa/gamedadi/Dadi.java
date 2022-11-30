package it.isa.gamedadi;

public class Dadi 
{
    private int facce;
    private ServerInterface si;

    public Dadi() {
        facce = 6;
    }
    public int getNumFacce() {
        return facce;
    }

    

    //costruttore attraverso il mock
    public Dadi(ServerInterface si) {
        this.si = si;
    }
    public int getNumFacceSi() {
        return si.getFacce();
    }
    public String getNameProprietari(String name, String surname) {
        return si.getProprietari(name, surname);
    }

    


    public int lancio(int a, int b) {
        int res = a + b;

        //if(res > java.lang.Integer.MAX_VALUE) {
        if(res <= 0) {
            System.out.println("C'e' stato overflow");
        }
        else {
            System.out.println("Dado 1: " + a + " || Dado 2: " + b + "\nRisultato del lancio: " + res);
        }
            
        return a + b;
    }

}
