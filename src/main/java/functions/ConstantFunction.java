package functions;

public class ConstantFunction implements MathFunction {

    private final int constant;

    public ConstantFunction(int constant) {
        this.constant = constant;
    }

    public double apply(double x) {
        return constant;
    }

}
