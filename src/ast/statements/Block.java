package ast.statements;

import ast.AbstractLocatable;
import java.util.List;

public abstract class Block extends AbstractLocatable implements Statement {

    private List<Statement> sentences;

    public Block(List<Statement> sentences, int line, int column) {
        super(line, column);
        this.sentences = sentences;
    }

    public List<Statement> getSentences() {
        return sentences;
    }
}
