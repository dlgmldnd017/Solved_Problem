import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, pop[][], totalPop, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        pop = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                pop[i][j] = Integer.parseInt(st.nextToken());
                totalPop += pop[i][j];
            }
        }

        ans = Integer.MAX_VALUE;

        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if (x + d1 + d2 <= N && 1 <= y - d1 && y + d2 <= N) solve(x, y, d1, d2);

                    }
                }
            }
        }

        System.out.println(ans);
    }

    static void solve(int x, int y, int d1, int d2) {
        boolean border[][] = new boolean[N + 1][N + 1];

        for (int i = 0; i <= d1; i++) {
            border[x + i][y - i] = true;
            border[x + d2 + i][y + d2 - i] = true;
        }

        for (int i = 0; i <= d2; i++) {
            border[x + i][y + i] = true;
            border[x + d1 + i][y - d1 + i] = true;
        }

        int sumPop[] = new int[5];

        // 1
        for (int i = 1; i < x + d1; i++) {
            for (int j = 1; j <= y; j++) {
                if (border[i][j]) break;
                sumPop[0] += pop[i][j];
            }
        }

        // 2
        for (int i = 1; i <= x + d2; i++) {
            for (int j = N; j > y; j--) {
                if (border[i][j]) break;
                sumPop[1] += pop[i][j];
            }
        }

        // 3
        for (int i = x + d1; i <= N; i++) {
            for (int j = 1; j < y - d1 + d2; j++) {
                if (border[i][j]) break;
                sumPop[2] += pop[i][j];
            }
        }

        // 4
        for (int i = N; i > x + d2; i--) {
            for (int j = N; j >= y - d1 + d2; j--) {
                if (border[i][j]) break;
                sumPop[3] += pop[i][j];
            }
        }

        sumPop[4] = totalPop;

        for (int i = 0; i < 4; i++) sumPop[4] -= sumPop[i];

        Arrays.sort(sumPop);

        int diff = sumPop[4] - sumPop[0];

        if (ans > diff) ans = diff;
    }
}
