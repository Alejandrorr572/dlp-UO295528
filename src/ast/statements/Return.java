package ast.statements;

import ast.AbstractLocatable;
import ast.expressions.Expression;
import visitors.Visitor;

public class Return extends AbstractLocatable implements Statement {

    private Expression returnValue;

    public Return(Expression returnValue, int line, int column) {
        super(line, column);
        this.returnValue = returnValue;
    }

    public Expression getReturnValue() {
        return returnValue;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "Return [line=" + getLine() + ", column=" + getColumn() + "]";
    }
}
