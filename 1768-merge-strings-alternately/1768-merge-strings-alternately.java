class Solution {
    public String mergeAlternately(String word1, String word2) {

        String result = "";

        int min = Math.min(word1.length(), word2.length());

        for (int i = 0; i < min; i++) {
            result += word1.charAt(i);
            result += word2.charAt(i);
        }

        for (int i = min; i < word1.length(); i++) {
            result += word1.charAt(i);
        }

        for (int i = min; i < word2.length(); i++) {
            result += word2.charAt(i);
        }

        return result;
    }
}