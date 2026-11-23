
import java.util.*;

class Solution {
    public List<String> letterCombinations(String digits) {

        if (digits.length() == 0) {
            return new ArrayList<>();
        }

        String[] map = {
            "", "", "abc", "def", "ghi",
            "jkl", "mno", "pqrs", "tuv", "wxyz"
        };

        List<String> result = new ArrayList<>();

        for (char c : map[digits.charAt(0) - '0'].toCharArray()) {
            result.add(String.valueOf(c));
        }

        for (int i = 1; i < digits.length(); i++) {

            List<String> temp = new ArrayList<>();

            String letters = map[digits.charAt(i) - '0'];

            for (String combination : result) {

                for (char ch : letters.toCharArray()) {

                    temp.add(combination + ch);
                }
            }

            result = temp;
        }

        return result;
    }
}
