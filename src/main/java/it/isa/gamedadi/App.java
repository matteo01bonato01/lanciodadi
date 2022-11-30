package it.isa.gamedadi;

import java.util.Scanner;

/**
 * eseguire: mvn exec:java
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        //IteratorPattern.esegui();
        
        System.out.println(ConsoleColors.YELLOW + "******* Hai 3 opzioni ******\n" +
                            ConsoleColors.RESET + "Premi 1 per avviare un lancio automatico\n" +
                            "Premi 2 per avviare un lancio manuale\n" +
                            "Premi 3 o qualsiasi altro tasto per chiudere");
        Scanner scanner = new Scanner(System.in);
        int inputString = scanner.nextInt();

        Dadi dadi = new Dadi();
        int a, b;

        switch(inputString) { //Math random genera numeri sempre tra 0 e 1
            case 1: a = (int) (Math.random()*10);
                    b = (int) (Math.random()*10);
                    if(a > 6) a = 6;
                    if(b > 6) b = 6;
                    dadi.lancio(a, b);

                    break;

            case 2: System.out.println("Scrivi il primo numero del dado: ");
                    a = scanner.nextInt();
                    System.out.println("Scrivi il secondo numero del dado: ");
                    b = scanner.nextInt();
                    if(a > 6) {System.out.println("'a' non valido. Imposto default."); a = 6;}
                    if(a < 1) {System.out.println("'a' non valido. Imposto default."); a = 1;}

                    if(b > 6) {System.out.println("'b' non valido. Imposto default."); b = 6;}
                    if(b < 1) {System.out.println("'b' non valido. Imposto default."); b = 1;}
                    dadi.lancio(a, b);

                    break;

            case 3: System.out.println("Chiusura interazione"); break;
            default: System.out.println(ConsoleColors.RED + "Digita correttamente la prossima volta\n\n" + ConsoleColors.RESET);
        }
    }
}
