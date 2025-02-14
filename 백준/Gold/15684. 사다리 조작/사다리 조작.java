import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, H, arr[][], ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        arr = new int[H + 1][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            arr[a][b] = 1;
            arr[a][b + 1] = -1;
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        if (M == 0 || check()) return;

        ans = -1;

        for (int i = 1; i <= 3; i++) dfs(0, 0, i, 0);
    }

    static void dfs(int y, int x, int depth, int cnt) {
        if (depth == cnt) {
            if (check()) {
                System.out.println(cnt);
                System.exit(0);
            }
            return;
        }

        if (x >= N - 1) {
            dfs(y + 1, 0, depth, cnt);
            return;
        }

        if (y == H) return;

        if (arr[y][x] == 0 && arr[y][x + 1] == 0) {
            arr[y][x] = 1;
            arr[y][x + 1] = -1;
            dfs(y, x + 2, depth, cnt + 1);
            arr[y][x] = 0;
            arr[y][x + 1] = 0;
        }

        dfs(y, x + 1, depth, cnt);
    }

    static boolean check() {
        for (int i = 0; i < N; i++) {
            int y = 0, x = i;

            while (y < H) {
                if (arr[y][x] == 1) x += 1;
                else if (arr[y][x] == -1) x -= 1;
                y++;
            }

            if (x != i) return false;
        }

        return true;
    }
}