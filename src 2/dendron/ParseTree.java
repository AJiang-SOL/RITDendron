package dendron;


import dendron.treenodes.*;

import java.io.PrintWriter;
import java.util.*;


/**
 * Operations that are done on a Dendron code parse tree.
 *
 * @author Alex Jiang acj3276@rit.edu
 */
public class ParseTree
{
    private Program tree;
    public static final String ASSIGN = ":=";
    public static final String PRINT = "#";


    /**
     * Parse the entire list of program tokens. The program is a
     * sequence of actions (statements), each of which modifies something
     * in the program's set of variables. The resulting parse tree is
     * stored internally.
     *
     * @param tokens the token list (Strings). This list may be destroyed
     *               by this constructor.
     *               stacks and queues
     */

    public ParseTree(List<String> tokens) {
        Program tree = new Program();
        while (!tokens.isEmpty()){
            tree.addAction(parseAction(tokens));
        }
        this.tree = tree;
    }

    /**
     * parses Action Nodes from the token
     * @param token (List<String>)
     * @return (ActionNode)
     */
    private ActionNode parseAction(List<String> token){
        if (token.isEmpty()){
            Errors.report(Errors.Type.PREMATURE_END,"Premature end of file when parsing the Dendron source file");
        }
        String Action = token.remove(0);
        if (Action.equals(PRINT)){
            return new Print(parseExpression(token));
        }
        else{
            if (token.isEmpty()){
                Errors.report(Errors.Type.PREMATURE_END,"Premature end of file when parsing the Dendron source file");
            }
            String arg1 = token.remove(0);
            if (arg1.matches("-?\\d+")){
                Errors.report(Errors.Type.UNINITIALIZED,"Unknown/unexpected token when parsing the Dendron source file");
            }
            return new Assignment(arg1,parseExpression(token));
        }
    }

    /**
     *  parses Expression Nodes from the token
     * @param token (List<String>)
     * @return (ExpressionNode)
     */
    private ExpressionNode parseExpression(List<String> token){
        if (token.isEmpty()){
            Errors.report(Errors.Type.PREMATURE_END,"Premature end of file when parsing the Dendron source file");
        }
        String Expression = token.remove(0);
        if (Expression.equals("%") || Expression.equals("_")){
            return new UnaryOperation(Expression,parseExpression(token));
        }
        else if (Expression.equals("+") || Expression.equals("-") || Expression.equals("/") || Expression.equals("*")){
            return new BinaryOperation(Expression,parseExpression(token),parseExpression(token));
        }
        else if (Expression.matches("-?\\d+")){
            return new Constant(Integer.parseInt(Expression));
        }
        else{
            return new Variable(Expression);
        }
    }

    /**
     * Print the program the tree represents in a more typical
     * infix style, and with one statement per line.
     *
     * @see ActionNode#infixDisplay()
     */

    public void displayProgram()
    {
        this.tree.infixDisplay();
    }

    /**
     * Run the program represented by the tree directly
     *
     * @see ActionNode#execute(Map)
     */
    public void interpret() {
        System.out.println("");
        System.out.println("");
        System.out.println("Interpreting the parse tree...");
        Map<String,Integer> map = new HashMap<>();
        this.tree.execute(map);
        System.out.println("Interpretation complete.");
        System.out.println("");
        Errors.dump(map);
    }

    /**
     * Build the list of machine instructions for
     * the program represented by the tree.
     *
     * @param out where to print the Soros instruction list
     */
    public void compileTo(PrintWriter out)
    {
        this.tree.compile(out);
    }
}
