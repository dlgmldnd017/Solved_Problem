import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K, A[], min;
    static List<Integer> list[];
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) A[i] = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[v].add(w);
            list[w].add(v);
        }

        visited = new boolean[N + 1];

        int k = K;

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;

            min = 1_000_000;
            solve(i);
            k -= min;

            if (k < 0) break;
        }

        if (k < 0) System.out.println("Oh no");
        else System.out.println(K - k);
    }

    static void solve(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (visited[cur]) continue;
            visited[cur] = true;

            min = Math.min(min, A[cur]);

            for (int next : list[cur]) {
                if (visited[next]) continue;
                q.add(next);
            }
        }
    }
}

class Node implements Comparable<Node> {
    int idx, cost;

    Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }

    public int compareTo(Node n) {
        return this.cost - n.cost;
    }
}