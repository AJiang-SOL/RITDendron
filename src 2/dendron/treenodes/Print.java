package dendron.treenodes;

import java.io.PrintWriter;
import java.util.Map;

/**
 * A node that represents the displaying of the value of an expression on the console
 * @author Alex Jiang acj3276@rit.edu
 */

public class Print implements ActionNode{

    public static final String PRINT_PREFIX = "=== ";
    private final ExpressionNode node;


    /**
     * Set up a Print node.
     * @param printee (ExpressionNode)
     */
    public Print(ExpressionNode printee){
        this.node = printee;
    }

    /**
     * Evaluate the expression and display the result on the console. Precede it with three equal signs so it
     * stands out a little.
     * @param symTab the table where variable values are stored
     */
    public void execute(Map<String,Integer> symTab){
        System.out.printf("%s",PRINT_PREFIX);
        System.out.printf("%s \n",this.node.evaluate(symTab));
    }

    /**
     * Show this statement on standard output as the word "Print" followed by the infix form of the expression.
     */
    public void infixDisplay(){
        System.out.printf("%s","Print ");
        node.infixDisplay();

    }

    /**
     * Emit onto a stream the text of the Soros assembly language instructions that, when executed, perform a print
     * action.
     * @param out the output stream for the compiled code &mdash;
     */
    public void compile(PrintWriter out){
        this.node.compile(out);
        out.println("PRINT");
    }

}
