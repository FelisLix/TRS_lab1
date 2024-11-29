package threads.lab_1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionTest {

    @Test
    void functionTest1(){
        Function function = new Function(0, 1, 100 ,t -> Math.log(1 + t));
        double expected = 0.386294;
        double result = function.calculate();
        assertEquals(expected, result, 1e-1);
    }
    @Test
    void functionTest2(){
        Function function = new Function(2, 6, 100 ,t -> Math.log(1 + t));
        double expected = 6.32553;
        double result = function.calculate();
        assertEquals(expected, result, 1e-1);
    }
}