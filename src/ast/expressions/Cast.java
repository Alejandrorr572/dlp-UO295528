package ast.expressions;

import ast.types.Type;

public class Cast {
    private Type type;
    private Expression expression;

    public Cast(Type type, Expression expression){
        this.type = type;
        this.expression = expression;
    }
}
