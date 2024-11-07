import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, arr[];
    static long ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        long sum = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        Arrays.sort(arr);

        M = Integer.parseInt(br.readLine());

        if (M >= sum) ans = arr[N - 1];
        else solve();

        System.out.println(ans);
    }

    static void solve() {
        long low = 1, high = M;

        while (low <= high) {
            long mid = (low + high) / 2;

            long sum = getSum(mid);

            if (M < sum) {
                high = mid - 1;
            } else {
                low = mid + 1;
                ans = mid;
            }
        }
    }

    static long getSum(long mid) {
        long sum = 0;

        for (int i : arr) {
            if (i <= mid) sum += i;
            else sum += mid;

            if (sum > M) break;
        }

        return sum;
    }
}