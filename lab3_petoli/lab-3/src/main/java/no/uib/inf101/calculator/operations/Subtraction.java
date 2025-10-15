package no.uib.inf101.calculator.operations;

public class Subtraction implements Operator{

    @Override
    public String getSymbol() {
        return "-";
    }

    @Override
    public String getDescription() {
        return "Subtraction: \"Find the difference between two numbers by taking one away from the other.\"";
    }

    @Override
    public double calculate(double num1, double num2) {
        return num1 - num2;
    }
    
}
