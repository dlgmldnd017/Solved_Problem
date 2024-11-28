import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, arr[][], ans;
    static boolean visited[][];

    static int dr[] = {-1, 0, 1, 0};
    static int dc[] = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[N][M];

        solve(r, c, d);

        for (boolean i[] : visited) {
            for (boolean j : i) {
                if (j) ans++;
            }
        }

        System.out.println(ans);
    }

    static void solve(int r, int c, int d) {
        visited[r][c] = true;

        int dir = d;
        boolean check = false;

        for (int k = 0; k < 4; k++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (!inRange(nr, nc) || arr[nr][nc] == 1 || visited[nr][nc]) {
                d--;
                if (d < 0) d = 3;
            } else {
                check = true;
                break;
            }
        }

        if (!check) {
            // 후진
            int nr = -1, nc = -1;

            switch (dir) {
                case 0:
                    nr = r + dr[2];
                    nc = c + dc[2];
                    break;
                case 1:
                    nr = r + dr[3];
                    nc = c + dc[3];
                    break;
                case 2:
                    nr = r + dr[0];
                    nc = c + dc[0];
                    break;
                case 3:
                    nr = r + dr[1];
                    nc = c + dc[1];
                    break;
            }

            if (!inRange(nr, nc) || arr[nr][nc] == 1) return;
            else solve(nr, nc, dir);

        } else {
            for (int k = 0; k < 4; k++) {
                dir--;
                if (dir < 0) dir = 3;

                int nr = r + dr[dir];
                int nc = c + dc[dir];

                if (!inRange(nr, nc) || visited[nr][nc] || arr[nr][nc] == 1) continue;

                solve(nr, nc, dir);
                break;
            }
        }
    }

    static boolean inRange(int r, int c) {
        return (r >= 0 && r < N) && (c >= 0 && c < M);
    }
}