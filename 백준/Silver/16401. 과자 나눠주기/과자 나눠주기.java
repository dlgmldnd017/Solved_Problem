import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M, N;
    static long arr[], ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new long[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        solve();

        System.out.println(ans);
    }

    static void solve() {
        long low = 1, high = arr[M - 1];

        while (low <= high) {
            long mid = (low + high) / 2;

            int cnt = getCnt(mid);

            if (cnt < N) {
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

            if (cnt > N) break;
        }

        return cnt;
    }
}