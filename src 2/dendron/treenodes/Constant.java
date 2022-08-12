package dendron.treenodes;

import java.io.PrintWriter;
import java.util.Map;

/**
 * An expression node representing a constant, i.e., literal value
 * @author Alex Jiang acj3276@rit.edu
 */

public class Constant implements ExpressionNode{

    private final int value;

    /**
     * Store the integer value in this new Constant.
     * @param value (int)
     */
    public Constant(int value){
        this.value = value;
    }

    /**
     * Print this Constant's value on standard output.
     */
    public void infixDisplay(){
        System.out.printf("%d",this.value);
    }

    /**
     * Evaluate the constant
     * @param symTab symbol table, if needed, to fetch variable values
     * @return (int) the result
     */
    public int evaluate(Map<String,Integer> symTab){
        return value;
    }

    /**
     * Emit onto a stream the text of the Soros assembly language instructions that, when executed, saves the constant
     * on the stack.
     * @param out the output stream for the compiled code &mdash;
     */
    public void compile(PrintWriter out){
        out.println("PUSH " + this.value );
    }
}
