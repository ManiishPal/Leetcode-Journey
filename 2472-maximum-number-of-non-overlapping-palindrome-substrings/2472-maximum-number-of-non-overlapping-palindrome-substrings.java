class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();

        boolean[][] pal = new boolean[n][n];

        // Build palindrome table.
        for (int len = 1; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {

                int r = l + len - 1;

                if (len == 1) {
                    pal[l][r] = true;
                } else if (len == 2) {
                    pal[l][r] = s.charAt(l) == s.charAt(r);
                } else {
                    pal[l][r] =
                        s.charAt(l) == s.charAt(r)
                        && pal[l + 1][r - 1];
                }
            }
        }

        int answer = 0;
        int lastEnd = -1;

        // Scan intervals by increasing end position.
        for (int r = 0; r < n; r++) {

            for (int l = 0; l <= r - k + 1; l++) {

                if (l > lastEnd &&
                    pal[l][r]) {

                    answer++;
                    lastEnd = r;

                    // We selected the earliest-ending
                    // valid palindrome.
                    break;
                }
            }
        }

        return answer;
    }
}