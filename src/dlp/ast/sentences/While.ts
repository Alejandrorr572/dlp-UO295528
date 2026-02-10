
import {Sentence} from "./Sentence";
import {Expression} from "../expressions/Expression";
import {ConditionalSentence} from "./ConditionalSentence";

export abstract class IfElse extends ConditionalSentence implements Sentence{

    constructor(condition: Expression, body: Sentence, line: number, column: number) {
        super(condition, body, line,column);
    }
}