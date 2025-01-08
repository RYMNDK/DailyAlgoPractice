package Algorithm.Concept.NotLink;

import java.util.Stack;

public class StackNQueue {

    // balance If statements (LC #20 advanced)
    // [if|elseif|else|endif] * n -> return max depth, -1 on invalid
    // only if, elseif, else and endif will be in statements
    public static int maxDepth(String[] statements) {
        int result = 0;
        String lastStatement;
        Stack<String> stack = new Stack<String>();

        for (String statement : statements) {
            switch (statement) {
                case "if":
                    stack.push(statement);
                    result = Math.max(result, stack.size());
                    break;
                case "endif":
                    if (stack.isEmpty()) {
                        // empty stack exception
                        return -1;
                    }
                    stack.pop();
                    break;

                case "elseif":
                    lastStatement = stack.pop();
                    if (lastStatement.equals("else") || stack.pop().equals("endif")) {
                        return -1;
                    }
                    stack.push(statement);
                break;
                case "else":
                    lastStatement = stack.pop();
                    if (lastStatement.equals("else") || stack.pop().equals("endif")) {
                        return -1;
                    }
                break;
            }
        }
        if (!stack.isEmpty()) {
            return -1;
        }
        return result;
    }

    public static void main(String[] args) {

    }

}
