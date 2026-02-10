
import {Expression} from "./Expression";
import {AbstractLocatable} from "../AbstractLocatable";

class CharLiteral extends AbstractLocatable implements Expression {

    value: String

    constructor(value: String, line: number, column: number) {
        super(line, column);
        if (value.length !== 1) {
            throw new Error("Char only has one character");
        }
        this.value = value;
    }
}