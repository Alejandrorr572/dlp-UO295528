package ast.types;

import ast.RecordField;
import ast.definitions.VarDefinition;
import java.util.List;

public class RecordType implements Type {

    private List<RecordField> fields;

    public RecordType(List<VarDefinition> definitions) {
        this.fields = fields;
    }

    public List<RecordField> getFields() {
        return fields;
    }
}
