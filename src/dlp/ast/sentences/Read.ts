
import {Expression} from "../expressions/Expression";
import {AbstractLocatable} from "../AbstractLocatable";
import {Sentence} from "./Sentence";

export class Read extends AbstractLocatable implements Sentence {

    expressions: Expression[]

    constructor( expressions: Expression[], line: number, column: number){
        super(line, column);
        this.expressions = expressions;
    }
}