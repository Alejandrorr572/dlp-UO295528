package ast.expressions;

import ast.types.Type;
import visitors.Visitor;

public class Cast extends AbstractExpression {
    private Type castType;
    private Expression expression;

    public Cast(Type type, Expression expression, int line, int column){
        super(line, column);
        this.castType = type;
        this.expression = expression;
    }

    public Type getCastType(){
        return castType;
    }

    public Expression getExpression(){
        return expression;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "Cast [targetType=" + castType + ", line=" + getLine() + ", column=" + getColumn() + "]";
    }
}
