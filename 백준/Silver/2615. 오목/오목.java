import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, arr[][], memo[][][];

    static int dy[] = {-1, 0, 1, 1};
    static int dx[] = {1, 1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = 21;

        arr = new int[N][N];

        for (int i = 1; i <= 19; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 19; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        memo = new int[N][N][4];

        for (int i = 1; i <= 19; i++) {
            for (int j = 1; j <= 19; j++) {
                if (arr[j][i] == 0) continue;

                for (int k = 0; k < 4; k++) {
                    if (memo[j][i][k] == 0 && solve(j, i, k, arr[j][i]) == 5) {
                        System.out.println(arr[j][i]);
                        System.out.println(j + " " + i);
                        System.exit(0);
                    }
                }
            }
        }

        System.out.println(0);
    }

    static int solve(int y, int x, int k, int color) {
        int ny = dy[k] + y;
        int nx = dx[k] + x;

        if (arr[ny][nx] == color) return memo[ny][nx][k] = solve(ny, nx, k, color) + 1;
        return 1;
    }
}