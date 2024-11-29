package threads.lab_1;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.IntStream;

public class Function {
    private final double start;
    private final int nSteps;
    private final double step;
    private final DoubleUnaryOperator f;


    public Function(double start, double end, int nSteps, DoubleUnaryOperator f) {
        this.start = start;
        this.nSteps = nSteps;
        this.f = f;
        step = (end - start) / nSteps;
    }

    public double calculate(){
        return IntStream.range(0, nSteps).mapToDouble(i -> start + i * step).map(f).map(y -> y * step).sum();
    }

}
