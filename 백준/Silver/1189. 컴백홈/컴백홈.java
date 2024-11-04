import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, K, visited[][], ans;
    static char map[][];

    static int dy[] = {1, -1, 0, 0};
    static int dx[] = {0, 0, 1, -1};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String input = br.readLine();

            for (int j = 0; j < C; j++) map[i][j] = input.charAt(j);
        }

        visited = new int[R][C];

        visited[R - 1][0] = 1;
        solve(R - 1, 0, 1);

        System.out.println(ans);
    }

    static void solve(int y, int x, int cnt) {
        if (y == 0 && x == C - 1) {
            if (cnt == K) ans++;
            return;
        }

        for (int k = 0; k < 4; k++) {
            int nr = dy[k] + y;
            int nc = dx[k] + x;

            if (!inRange(nr, nc) || visited[nr][nc] == 1 || map[nr][nc] == 'T') continue;

            visited[nr][nc] = 1;
            solve(nr, nc, cnt + 1);
            visited[nr][nc] = 0;
        }
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < R) && (x >= 0 && x < C);
    }
}