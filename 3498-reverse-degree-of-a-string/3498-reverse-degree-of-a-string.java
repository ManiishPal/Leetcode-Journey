class Solution {
    public int reverseDegree(String s) {
        int[] reverse = new int[26];

        for (int i = 0; i < 26; i++) {
            reverse[i] = 26 - i;
        }

        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            sum += reverse[index] * (i + 1);
        }

        return sum;
    }
}