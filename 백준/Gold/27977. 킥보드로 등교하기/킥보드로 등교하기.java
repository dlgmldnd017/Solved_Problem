import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int L, N, K, ans;
    static int[] arr;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N + 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());
        arr[N + 1] = L;

        solve();

        System.out.println(ans);
    }

    static void solve() {
        if (K == 0) {
            ans = L;
            return;
        }

        int left = 0, right = L;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (check(mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    }

    static boolean check(int mid) {
        int i = 0, j = 1, cnt = 0;

        while (j != N + 2) {
            int diff = arr[j] - arr[i];

            if (diff > mid) {
                if (j - i == 1) return false;
                i = j - 1;
                cnt++;
            } else j++;
        }

        return cnt <= K;
    }
}