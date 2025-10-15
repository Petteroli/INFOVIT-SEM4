package no.uib.inf101.calculator.operations;

public class Exponent implements Operator {

    @Override
    public String getSymbol() {
        return "^";
    }

    @Override
    public String getDescription() {
        return "Calculate the result of raising a base number to the power of an exponent.";
    }

    @Override
    public double calculate(double num1, double num2) {
        return Math.pow(num1, num2);
    }
    
}
