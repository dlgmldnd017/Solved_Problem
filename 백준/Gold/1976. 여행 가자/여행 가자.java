import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, arr[];
    static List<Integer> list[];
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();

        M = Integer.parseInt(br.readLine());

        arr = new int[M];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int k = Integer.parseInt(st.nextToken());

                if (k == 0) continue;

                list[i].add(j);
                list[j].add(i);
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) arr[i] = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];

        if (N == 1) {
            System.out.println("YES");
            return;
        }

        solve(arr[0]);

        for (int i = 0; i < M; i++) {
            if (visited[arr[i]]) continue;

            System.out.println("NO");
            return;
        }

        System.out.println("YES");
    }

    static void solve(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);

        visited[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : list[cur]) {
                if (visited[next]) continue;
                visited[next] = true;

                q.add(next);
            }
        }
    }
}
