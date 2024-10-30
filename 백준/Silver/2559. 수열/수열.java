import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, arr[], ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        ans = Integer.MIN_VALUE;

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int i = 0, j = 0, sum = 0;

        while (i < (N - K + 1)) {
            while (j < K + i) sum += arr[j++];
            if (ans < sum) ans = sum;
            sum -= arr[i++];
        }
    }
}