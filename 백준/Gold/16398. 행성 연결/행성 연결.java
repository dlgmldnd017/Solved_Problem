import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static int[][] cost;
    static long ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        cost = new int[N][N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i; j++) cost[j][i] = cost[i][j] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));

        boolean visited[] = new boolean[N];

        arr[0] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.e]) continue;
            visited[cur.e] = true;

            ans += arr[cur.e];

            for (int next = 0; next < N; next++) {
                int nextCost = cost[cur.e][next];

                if (nextCost == 0 || nextCost >= arr[next]) continue;

                arr[next] = nextCost;
                pq.add(new Node(next, nextCost));
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