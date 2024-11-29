package threads.lab_1;

import java.util.function.DoubleUnaryOperator;
import java.util.concurrent.Callable;

public class IntegralCalculator implements Callable<Double> {

    private final Function function;

    public IntegralCalculator(double start, double end, int nSteps, DoubleUnaryOperator f) {
        function = new Function(start, end, nSteps, f);
    }

    @Override
    public Double call() {
        return function.calculate();
    }
}
