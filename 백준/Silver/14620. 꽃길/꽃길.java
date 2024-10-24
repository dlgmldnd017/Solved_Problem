import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, arr[][], ans;
    static boolean visited[][];

    static int dy[] = {-1, 1, 0, 0};
    static int dx[] = {0, 0, -1, 1};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[N][N];

        ans = Integer.MAX_VALUE;

        solve(0, 0);

        System.out.println(ans);
    }

    static void solve(int cost, int cnt) {
        if (cnt == 3) {
            if (ans > cost) ans = cost;
            return;
        }

        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                if (visited[i][j] || check(i, j)) continue;
                visited[i][j] = true;

                int sum = getSum(i, j);

                solve(cost + sum, cnt + 1);

                visitClear(i, j);
                visited[i][j] = false;
            }
        }
    }

    static boolean check(int y, int x) {
        for (int k = 0; k < 4; k++) {
            int ny = dy[k] + y;
            int nx = dx[k] + x;

            if (visited[ny][nx]) return true;
        }

        return false;
    }

    static int getSum(int y, int x) {
        int sum = arr[y][x];

        for (int k = 0; k < 4; k++) {
            int ny = dy[k] + y;
            int nx = dx[k] + x;

            visited[ny][nx] = true;
            sum += arr[ny][nx];
        }

        return sum;
    }

    static void visitClear(int y, int x) {
        for (int k = 0; k < 4; k++) {
            int ny = dy[k] + y;
            int nx = dx[k] + x;

            visited[ny][nx] = false;
        }
    }
}