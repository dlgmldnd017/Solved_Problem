import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static long arr[], ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N];

        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        solve();

        System.out.println(ans);
    }

    static void solve() {
        long low = 1, high = arr[N - 1];

        while (low <= high) {
            long mid = (low + high) / 2;

            int cnt = getCnt(mid);

            if (cnt < K) {
                high = mid - 1;
            } else {
                low = mid + 1;
                ans = mid;
            }
        }
    }

    static int getCnt(long mid) {
        int cnt = 0;

        for (long i : arr) {
            if (i / mid > 0) cnt += i / mid;

            if (cnt >= K) break;
        }
        return cnt;
    }
}