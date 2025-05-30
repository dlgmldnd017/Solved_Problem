import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int K, ans;
    static int[][] map = new int[5][5];
    static boolean[][] visited = new boolean[5][5];

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;

            map[y][x] = 1;
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        if (K % 2 == 1) return;

        dfs(0, 0, 1, false);
    }

    static void dfs(int y, int x, int depth, boolean isHalf) {
        int cnt = depth;
        boolean flag = isHalf;

        if (depth == (25 - K) / 2) {
            if (isHalf) {
                if (y == 4 && x == 4) ans++;
                return;
            } else {
                cnt = -1;
                flag = true;
            }
        }

        visited[y][x] = true;

        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];

            if (!inRange(ny, nx) || visited[ny][nx] || map[ny][nx] == 1) continue;

            dfs(ny, nx, cnt + 1, flag);
        }

        visited[y][x] = false;
    }

    static boolean inRange(int y, int x) {
        return (0 <= y && y < 5) && (0 <= x && x < 5);
    }
}