import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int C, N, ans;
    static int[] dp;
    static List<Node> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            list.add(new Node(cost, num));
        }

        ans = Integer.MAX_VALUE;

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int max = C + 100;
        dp = new int[max + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (Node cur : list) {
            for (int i = cur.n; i <= max; i++) {
                if (dp[i - cur.n] != Integer.MAX_VALUE) dp[i] = Math.min(dp[i], dp[i - cur.n] + cur.c);
            }
        }

        for (int i = C; i <= max; i++) ans = Math.min(ans, dp[i]);
    }
}

class Node {
    int c, n;

    Node(int c, int n) {
        this.c = c;
        this.n = n;
    }
}