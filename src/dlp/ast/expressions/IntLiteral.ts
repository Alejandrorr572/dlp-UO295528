
import {Expression} from "./Expression";
import {AbstractLocatable} from "../AbstractLocatable";

class IntLiteral  extends AbstractLocatable implements Expression {

    value: Number;

    constructor(value: Number, line: number, column: number){
        super(line,column);
        this.value = value;
    }
}