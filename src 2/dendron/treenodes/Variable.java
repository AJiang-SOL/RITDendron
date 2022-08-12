package dendron.treenodes;

import dendron.Errors;

import java.io.PrintWriter;
import java.util.Map;

/**
 * The ExpressionNode for a simple variable
 * @author Alex Jiang acj3276@rit.edu
 */

public class Variable implements ExpressionNode{
    private final String name;

    /**
     * Set the name of this new Variable
     * @param name (String)
     */
    public Variable(String name){
        this.name = name;
    }

    /**
     * Print on standard output the Variable's name.
     */
    public void infixDisplay(){
        System.out.printf("%s",this.name);

    }

    /**
     * Evaluate a variable by fetching its value
     * @param symTab symbol table, if needed, to fetch variable values
     * @return (int) the result
     */
    public int evaluate(Map<String,Integer> symTab){
        if (!symTab.containsKey(this.name)){
            Errors.report(Errors.Type.ILLEGAL_VALUE,"Variable evaluation of its value when it has never been set");
        }
        return symTab.get(this.name);
    }

    /**
     * Emit onto a stream the text of the Soros assembly language instructions that, when executed, pushes the value of
     * the variable on the stack.
     * @param out the output stream for the compiled code &mdash;
     */

    public void compile(PrintWriter out){
        out.println("LOAD " + this.name);

    }

}
