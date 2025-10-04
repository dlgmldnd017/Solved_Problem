import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int M, N, ans;
    static int[][] arr, dp;

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[M + 1][N + 1];
        dp = new int[M + 1][N + 1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        ans = dfs(1, 1);
    }

    static int dfs(int y, int x) {
        if (y == M && x == N) return 1;

        if (dp[y][x] != -1) return dp[y][x];

        dp[y][x] = 0;

        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];

            if (!inRange(ny, nx) || arr[y][x] <= arr[ny][nx]) continue;

            dp[y][x] += dfs(ny, nx);
        }

        return dp[y][x];
    }

    static boolean inRange(int y, int x) {
        return (1 <= y && y <= M) && (1 <= x && x <= N);
    }
}