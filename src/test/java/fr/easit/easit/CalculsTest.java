package fr.easit.easit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculsTest {

    private Calculs c1 = null;
    private Calculs c2 = null;
    private Calculs c3 = null;

    @BeforeEach
    void setUp() throws Exception {

        c1 = new Calculs(1,2);
        c2 = new Calculs(10,20);
        c3 = new Calculs(100,200);

    }


    @Test
    void testMultiplier()
    {
        fail("Echec divide");
        if (c1.multiplier() != 2)
            fail("Echec multiply");
        assertEquals(c1.multiplier(), 2);
    }


    @Test
    void testAdditionner() {
        fail("Echec divide");
        if (c2.additionner() != 30)
            fail("Echec add");
        assertEquals(c2.additionner(), 30);
    }


    @Test
    void testDiviser()
    {
        fail("Echec divide");
        if (c2.diviser() != 0)
                fail("Echec divide");
        assertEquals(c2.diviser(), 0);
    }


    @Test
    void testSoustraire() {
        fail("Echec divide");
        if (c3.soustraire() != -100)
            fail("Echec Substract");
        assertEquals(c3.soustraire(), -100);
    }


    @AfterEach
    void tearDown() {
    }

}