import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, T, air, ans;
    static int[][] A;

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        A = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());

                if (A[i][j] == -1) air = i;
                else ans += A[i][j];
            }
        }

        air -= 1;

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int t = 0; t < T; t++) {
            spreadDust();

            excuteAirCleaner();
        }

    }

    static void spreadDust() {
        int[][] tmp = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (A[i][j] == -1 || A[i][j] == 0) continue;

                int value = A[i][j] / 5, cnt = 0;

                for (int k = 0; k < 4; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];

                    if (!inRange(ny, nx) || A[ny][nx] == -1) continue;

                    cnt++;
                    tmp[ny][nx] += value;
                }

                tmp[i][j] += A[i][j] - (value * cnt);
            }
        }

        for (int i = 0; i < R; i++) A[i] = tmp[i].clone();

        A[air][0] = A[air + 1][0] = -1;
    }

    static void excuteAirCleaner() {
        // 반시계
        ans -= A[air - 1][0];

        A[air - 1][0] = 0;

        for (int i = air - 1; i > 0; i--) A[i][0] = A[i - 1][0];

        for (int i = 0; i < C - 1; i++) A[0][i] = A[0][i + 1];

        for (int i = 0; i < air; i++) A[i][C - 1] = A[i + 1][C - 1];

        for (int i = C - 1; i > 1; i--) A[air][i] = A[air][i - 1];

        A[air][1] = 0;

        // 시계
        ans -= A[air + 2][0];

        A[air + 2][0] = 0;

        for (int i = air + 2; i < R - 1; i++) A[i][0] = A[i + 1][0];

        for (int i = 0; i < C - 1; i++) A[R - 1][i] = A[R - 1][i + 1];

        for (int i = R - 1; i > air + 1; i--) A[i][C - 1] = A[i - 1][C - 1];

        for (int i = C - 1; i > 1; i--) A[air + 1][i] = A[air + 1][i - 1];

        A[air + 1][1] = 0;
    }

    static boolean inRange(int y, int x) {
        return (0 <= y && y < R) && (0 <= x && x < C);
    }
}