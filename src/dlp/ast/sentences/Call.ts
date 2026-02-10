
import {Expression} from "../expressions/Expression";
import {AbstractLocatable} from "../AbstractLocatable";
import {Sentence} from "./Sentence";

export class Read extends AbstractLocatable implements Sentence,Expression {

    name: String;
    expressions: Expression[]

    constructor(name: String, expressions: Expression[], line: number, column: number){
        super(line, column);
        this.name = name;
        this.expressions = expressions;
    }
}