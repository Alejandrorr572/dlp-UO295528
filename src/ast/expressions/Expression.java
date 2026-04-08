package ast.expressions;

import ast.Locatable;
import ast.types.Type;

public interface Expression extends Locatable {
    public void setLvalue(boolean value);
    public boolean getLvalue();

    public void setType(Type type);
    public Type getType();
}
