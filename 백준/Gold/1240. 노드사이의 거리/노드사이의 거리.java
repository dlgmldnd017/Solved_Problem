import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, ans;
    static List<Node> list[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, w));
            list[b].add(new Node(a, w));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            solve(a, b);

            sb.append(ans + "\n");
            ans = 0;
        }

        System.out.println(sb);
    }

    static void solve(int start, int end) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(start, 0));

        boolean visited[] = new boolean[N + 1];

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (visited[cur.e]) continue;
            visited[cur.e] = true;

            for (Node next : list[cur.e]) {
                if (visited[next.e]) continue;

                if (next.e == end) {
                    ans = cur.w + next.w;
                    return;
                }

                q.offer(new Node(next.e, cur.w + next.w));
            }
        }
    }
}

class Node {
    int e, w;

    Node(int e, int w) {
        this.e = e;
        this.w = w;
    }
}