package codegen;

import ast.types.*;

import java.io.IOException;
import java.io.PrintWriter;

public class CodeGenerator {

    private int labelCounter;
    private String input;
    private PrintWriter out;

    public CodeGenerator(String output, String input) {
        try {
            this.input = input;
            this.out = new PrintWriter(output);
        } catch (IOException e) {
            System.err.println("Error abriendo el archivo de salida: " + e.getMessage());
        }
    }

    private void write(String instruction) {
        out.println(instruction);
        out.flush(); // Oscar de hacer flush siempre para que no pasen cosas raras
    }

    private String suffix(Type type) {
        if (type instanceof Int) return "i";
        if (type instanceof Real) return "f";
        if (type instanceof Char) return "b";
        return "";
    }

    public void source(String input) {
        write("#source \"" + input + "\""); //Lo que dijo Oscar de poner el source (Se hace en el accept de program)
    }

    public void line(int line) {
        write("#line " + line);
    }

    public void comment(String comment) {
        write("\t' " + comment);
    }

    public void invocationToMain() {
        write("");
        comment("Invocation to main");
        write("call main");
        write("halt");
        write("");
    }

    public void pusha(int offset) {
        write("\tpusha " + offset);
    }

    public void pushBp() {
        write("\tpush bp");
    }

    public void enter(int localBytes) {
        write("\tenter " + localBytes);
    }

    public void ret(int bytesReturn, int localBytes, int paramBytes) {
        write("\tret " + bytesReturn + ", " + localBytes + ", " + paramBytes);
    }

    public void load(Type type) {
        write("\tload" + suffix(type));
    }

    public void store(Type type) {
        write("\tstore" + suffix(type));
    }

    public void push(int constant) {
        write("\tpushi " + constant);
    }

    public void push(double constant) {
        write("\tpushf " + constant);
    }

    public void push(char constant) {
        write("\tpushb " + (int) constant);
    }

    public void arithmetic(String operator, Type type) {
        String instr = switch (operator) {
            case "+" -> "add";
            case "-" -> "sub";
            case "*" -> "mul";
            case "/" -> "div";
            case "%" -> "mod";
            default -> throw new IllegalArgumentException("Operador aritmético desconocido: " + operator);
        };
        write("\t" + instr + suffix(type));
    }

    public void comparison(String operator, Type type) {
        String instr = switch (operator) {
            case ">"  -> "gt";
            case "<"  -> "lt";
            case ">=" -> "ge";
            case "<=" -> "le";
            case "==" -> "eq";
            case "!=" -> "ne";
            default -> throw new IllegalArgumentException("Operador comparativo desconocido: " + operator);
        };
        write("\t" + instr + suffix(type));
    }

    public void logic(String operator) {
        String instr = switch (operator) {
            case "&&" -> "and";
            case "||" -> "or";
            default -> throw new IllegalArgumentException("Operador lógico desconocido: " + operator);
        };
        write("\t" + instr);
    }

    public void minus() {
        //aser
    }

    public void not() {
        write("\tnot");
    }

    public void in(Type type) {
        write("\tin" + suffix(type));
    }

    public void out(Type type) {
        write("\tout" + suffix(type));
    }

    public void convertTo(Type from, Type to) {
        if (from instanceof Int && to instanceof Real) {
            write("\ti2f");
        } else if (from instanceof Real && to instanceof Int) {
            write("\tf2i");
        } else if (from instanceof Char && to instanceof Int) {
            write("\tb2i");
        } else if (from instanceof Int && to instanceof Char) {
            write("\ti2b");
        }
    }

    public void call(String functionName) {
        write("\tcall " + functionName);
    }

    public void pop(Type type) {
        if (type instanceof VoidType) return;
        write("\tpop" + suffix(type));
    }

    public String getInput() {
        return input;
    }

    public void close() {
        out.close();
    }

    public void jz(String label) {
        write("\t" + "jz " + label);
    }

    public void jmp(String label) {
        write("\t" + "jmp " + label);
    }

    public String getLabel(String label) {
        labelCounter++;
        return label+labelCounter ;
    }
    public void writeLabel(String label) {
        write(" " + label + ":");
    }
}
