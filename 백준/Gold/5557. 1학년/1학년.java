import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static long ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(ans);
    }

    static void solve() {
        // (2^63) - 1이므로, long 배열 타입 선언
        long[][] dp = new long[N][21];

        // 첫 번째 경우의 수 삽입
        dp[0][arr[0]] = 1;

        // 모든 경우의 수 구하기
        for (int i = 1; i < N - 1; i++) {

            // i는 현재 원소 값
            // j는 이전 결과 값 또는 이전 원소 값
            for (int j = 0; j <= 20; j++) {
                if (dp[i - 1][j] <= 0) continue;

                int plus = j + arr[i];
                int minus = j - arr[i];

                if (plus <= 20) dp[i][plus] += dp[i - 1][j];
                if (minus >= 0) dp[i][minus] += dp[i - 1][j];
            }
        }

        // 마지막 두 숫자 사이에 =을 넣어야 하므로, 정답은 아래와 같다.
        ans = dp[N - 2][arr[N - 1]];
    }
}