package fr.easit.easit;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CalculsTest2 {

    private Calculs c1 = null;
    private Calculs c2 = null;
    private Calculs c3 = null;

    @BeforeEach
    void setUp() throws Exception {

        c1 = new Calculs(1,2);
        c2 = new Calculs(10,20);
        c3 = new Calculs(100,200);

    }

    static Stream<Arguments> chargerJeuDeTest() throws Throwable
    {
        return Stream.of(
                Arguments.of(2,2,4),
                Arguments.of(6,6,36),
                Arguments.of(3,2,3)
        );
    }

    @ParameterizedTest(name = "Multiplication numéro {index}: number1={0}, number2={1}, expected_result={2}")
    @MethodSource("chargerJeuDeTest")
    void testMultiplier(int firstNumber, int secondNumber, int expectedResults)
    {
        Calculs monCal = new Calculs(firstNumber, secondNumber);
        assertEquals(expectedResults, monCal.multiplier(), "test en échec pour " + firstNumber + "*" + secondNumber + " !");

        String n = null;
        assertNull(n);
    }
}
