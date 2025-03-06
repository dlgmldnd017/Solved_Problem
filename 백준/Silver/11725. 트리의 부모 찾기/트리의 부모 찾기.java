import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] parent;
    static List<Integer> list[];
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        parent = new int[N + 1];

        visited = new boolean[N + 1];

        solve();

        System.out.println(sb);
    }

    static void solve() {
        visited[1] = true;

        dfs(1);

        for (int i = 2; i <= N; i++) sb.append(parent[i]).append("\n");
    }

    static void dfs(int cur) {
        for (int next : list[cur]) {
            if (visited[next]) continue;
            visited[next] = true;
            parent[next] = cur;

            dfs(next);
        }
    }
}