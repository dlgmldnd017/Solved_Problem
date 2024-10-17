import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, parent[];
    static List<Integer> list[];

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        parent = new int[N + 1];

        list = new ArrayList[N + 1];

        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        solve(1);

        for (int i = 2; i < N + 1; i++) sb.append(parent[i] + "\n");

        System.out.println(sb);
    }

    static void solve(int start) {
        boolean visited[] = new boolean[N + 1];
        visited[start] = true;

        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : list[cur]) {
                if (visited[next]) continue;

                q.add(next);
                visited[next] = true;

                parent[next] = cur;
            }
        }
    }
}