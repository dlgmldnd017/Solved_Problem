import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long len[], cost[], ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        len = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) len[i] = Long.parseLong(st.nextToken());

        cost = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) cost[i] = Long.parseLong(st.nextToken());

        ans = Long.MAX_VALUE;

        solve();

        System.out.println(ans);
    }

    static void solve() {
        ans = cost[0] * len[0];
        long min = Integer.MAX_VALUE;

        for (int i = 1; i < N; i++) {
            min = Math.min(min, Math.min(cost[i - 1], cost[i]));
            ans += min * len[i];
        }
    }
}