package fr.easit.easit;

import fr.easit.Calculs;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculsTest {

    private Calculs c1 = null;
    private Calculs c2 = null;
    private Calculs c3 = null;

    @BeforeEach
    void setUp() throws Exception
    {
        c1 = new Calculs(1,2);
        c2 = new Calculs(10,20);
        c3 = new Calculs(100,200);
    }


    @Test
    void testMultiplier()
    {
        assertEquals(c1.multiplier(), 2, "Echec methode multiplier");
    }


    @Test
    void testAdditionner()
    {
        assertEquals(c2.additionner(), 30, "Echec methode additionner");
    }


    @Test
    void testDiviser()
    {
        assertEquals(c2.diviser(), 0, "Echec methode diviser");
    }


    @Test
    void testSoustraire()
    {
        assertEquals(c3.soustraire(), -100, "Echec methode soustraire");
    }


    @AfterEach
    void tearDown() {
    }

}