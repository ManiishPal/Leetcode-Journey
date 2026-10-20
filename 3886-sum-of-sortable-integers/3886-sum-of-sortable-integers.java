
import java.util.*;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int answer = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;

            for (int start = 0; start < n && valid; start += k) {
                // Check whether this block has the same elements
                // as its corresponding segment in the sorted array.
                int[] block = Arrays.copyOfRange(nums, start, start + k);
                Arrays.sort(block);

                for (int j = 0; j < k; j++) {
                    if (block[j] != sorted[start + j]) {
                        valid = false;
                        break;
                    }
                }

                if (!valid) {
                    break;
                }

                // A circular sequence can be sorted by rotation
                // if it has at most one circular descent.
                int descents = 0;

                for (int j = start; j < start + k; j++) {
                    int next = start + (j - start + 1) % k;

                    if (nums[j] > nums[next]) {
                        descents++;
                    }
                }

                if (descents > 1) {
                    valid = false;
                }
            }

            if (valid) {
                answer += k;
            }
        }

        return answer;
    }
}
