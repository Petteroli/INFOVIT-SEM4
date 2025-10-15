package no.uib.inf101.calculator.operations;

public class Multiplication implements Operator {

    @Override
    public String getSymbol() {
        return "*";
    }

    @Override
    public String getDescription() {
        return "Multiplication: \"Calculate the result of multiplying one number by another.\"";
    }

    @Override
    public double calculate(double num1, double num2) {
        return num1 * num2;
    }
    
}
