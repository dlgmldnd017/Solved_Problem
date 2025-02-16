import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, R, Q, ans;
    static int[] query, dp;
    static List<Integer> list[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            list[U].add(V);
            list[V].add(U);
        }

        query = new int[Q];

        for (int i = 0; i < Q; i++) query[i] = Integer.parseInt(br.readLine());

        solve();

        System.out.println(sb);
    }

    static void solve() {
        dp = new int[N + 1];
        Arrays.fill(dp, 1);

        makeTree(R, -1);

        for (int i : query) {
            sb.append(dp[i] + "\n");
        }
    }

    static void makeTree(int currentNode, int parent) {
        for (int next : list[currentNode]) {
            if (next != parent) makeTree(next, currentNode);
        }

        if (parent != -1) dp[parent] += dp[currentNode];
    }
}