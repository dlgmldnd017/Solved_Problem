import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int[] max;
    static boolean[][] visited;
    static TreeSet<Integer> set = new TreeSet<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        max = new int[3];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) max[i] = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(sb);
    }

    static void solve() {
        visited = new boolean[201][201];

        dfs(0, 0, max[2]);

        for (int i : set) sb.append(i).append(" ");
    }

    static void dfs(int A, int B, int C) {
        if (visited[A][B]) return;
        visited[A][B] = true;

        if (A == 0 || C == max[2]) set.add(C);

        int[] tmp1 = {A, B, C};

        for (int i = 0; i < 3; i++) {
            if (tmp1[i] == 0) continue;

            for (int j = 0; j < 3; j++) {
                if (i == j) continue;

                int min = Math.min(tmp1[i], max[j] - tmp1[j]);

                int[] tmp2 = tmp1.clone();
                tmp2[i] -= min;
                tmp2[j] += min;

                dfs(tmp2[0], tmp2[1], tmp2[2]);
            }
        }
    }
}