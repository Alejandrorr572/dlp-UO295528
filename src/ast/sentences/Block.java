package ast.sentences;

import ast.AbstractLocatable;
import java.util.List;

public abstract class Block extends AbstractLocatable implements Sentence {

    private List<Sentence> sentences;

    public Block(List<Sentence> sentences, int line, int column) {
        super(line, column);
        this.sentences = sentences;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }
}
