import {Type} from "./Type";
import {Sentence} from "../sentences/Sentence";


export class FunctionType implements Type{
    parameters: Sentence[];
    returnType: Type;

    constructor(parameters: Sentence[], returnType: Type) {
        this.parameters = parameters;
        this.returnType = returnType;
    }
}