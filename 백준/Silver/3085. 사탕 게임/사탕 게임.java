import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, ans;
    static char map[][];

    static int dy[] = {0, 1};
    static int dx[] = {1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            for (int j = 0; j < N; j++) map[i][j] = str.charAt(j);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                solve(i, j, map[i][j]);
                if (ans == N) {
                    System.out.println(ans);
                    return;
                }
            }
        }

        System.out.println(ans);
    }

    static void solve(int y, int x, char target) {
        for (int k = 0; k < 2; k++) {
            int ny = dy[k] + y;
            int nx = dx[k] + x;

            if (!inRange(ny, nx) || map[ny][nx] == target) continue;
            char tmp;

            tmp = map[ny][nx];
            map[ny][nx] = map[y][x];
            map[y][x] = tmp;

            int maxLen = getMaxLen();

            if (ans < maxLen) ans = maxLen;

            tmp = map[ny][nx];
            map[ny][nx] = map[y][x];
            map[y][x] = tmp;
        }
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < N);
    }

    static int getMaxLen() {
        int maxLen = 0;

        for (int i = 0; i < N; i++) {
            int cntRow = 1, cntCol = 1;

            for (int j = 1; j < N; j++) {
                if (map[i][j] == map[i][j - 1]) cntRow++;
                else {
                    maxLen = Math.max(maxLen, cntRow);
                    cntRow = 1;
                }

                if (map[j][i] == map[j - 1][i]) cntCol++;
                else {
                    maxLen = Math.max(maxLen, cntCol);
                    cntCol = 1;
                }
            }

            maxLen = Math.max(maxLen, Math.max(cntRow, cntCol));
        }

        return maxLen;
    }
}