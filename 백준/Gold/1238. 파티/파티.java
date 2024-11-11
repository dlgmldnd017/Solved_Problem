import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

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

public class Main {
    static int N, M, X, ans;
    static List<Node> list1[], list2[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        list1 = new ArrayList[N + 1];
        list2 = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            list1[i] = new ArrayList<>();
            list2[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());

            list1[A].add(new Node(B, T));
            list2[B].add(new Node(A, T));
        }

        int dist1[] = solve(list1);
        int dist2[] = solve(list2);

        ans = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            int sum = dist1[i] + dist2[i];

            if(ans < sum) ans = sum;
        }

        System.out.println(ans);
    }

    static int[] solve(List<Node> list[]) {
        int dist[] = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[X] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(X, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (Node next : list[cur.e]) {
                if (dist[next.e] > dist[cur.e] + next.w) {
                    dist[next.e] = dist[cur.e] + next.w;
                    pq.add(new Node(next.e, dist[next.e]));
                }
            }
        }

        return dist;
    }
}