package ast.expressions;

import ast.AbstractLocatable;
import ast.types.Type;

public class Cast extends AbstractLocatable implements Expression {
    private Type type;
    private Expression expression;

    public Cast(Type type, Expression expression, int line, int column){
        super(line, column);
        this.type = type;
        this.expression = expression;
    }
}
