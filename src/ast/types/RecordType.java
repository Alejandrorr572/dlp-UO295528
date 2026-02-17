package ast.types;

import ast.definitions.VarDefinition;
import java.util.List;

public class RecordType implements Type {

    private List<VarDefinition> definitions;

    public RecordType(List<VarDefinition> definitions) {
        this.definitions = definitions;
    }

    public List<VarDefinition> getDefinitions() {
        return definitions;
    }
}
