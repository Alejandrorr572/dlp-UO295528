package ast.expressions;

import ast.AbstractLocatable;
import ast.types.Type;

public abstract class AbstractExpression extends AbstractLocatable implements Expression {

    private Type type;
    private boolean lvalue;

    public AbstractExpression(int line, int column) {
        super(line, column);
    }

    @Override
    public void setLvalue(boolean lvalue){
        this.lvalue = lvalue;
    }

    @Override
    public void setType(Type type){this.type=type;}

    public Type getType(){return type;}

    @Override
    public boolean getLvalue(){
        return lvalue;
    }
}
