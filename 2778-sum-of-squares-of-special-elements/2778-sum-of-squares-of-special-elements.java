
class Solution {
    public int sumOfSquares(int[] nums) {
        return calculate(nums, 1);
    }

    private int calculate(int[] nums, int i) {
        int n = nums.length;

        if (i > n) {
            return 0;
        }

        int sum = 0;

        if (n % i == 0) {
            sum = nums[i - 1] * nums[i - 1];
        }

        return sum + calculate(nums, i + 1);
    }
}
