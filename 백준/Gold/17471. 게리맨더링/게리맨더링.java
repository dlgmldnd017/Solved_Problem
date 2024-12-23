import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, pop[], ans;
    static List<Integer> link[], A, B;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        pop = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) pop[i] = Integer.parseInt(st.nextToken());

        link = new List[N + 1];

        for (int i = 1; i <= N; i++) link[i] = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());

            for (int j = 0; j < M; j++) {
                int V = Integer.parseInt(st.nextToken());

                link[i].add(V);
                link[V].add(i);
            }
        }

        A = new ArrayList<>();
        B = new ArrayList<>();

        ans = Integer.MAX_VALUE;

        solve(1);

        if (ans == Integer.MAX_VALUE) ans = -1;
        System.out.println(ans);
    }

    static void solve(int depth) {
        if (depth == N + 1) {
            if (A.isEmpty() || B.isEmpty()) return;

            if (!checkConnected(A) || !checkConnected(B)) return;

            int a = getSum(A);
            int b = getSum(B);
            int diff = Math.abs(a - b);

            if (ans > diff) ans = diff;

            return;
        }

        A.add(depth);
        solve(depth + 1);
        A.remove(A.size() - 1);

        B.add(depth);
        solve(depth + 1);
        B.remove(B.size() - 1);
    }

    static boolean checkConnected(List<Integer> list) {
        if (list.size() == 1) return true;

        Queue<Integer> q = new ArrayDeque<>();
        q.add(list.get(0));

        boolean visited[] = new boolean[N + 1];

        int cnt = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (visited[cur]) continue;
            visited[cur] = true;
            cnt++;

            if (cnt == list.size()) return true;

            for (int next : link[cur]) {
                if (visited[next] || !list.contains(next)) continue;

                q.add(next);
            }
        }

        return false;
    }

    static int getSum(List<Integer> list) {
        int sum = 0;

        for (int i : list) sum += pop[i];

        return sum;
    }
}