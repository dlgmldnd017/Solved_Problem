import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, arr[][], prefixSum[][], ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        prefixSum = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + arr[i][j];
            }
        }

        ans = Integer.MIN_VALUE;

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int k = 1; k <= N; k++) {
            for (int i = k; i <= N; i++) {
                for (int j = k; j <= N; j++) {
                    int sum = getSum(k, i, j);

                    if (ans < sum) ans = sum;
                }
            }
        }
    }

    static int getSum(int k, int i, int j) {
        return prefixSum[i][j] - prefixSum[i-k][j] - prefixSum[i][j-k] + prefixSum[i-k][j-k];
    }
}