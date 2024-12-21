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

        arr = new int[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a][b] = 1;
            arr[a][b + 1] = 2;
        }

        for (int i = 0; i <= 3; i++) {
            ans = i;
            solve(1, 0);
        }

        if (ans == 3) ans = -1;
        System.out.println(ans);
    }

    static void solve(int y, int cnt) {
        if (ans == cnt) {
            if (!check()) return;
            System.out.println(ans);
            System.exit(0);
        }

        for (int i = y; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (arr[i][j] == 0 && arr[i][j + 1] == 0) {
                    arr[i][j] = 1;
                    arr[i][j + 1] = 2;
                    solve(i, cnt + 1);
                    arr[i][j] = arr[i][j + 1] = 0;
                }
            }
        }
    }

    static boolean check() {
        for (int i = 1; i <= N; i++) {
            int y = 1, x = i;

            for (int j = 1; j <= H; j++) {
                if (arr[y][x] == 1) x++;
                else if (arr[y][x] == 2) x--;
                y++;
            }

            if (x != i) return false;
        }
        return true;
    }
}