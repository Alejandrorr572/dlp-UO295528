
import {Expression} from "./Expression";
import {AbstractLocatable} from "../AbstractLocatable";

export class ArrayAccess extends AbstractLocatable implements Expression {

    access: Expression;
    value: Expression;

    constructor( access: Expression, value: Expression, line: number, column: number){
        super(line, column);
        this.access = access;
        this.value = value;
    }
}