
import {Expression} from "../expressions/Expression";
import {AbstractLocatable} from "../AbstractLocatable";
import {Sentence} from "./Sentence";

export class Assignment extends AbstractLocatable implements Sentence {

    left: Expression;
    right: Expression;

    constructor( left: Expression, right: Expression, line: number, column: number){
        super(line, column);
        this.left = left;
        this.right = right;
    }
}