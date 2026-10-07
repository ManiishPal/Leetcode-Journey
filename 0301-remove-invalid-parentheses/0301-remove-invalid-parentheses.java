import java.util.*;

class Solution {

    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();

        // Count parentheses
        int n = 0;
        for (char c : s.toCharArray()) {
            if (c == '(' || c == ')') {
                n++;
            }
        }

        int minRemovals = Integer.MAX_VALUE;
        Set<String> set = new HashSet<>();

        // Try every subset of parentheses
        for (int mask = 0; mask < (1 << n); mask++) {

            int removed = n - Integer.bitCount(mask);

            // No need to check if already worse
            if (removed > minRemovals) {
                continue;
            }

            StringBuilder current = new StringBuilder();
            int index = 0;

            for (char c : s.toCharArray()) {
                if (c == '(' || c == ')') {
                    // Keep this parenthesis if bit is 1
                    if ((mask & (1 << index)) != 0) {
                        current.append(c);
                    }
                    index++;
                } else {
                    current.append(c);
                }
            }

            if (isValid(current.toString())) {

                if (removed < minRemovals) {
                    minRemovals = removed;
                    set.clear();
                }

                set.add(current.toString());
            }
        }

        result.addAll(set);
        return result;
    }

    private boolean isValid(String s) {
        int balance = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;

                if (balance < 0) {
                    return false;
                }
            }
        }

        return balance == 0;
    }
}