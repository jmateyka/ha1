package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Retro calculator")
class CalculatorTest {

    @Test
    @DisplayName("should display result after adding two positive multi-digit numbers")
    void testPositiveAddition() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "40";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display result after getting the square root of two")
    void testSquareRoot() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressUnaryOperationKey("√");

        String expected = "1.41421356";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when dividing by zero")
    void testDivisionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when drawing the square root of a negative number")
    void testSquareRootOfNegative() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressNegativeKey();
        calc.pressUnaryOperationKey("√");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should not allow multiple decimal dots")
    void testMultipleDecimalDots() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(7);
        calc.pressDotKey();
        calc.pressDigitKey(8);

        String expected = "1.78";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    // Teilaufgabe 1 //

    /**
     * Ein Test, der die Vorzeichenumkehr einer eingegebenen Zahl nach Betätigung der Vorzeichenumkehr-Taste
     * überprüft. Prüft ebenfalls eine erneute Transformation der gegebenen Zahl.
     */
    @Test
    @DisplayName("should toggle the sign of a number between positive and negative")
    void testToggleSign() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(3);
        calc.pressNegativeKey();

        String expectedAfterFirstToggle = "-3";
        String actualAfterFirstToggle = calc.readScreen();

        assertEquals(expectedAfterFirstToggle, actualAfterFirstToggle);

        calc.pressNegativeKey();

        String expectedAfterSecondToggle = "3";
        String actualAfterSecondToggle = calc.readScreen();

        assertEquals(expectedAfterSecondToggle, actualAfterSecondToggle);
    }
// Teilaufgabe 2 //

    /**
     * Ein Test, der überprüft, ob durch mehrmaliges Betätigen der "=" Taste, nach vorangeganer Eingabe einer Rechnung,
     * die letzte Rechenoperation wiederholt wird.
     */

    @Test
    @DisplayName("should repeat the last operation correctly with multiple equal presses")
    void repeatLastOperation() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(5);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(5);
        calc.pressEqualsKey();
        calc.pressEqualsKey();

        String expected = "15"; // Erwartet 5 + 5 + 5 = 15 beim zweiten `=` drücken
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    /**
     * Ein Test, der überprüft, ob nach einmaliger Division durch 0, in weiterführenden Rechenoperationen
     * weiterhin die Ausgabe "Error" angezeigt wird.
     */

    @Test
    @DisplayName("should display error after continuous division by zero")
    void continuousDivisionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(0);
        calc.pressEqualsKey();
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(1);
        calc.pressEqualsKey();

        String expected = "Error"; // Erwartung ist, dass "Error" angezeigt bleibt
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
}

