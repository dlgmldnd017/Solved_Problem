import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, ans;
    static int[][] arr;
    static boolean[][] visited;

    static int[][][] boo = {
            {{0, 0}, {1, 0}, {0, -1}}, // ㄱ
            {{0, 0}, {1, 0}, {0, 1}}, // ┌
            {{0, 0}, {-1, 0}, {0, -1}}, // ┘
            {{0, 0}, {-1, 0}, {0, 1}} // ㄴ
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

        solve();

        System.out.println(ans);
    }

    static void solve() {
        if (N + M < 3) return;

        visited = new boolean[N][M];

        dfs(0, 0, 0);
    }

    static void dfs(int y, int x, int total) {
        if (y == N) {
            if (ans < total) ans = total;
            return;
        }

        int ny = (x + 1 == M) ? y + 1 : y;
        int nx = (x + 1 == M) ? 0 : x + 1;

        if (!visited[y][x]) {
            for (int[][] shape : boo) {
                int[][] tmp = new int[3][2];

                boolean canPlace = true;

                int sum = 0;

                for (int k = 0; k < 3; k++) {
                    int dy = y + shape[k][0];
                    int dx = x + shape[k][1];

                    if (!inRange(dy, dx) || visited[dy][dx]) {
                        canPlace = false;
                        break;
                    }

                    tmp[k][0] = dy;
                    tmp[k][1] = dx;

                    if (k == 0) sum += arr[dy][dx] * 2;
                    else sum += arr[dy][dx];
                }

                if (canPlace) {
                    for (int[] t : tmp) visited[t[0]][t[1]] = true;
                    dfs(ny, nx, total + sum);
                    for (int[] t : tmp) visited[t[0]][t[1]] = false;
                }
            }
        }

        dfs(ny, nx, total);
    }

    static boolean inRange(int y, int x) {
        return (0 <= y && y < N) && (0 <= x && x < M);
    }
}