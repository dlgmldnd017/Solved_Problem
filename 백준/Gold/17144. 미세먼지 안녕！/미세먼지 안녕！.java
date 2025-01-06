import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, T, arr[][], ans;
    static List<Dust> dusts = new ArrayList<>();
    static List<Dust> airs = new ArrayList<>();

    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new int[R][C];

        int flag = 1;

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] == -1) {
                    airs.add(new Dust(i, j, flag));
                    flag = -1;
                } else if (arr[i][j] != 0) dusts.add(new Dust(i, j, arr[i][j]));
            }
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int t = 0; t < T; t++) {
            spreadDusts();

            useAirCleaner();

            if (t + 1 != T) init();
        }

        for (int i[] : arr) {
            for (int j : i) {
                if (j == -1) continue;

                ans += j;
            }
        }
    }

    static void spreadDusts() {
        int tmp[][] = new int[R][C];

        for (Dust d : dusts) {
            int sum = 0;

            for (int k = 0; k < 4; k++) {
                int ny = dy[k] + d.y;
                int nx = dx[k] + d.x;

                if (!inRange(ny, nx) || arr[ny][nx] == -1) continue;

                sum += arr[d.y][d.x] / 5;
                tmp[ny][nx] += arr[d.y][d.x] / 5;
            }

            arr[d.y][d.x] -= sum;
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (tmp[i][j] == 0) continue;

                arr[i][j] += tmp[i][j];
            }
        }
    }

    static void useAirCleaner() {
        for (Dust a : airs) {
            if (a.cnt == 1) {
                if (a.y == 0) continue;
                arr[a.y - 1][0] = 0;

                for (int i = a.y - 1; i > 0; i--) {
                    int tmp = arr[i][0];
                    arr[i][0] = arr[i - 1][0];
                    arr[i - 1][0] = tmp;
                }

                for (int i = 0; i < C - 1; i++) {
                    int tmp = arr[0][i];
                    arr[0][i] = arr[0][i + 1];
                    arr[0][i + 1] = tmp;
                }

                for (int i = 0; i < a.y; i++) {
                    int tmp = arr[i][C - 1];
                    arr[i][C - 1] = arr[i + 1][C - 1];
                    arr[i + 1][C - 1] = tmp;
                }

            } else {
                if (a.y == R - 1) continue;
                arr[a.y + 1][0] = 0;

                for (int i = a.y + 1; i < R - 1; i++) {
                    int tmp = arr[i][0];
                    arr[i][0] = arr[i + 1][0];
                    arr[i + 1][0] = tmp;
                }

                for (int i = 0; i < C - 1; i++) {
                    int tmp = arr[R - 1][i];
                    arr[R - 1][i] = arr[R - 1][i + 1];
                    arr[R - 1][i + 1] = tmp;
                }

                for (int i = R - 1; i > a.y; i--) {
                    int tmp = arr[i][C - 1];
                    arr[i][C - 1] = arr[i - 1][C - 1];
                    arr[i - 1][C - 1] = tmp;
                }
            }

            for (int i = C - 1; i > 1; i--) {
                int tmp = arr[a.y][i];
                arr[a.y][i] = arr[a.y][i - 1];
                arr[a.y][i - 1] = tmp;
            }
        }
    }

    static void init() {
        dusts = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == -1 || arr[i][j] == 0) continue;

                dusts.add(new Dust(i, j, arr[i][j]));
            }
        }
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < R) && (x >= 0 && x < C);
    }
}

class Dust {
    int y, x, cnt;

    Dust(int y, int x, int cnt) {
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }
}