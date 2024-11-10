import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, ans[];
    static List<Integer> list[];
    static boolean visited[];

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            list[s].add(e);
            list[e].add(s);
        }

        ans = new int[N + 1];

        visited = new boolean[N + 1];

        solve();

        System.out.println(sb);
    }

    static void solve() {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (visited[cur]) continue;
            visited[cur] = true;

            for (int next : list[cur]) {
                if (visited[next]) continue;

                ans[next] = cur;
                q.add(next);
            }
        }

        for (int i = 2; i <= N; i++) sb.append(ans[i]+"\n");
    }
}