package dendron.treenodes;

import java.io.PrintWriter;
import java.util.Map;


/**
 * An ActionNode that represents the assignment of the value of an expression to a variable.
 * @author Alex Jiang acj3276@rit.edu
 */

public class Assignment implements ActionNode{
    private final String ident;
    private final ExpressionNode rhs;

    /**
     * Builds an Assignment node
     * @param ident (String)
     * @param rhs (ExpressionNode)
     */

    public Assignment(String ident,ExpressionNode rhs){
        this.rhs = rhs;
        this.ident = ident;
    }

    /**
     * evaluates the expression
     * @param symTab the table where variable values are stored
     */
    public void execute(Map<String,Integer> symTab){
        int var =this.rhs.evaluate(symTab);
        symTab.put(this.ident,var);
    }


    /**
     * Outputs the result
     */
    public void infixDisplay(){
        System.out.printf("%s := ",ident);
        rhs.infixDisplay();

    }

    /**
     * Compiles for the SOROS
     * @param out the output stream for the compiled code &mdash;
     */
    public void compile(PrintWriter out){
        this.rhs.compile(out);
        out.println("STORE " + this.ident );
   }
}
