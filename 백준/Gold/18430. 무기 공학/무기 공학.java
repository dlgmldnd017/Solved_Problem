import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, ans;
    static int[][] arr;
    static boolean[][] visited;
    static int[][][] dirs = {
            {{0, 1}, {1, 0}},
            {{0, -1}, {1, 0}},
            {{-1, 0}, {0, 1}},
            {{-1, 0}, {0, -1}},
    };

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
        if (N < 2 || M < 2) return;

        dfs(0, 0, 0);
    }

    static void dfs(int y, int x, int sum) {
        if (ans < sum) ans = sum;

        if (x == M) {
            dfs(y + 1, 0, sum);
            return;
        }

        if (y == N) return;

        if (visited[y][x]) dfs(y, x + 1, sum);
        else {
            visited[y][x] = true;

            for (int i = 0; i < 4; i++) {
                int tmp = arr[y][x] * 2, cnt = 0;
                int[][] placed = new int[2][2];
                boolean can = true;

                for (int j = 0; j < 2; j++) {
                    int ny = y + dirs[i][j][0];
                    int nx = x + dirs[i][j][1];

                    if (!inRange(ny, nx) || visited[ny][nx]) {
                        can = false;
                        break;
                    }

                    tmp += arr[ny][nx];
                    visited[ny][nx] = true;
                    placed[cnt][0] = ny;
                    placed[cnt][1] = nx;
                    cnt++;
                }

                if (can) dfs(y, x + 1, sum + tmp);

                for (int k = 0; k < cnt; k++) visited[placed[k][0]][placed[k][1]] = false;
            }

            visited[y][x] = false;

            dfs(y, x + 1, sum);
        }
    }

    static boolean inRange(int y, int x) {
        return (0 <= y && y < N) && (0 <= x && x < M);
    }
}