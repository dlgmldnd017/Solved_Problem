import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<Integer> list = new ArrayList<>();
    static boolean visited[];

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];

        solve(0);

        System.out.println(sb);
    }

    static void solve(int depth) {
        if (depth == N) {
            for (int i : list) sb.append(i + " ");
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;

            list.add(i);
            visited[i] = true;
            solve(depth + 1);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }
}