import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int K, N;
    static long arr[], ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new long[K];

        for (int i = 0; i < K; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(arr);

        solve();

        System.out.println(ans);
    }

    static void solve() {
        long low = 1, high = arr[K - 1];

        while (low <= high) {
            long mid = (low + high) / 2;

            if (getLANCnt(mid) >= N) {
                if (ans < mid) ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
    }

    static long getLANCnt(long size) {
        if (size == 0) return K;

        long cnt = 0;

        for (int i = 0; i < K; i++) {
            cnt += arr[i] / size;
        }

        return cnt;
    }
}