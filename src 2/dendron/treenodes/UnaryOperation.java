package dendron.treenodes;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;

/**
 * A calculation represented by a unary operator and its operand.
 * @author Alex Jiang acj3276@rit.edu
 */

public class UnaryOperation implements ExpressionNode {

    public final static String NEG = "_";
    public final static String SQRT = "%";
    public static Collection<String> OPERATORS;
    private final String operator;
    private final ExpressionNode expr;

    /**
     * Create a new UnaryOperation node.
     * @param operator (String)
     * @param expr (ExpressionNode)
     */
    public UnaryOperation(String operator,ExpressionNode expr){
        this.expr = expr;
        this.operator = operator;

    }

    /**
     * Print, on standard output, the infixDisplay of the child nodes preceded by the operator and
     * without an intervening blank.
     */
    public void infixDisplay(){
        System.out.printf("%s",operator);
        expr.infixDisplay();

    }

    /**
     * Compute the result of evaluating the expression and applying the operator to it.
     * @param symTab symbol table, if needed, to fetch variable values
     * @return (int) the result
     */
    public int evaluate(Map<String,Integer> symTab){
        if (this.operator.equals(NEG)){
            return -(this.expr.evaluate(symTab));
        }
        else{
            return (int) Math.sqrt(this.expr.evaluate(symTab));
        }
    }

    /**
     * Emit onto a stream the text of the Soros assembly language instructions that, when executed, computes the result
     * of this operation.
     * @param out the output stream for the compiled code &mdash;
     */
    public void compile(PrintWriter out) {
        if(this.operator.equals(NEG)){
            this.expr.compile(out);
            out .println("NEG");

        }
        else if(this.operator.equals(SQRT)){
            this.expr.compile(out);
            out.println("SQRT");
        }
    }

}
