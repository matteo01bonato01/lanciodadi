package it.isa.gamedadi;

import java.util.HashMap;
import java.util.Map;

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
        listaCodici[i] = "204: Rapina in corso;"; i++;
        listaCodici[i] = "205: Incidente;"; i++;
        listaCodici[i] = "206: Sparatoria in corso;"; i++;
        listaCodici[i] = "207: Assalto;"; i++;
        listaCodici[i] = "208: Agente a terra/Omicidio;"; i++;
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
            if( c  == ' ' || c == '!' || c == ':' || c == ';' || c == '/') strBuilder.append(c);

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



public class IteratorPattern {
    private int shift = 1;

    
    public String decifraRiga(String s) {

        StringBuilder strBuilder = new StringBuilder();
        char c;
        for (int i=0; i < s.length(); i++) {
            
            c = (char) s.charAt(i);

            //caso spaziatura
            if( c  == ' ' || c == '!' || c == ':' || c == ';' || c == '/') strBuilder.append(c);

            //caso a
            else if( c  == 'a') strBuilder.append('z');

            //caso 0
            else if( c  == '0') strBuilder.append('9');

            else {
                c = (char) ((char) s.charAt(i) - shift);
                strBuilder.append(c);
            }
        }
        //System.out.println(strBuilder.toString());
        return strBuilder.toString();
    }



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


        //azzero l'index della lista sul libro mastro
        li.rewind();
        //li.nextElement();
        
        //CREO "NUOVO LIBRO MASTRO CIFRATO" delle intercettazioni tramite hashMap
        Map<Integer, String> hashMapIntercetta = new HashMap<Integer, String>();
        while(li.hasNextElement()) {
            String strAnalisi = li.nextElementCifrato();

            hashMapIntercetta.put( Integer.parseInt(strAnalisi.substring(0, 3)), 
                                strAnalisi.substring(6));
            System.out.println(strAnalisi);
        }



        /*
        System.out.println("Stampa valori:");
        for(String s : hashMapIntercetta.values()) {
            System.out.println(s);
        }
*/
        IteratorPattern it = new IteratorPattern();
        System.out.println("\n\nStampa chiavi:");
        for(int s : hashMapIntercetta.keySet()) {
            System.out.print("Il codice " + s + " decifrato in realta' e' un: ");
            //it.decifraRiga(Integer.toString(s));
            System.out.print(   it.decifraRiga(Integer.toString(s))    + "\n");
        }
        

        


        
        li.rewind();
        int t, limit;
        System.out.println("\n\nIl giornalista sta hackerando i messaggi alla radio...\n\n" +
        "E' alla ricerca delle parole: 'omicidio', 'sparatoria' e 'incidente'.\n\n");
        
        for (int randomico = 0; randomico < 4; randomico++) {
            //inizializzo randomizzazione e azzero l'index della lista
            limit = (int) (Math.random()*10);
            li.rewind();

            for(t=0; t <= limit; t++){
                
                if (t==limit){
                    String stringtest = li.nextElementCifrato();
                    System.out.println(stringtest);

                    //il giornalista sta decifrando per sapere se il codice gli interessa
                    String stringDecifroCodice = it.decifraRiga(stringtest.substring(0,3));
                    String stringDecifroMessaggio = it.decifraRiga(stringtest.substring(4));
                    System.out.println("Il codice decifrato e': " + stringDecifroCodice +
                    " associato a: " + stringDecifroMessaggio);
                    
                    if(stringDecifroMessaggio.toLowerCase().contains("omicidio")
                        || stringDecifroMessaggio.toLowerCase().contains("sparatoria") 
                        || stringDecifroMessaggio.toLowerCase().contains("incidente"))
                        System.out.println(ConsoleColors.RED +
                        "INTERCETTATA PAROLA CHIAVE !!!!****  ....::::"+
                        " Mi muovo! ::::....\n" + ConsoleColors.RESET);
                    else System.out.println(ConsoleColors.GREEN +
                            "Non di interesse ==> Resto fermo.\n" + ConsoleColors.RESET);
                    
                }  else li.nextElement();
                
                //ogni 2 secondi viene inviata una comunicazione alla stazione radio
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }            
        }
    }
    /*public static void main( String[] args )
    {
        System.out.println( "Pattern!" );
    }*/
}
