import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, ans;
    static List<Integer> list1[], list2[];

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        list1 = new ArrayList[N + 1];
        list2 = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            list1[i] = new ArrayList<>();
            list2[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list1[a].add(b);
            list2[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            boolean visited[] = new boolean[N + 1];
            ans = 0;

            solve(i, visited, list1);

            visited[i] = false;
            solve(i, visited, list2);

            for (boolean b : visited) {
                if (!b) ans++;
            }

            sb.append(ans - 1 + "\n");
        }

        System.out.println(sb);
    }

    static void solve(int start, boolean visited[], List<Integer> list[]) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (visited[cur]) continue;
            visited[cur] = true;

            for (int next : list[cur]) {
                if (visited[next]) continue;
                q.add(next);
            }
        }
    }
}