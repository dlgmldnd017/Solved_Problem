import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static int N, arr[];
    static boolean visited[], cycle[];
    static List<Integer> result = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        cycle = new boolean[N + 1];

        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(br.readLine());

        solve();

        System.out.println(sb);
    }

    static void solve() {
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            dfs(i, i);
        }

        Collections.sort(result);
        sb.append(result.size()).append("\n");
        for (int i : result) sb.append(i).append("\n");
    }

    static void dfs(int start, int current) {
        if (!visited[current]) {
            visited[current] = true;
            dfs(start, arr[current]);
        } else if (current == start) result.add(start);
    }
}