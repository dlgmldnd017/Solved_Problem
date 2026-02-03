import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 17_000_000;

    static int N, M;
    static int[][] arr, dp;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = (1 << N) - 1;

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        System.out.print(solve(0, 1));
    }

    static int solve(int cur, int visit) {
        if (visit == M) {
            if (arr[cur][0] == 0) return INF;
            return arr[cur][0];
        }

        if (dp[cur][visit] != -1) return dp[cur][visit];
        dp[cur][visit] = INF;

        for (int i = 1; i < N; i++) {
            if (arr[cur][i] == 0) continue;

            int next = 1 << i;
            if ((visit & next) != 0) continue;

            dp[cur][visit] = Math.min(solve(i, visit | next) + arr[cur][i], dp[cur][visit]);
        }

        return dp[cur][visit];
    }
}