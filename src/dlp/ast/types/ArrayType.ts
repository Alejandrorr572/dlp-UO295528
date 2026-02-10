
import {Type} from "./Type";

class ArrayType implements Type{
    size: number;
    elementsType: Type;

    constructor(size: number, elementsType: Type) {
        this.size= size;
        this.elementsType = elementsType;
    }
}