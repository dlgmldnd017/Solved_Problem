import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int r, c, arr[][], ans;
    static boolean visited[][];

    static int dy[] = {-1, 1, 0, 0};
    static int dx[] = {0, 0, -1, 1};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr = new int[5][5];

        visited = new boolean[5][5];

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        solve(r, c, 0, 0);
        System.out.println(0);
    }

    static void solve(int y, int x, int cnt, int time) {
        if (time == 4) return;

        if (cnt == 2) {
            System.out.println(1);
            System.exit(0);
        }

        visited[y][x] = true;

        for (int k = 0; k < 4; k++) {
            int ny = dy[k] + y;
            int nx = dx[k] + x;

            if (!inRange(ny, nx) || visited[ny][nx] || arr[ny][nx] == -1) continue;

            solve(ny, nx, cnt + (arr[ny][nx] == 1 ? 1 : 0), time + 1);
        }
        visited[y][x] = false;
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < 5) && (x >= 0 && x < 5);
    }
}