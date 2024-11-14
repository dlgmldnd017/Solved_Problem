import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, arr[];
    static long ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        solve();

        System.out.println(ans);
    }

    static void solve() {
        long low = 0, high = (long) arr[N - 1] * M;

        while (low <= high) {
            long mid = (low + high) / 2;

            long cnt = getCnt(mid);

            if (cnt >= M) {
                ans = mid;
                high = mid - 1;
            } else low = mid + 1;
        }
    }

    static long getCnt(long mid) {
        long cnt = 0;

        for (int i = 0; i < N; i++) {
            cnt += mid / arr[i];

            if (cnt >= M) break;
        }

        return cnt;
    }
}
