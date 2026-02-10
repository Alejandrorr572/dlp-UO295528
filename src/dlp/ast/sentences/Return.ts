
import {Expression} from "../expressions/Expression";
import {AbstractLocatable} from "../AbstractLocatable";
import {Sentence} from "./Sentence";

export class Assignment extends AbstractLocatable implements Sentence {

    returnValue: Expression;

    constructor( returnValue: Expression, line: number, column: number){
        super(line, column);
        this.returnValue = returnValue;
    }
}