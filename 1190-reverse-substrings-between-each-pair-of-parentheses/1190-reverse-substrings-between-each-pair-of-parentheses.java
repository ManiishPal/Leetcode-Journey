class Solution {
    public String reverseParentheses(String s) {

        while (s.contains("(")) {

            int open = -1;

            // Find an opening parenthesis
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    open = i;
                }

                // The first ')' after the latest '('
                // gives us an innermost pair.
                if (s.charAt(i) == ')' && open != -1) {

                    String middle = s.substring(open + 1, i);

                    String reversed = new StringBuilder(middle)
                            .reverse()
                            .toString();

                    s = s.substring(0, open)
                            + reversed
                            + s.substring(i + 1);

                    break;
                }
            }
        }

        return s;
    }
}