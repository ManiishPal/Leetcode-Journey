class Solution {
    private int digitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    private boolean smaller(int a, int b) {
        int sa = digitSum(a);
        int sb = digitSum(b);

        if (sa != sb) {
            return sa < sb;
        }
        return a < b;
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (smaller(nums[j], nums[minIndex])) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = temp;
                swaps++;
            }
        }

        return swaps;
    }
}