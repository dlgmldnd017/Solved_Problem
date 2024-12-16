import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, arr[][], startY, startX, ans;

    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            for (int j = 0; j < M; j++) {
                char c = input.charAt(j);

                if (c == '@') {
                    startY = i;
                    startX = j;
                } else if (c == '*') {
                    arr[i][j] = 1;
                    cnt++;
                }
                else if (c == '#') {
                    arr[i][j] = 2;
                    cnt++;
                }
                else if (c == '|') arr[i][j] = -1;
                else arr[i][j] = 0;
            }
        }

        arr[startY][startX] = 0;

        solve(startY, startX, 2);

        System.out.println(ans + " " + (cnt - ans));
    }

    static void solve(int y, int x, int flag) {
        for (int k = 0; k < 4; k++) {
            for (int j = 1; j <= flag; j++) {
                int ny = y + dy[k] * j;
                int nx = x + dx[k] * j;

                if (!inRange(ny, nx) || arr[ny][nx] == -1) break;

                if (arr[ny][nx] == 1) {
                    arr[ny][nx]--;
                    solve(ny, nx, 1);
                    ans++;
                } else if (arr[ny][nx] == 2) arr[ny][nx]--;
            }
        }
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < M);
    }
}