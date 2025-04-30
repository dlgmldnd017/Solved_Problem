import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int K, dis, ans;
    static boolean[][] map, visited;

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());

        map = new boolean[5][5];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;

            map[y][x] = true;
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        dis = (25 - K) / 2;

        visited = new boolean[5][5];

        dfs(0, 0, 0, false);
    }

    static void dfs(int y, int x, int cnt, boolean flag) {
        if (dis == cnt) {
            if (!flag) dfs(y, x, 0, true);
            else if (y == 4 && x == 4) ans++;
            return;
        }

        visited[y][x] = true;

        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];

            if (!inRange(ny, nx) || visited[ny][nx] || map[ny][nx]) continue;

            dfs(ny, nx, cnt + 1, flag);
        }

        visited[y][x] = false;
    }

    static boolean inRange(int y, int x) {
        return (0 <= y && y < 5) && (0 <= x && x < 5);
    }
}