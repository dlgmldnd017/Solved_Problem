import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, T, arr[][], ans;
    static List<AirPur> air = new ArrayList<>();
    static List<Dust> dusts;

    static int dy[] = {0, -1, 0, 1};
    static int dx[] = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new int[R][C];

        int dir = 1;

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] == -1) air.add(new AirPur(i, j, dir++));
            }
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int t = 0; t < T; t++) {
            checkDust();

            spreadDust();
//            for (int i[] : arr) {
//                for (int j : i) System.out.print(j + " ");
//                System.out.println();
//            }
//            System.out.println();
            clearAir();
        }

        for (int i[] : arr) {
            for (int j : i) ans += j;
        }

        ans += 2;
    }

    static void checkDust() {
        dusts = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == 0 || arr[i][j] == -1) continue;

                dusts.add(new Dust(i, j, arr[i][j]));
            }
        }
    }

    static void spreadDust() {
        for (Dust d : dusts) {
            int sum = d.cnt / 5, cnt = 0;

            for (int k = 0; k < 4; k++) {
                int ny = d.y + dy[k];
                int nx = d.x + dx[k];

                if (!inRange(ny, nx) || arr[ny][nx] == -1) continue;

                arr[ny][nx] += sum;
                cnt++;
            }

            arr[d.y][d.x] -= sum * cnt;
        }
    }

    static void clearAir() {
        for (AirPur a : air) {
            if (a.dir == 1) {
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

class AirPur {
    int y, x, dir;

    AirPur(int y, int x, int dir) {
        this.y = y;
        this.x = x;
        this.dir = dir;
    }
}