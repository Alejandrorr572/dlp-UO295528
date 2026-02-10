
import {Expression} from "./Expression";
import {AbstractLocatable} from "../AbstractLocatable";

export class UnaryMinus extends AbstractLocatable implements Expression {

    right: Expression

    constructor( right: Expression, line: number, column: number){
        super(line, column);
        this.right = right;
    }
}