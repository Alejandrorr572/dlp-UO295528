
import {Sentence} from "./Sentence";
import {AbstractLocatable} from "../AbstractLocatable";

export abstract class Block extends AbstractLocatable implements Sentence{

    sentences: Sentence[];

    constructor(sentences: Sentence[], line: number, column: number) {
        super(line,column);
        this.sentences = sentences;
    }
}