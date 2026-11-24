
class Solution {
    public String gcdOfStrings(String str1, String str2) {

        int minLength = Math.min(str1.length(), str2.length());

        for (int i = minLength; i >= 1; i--) {

            String candidate = str1.substring(0, i);

            if (isDivisible(str1, candidate) &&
                isDivisible(str2, candidate)) {

                return candidate;
            }
        }

        return "";
    }

    private boolean isDivisible(String str, String pattern) {

        if (str.length() % pattern.length() != 0) {
            return false;
        }

        StringBuilder sb = new StringBuilder();

        while (sb.length() < str.length()) {
            sb.append(pattern);
        }

        return sb.toString().equals(str);
    }
}
