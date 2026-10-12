
class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final long MOD = 1_000_000_007L;
        int[] count = new int[31];

        for (int num : nums) {
            for (int bit = 0; bit < 31; bit++) {
                if (((num >> bit) & 1) != 0) {
                    count[bit]++;
                }
            }
        }

        long answer = 0;

        for (int i = 0; i < k; i++) {
            long value = 0;

            for (int bit = 0; bit < 31; bit++) {
                if (count[bit] > 0) {
                    value |= (1L << bit);
                    count[bit]--;
                }
            }

            answer = (answer + value * value) % MOD;
        }

        return (int) answer;
    }
}
