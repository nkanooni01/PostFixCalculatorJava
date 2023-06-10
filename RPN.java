/**
 * Nathaniel Kanooni
 * 6/5/2020
 * CSC 143
 * 
 * RPN.java
 * 
 * This project demonstrates the usage of Stacks and Queues through printing
 * out the process of evaluating a post-fix expression (operand then operator),
 * and then returning the result as a double.
 *
 */

import java.util.*;
public class RPN {
	
	/**
	 * Calls the evaluateRPN(String input) method
	 */
	public static void main(String[] args) {
		// 6 test cases
		testRPN();
	}
	
	 /**
     * Tests the RPN evaluator
     */
    public static void testRPN() {
        String[] tests = {
                "2 2 +", 
                "2 3 -", 
                "4 5 *", 
                "6 5 /", 
                "1 2 3 4 5 6 7 8 9 + + + + + + + +",
        "5 2 2 * - 1 2 + /"};
        double[] results = {4.0,-1,20,1.2,45, 0.3333333333333333};

        for (int i = 0; i < tests.length; i++) {
            System.out.println(String.format("Evaluating => %s", tests[i]));
            double result = evaluateRPN(tests[i]);
            System.out.println(String.format("Result => %s", result));
            if (result != results[i]) {
                System.out.println(String.format("Error on test %s expected %s, received %s", 
                        tests[i], results[i], evaluateRPN(tests[i])));
                return;
            }
        }
        System.out.println("Congratulations - you passed the tests");

    }

	
	/**
	 * <p>
	 * Takes in String input and evaluates it as a postfix expression (meaning
	 * the operators come after the operands), with the evaluation being printed
	 * on seperate lines each time, until the final result of the expression is
	 * found and then printed as a double.
	 * </p>
	 * <p>
	 * e.g evaluateRPN(20 15 *) would evaluate to the following:
	 * </p>
	 * <pre>
	 * 	Evaluating => 20 15 *
	 * 	[20, 15, *]
	 * 	[] [300.0] 
	 * 	Result => 300.0 
	 * </pre>
	 * @param input - the postfix expression that is being solved
	 * @return double - the result of the postfix expression
	 */
	public static double evaluateRPN(String input) {
		// make an empty queue of strings
		Queue<String> token = new LinkedList<String>();
		
		// make a scanner taking in input
		Scanner sc = new Scanner(input);
		// while scanner has next value
		while(sc.hasNext()) {
			// add scanner.next to queue
			token.add(sc.next());
		}
		
		sc.close();
		
		// print out queue
		System.out.println(token);
		
		// create a temp stack of strings
		Stack<String> calculate = new Stack<String>();
		
		// while queue has items
		while(!token.isEmpty()) {
			// take in queue.remove as a String
			String removed = token.remove();
			
			// if operator such as '*' - 
			if(isOperator(removed)) {
            	// pop two operands, evaluate, push result: Queue [+] Stack [2 3] => [][5]
				double op2 = Double.parseDouble(calculate.pop());
				double op1 = Double.parseDouble(calculate.pop());
				String evaluateOp = evaluateBinaryOperator(op1, removed, op2);
				calculate.push(evaluateOp);
				
				// print queue and stack
				System.out.print(token);
				System.out.println(calculate);
			}else {
				// else operands such as "5" just need to be pushed
				calculate.push(removed);
			}
		}
		
		// return the last item in stack
		return Double.parseDouble(calculate.pop());
	}
	
	/**
	 * This method takes in a String and sees if it is +, -, *, or /. If it is,
	 * it returns true. Else, it returns false.
	 * @param input - String getting evaluated 
	 * @return true or false
	 */
	private static boolean isOperator(String input) {
		input = input.substring(0,1);
		// if +, -, *, / return true
		if(input.equals("+") || input.equals("-") || input.equals("*") ||
				input.equals("/")) {
			return true;
		}else {
			// else return false
			return false;
		}
	}
	
	/**
	 * This method takes in 2 operands (op1 and op2) and a operator and tries to
	 * evaluate the 2 operands based on the operator. It then returns the result
	 * as a String.
	 * 
	 * @param op1 - double that is the first operand
	 * @param operator - String that is the operator the operands are working on
	 * @param op2 - double that is the second operand
	 * @return String - representing the result of the 2 operands.
	 */
	private static String evaluateBinaryOperator(double op1, String operator,
			double op2) {
		// if "+" add op1 and op2 and return it as a String
		if(operator.equals("+")) {
			return Double.toString(op1 + op2);
		}else if(operator.equals("-")) {
			// else if "-" subtract op1 and op2 and return it as a String
			return Double.toString(op1 - op2);
		}else if(operator.equals("*")) {	
			// else if "*" multiply op1 and op2 and return it as a String
			return Double.toString(op1 * op2);
		}else {
			// else divide op1 and op2 and return it as a String
			return Double.toString(op1 / op2);
		}	
	}
		

}
