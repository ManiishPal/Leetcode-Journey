class Solution {
    public boolean checkValidString(String s) {
        Boolean[][] memo = new Boolean[s.length()][s.length() + 1];
        return backtrack(s, 0, 0, memo);
    }

    private boolean backtrack(String s, int index, int balance, Boolean[][] memo) {
        // Too many closing brackets
        if (balance < 0) {
            return false;
        }

        // Reached end
        if (index == s.length()) {
            return balance == 0;
        }

        // Already calculated this state
        if (memo[index][balance] != null) {
            return memo[index][balance];
        }

        char ch = s.charAt(index);
        boolean result;

        if (ch == '(') {
            result = backtrack(s, index + 1, balance + 1, memo);
        }
        else if (ch == ')') {
            result = backtrack(s, index + 1, balance - 1, memo);
        }
        else {
            // '*' -> '(', ')' or empty
            result =
                backtrack(s, index + 1, balance + 1, memo) ||
                backtrack(s, index + 1, balance - 1, memo) ||
                backtrack(s, index + 1, balance, memo);
        }

        memo[index][balance] = result;
        return result;
    }
}