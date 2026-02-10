
import {Expression} from "./Expression";
import {AbstractLocatable} from "../AbstractLocatable";

export class UnaryNot extends AbstractLocatable implements Expression {

    left: Expression;
    operator: String;
    right: Expression;

    constructor( left: Expression, operator: String, right: Expression, line: number, column: number){
        super(line, column);
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
}