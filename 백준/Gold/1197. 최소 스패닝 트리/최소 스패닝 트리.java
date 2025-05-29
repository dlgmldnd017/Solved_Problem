import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V, E;
    static int[] arr;
    static long ans;
    static List<Node>[] list;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        arr = new int[V + 1];
        list = new ArrayList[V + 1];

        for (int i = 1; i <= V; i++) {
            arr[i] = Integer.MAX_VALUE;
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[y].add(new Node(x, c));
            list[x].add(new Node(y, c));
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));

        boolean[] visited = new boolean[V + 1];

        arr[1] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.e]) continue;
            visited[cur.e] = true;

            ans += cur.c;

            for (Node next : list[cur.e]) {
                if (visited[next.e] || arr[next.e] < next.c) continue;

                arr[next.e] = next.c;
                pq.add(new Node(next.e, next.c));
            }
        }
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