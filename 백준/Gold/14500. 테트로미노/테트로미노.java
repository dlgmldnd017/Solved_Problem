import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, arr[][], ans;
    static boolean visited[][];

    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};

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

        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                solve(1, i, j, arr[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(ans);
    }

    static void solve(int depth, int y, int x, int sum) {
        if (depth == 4) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];

            if (!inRange(ny, nx) || visited[ny][nx]) continue;

            if (depth == 2) {
                visited[ny][nx] = true;
                solve(depth + 1, y, x, sum + arr[ny][nx]);
                visited[ny][nx] = false;
            }

            visited[ny][nx] = true;
            solve(depth + 1, ny, nx, sum + arr[ny][nx]);
            visited[ny][nx] = false;
        }
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < M);
    }
}