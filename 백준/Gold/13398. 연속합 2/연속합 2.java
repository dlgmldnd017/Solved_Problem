import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, ans;
    static int[] arr;

    public static void main(String args[]) throws Exception {
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
        // 오른쪽 방향
        int[] dp1 = new int[N];
        ans = dp1[0] = arr[0];
        
        for (int i = 1; i < N; i++) {
            dp1[i] = Math.max(dp1[i - 1] + arr[i], arr[i]);
            ans = Math.max(ans, dp1[i]);
        }

        // 왼쪽 방향
        int[] dp2 = new int[N];
        dp2[N - 1] = arr[N - 1];

        for (int i = N - 2; i >= 0; i--) dp2[i] = Math.max(dp2[i + 1] + arr[i], arr[i]);

        // 하나의 수 제거
        for (int i = 1; i < N - 1; i++) {
            int tmp = dp1[i - 1] + dp2[i + 1];

            ans = Math.max(ans, tmp);
        }
    }
}