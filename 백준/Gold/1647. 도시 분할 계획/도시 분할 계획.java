import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, ans;
    static int[] arr;
    static List<Node>[] list;
    static List<Integer> cost = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.MAX_VALUE;
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
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
        pq.add(new Node(1, 0));

        boolean visited[] = new boolean[N + 1];

        arr[1] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.e]) continue;
            visited[cur.e] = true;

            cost.add(cur.c);

            for (Node n : list[cur.e]) {
                if (visited[n.e] || arr[n.e] < n.c) continue;

                arr[n.e] = n.c;
                pq.add(new Node(n.e, n.c));
            }
        }

        Collections.sort(cost);

        for (int i = 1; i < cost.size() - 1; i++) ans += cost.get(i);
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