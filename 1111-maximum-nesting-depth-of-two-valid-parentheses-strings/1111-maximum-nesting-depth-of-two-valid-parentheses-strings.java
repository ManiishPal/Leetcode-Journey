class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] answer = new int[n];

        int depth = 0;

        for (int i = 0; i < n; i++) {

            if (seq.charAt(i) == '(') {
                // Current '(' belongs to the current depth level
                answer[i] = depth % 2;

                // Enter the next nesting level
                depth++;
            } else {
                // Leave the current nesting level first
                depth--;

                // ')' belongs to the level we just returned to
                answer[i] = depth % 2;
            }
        }

        return answer;
    }
}