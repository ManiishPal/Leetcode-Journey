
import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        long[] squares = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }

        Arrays.sort(squares);

        long score = 0;
        int negativeCount = nums.length / 2;

        for (int i = 0; i < squares.length; i++) {
            if (i < negativeCount) {
                score -= squares[i];
            } else {
                score += squares[i];
            }
        }

        return score;
    }
}
