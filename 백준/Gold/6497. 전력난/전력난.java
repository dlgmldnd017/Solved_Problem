import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M, N, ans;
    static List<Node> list[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            if (M == 0 && N == 0) {
                System.out.println(sb);
                return;
            }

            list = new ArrayList[M];

            for (int i = 0; i < M; i++) list[i] = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                list[x].add(new Node(y, z));
                list[y].add(new Node(x, z));

                ans += z;
            }

            solve();

            ans = 0;
        }
    }

    static void solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));

        boolean[] visited = new boolean[M];

        int[] cost = new int[M];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[0] = 0;

        int sum = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.e]) continue;
            visited[cur.e] = true;

            sum += cur.c;

            for (Node next : list[cur.e]) {
                if (visited[next.e] || cost[next.e] < next.c) continue;

                cost[next.e] = next.c;
                pq.add(new Node(next.e, next.c));
            }
        }

        sb.append(ans - sum).append("\n");
    }
}

class Node implements Comparable<Node> {
    int e, c;

    Node(int e, int c) {
        this.e = e;
        this.c = c;
    }

    public int compareTo(Node n) {
        return this.c - n.c;
    }
}