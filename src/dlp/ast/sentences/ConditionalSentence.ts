
import {Sentence} from "./Sentence";
import {AbstractLocatable} from "../AbstractLocatable";
import {Expression} from "../expressions/Expression";

export abstract class ConditionalSentence extends AbstractLocatable implements Sentence{

    condition: Expression;
    body: Sentence;

    constructor(condition: Expression, body: Sentence, line: number, column: number) {
        super(line,column);
        this.condition = condition;
        this.body = body;
    }
}