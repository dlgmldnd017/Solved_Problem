import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, T;
    static int[] parent, depth;
    static List<Integer>[] tree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            tree = new ArrayList[N + 1];
            parent = new int[N + 1];
            depth = new int[N + 1];

            for (int i = 1; i <= N; i++) tree[i] = new ArrayList<>();

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                tree[a].add(b);
                parent[b] = a;
            }

            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            solve(A, B);
        }

        System.out.println(sb);
    }

    static void solve(int A, int B) {
        int root = findRoot();

        dfs(root, 0);

        sb.append(findLCA(A, B)).append("\n");
    }

    static int findRoot() {
        for (int i = 1; i <= N; i++) {
            if (parent[i] == 0) return i;
        }
        return -1;
    }

    static void dfs(int node, int d) {
        depth[node] = d;

        for (int child : tree[node]) dfs(child, d + 1);
    }

    static int findLCA(int a, int b) {
        while (depth[a] > depth[b]) a = parent[a];
        while (depth[b] > depth[a]) b = parent[b];

        while (a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }
}
