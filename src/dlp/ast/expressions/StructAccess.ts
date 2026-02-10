
import {Expression} from "./Expression";
import {AbstractLocatable} from "../AbstractLocatable";

export class ArrayAccess extends AbstractLocatable implements Expression {

    name: String;
    value: Expression;

    constructor( name: String, value: Expression, line: number, column: number){
        super(line, column);
        this.name = name;
        this.value = value;
    }
}