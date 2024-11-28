import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, ans;
    static List<Integer> list[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];

        for (int i = 1; i < N + 1; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);

        boolean visited[] = new boolean[N + 1];

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (visited[cur]) continue;
            visited[cur] = true;

            for (int next : list[cur]) {
                if (visited[next]) continue;

                q.add(next);
            }
        }

        for (int i = 2; i <= N; i++) {
            if (visited[i]) ans++;
        }
    }
}
