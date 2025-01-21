import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, map[][], dp[][], ans;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        ans = solve(0, 0);

        System.out.println(ans);
    }

    static int solve(int y, int x) {
        if (y == N - 1 && x == M - 1) return 1;

        if (dp[y][x] != -1) return dp[y][x];

        dp[y][x] = 0;

        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];

            if (!inRange(ny, nx) || map[y][x] <= map[ny][nx]) continue;

            dp[y][x] += solve(ny, nx);
        }

        return dp[y][x];
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < M);
    }
}