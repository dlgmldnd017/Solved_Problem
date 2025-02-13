import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, arr[][], ans;
    static boolean visited[][];
    static int dy[] = {0, 0, 1};
    static int dx[] = {-1, 1, 0};

    public static void main(String[] args) throws Exception {
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
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, arr[i][j]);
                visited[i][j] = false;
            }
        }
    }

    static void dfs(int y, int x, int cnt, int sum) {
        if (ans < sum) ans = sum;

        if (cnt == 4) return;

        for (int k = 0; k < 3; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];

            if (!inRange(ny, nx) || visited[ny][nx]) continue;

            if (cnt == 2) {
                visited[ny][nx] = true;
                dfs(y, x, cnt + 1, sum + arr[ny][nx]);
                visited[ny][nx] = false;
            }

            visited[ny][nx] = true;
            dfs(ny, nx, cnt + 1, sum + arr[ny][nx]);
            visited[ny][nx] = false;
        }
    }

    static boolean inRange(int y, int x) {
        return (0 <= y && y < N) && (0 <= x && x < M);
    }
}