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
        int[] left = new int[N];
        ans = left[0] = arr[0];

        for (int i = 1; i < N; i++) {
            left[i] = Math.max(left[i - 1] + arr[i], arr[i]);

            // 카데인 알고리즘
            ans = Math.max(ans, left[i]);
        }

        int[] right = new int[N];
        right[N - 1] = arr[N - 1];

        for (int i = N - 2; i >= 0; i--) right[i] = Math.max(right[i + 1] + arr[i], arr[i]);

        // 하나의 수 제거
        for (int i = 1; i < N - 1; i++) {
            int tmp = left[i - 1] + right[i + 1];

            ans = Math.max(ans, tmp);
        }
    }
}