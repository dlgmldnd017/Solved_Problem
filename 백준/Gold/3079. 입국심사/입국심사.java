import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static long arr[], ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new long[N];

        for (int i = 0; i < N; i++) arr[i] = Long.parseLong(br.readLine());

        Arrays.sort(arr);

        solve();

        System.out.println(ans);
    }

    static void solve() {
        long low = 1, high = M * arr[N - 1];

        while (low <= high) {
            long mid = (low + high) / 2;

            long cnt = canHowManyPeople(mid);

            if (M > cnt) low = mid + 1;
            else {
                ans = mid;
                high = mid - 1;
            }
        }
    }

    static long canHowManyPeople(long mid) {
        long cnt = 0;

        for (long i : arr) {
            if (M < cnt) break;
            cnt += mid / i;
        }

        return cnt;
    }
}