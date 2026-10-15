
import java.util.*;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                Set<Integer> set = new HashSet<>();

                for (int k = i; k <= j; k++) {
                    set.add(nums.get(k));
                }

                int distinct = set.size();
                sum += distinct * distinct;
            }
        }

        return sum;
    }
}
