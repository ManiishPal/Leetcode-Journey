
class Solution {
    public boolean checkOverlap(
        int radius, int xCenter, int yCenter,
        int x1, int y1, int x2, int y2
    ) {
        long r2 = (long) radius * radius;

        // Circle center inside rectangle
        if (xCenter >= x1 && xCenter <= x2 &&
            yCenter >= y1 && yCenter <= y2) {
            return true;
        }

        // Check the four rectangle corners
        int[][] corners = {
            {x1, y1}, {x1, y2},
            {x2, y1}, {x2, y2}
        };

        for (int[] p : corners) {
            long dx = p[0] - xCenter;
            long dy = p[1] - yCenter;

            if (dx * dx + dy * dy <= r2) {
                return true;
            }
        }

        // Check each rectangle edge
        return edgeOverlap(x1, y1, x2, y1,
                           xCenter, yCenter, r2)
            || edgeOverlap(x2, y1, x2, y2,
                           xCenter, yCenter, r2)
            || edgeOverlap(x2, y2, x1, y2,
                           xCenter, yCenter, r2)
            || edgeOverlap(x1, y2, x1, y1,
                           xCenter, yCenter, r2);
    }

    private boolean edgeOverlap(
        int ax, int ay, int bx, int by,
        int cx, int cy, long r2
    ) {
        double vx = bx - ax;
        double vy = by - ay;

        double len2 = vx * vx + vy * vy;

        double t = ((cx - ax) * vx +
                    (cy - ay) * vy) / len2;

        t = Math.max(0, Math.min(1, t));

        double px = ax + t * vx;
        double py = ay + t * vy;

        double dx = px - cx;
        double dy = py - cy;

        return dx * dx + dy * dy <= r2;
    }
}