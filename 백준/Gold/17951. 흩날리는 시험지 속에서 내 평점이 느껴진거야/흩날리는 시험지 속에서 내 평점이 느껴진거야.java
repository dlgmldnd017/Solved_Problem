import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, ans;
    static int[] arr;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int l = 0, r = 2_000_000;

        while (l <= r) {
            int mid = (l + r) >>> 1;

            if (K >= check(mid)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
    }

    static int check(int mid) {
        int cnt = 1, sum = 0;

        for (int i = 0; i < N; i++) {
            sum += arr[i];

            if (sum > mid) {
                sum = 0;
                cnt++;
            }
        }

        return cnt;
    }
}