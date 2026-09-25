import java.util.*;

class Solution {

    private String s;
    private int index;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        index = 0;

        Set<String> result = parseExpression();

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    // Handles union: term , term , term ...
    private Set<String> parseExpression() {
        Set<String> result = parseTerm();

        while (index < s.length() && s.charAt(index) == ',') {
            index++; // skip ','

            Set<String> next = parseTerm();
            result.addAll(next);
        }

        return result;
    }

    // Handles concatenation: factor factor factor ...
    private Set<String> parseTerm() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (index < s.length()
                && s.charAt(index) != '}'
                && s.charAt(index) != ',') {

            Set<String> next = parseFactor();

            Set<String> combined = new HashSet<>();

            for (String a : result) {
                for (String b : next) {
                    combined.add(a + b);
                }
            }

            result = combined;
        }

        return result;
    }

    // Handles a letter or {...}
    private Set<String> parseFactor() {

        // Single lowercase letter
        if (Character.isLetter(s.charAt(index))) {
            Set<String> result = new HashSet<>();
            result.add(String.valueOf(s.charAt(index)));
            index++;
            return result;
        }

        // {...}
        index++; // skip '{'

        Set<String> result = parseExpression();

        index++; // skip '}'

        return result;
    }
}