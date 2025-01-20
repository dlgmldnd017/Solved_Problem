import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, E, v1, v2, ans;
    static List<Node> list[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, w));
            list[b].add(new Node(a, w));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        if (E == 0) ans = -1;
        else solve();

        System.out.println(ans);
    }

    static void solve() {
        int a1 = bfs(1, v1);
        int b1 = bfs(v1, v2);
        int c1 = bfs(v2, N);

        if (a1 == Integer.MAX_VALUE || b1 == Integer.MAX_VALUE || c1 == Integer.MAX_VALUE) {
            ans = -1;
            return;
        } else {
            ans = a1 + b1 + c1;
        }

        int a2 = bfs(1, v2);
        int b2 = bfs(v2, v1);
        int c2 = bfs(v1, N);

        if (ans > a2 + b2 + c2) ans = a2 + b2 + c2;
    }

    static int bfs(int start, int end) {
        int dist[] = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        boolean visited[] = new boolean[N + 1];

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.e]) continue;
            visited[cur.e] = true;

            for (Node next : list[cur.e]) {
                if (visited[next.e] || dist[next.e] <= dist[cur.e] + next.w) continue;

                dist[next.e] = dist[cur.e] + next.w;
                pq.add(new Node(next.e, dist[next.e]));
            }
        }
        return dist[end];
    }
}

class Node implements Comparable<Node> {
    int e, w;

    Node(int e, int w) {
        this.e = e;
        this.w = w;
    }

    public int compareTo(Node n) {
        return this.w - n.w;
    }
}