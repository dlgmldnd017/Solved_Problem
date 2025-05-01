import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int M, N, sum;
    static List<Node> list[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            if (M == 0 && N == 0) break;

            list = new ArrayList[M];

            for (int i = 0; i < M; i++) list[i] = new ArrayList<>();

            sum = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                list[x].add(new Node(y, z));
                list[y].add(new Node(x, z));

                sum += z;
            }

            solve();
        }

        System.out.println(sb);
    }

    static void solve() {
        int total = 0;

        boolean[] visited = new boolean[M];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.e]) continue;
            visited[cur.e] = true;

            total += cur.w;

            for (Node next : list[cur.e]) {
                if (visited[next.e]) continue;

                pq.add(new Node(next.e, next.w));
            }
        }
        
        sb.append(sum - total).append("\n");
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