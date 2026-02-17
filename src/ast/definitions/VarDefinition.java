package ast.definitions;

import ast.types.Type;
import java.util.List;

public class VarDefinition implements Definition {

    private Type type;
    private List<String> identifiers;

    public VarDefinition(Type type, List<String> identifiers) {
        this.type = type;
        this.identifiers = identifiers;
    }

    @Override
    public Type getType() {
        return type;
    }

    public List<String> getIdentifiers() {
        return identifiers;
    }
}
