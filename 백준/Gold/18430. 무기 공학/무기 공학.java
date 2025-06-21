import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, ans;
    static int[][] arr;
    static boolean[][] visited;
    static int[][] dirs = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1},};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[N][M];

        solve();

        System.out.println(ans);
    }

    static void solve() {
        if (N + M < 3) return;

        dfs(0, 0, 0);
    }

    static void dfs(int y, int x, int sum) {
        if (y == N) {
            if (ans < sum) ans = sum;
            return;
        }

        if (x == M) {
            dfs(y + 1, 0, sum);
            return;
        }

        if (!visited[y][x]) {
            visited[y][x] = true;

            for (int i = 0; i < 4; i++) {

                if (!inRange(y + dirs[i][0], x + dirs[i][1]) || visited[y + dirs[i][0]][x] || visited[y][x + dirs[i][1]]) continue;

                visited[y + dirs[i][0]][x] = true;
                visited[y][x + dirs[i][1]] = true;

                dfs(y, x + 1, sum + arr[y][x] * 2 + arr[y + dirs[i][0]][x] + arr[y][x + dirs[i][1]]);

                visited[y + dirs[i][0]][x] = false;
                visited[y][x + dirs[i][1]] = false;
            }

            visited[y][x] = false;
        }

        dfs(y, x + 1, sum);
    }

    static boolean inRange(int y, int x) {
        return (0 <= y && y < N) && (0 <= x && x < M);
    }
}