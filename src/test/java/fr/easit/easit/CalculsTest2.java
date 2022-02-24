package fr.easit.easit;

import fr.easit.Calculs;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CalculsTest2 {

    static Stream<Arguments> chargerJeuDeTestMultiplier() throws Throwable
    {
        return Stream.of(
                Arguments.of(2,2,4),
                Arguments.of(6,6,36),
                // Mauvais expected result passé en 3ème paramètre pour s'assurer de la robustesse du test
                Arguments.of(7,1,6)
        );
    }

    static Stream<Arguments> chargerJeuDeTestDiviser() throws Throwable
    {
        return Stream.of(
                Arguments.of(2,0,0),
                Arguments.of(6,7,0),
                Arguments.of(3,2,1)
        );
    }

    static Stream<Arguments> chargerJeuDeTestAdditionner() throws Throwable
    {
        return Stream.of(
                Arguments.of(2,0,2),
                Arguments.of(3,3,6),
                Arguments.of(4,-3,1)
        );
    }

    static Stream<Arguments> chargerJeuDeTestSoustraire() throws Throwable
    {
        return Stream.of(
                Arguments.of(2,2,0),
                Arguments.of(4,6,-2),
                Arguments.of(4,-3,7)
        );
    }

    @ParameterizedTest(name = "Multiplication numéro {index}: number1={0}, number2={1}, expected_result={2}")
    @MethodSource("chargerJeuDeTestMultiplier")
    void testMultiplier(int firstNumber, int secondNumber, int expectedResults)
    {
        Calculs monCal = new Calculs(firstNumber, secondNumber);
        assertEquals(expectedResults, monCal.multiplier(), "Test en échec pour " + firstNumber + "*" + secondNumber + " !");
    }

    @ParameterizedTest(name = "Division numéro {index}: number1={0}, number2={1}, expected_result={2}")
    @MethodSource("chargerJeuDeTestDiviser")
    void testDiviser(int firstNumber, int secondNumber, int expectedResults)
    {
        if (firstNumber == 0 || secondNumber == 0)
            fail("Division par 0 impossible !");
        Calculs monCal = new Calculs(firstNumber, secondNumber);
        assertEquals(expectedResults, monCal.diviser(), "Test en échec pour " + firstNumber + "*" + secondNumber + " !");
    }

    @ParameterizedTest(name = "Addition numéro {index}: number1={0}, number2={1}, expected_result={2}")
    @MethodSource("chargerJeuDeTestAdditionner")
    void testAdditionner(int firstNumber, int secondNumber, int expectedResults)
    {
        Calculs monCal = new Calculs(firstNumber, secondNumber);
        assertEquals(expectedResults, monCal.additionner(), "Test en échec pour " + firstNumber + "*" + secondNumber + " !");
    }

    @ParameterizedTest(name = "Soustraction numéro {index}: number1={0}, number2={1}, expected_result={2}")
    @MethodSource("chargerJeuDeTestSoustraire")
    void testSoustraire(int firstNumber, int secondNumber, int expectedResults)
    {
        Calculs monCal = new Calculs(firstNumber, secondNumber);
        assertEquals(expectedResults, monCal.soustraire(), "Test en échec pour " + firstNumber + "*" + secondNumber + " !");
    }

}
