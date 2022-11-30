package it.isa.gamedadi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* Semplificando quelli che sono i codici della polizia,
 * se ne sfruttino solo 1 a 10, dal meno rilevante al più importante.
 */
interface myIterator {
    void rewind();
    String nextElement();
    boolean hasNextElement();
    String nextElementCifrato();
}

interface myCollection {
    public myIterator getIter(); // un metodo solo, che restituisce un 'myIterator'
}

class myListArray implements myCollection {
    public String[] listaCodici;
    public int totaleElementi;

    public myListArray() {
        totaleElementi = 10;
        listaCodici = new String[totaleElementi+1];
        int i=0;

        //readfile
        /*try {
            File libro = new File("resources/LibroMastroDeiCodici.txt");
            Scanner myReader = new Scanner(libro);
            while (myReader.hasNextLine()) {
              //String data = myReader.nextLine();
              listaCodici[i] = myReader.nextLine();
              //System.out.println(listaCodici[i]);
            }myReader.close();
        } catch (FileNotFoundException e) {
          System.out.println("Libro mastro non trovato nella folder corrente.");
          e.printStackTrace();
        }*/
        
        listaCodici[i] = ""; i++;
        listaCodici[i] = "200: Furto minore;"; i++;
        listaCodici[i] = "201: Furto con scasso;"; i++;
        listaCodici[i] = "202: Borseggio;"; i++;
        listaCodici[i] = "203: Sospetto attentatore;"; i++;
        listaCodici[i] = "204: Colto in flagrante;"; i++;
        listaCodici[i] = "205: Rapina in corso;"; i++;
        listaCodici[i] = "206: Sparatoria in corso;"; i++;
        listaCodici[i] = "207: Assalto;"; i++;
        listaCodici[i] = "208: Agente a terra;"; i++;
        listaCodici[i] = "209: Attentato;"; i++;
    }

    public myIterator getIter() {
        return new myListIterator(this);
    }
}



class myListIterator implements myIterator {
    private int index = 0;  //necessariamente 0
    private int shift = 1;
    private String[] lista;

    public myListIterator(myListArray la) {
        lista = la.listaCodici;
    }

    public void rewind() {
        index = 0;
    }

    public String nextElement() {
        index++;        //devo far partire nextElement da 0 anziche 1
        return lista[index];
    }

    public boolean hasNextElement() {
        return index < lista.length-1; //ritorna un boolean
    }


    public String nextElementCifrato() {
        index++;        //devo far partire nextElement da 0 anziche 1
        String str = lista[index];


        StringBuilder strBuilder = new StringBuilder();
        char c;
        for (int i=0; i < str.length(); i++) {
            
            c = (char) str.charAt(i);
            //caso spaziatura
            if( c  == ' ' || c == '!' || c == ':' || c == ';') strBuilder.append(c);

            //caso z
            else if( c  == 'z') strBuilder.append('a');

            //caso 9
            else if( c  == '9') strBuilder.append('0');

            else {
                c = (char) ((char) str.charAt(i) + shift);
                strBuilder.append(c);
            }
        }
        return strBuilder.toString();
    }
}



public class IteratorPattern 
{
    public static void esegui(){
        System.out.println( "\nCodici della polizia: sono evidenziati in verde \n" + 
        "i codici meno gravi, mentre in rosso quelli più gravi.\n" );
        myCollection la = new myListArray();
        myIterator li = la.getIter();

        while(li.hasNextElement()) {

            String rigaEmergenza = li.nextElement();
            String codice = rigaEmergenza.substring(0,3);
            String testo = rigaEmergenza.substring(3);


            if(Integer.parseInt(codice.substring(2)) <= 3)
                System.out.println(ConsoleColors.GREEN + codice +
                testo + ConsoleColors.RESET);
            else if(Integer.parseInt(codice.substring(2)) >=4 &&
                    Integer.parseInt(codice.substring(2)) <=6)
                System.out.println(ConsoleColors.YELLOW + codice +
                testo + ConsoleColors.RESET);
            else System.out.println(ConsoleColors.RED + codice +
            testo + ConsoleColors.RESET);
        }

        System.out.println("\n\n\nLa polizia ha scoperto che i giornalisti ascoltano" + 
        " sempre le stazioni radio. \nPerciò, grazie al cifrario di Cesare, aggiunge +1 " +
        "ai caratteri prima di segnalare\n i codici via radio.\n" +
        "Ovviamente non si vuole far sapere la gravita' dell'emergenza,\n" +
        "percio' e' stata eliminata anche la gradazione dei colori.\n\n");

        li.rewind();
        while(li.hasNextElement()) {
            System.out.println(li.nextElementCifrato());
        }

    }
    /*public static void main( String[] args )
    {
        System.out.println( "Pattern!" );
    }*/
}
