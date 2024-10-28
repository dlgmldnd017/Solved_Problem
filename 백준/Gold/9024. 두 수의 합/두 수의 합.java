import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int T, N, K, arr[], ans;

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

            Arrays.sort(arr);

            ans = 0;

            solve();

            sb.append(ans + "\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        int min = Integer.MAX_VALUE;
        int low = 0, high = N - 1;

        while (low < high) {
            int sum = arr[low] + arr[high];
            int diff = Math.abs(sum - K);

            if (min >= diff) {
                if (min > diff) ans = 0;
                min = diff;
                ans++;
            }

            if (sum >= K) high--;
            else low++;
        }
    }
}