package ast;

import ast.types.Type;
import visitors.Visitor;

public class RecordField extends AbstractLocatable implements ASTNode{

    private Type type;
    private String identifier;

    public RecordField(Type type, String identifier, int line, int column) {
        super(line,column);
        this.type = type;
        this.identifier = identifier;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }
}
