package ast.expressions;

import ast.AbstractLocatable;

public class RealLiteral extends AbstractLocatable implements Expression {

    private double value;

    public RealLiteral(double value, int line, int column) {
        super(line, column);
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
