import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static long arr[], ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        arr = new long[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        solve();

        System.out.println(ans);
    }

    static void solve() {
        long diffArr[] = new long[N - 1];

        for (int i = 0; i < N - 1; i++) {
            diffArr[i] = arr[i + 1] - arr[i];
        }

        Arrays.sort(diffArr);

        for (int i = 0; i < N - K; i++) {
            ans += diffArr[i];
        }
    }
}