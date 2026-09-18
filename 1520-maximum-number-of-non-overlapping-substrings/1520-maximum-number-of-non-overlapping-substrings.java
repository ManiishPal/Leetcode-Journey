
import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);

        // Step 1: Find first and last occurrence.
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Step 2: Generate minimal valid intervals.
        for (int c = 0; c < 26; c++) {
            if (first[c] == n) continue;

            int left = first[c];
            int right = last[c];
            boolean valid = true;

            for (int j = left; j <= right; j++) {
                int x = s.charAt(j) - 'a';

                // This character occurs before left.
                if (first[x] < left) {
                    valid = false;
                    break;
                }

                // Expand to include all occurrences.
                right = Math.max(right, last[x]);
            }

            if (valid) {
                intervals.add(new int[]{left, right});
            }
        }

        // Step 3: Sort by ending position.
        intervals.sort((a, b) -> a[1] - b[1]);

        // Step 4: Greedily select non-overlapping intervals.
        List<String> ans = new ArrayList<>();
        int prevEnd = -1;

        for (int[] interval : intervals) {
            int left = interval[0];
            int right = interval[1];

            if (left > prevEnd) {
                ans.add(s.substring(left, right + 1));
                prevEnd = right;
            }
        }

        return ans;
    }
}