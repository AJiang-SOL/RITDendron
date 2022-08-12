package dendron.treenodes;

import dendron.Errors;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;

/**
 * A calculation represented by a binary operator and its two operands.
 * @author Alex Jiang acj3276@rit.edu
 */

public class BinaryOperation implements ExpressionNode {
    public static final String ADD = "+";
    public static final String SUB = "-";
    public static final String DIV = "/";
    public static final String MUL = "*";
    public static Collection<String> OPERATORS;
    private final String operator;
    private final ExpressionNode leftChild;
    private final ExpressionNode rightChild;

    /**
     * Builds an the node
     * @param operator (String)
     * @param leftChild (ExpressionNode)
     * @param rightChild (ExpressionNode)
     */
    public BinaryOperation(String operator,ExpressionNode leftChild,ExpressionNode rightChild){
        this.leftChild = leftChild;
        this.operator = operator;
        this.rightChild = rightChild;

    }

    /**
     * Compute the result of evaluating both operands and applying the operator to them.
     * @param symTab symbol table, if needed, to fetch variable values
     * @return (int) the resulting calcuations
     */
    public int evaluate(Map<String,Integer> symTab){
        if (this.operator.equals(ADD)){
            return leftChild.evaluate(symTab)+rightChild.evaluate(symTab);
        }
        else if (this.operator.equals(SUB)){
            return leftChild.evaluate(symTab)-rightChild.evaluate(symTab);
        }
        else if(this.operator.equals(DIV)){
            if(rightChild.evaluate(symTab) == 0){
                Errors.report(Errors.Type.DIVIDE_BY_ZERO,"Divide by zero");
            }
            return leftChild.evaluate(symTab) / rightChild.evaluate(symTab);
        }
        else{
            return leftChild.evaluate(symTab) * rightChild.evaluate(symTab);
        }
    }

    /**
     * Print, on standard output, the infixDisplay of the two child nodes separated by the operator and surrounded
     * by parentheses.
     * Blanks are inserted throughout.
     */
    public void infixDisplay(){
        System.out.printf("%s","(");
        leftChild.infixDisplay();
        System.out.printf("%s",operator);
        rightChild.infixDisplay();
        System.out.printf("%s",")");


    }

    /**
     * Emit onto a stream the text of the Soros assembly language instructions that, when executed, computes the result
     * of this operation.
     * @param out the output stream for the compiled code &mdash;
     */
    public void compile(PrintWriter out){

        if (this.operator.equals(ADD)){
            this.leftChild.compile(out);
            this.rightChild.compile(out);
            out.println("ADD");
        }
        else if (this.operator.equals(SUB)){
            this.leftChild.compile(out);
            this.rightChild.compile(out);
            out.println("SUB");

        }
        else if(this.operator.equals(DIV)){
            this.leftChild.compile(out);
            this.rightChild.compile(out);
            out.println("DIV");

        }
        else if(this.operator.equals(MUL)){
            this.leftChild.compile(out);
            this.rightChild.compile(out);
            out.println("MUL");
        }
    }

}
