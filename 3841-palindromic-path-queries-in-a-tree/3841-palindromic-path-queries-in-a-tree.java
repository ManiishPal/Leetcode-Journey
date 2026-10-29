
import java.util.*;

class Solution {
    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        char[] chars = s.toCharArray();

        int log = 1;
        while ((1 << log) <= n) log++;

        int[] parent = new int[n];
        int[] depth = new int[n];
        int[] tin = new int[n];
        int[] tout = new int[n];
        int[] order = new int[n];
        int[] size = new int[n];
        int[][] up = new int[log][n];

        Arrays.fill(parent, -1);

        int[] stack = new int[n];
        int top = 0, timer = 0;
        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {
            int u = stack[--top];
            tin[u] = timer;
            order[timer++] = u;

            for (int v : graph[u]) {
                if (parent[v] != -1) continue;
                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        Arrays.fill(size, 1);

        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            size[parent[u]] += size[u];
        }

        for (int u = 0; u < n; u++) {
            tout[u] = tin[u] + size[u] - 1;
            up[0][u] = parent[u];
        }

        for (int j = 1; j < log; j++) {
            for (int u = 0; u < n; u++) {
                up[j][u] = up[j - 1][up[j - 1][u]];
            }
        }

        Fenwick fenwick = new Fenwick(n);

        for (int u = 0; u < n; u++) {
            fenwick.rangeXor(
                tin[u], tout[u], 1 << (chars[u] - 'a')
            );
        }

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");
            int u = Integer.parseInt(p[1]);

            if (p[0].equals("update")) {
                char c = p[2].charAt(0);

                if (chars[u] != c) {
                    int delta = (1 << (chars[u] - 'a'))
                              ^ (1 << (c - 'a'));

                    fenwick.rangeXor(tin[u], tout[u], delta);
                    chars[u] = c;
                }
            } else {
                int v = Integer.parseInt(p[2]);
                int a = u, b = v;

                if (depth[a] < depth[b]) {
                    int temp = a;
                    a = b;
                    b = temp;
                }

                int diff = depth[a] - depth[b];

                for (int j = 0; j < log; j++) {
                    if (((diff >> j) & 1) != 0) {
                        a = up[j][a];
                    }
                }

                if (a != b) {
                    for (int j = log - 1; j >= 0; j--) {
                        if (up[j][a] != up[j][b]) {
                            a = up[j][a];
                            b = up[j][b];
                        }
                    }
                    a = parent[a];
                }

                int lca = a;

                int mask = fenwick.pointQuery(tin[u])
                         ^ fenwick.pointQuery(tin[v])
                         ^ (1 << (chars[lca] - 'a'));

                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }

    static class Fenwick {
        int[] tree;
        int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 2];
        }

        void add(int index, int value) {
            for (int i = index + 1; i <= n + 1;
                 i += i & -i) {
                tree[i] ^= value;
            }
        }

        void rangeXor(int left, int right, int value) {
            add(left, value);
            add(right + 1, value);
        }

        int pointQuery(int index) {
            int result = 0;
            for (int i = index + 1; i > 0; i -= i & -i) {
                result ^= tree[i];
            }
            return result;
        }
    }
}
