import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int end, len;

    Node(int end, int len) {
        this.end = end;
        this.len = len;
    }
}

public class Main {
    static int N, D, dp[], ans;
    static List<Node> list[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        list = new ArrayList[D + 1];

        for (int i = 0; i <= D; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());

            if (end > D || end - start <= len) continue;
            list[start].add(new Node(end, len));
        }

        dp = new int[D + 1];

        for (int i = 0; i <= D; i++) dp[i] = i;

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int i = 0; i <= D; i++) {
            if (i != 0) dp[i] = Math.min(dp[i - 1] + 1, dp[i]);
            if (list[i].size() == 0) continue;

            for (Node n : list[i]) {
                if (dp[n.end] > dp[i] + n.len) dp[n.end] = dp[i] + n.len;
            }
        }

        ans = dp[D];
    }
}