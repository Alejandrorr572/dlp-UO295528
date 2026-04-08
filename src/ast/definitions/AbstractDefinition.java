package ast.definitions;

import ast.AbstractLocatable;
import ast.types.Type;

public abstract class AbstractDefinition extends AbstractLocatable implements Definition  {

    protected int scope;
    private String name;
    private Type type;

    public AbstractDefinition(int line, int column, String name, Type type) {
        super(line, column);
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public int getScope() {
        return scope;
    }

    @Override
    public void setScope(int scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [name=" + name + ", type=" + type +
                ", scope=" + scope + ", line=" + getLine() + ", column=" + getColumn() + "]";
    }
}
