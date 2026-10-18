
import java.util.*;

class Solution {
    static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }
    }

    private Node insert(Node root, int val) {
        if (root == null) return new Node(val);

        if (val < root.val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }

        return root;
    }

    private Node buildBST(int[] arr) {
        Node root = null;
        for (int x : arr) {
            root = insert(root, x);
        }
        return root;
    }

    private String serialize(Node root) {
        if (root == null) return "#";

        return root.val + "("
                + serialize(root.left) + ")("
                + serialize(root.right) + ")";
    }

    private long count;
    private String target;

    private void generate(int[] nums, boolean[] used,
                          int[] current, int index) {
        if (index == nums.length) {
            Node root = buildBST(current);

            if (serialize(root).equals(target)) {
                count++;
            }
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            used[i] = true;
            current[index] = nums[i];

            generate(nums, used, current, index + 1);

            used[i] = false;
        }
    }

    public int numOfWays(int[] nums) {
        target = serialize(buildBST(nums));
        count = 0;

        generate(nums, new boolean[nums.length],
                 new int[nums.length], 0);

        return (int) (count - 1);
    }
}
