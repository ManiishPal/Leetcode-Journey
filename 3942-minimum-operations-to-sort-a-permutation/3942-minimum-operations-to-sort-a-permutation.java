class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 0; i < n; i++) {
            if (nums[(zero + i) % n] != i) {
                increasing = false;
            }

            if (nums[(zero - i + n) % n] != i) {
                decreasing = false;
            }
        }

        int ans = Integer.MAX_VALUE;

        if (increasing) {
            ans = Math.min(ans, Math.min(zero, n - zero + 2));
        }

        if (decreasing) {
            ans = Math.min(ans, Math.min(zero + 2, n - zero));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}