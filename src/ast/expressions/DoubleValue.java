package ast.expressions;

import visitors.Visitor;

public class DoubleValue extends AbstractExpression {

    private double value;

    public DoubleValue(double value, int line, int column) {
        super(line, column);
        this.value = value;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "DoubleValue [value=" + value + ", line=" + getLine() + ", column=" + getColumn() + "]";
    }
}
