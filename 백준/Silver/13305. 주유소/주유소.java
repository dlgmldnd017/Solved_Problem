import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, len[], cost[], ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        len = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) len[i] = Integer.parseInt(st.nextToken());

        cost = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) cost[i] = Integer.parseInt(st.nextToken());

        ans = Integer.MAX_VALUE;

        solve();

        System.out.println(ans);
    }

    static void solve() {
        ans = cost[0] * len[0];
        int min = Integer.MAX_VALUE;

        for (int i = 1; i < N; i++) {
            min = Math.min(min, Math.min(cost[i - 1], cost[i]));
            ans += min * len[i];
        }
    }
}