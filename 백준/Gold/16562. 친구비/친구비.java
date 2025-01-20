import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K, ans;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static List<Integer> list[];
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Node(i, cost));
        }

        list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        visited = new boolean[N + 1];

        solve();

        System.out.println(ans == -1 ? "Oh no" : ans);
    }

    static void solve() {
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.idx]) continue;

            ans += cur.cost;

            Queue<Integer> q = new ArrayDeque<>();
            q.add(cur.idx);

            while (!q.isEmpty()) {
                int next1 = q.poll();

                if (visited[next1]) continue;
                visited[next1] = true;

                for (int next2 : list[next1]) {
                    if (visited[next2]) continue;
                    q.add(next2);
                }
            }
        }

        if (ans > K) ans = -1;
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