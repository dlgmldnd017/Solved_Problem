import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] max;
    static boolean[] result;
    static boolean[][] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        max = new int[3];

        st = new StringTokenizer(br.readLine());
        max[0] = Integer.parseInt(st.nextToken());
        max[1] = Integer.parseInt(st.nextToken());
        max[2] = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(sb);
    }

    static void solve() {
        result = new boolean[201];

        visited = new boolean[201][201];

        dfs(0, 0, max[2]);

        for (int i = 0; i <= 200; i++) {
            if (result[i]) sb.append(i).append(" ");
        }
    }

    static void dfs(int a, int b, int c) {
        if (visited[a][b]) return;
        visited[a][b] = true;

        if (a == 0) result[c] = true;

        int[] cur = {a, b, c};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) continue;

                int[] next = cur.clone();

                int amount = Math.min(cur[i], max[j] - cur[j]);

                next[i] -= amount;
                next[j] += amount;

                dfs(next[0], next[1], next[2]);
            }
        }
    }
}