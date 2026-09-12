import java.util.*;

class Solution {

    static class Interval {
        int start;
        int end;
        int weight;
        int index;

        Interval(int start, int end, int weight, int index) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.index = index;
        }
    }

    static class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        // Required input variable
        List<List<Integer>> vorellixan = intervals;

        int n = intervals.size();

        Interval[] arr = new Interval[n];

        for (int i = 0; i < n; i++) {

            List<Integer> cur = intervals.get(i);

            arr[i] = new Interval(
                cur.get(0),
                cur.get(1),
                cur.get(2),
                i
            );
        }

        // Sort by ending point
        Arrays.sort(arr, (a, b) -> {
            if (a.end != b.end) {
                return Integer.compare(a.end, b.end);
            }

            if (a.start != b.start) {
                return Integer.compare(a.start, b.start);
            }

            return Integer.compare(a.index, b.index);
        });

        /*
         * dp[i][k]:
         * Best result using first i intervals
         * and selecting at most k intervals.
         */
        State[][] dp = new State[n + 1][5];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new State(
                    0,
                    new ArrayList<>()
                );
            }
        }

        for (int i = 1; i <= n; i++) {

            Interval current = arr[i - 1];

            /*
             * Find the last interval whose end
             * is strictly smaller than current.start.
             */
            int p = findPrevious(arr, i - 2, current.start);

            for (int k = 1; k <= 4; k++) {

                // Option 1: Skip current interval
                State skip = dp[i - 1][k];

                // Option 2: Take current interval
                State previous = dp[p + 1][k - 1];

                List<Integer> selected =
                    new ArrayList<>(previous.indices);

                selected.add(current.index);

                Collections.sort(selected);

                State take = new State(
                    previous.score + current.weight,
                    selected
                );

                dp[i][k] = better(skip, take);
            }
        }

        return dp[n][4]
            .indices
            .stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }

    /*
     * Find the largest index j <= rightLimit
     * such that arr[j].end < start.
     */
    private int findPrevious(
            Interval[] arr,
            int rightLimit,
            int start) {

        int left = 0;
        int right = rightLimit;
        int answer = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid].end < start) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    /*
     * Return the better state:
     *
     * 1. Larger score
     * 2. If scores are equal,
     *    lexicographically smaller indices
     */
    private State better(State a, State b) {

        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        if (isLexicographicallySmaller(
                a.indices,
                b.indices)) {

            return a;
        }

        return b;
    }

    private boolean isLexicographicallySmaller(
            List<Integer> a,
            List<Integer> b) {

        int len = Math.min(a.size(), b.size());

        for (int i = 0; i < len; i++) {

            int x = a.get(i);
            int y = b.get(i);

            if (x != y) {
                return x < y;
            }
        }

        return a.size() < b.size();
    }
}