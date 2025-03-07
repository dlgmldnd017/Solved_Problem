import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] arr, prefixSum;
    static long ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String input = br.readLine();
            for (int j = 1; j <= M; j++) arr[i][j] = input.charAt(j - 1) - '0';
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        prefixSum = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                prefixSum[i][j] = arr[i][j] + prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1];
            }
        }

        // 가로 고정
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                // 위
                long a = getSum(1, 1, i, M);
                long b = getSum(i + 1, 1, N, j);
                long c = getSum(i + 1, j + 1, N, M);
                ans = Math.max(ans, a * b * c);

                // 아래
                a = getSum(1, 1, i, j);
                b = getSum(1, j + 1, i, M);
                c = getSum(i + 1, 1, N, M);
                ans = Math.max(ans, a * b * c);
            }
        }

        // 세로 고정
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {

                // 왼쪽
                long a = getSum(1, 1, N, i);
                long b = getSum(1, i + 1, j, M);
                long c = getSum(j + 1, i + 1, N, M);
                ans = Math.max(ans, a * b * c);

                // 오른쪽
                a = getSum(1, 1, j, i);
                b = getSum(j + 1, 1, N, i);
                c = getSum(1, i + 1, N, M);
                ans = Math.max(ans, a * b * c);
            }
        }

        // 가로 3개
        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                long a = getSum(1, 1, i, M);
                long b = getSum(i + 1, 1, j, M);
                long c = getSum(j + 1, 1, N, M);
                ans = Math.max(ans, a * b * c);
            }
        }

        // 세로로 3개
        for (int i = 1; i < M; i++) {
            for (int j = i + 1; j < M; j++) {
                long a = getSum(1, 1, N, i);
                long b = getSum(1, i + 1, N, j);
                long c = getSum(1, j + 1, N, M);
                ans = Math.max(ans, a * b * c);
            }
        }
    }

    static long getSum(int y1, int x1, int y2, int x2) {
        return prefixSum[y2][x2] - prefixSum[y2][x1 - 1] - prefixSum[y1 - 1][x2] + prefixSum[y1 - 1][x1 - 1];
    }
}