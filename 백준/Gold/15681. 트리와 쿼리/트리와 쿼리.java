import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, R, Q, dp[];
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

        dp = new int[N + 1];
        Arrays.fill(dp, 1);

        solve(R, -1);

        for (int q = 0; q < Q; q++) {
            int U = Integer.parseInt(br.readLine());
            sb.append(dp[U] + "\n");
        }

        System.out.println(sb);
    }

    static void solve(int idx, int p) {
        for (int next : list[idx]) {
            if (p != next) solve(next, idx);
        }

        if (p != -1) dp[p] += dp[idx];
    }
}