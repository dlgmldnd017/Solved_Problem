import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, time[], indegree[], dp[], ans;
    static List<Integer> list[];
    static Queue<Integer> q = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        time = new int[N + 1];
        indegree = new int[N + 1];
        dp = new int[N + 1];

        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());

            int cnt = Integer.parseInt(st.nextToken());

            if (cnt == 0) {
                q.add(i);
                dp[i] = time[i];
            }

            for (int j = 0; j < cnt; j++) {
                int taskNum = Integer.parseInt(st.nextToken());

                list[taskNum].add(i);
                indegree[i]++;
            }
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : list[cur]) {
                indegree[next]--;
                dp[next] = Math.max(dp[next], dp[cur] + time[next]);

                if (indegree[next] == 0) q.add(next);
            }
        }

        for (int i = 1; i <= N; i++) ans = Math.max(ans, dp[i]);
    }
}