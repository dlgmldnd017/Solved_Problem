import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] color;
    static boolean[] visited;
    static List<Node> list[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            list = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                list[a].add(new Node(b, -1));
                list[b].add(new Node(a, -1));
            }

            color = new int[N + 1];

            visited = new boolean[N + 1];

            solve();
        }

        System.out.println(sb);
    }

    static void solve() {
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) bfs(i);

            for (Node n : list[i]) {
                if (color[i] != color[n.e]) continue;
                sb.append("impossible").append("\n");
                return;
            }
        }

        sb.append("possible").append("\n");
    }

    static void bfs(int start) {
        visited[start] = true;

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(start, 1));

        while (!q.isEmpty()) {
            Node cur = q.poll();

            color[cur.e] = cur.c;

            for (Node next : list[cur.e]) {
                if (visited[next.e]) continue;
                visited[next.e] = true;

                q.add(new Node(next.e, cur.c == 1 ? 2 : 1));
            }
        }
    }
}

class Node {
    int e, c;

    Node(int e, int c) {
        this.e = e;
        this.c = c;
    }
}