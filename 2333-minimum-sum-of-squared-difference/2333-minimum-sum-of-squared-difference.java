
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2,
                                 int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];

        long k = (long) k1 + k2;
        long totalDiff = 0;
        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            totalDiff += diff[i];
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        if (k >= totalDiff) {
            return 0;
        }

        // Find the smallest feasible maximum difference.
        int left = 0;
        int right = maxDiff;

        while (left < right) {
            int mid = left + (right - left) / 2;
            long needed = 0;

            for (int d : diff) {
                if (d > mid) {
                    needed += d - mid;
                }
            }

            if (needed <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        int threshold = left;
        long used = 0;
        long result = 0;
        long countAtThreshold = 0;

        for (int d : diff) {
            int reduced = Math.min(d, threshold);

            used += d - reduced;
            result += (long) reduced * reduced;

            if (d >= threshold) {
                countAtThreshold++;
            }
        }

        // Remaining operations reduce threshold to threshold - 1.
        long remaining = k - used;
        result -= remaining * (2L * threshold - 1);

        return result;
    }
}
