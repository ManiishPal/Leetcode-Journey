class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;

        for (int k = n - 1; k >= 0; k--) {
            boolean valid = true;

            for (int i = 0; i < n; i++) {
                if (nums[i] != i && (nums[i] & k) != k) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                boolean sorted = true;

                for (int i = 0; i < n; i++) {
                    if (nums[i] != i) {
                        sorted = false;
                        break;
                    }
                }

                return sorted ? 0 : k;
            }
        }

        return 0;
    }
}