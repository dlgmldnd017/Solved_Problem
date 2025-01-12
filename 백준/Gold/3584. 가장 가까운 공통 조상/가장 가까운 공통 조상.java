import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, N, p[], h[], A, B, ans;
    static List<Integer> tree[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            tree = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++) tree[i] = new ArrayList<>();

            p = new int[N + 1];

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                tree[parent].add(child);
                p[child] = parent;
            }

            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            solve();
        }

        System.out.println(sb);
    }

    static void solve() {
        int root = findRoot();

        h = new int[N + 1];

        findHeight(root, 0);

        findLCA();

        sb.append(ans + "\n");
    }

    static int findRoot() {
        for (int i = 1; i <= N; i++) {
            if (p[i] == 0) return i;
        }

        return -1;
    }

    static void findHeight(int cur, int depth) {
        h[cur] = depth;

        for (int next : tree[cur]) findHeight(next, depth + 1);
    }

    static void findLCA() {
        while (h[A] > h[B]) A = p[A];
        while (h[B] > h[A]) B = p[B];

        while (A != B) {
            A = p[A];
            B = p[B];
        }

        ans = A;
    }
}
