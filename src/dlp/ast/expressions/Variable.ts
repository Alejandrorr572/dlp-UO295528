
import {Expression} from "./Expression";
import {AbstractLocatable} from "../AbstractLocatable";

class Variable  extends AbstractLocatable implements Expression {

    name: String;

    constructor(name: String, line: number, column: number){
        super(line,column);
        this.name = name;
    }
}