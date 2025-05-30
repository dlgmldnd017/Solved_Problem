import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int K, distance, ans;
    static boolean[][] map, visited;

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new boolean[5][5];

        K = Integer.parseInt(br.readLine());

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
        distance = (25 - K) / 2;

        if ((25 - K) % 2 == 0) return;

        visited = new boolean[5][5];

        dfs(0, 0, 0, false);
    }

    static void dfs(int y, int x, int depth, boolean isHalf) {
        if (depth == distance) {
            if (!isHalf) dfs(y, x, 0, true);
            else if(y == 4 && x == 4) ans++;
            return;
        }

        visited[y][x] = true;

        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];

            if (!inRange(ny, nx) || visited[ny][nx] || map[ny][nx]) continue;

            dfs(ny, nx, depth + 1, isHalf);
        }

        visited[y][x] = false;
    }

    static boolean inRange(int y, int x) {
        return (0 <= y && y < 5) && (0 <= x && x < 5);
    }
}