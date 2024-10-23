import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, dp[][], map[][], ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N][(1 << N) - 1];

        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        ans = solve(0, 1);

        System.out.println(ans);
    }

    static int solve(int cur, int visit) {
        if (visit == (1 << N) - 1) {
            if (map[cur][0] == 0) return 10_000_000;
            return map[cur][0];
        }

        if (dp[cur][visit] != -1) return dp[cur][visit];
        dp[cur][visit] = 10_000_000;

        for (int i = 1; i < N; i++) {
            if ((visit & (1 << i)) != 0 || map[cur][i] == 0) continue;

            dp[cur][visit] = Math.min(solve(i, visit | 1 << i) + map[cur][i], dp[cur][visit]);
        }

        return dp[cur][visit];
    }
}