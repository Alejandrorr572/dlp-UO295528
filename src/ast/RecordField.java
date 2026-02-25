package ast;

import ast.types.Type;

public class RecordField {

    private Type type;
    private String identifier;

    public RecordField(Type type, String identifier) {
        this.type = type;
        this.identifier = identifier;
    }
}
