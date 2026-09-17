
import java.util.*;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;

        int[] best = new int[n];
        Arrays.fill(best, Integer.MAX_VALUE);

        int left = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left++];
            }

            if (sum == target) {
                int len = right - left + 1;

                // Combine with a subarray ending before left
                if (left > 0 && best[left - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(
                        ans,
                        best[left - 1] + len
                    );
                }

                minLen = Math.min(minLen, len);
            }

            // Best single subarray ending at or before right
            best[right] = minLen;
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}