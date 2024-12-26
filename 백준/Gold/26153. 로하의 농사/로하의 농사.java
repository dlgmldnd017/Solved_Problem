import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, arr[][], ans;
    static boolean visited[][];

    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        visited[x][y] = true;

        ans = arr[x][y];

        if (p != 0) {
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (!inRange(nx, ny)) continue;

                visited[nx][ny] = true;
                solve(nx, ny, p - 1, arr[x][y] + arr[nx][ny], k);
                visited[nx][ny] = false;
            }
        }

        System.out.println(ans);
    }

    static void solve(int x, int y, int p, int sum, int dir) {
        if (p < 0) return;

        if (ans < sum) ans = sum;

        if (p == 0) return;

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (!inRange(nx, ny) || visited[nx][ny]) continue;

            if (dir == k) {
                visited[nx][ny] = true;
                solve(nx, ny, p - 1, sum + arr[nx][ny], k);
                visited[nx][ny] = false;
            } else {
                visited[nx][ny] = true;
                solve(nx, ny, p - 2, sum + arr[nx][ny], k);
                visited[nx][ny] = false;
            }
        }
    }

    static boolean inRange(int x, int y) {
        return (x >= 0 && x < N) && (y >= 0 && y < M);
    }
}