package ast;

import ast.definitions.Definition;
import visitors.Visitor;

import java.util.List;

public class Program implements  ASTNode {

    private List<Definition> definitions;

    public Program(List<Definition> definitions) {
        this.definitions = definitions;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return (RT) v.visit(this, param);
    }
}
