import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;
    static int[][] Q;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());

        Q = new int[M][2];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            Q[i][0] = Integer.parseInt(st.nextToken());
            Q[i][1] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(sb);
    }

    static void solve() {
        boolean[][] dp = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) dp[i][i] = true;

        for (int i = 1; i < N; i++) {
            if (arr[i] == arr[i + 1]) dp[i][i + 1] = true;
        }

        for (int len = 3; len <= N; len++) {
            for (int i = 1; i <= N - len + 1; i++) {
                int j = i + len - 1;

                if (arr[i] == arr[j] && dp[i + 1][j - 1]) dp[i][j] = true;
            }
        }

        for (int i = 0; i < M; i++) {
            int s = Q[i][0];
            int e = Q[i][1];

            sb.append(dp[s][e] ? "1\n" : "0\n");
        }
    }
}