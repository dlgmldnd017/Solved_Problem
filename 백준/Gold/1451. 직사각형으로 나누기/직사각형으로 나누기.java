import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, arr[][], prefixSum[][];
    static long ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= M; j++) arr[i][j] = str.charAt(j - 1) - '0';
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        // 0. 누적합 구하기
        prefixSum = new int[N + 1][M + 1];
        calcPreSum();

        // 1. 가로로 3개 나누기
        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                long a = getSum(1, 1, i, M);
                long b = getSum(i + 1, 1, j, M);
                long c = getSum(j + 1, 1, N, M);
                ans = Math.max(ans, a * b * c);
            }
        }

        // 2. 세로로 3개 나누기
        for (int i = 1; i < M; i++) {
            for (int j = i + 1; j < M; j++) {
                long a = getSum(1, 1, N, i);
                long b = getSum(1, i + 1, N, j);
                long c = getSum(1, j + 1, N, M);
                ans = Math.max(ans, a * b * c);
            }
        }

        // 3. 가로 1개, 세로 2개 나누기
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                long a = getSum(1, 1, i, M);
                long b = getSum(i + 1, 1, N, j);
                long c = getSum(i + 1, j + 1, N, M);
                ans = Math.max(ans, a * b * c);

                a = getSum(1, 1, i, j);
                b = getSum(1, j + 1, i, M);
                c = getSum(i + 1, 1, N, M);
                ans = Math.max(ans, a * b * c);
            }
        }

        // 4. 세로 1개, 가로 2개 나누기
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                long a = getSum(1, 1, N, i);
                long b = getSum(1, i + 1, j, M);
                long c = getSum(j + 1, i + 1, N, M);
                ans = Math.max(ans, a * b * c);

                a = getSum(1, 1, j, i);
                b = getSum(j + 1, 1, N, i);
                c = getSum(1, i + 1, N, M);
                ans = Math.max(ans, a * b * c);
            }
        }
    }

    static void calcPreSum() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                prefixSum[i][j] = arr[i][j]
                        + prefixSum[i - 1][j]
                        + prefixSum[i][j - 1]
                        - prefixSum[i - 1][j - 1];
            }
        }
    }

    static long getSum(int x1, int y1, int x2, int y2) {
        return prefixSum[x2][y2]
                - prefixSum[x1 - 1][y2]
                - prefixSum[x2][y1 - 1]
                + prefixSum[x1 - 1][y1 - 1];
    }
}