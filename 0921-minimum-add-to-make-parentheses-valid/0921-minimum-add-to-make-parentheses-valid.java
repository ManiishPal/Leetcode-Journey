import java.util.Stack;

class Solution {
    public int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();
        int additions = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                stack.push(ch);
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    additions++;
                }
            }
        }

        return additions + stack.size();
    }
}