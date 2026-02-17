package ast;

import ast.definitions.Definition;
import java.util.List;

public class Program {

    private List<Definition> definitions;

    public Program(List<Definition> definitions) {
        this.definitions = definitions;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }
}
