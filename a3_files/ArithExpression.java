
/*
* Name: Elizabeth Jossy Vellethara
* ID: V00883616
* Date:11/4/2018
* Filename: ArithExpression.java
* Details: CSC115 Assignment 3
*/

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArithExpression {

	public TokenList postfixTokens;
	public TokenList infixTokens;

	/**
	 * Sets up a legal standard Arithmetic expression.
	 * The only parentheses accepted are "(" and ")".
	 * @param word An arithmetic expression in standard infix order.
	 * 	An invalid expression is not expressly checked for, but will not be
	 * 	successfully evaluated, when the <b>evaluate</b> method is called.
	 * @throws InvalidExpressionException if the expression cannot be properly parsed,
	 *  	or converted to a postfix expression.
	 */
	public ArithExpression(String word) {
		if (Tools.isBalancedBy("()",word)) {
			tokenizeInfix(word);
			infixToPostfix();
		} else {
			throw new InvalidExpressionException("Parentheses unbalanced");
		}
	}

	/*
	 * A private helper method that tokenizes a string by separating out
	 * any arithmetic operators or parens from the rest of the string.
	 * It does no error checking.
	 * The method makes use of Java Pattern matching and Regular expressions to
	 * isolate the operators and parentheses.
	 * The operands are assumed to be the substrings delimited by the operators and parentheses.
	 * The result is captured in the infixToken list, where each token is
	 * an operator, a paren or a operand.
	 * @param express The string that is assumed to be an arithmetic expression.
	 */
	private void tokenizeInfix(String express) {
		infixTokens  = new TokenList(express.length());

		// regular expression that looks for any operators or parentheses.
		Pattern opParenPattern = Pattern.compile("[-+*/^()]");
		Matcher opMatcher = opParenPattern.matcher(express);

		String matchedBit, nonMatchedBit;
		int lastNonMatchIndex = 0;
		String lastMatch = "";

		// find all occurrences of a matched substring
		while (opMatcher.find()) {
			matchedBit = opMatcher.group();
			// get the substring between matches
			nonMatchedBit = express.substring(lastNonMatchIndex, opMatcher.start());
			nonMatchedBit = nonMatchedBit.trim(); //removes outside whitespace
			// The very first '-' or a '-' that follows another operator is considered a negative sign
			if (matchedBit.charAt(0) == '-') {
				if (opMatcher.start() == 0 ||
					!lastMatch.equals(")") && nonMatchedBit.equals("")) {
					continue;  // ignore this match
				}
			}
			// nonMatchedBit can be empty when an operator follows a ')'
			if (nonMatchedBit.length() != 0) {
				infixTokens.append(nonMatchedBit);
			}
			lastNonMatchIndex = opMatcher.end();
			infixTokens.append(matchedBit);
			lastMatch = matchedBit;
		}
		// parse the final substring after the last operator or paren:
		if (lastNonMatchIndex < express.length()) {
			nonMatchedBit = express.substring(lastNonMatchIndex,express.length());
			nonMatchedBit = nonMatchedBit.trim();
			infixTokens.append(nonMatchedBit);
		}
	}
	
	private int precedence(String op) {
		switch(op){
			case"+":
			case"-":
				return 1;
			case"/":
			case"*":
			  return 2;
			case"^":
			  return 3;
		}
		return -1;
	}
	
	/**
	 * Determines whether a single character string is an operator.
	 * The allowable operators are {+,-,*,/,^}.
	 * @param op The string in question.
	 * @return True if it is recognized as a an operator.
	 */
	public static boolean isOperator(String op) {
		switch(op) {
			case "+":
			case "-":
			case "/":
			case "*":
			case "^":
				return true;
			default:
				return false;
		}
	}

	/*
	 * NOTE TO STUDENT for infixToPostfix below...:
	 * You do not need to check that the infixTokens data field is a legitimate infix
	 * expression at this time.
	 * If, during the process, something unexpected happens, then throw an Exception, but it
	 * is okay for the postfixTokens to contain an invalid postfix expression.
	 * It is only when processing the public method 'evaluate', that any errors must be
	 * acknowledged.
	 */

	 /**
	 * A private method that initializes the postfixTokens data field.
	 * It takes the information from the infixTokens data field.
		* If, during the process, it is determined that the expression is invalid,
	 * an InvalidExpressionException is thrown.
 	 * Note that since the only method that calls this method is the constructor,
	 * the Exception is propogated through the constructor.
	 */
	public void infixToPostfix() {
		try{
		StringStack stack = new StringStack();
		postfixTokens = new TokenList();
		for (int i=0; i<infixTokens.count; i++) {
			
		    if(infixTokens.get(i).equals("("))
					stack.push(infixTokens.get(i));
				
			else if(infixTokens.get(i).equals(")")){
				while(!stack.peek().equals( "("))
					postfixTokens.append(stack.pop());
				stack.pop();
			}
			
			else if(isOperator(infixTokens.get(i)) == false)
				postfixTokens.append(infixTokens.get(i));
				
			else if(isOperator(infixTokens.get(i)) == true){
				if(stack.isEmpty() == true )
					stack.push(infixTokens.get(i));
				
				else{
					while(stack.isEmpty()==false && precedence(infixTokens.get(i))<=precedence(stack.peek())&& !stack.peek().equals("("))
					postfixTokens.append(stack.pop());
					stack.push(infixTokens.get(i));
				}
			}
			
		}
		
		while(!stack.isEmpty()) {
			postfixTokens.append(stack.pop());
		}
		}
		catch(InvalidExpressionException e){
			System.out.println("Invaild infix expression");
		}
	}

	public String getInfixExpression() {
		return infixTokens.toString();
	}

	public String getPostfixExpression() {
		return postfixTokens.toString();
	}

	public double evaluate() {
		StringStack stack = new StringStack();
		try{
		for (int i=0; i<postfixTokens.count; i++) {
	
			if(isOperator(postfixTokens.get(i)) == false)
				stack.push(postfixTokens.get(i));
			
			else if(isOperator(postfixTokens.get(i)) == true){
				String integerOperand1 = stack.pop();
				String integerOperand2 = stack.pop();
				
				if(postfixTokens.get(i).equals("+"))
					stack.push(""+(Integer.parseInt(integerOperand2) + Integer.parseInt(integerOperand1)));
				
				else if(postfixTokens.get(i).equals("-"))
					stack.push(""+(Integer.parseInt(integerOperand2) - Integer.parseInt(integerOperand1)));
				
				else if(postfixTokens.get(i).equals("*"))
					stack.push(""+(Integer.parseInt(integerOperand2) * Integer.parseInt(integerOperand1)));
				
				else if(postfixTokens.get(i).equals("/"))
					stack.push(""+(Integer.parseInt(integerOperand2) / Integer.parseInt(integerOperand1)));
				
				else if(postfixTokens.get(i).equals("^"))
					stack.push(""+ (int)Math.pow(Integer.parseInt(integerOperand2),Integer.parseInt(integerOperand1)));
				
			} 	
		}
		}
		catch(InvalidExpressionException e){
			System.out.println("Invaild postfix expression");
		}
		return Double.parseDouble(stack.peek());
	}

	public static void main(String[] args) {
		String express = "(2 + 5) * 2^2";
		ArithExpression convert = new ArithExpression(express);
		System.out.println(convert.getInfixExpression());
		System.out.println(convert.getPostfixExpression());
		System.out.println(convert.evaluate());
	}

}