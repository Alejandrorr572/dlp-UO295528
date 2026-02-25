package ast.types;

public class Char implements Type {

    private static Char instance;

    private Char() {}

    public static Char getInstance(){
        if(instance == null)
            instance = new Char();
        return instance;
    }
}
