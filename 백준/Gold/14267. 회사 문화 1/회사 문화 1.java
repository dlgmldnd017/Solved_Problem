import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] p, q;
    static List<Integer>[] tree;

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) tree[i] = new ArrayList<>();

        p = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        p[1] = Integer.parseInt(st.nextToken());

        for (int i = 2; i <= N; i++) {
            p[i] = Integer.parseInt(st.nextToken());
            tree[p[i]].add(i);
        }

        q = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            q[idx] += w;
        }

        solve();

        System.out.println(sb);
    }

    static void solve() {
        dfs(1);

        for (int i = 1; i <= N; i++) sb.append(q[i]).append(" ");
    }

    static void dfs(int cur) {
        for (int child : tree[cur]) {
            q[child] += q[cur];
            dfs(child);
        }
    }
}