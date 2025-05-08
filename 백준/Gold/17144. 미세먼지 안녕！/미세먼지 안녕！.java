import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, T;
    static int r[], c[];
    static int map[][], tmp[][];

    static int dy[] = {-1, 0, 1, 0};
    static int dx[] = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(sc.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        r = new int[2];
        c = new int[2];
        map = new int[R][C];

        int idx = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(sc.readLine());

            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == -1) {
                    r[idx] = i;
                    c[idx] = j;
                    idx++;
                }
            }
        }

        solve();
        System.out.println(countDust());
    }

    static int countDust() {
        int sum = 0;

        for (int i[] : map) {
            for (int j : i) {
                if (j == 0 || j == -1) continue;

                sum += j;
            }
        }

        return sum;
    }

    static void solve() {
        for (int t = 1; t <= T; t++) {
            tmp = new int[R][C];
            spreadDust();

            tmp = new int[R][C];
            excuteAirCleaner();
        }
    }

    static void spreadDust() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 0) continue;

                int sum = 0;
                
                for (int k = 0; k < 4; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];

                    if (!inRange(ny, nx) || map[ny][nx] == -1) continue;

                    sum += map[i][j] / 5;

                    tmp[ny][nx] += map[i][j] / 5;
                }

                map[i][j] -= sum;
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (tmp[i][j] == 0) continue;
                map[i][j] += tmp[i][j];
            }
        }
    }
    
    static void excuteAirCleaner() {
        int r1 = r[0], c1 = c[0];
        int printX = C - 1, printY = r1, t = 1;

        for (int k = 0; k < 2; k++) {

            for (int i = 0; i < printX; i++) {
                int dust = map[r1][c1];
                c1 += t;

                if (dust == -1 || dust == 0) continue;

                tmp[r1][c1] = dust;
            }

            t *= -1;

            for (int i = 0; i < printY; i++) {
                int dust = map[r1][c1];
                r1 += t;

                if (dust == 0) continue;

                if (map[r1][c1] != -1) tmp[r1][c1] = dust;
            }
        }

        int r2 = r[1], c2 = c[1];
        printY = R - r2;
        printX = C - 1;
        t = 1;

        if (r2 != R - 1) {
            for (int k = 0; k < 2; k++) {
                for (int i = 0; i < printX; i++) {
                    int dust = map[r2][c2];
                    c2 += t;

                    if (dust == -1 || dust == 0) continue;

                    tmp[r2][c2] = dust;
                }

                for (int i = 0; i < printY - 1; i++) {
                    int dust = map[r2][c2];
                    r2 += t;

                    if (dust == 0) continue;

                    if (map[r2][c2] != -1) tmp[r2][c2] = dust;
                }

                t *= -1;
            }
        }
        copy();
    }

    static void copy() {
        int r1 = r[0], c1 = c[0];
        int printX = C - 1, printY = r1, t = 1;

        for (int k = 0; k < 2; k++) {
            for (int i = 0; i < printX; i++) {
                if (map[r1][c1] != -1) map[r1][c1] = tmp[r1][c1];
                c1 += t;
            }

            t *= -1;

            for (int i = 0; i < printY; i++) {
                if (map[r1][c1] != -1) map[r1][c1] = tmp[r1][c1];
                r1 += t;
            }
        }

        int r2 = r[1], c2 = c[1];
        printY = R - r2;
        printX = C - 1;
        t = 1;

        if (r2 != R - 1) {
            for (int k = 0; k < 2; k++) {
                for (int i = 0; i < printX; i++) {
                    if (map[r2][c2] != -1) map[r2][c2] = tmp[r2][c2];
                    c2 += t;
                }

                for (int i = 0; i < printY - 1; i++) {
                    if (map[r2][c2] != -1) map[r2][c2] = tmp[r2][c2];
                    r2 += t;
                }

                t *= -1;
            }
        }
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < R) && (x >= 0 && x < C);
    }
}