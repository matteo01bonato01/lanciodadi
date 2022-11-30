package it.isa.gamedadi;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.TestInstance.Lifecycle;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Timeout;
import org.junit.runner.RunWith;

import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import com.pholser.junit.quickcheck.Property;

/*
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
*/

@RunWith(JUnitQuickcheck.class)
public class DadiTest 
{
    @Test
    public void testLancio()
    {
        System.out.println("\n\nTesto il metodo 'testLancio()'");
        Dadi op = new Dadi();
        float result = op.lancio(2, 3);
        assertEquals(5, result);
        //assertEquals(5, op.sum(2,3));
        
        System.out.println("Fine test del metodo 'testLancio()'\n\n\n");
    }
    

    @Test
    public void testLancioConMathRandom()
    {
        System.out.println( "\n\n\nTesto il metodo 'testLancioConMathRandom'\nInizia il gioco!" );

        for(int i=0; i <5; i++) {
            System.out.println("Giro " + i + " .... Sto lanciando ....");
        
            Dadi dadi = new Dadi();
            int a = (int) (Math.random()*10);
            int b = (int) (Math.random()*10);
            if(a > 6) a = 6;
            if(b > 6) b = 6;
            dadi.lancio(a, b);
        }
        System.out.println( "\nFine test del metodo 'testLancioConMathRandom'\n\n" );
    }




    
    @Test
    public void testReassignProprierties() {
        ServerInterface si = mock(ServerInterface.class);
        Dadi dd = new Dadi(si);

        //usando mock: l'importante è che value e expected siano uguali
        when(si.getFacce()).thenReturn(6);
        assertEquals(6, dd.getNumFacceSi());

        when(si.getProprietari(anyString(), anyString())).thenReturn("Matteo Bonato");

        assertEquals("Matteo Bonato", dd.getNameProprietari("Matteo", "Bonato"));

    }





    @Property
    public void alwaysHoldLancio(int a, int b) {
        Dadi op = new Dadi();
        op.getNumFacce();
        int result = op.lancio((int) (a*0.00000001),(int) (b*0.00000001));

        if((int) (a*0.00000001) >= 1 &&
            (int) (a*0.00000001) <=6 &&
            (int) (b*0.00000001) >= 1 &&
            (int) (b*0.00000001) <=6) {

            assertTrue(result > 1 && result <= 12);
            //qui devo ripetere l'output perche' il return consegna all'assert il risultato
            System.out.println(ConsoleColors.GREEN + "test riuscito. Risultato del lancio: " + result +
                                ConsoleColors.RESET + "\n");

        } else {
            assertFalse( ( (int) (a*0.00000001) >= 1 ) &&
            ( (int) (a*0.00000001) <=6 ) &&
            ( (int) (b*0.00000001) >= 1 ) &&
            ( (int) (b*0.00000001) <=6 ) );
            System.out.println(ConsoleColors.RED + 
                                "\ntest non utile. Risultato del lancio non valido: " +
                                result + ConsoleColors.RESET + "\n");
        }
    }



    @Property
    public void alwaysHoldLanciInOverflow(int a, int b) {
        Dadi op = new Dadi();
        op.getNumFacce();
        int result = op.lancio(Math.abs(a), Math.abs(b));

        //assertTrue( result < java.lang.Integer.MAX_VALUE );
        assertFalse( result > java.lang.Integer.MAX_VALUE );
        System.out.println(ConsoleColors.PURPLE + 
                                "\ntest con lancio non controllato. Risultato: " +
                                result + ConsoleColors.RESET + "\n");
    }

/* 
    @Property
    @Disabled
    public void testApp() {
        App a = new App(); a.main(null);
    }*/
}