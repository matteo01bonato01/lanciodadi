package it.isa.gamedadi;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

//import org.junit.Test;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * Unit test for simple App.
 */
@TestInstance(Lifecycle.PER_CLASS)
@Disabled
public class AppTest 
{
    
    @BeforeAll
    public void execBeforeAll() {
        System.out.println("Before all");
    }

    @BeforeEach
    public void execBeforeEach() {
        System.out.println("Before Each");
    }



    @Test
    public void shouldAnswerWithTrue()
    {
        System.out.println(ConsoleColors.YELLOW + "Truetrue");
        assertTrue( true );
    }
    
    @Test
    public void shouldAnswerWithFalse()
    {
        System.out.println(ConsoleColors.RED + "Truetrue");
        assertFalse( false );
    }



    /* 
    @Test
    @Disabled
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    public void sleepForSomeTime() {
        System.out.println("Timeunit");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch(Exception e) {

        }
    }*/
}
