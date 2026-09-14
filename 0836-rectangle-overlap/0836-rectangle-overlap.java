class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {

        boolean separated =
            rec1[2] <= rec2[0] ||  // rec1 is left
            rec2[2] <= rec1[0] ||  // rec2 is left
            rec1[1] >= rec2[3] ||  // rec1 is above
            rec2[1] >= rec1[3];    // rec2 is above

        return !separated;
    }
}