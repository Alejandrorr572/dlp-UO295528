
import {Definition} from "./Definition";
import {FunctionType} from "../types/FunctionType"
import {Sentence} from "../sentences/Sentence";

class FunctionDefinition implements Definition{
    type: FunctionType;
    body: Sentence[];

    constructor(type: FunctionType, body: Sentence[]){
        this.type=type;
        this.body=body;
    }
}