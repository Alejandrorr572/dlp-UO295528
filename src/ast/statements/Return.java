package ast.statements;

import ast.AbstractLocatable;
import ast.expressions.Expression;

public class Return extends AbstractLocatable implements Statement {

    private Expression returnValue;

    public Return(Expression returnValue, int line, int column) {
        super(line, column);
        this.returnValue = returnValue;
    }

    public Expression getReturnValue() {
        return returnValue;
    }
}
