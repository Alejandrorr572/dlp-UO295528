
import {Expression} from "./Expression";
import {AbstractLocatable} from "../AbstractLocatable";

export class RealLiteral extends AbstractLocatable implements Expression {

    value: number

    constructor( value: number, line: number, column: number){
        super(line, column);
        this.value = value;
    }
}