import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, C, ans;
    static int[] arr;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int l = 1, r = arr[N - 1];

        while (l <= r) {
            int mid = (l + r) >>> 1;

            if (C > check(mid)) {
                r = mid - 1;
            } else {
                ans = mid;
                l = mid + 1;
            }
        }
    }

    static int check(int mid) {
        int cnt = 1, prev = 0;

        for (int i = 1; i < N; i++) {
            if (arr[i] - arr[prev] >= mid) {
                prev = i;
                cnt++;
            }
        }

        return cnt;
    }
}