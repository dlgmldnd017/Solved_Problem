import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, ans;
    static int[] parent;
    static boolean[] truth;
    static List<Integer>[] parties;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) parent[i] = i;

        truth = new boolean[N + 1];

        parties = new ArrayList[M];

        st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());

        for (int i = 0; i < cnt; i++) {
            int person = Integer.parseInt(st.nextToken());
            truth[person] = true;
        }

        for (int i = 0; i < M; i++) {
            parties[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());

            parties[i].add(first);

            for (int j = 1; j < size; j++) {
                int next = Integer.parseInt(st.nextToken());

                parties[i].add(next);
                union(first, next);
            }
        }

        solve();

        System.out.println(ans);
    }

    static void union(int a, int b) {
        int rootA = find(a);

        int rootB = find(b);

        if (rootA != rootB) {
            if (truth[rootA] || truth[rootB]) truth[rootA] = truth[rootB] = true;
            parent[rootB] = rootA;
        }
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void solve() {
        for (int i = 0; i < M; i++) {
            boolean canLie = true;

            for (int person : parties[i]) {
                if (truth[find(person)]) {
                    canLie = false;
                    break;
                }
            }

            if (canLie) ans++;
        }
    }
}
