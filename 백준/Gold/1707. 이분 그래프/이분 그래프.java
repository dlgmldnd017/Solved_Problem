import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int E, C;

    Node(int E, int C) {
        this.E = E;
        this.C = C;
    }
}

public class Main {
    static int K, V, E, color[], ans;
    static List<Node> list[];
    static boolean visited[];

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());

        for (int k = 1; k <= K; k++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            list = new ArrayList[V + 1];

            for (int i = 0; i < V + 1; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                list[u].add(new Node(v, -1));
                list[v].add(new Node(u, -1));
            }

            color = new int[V + 1];

            visited = new boolean[V + 1];

            ans = 0;

            for (int i = 1; i < V + 1; i++) {
                if (!visited[i]) solve(i);

                for (Node n : list[i]) {
                    if (color[n.E] == color[i]) {
                        ans = 1;
                        break;
                    }
                }

                if (ans == 1) break;
            }

            if (ans == 1) sb.append("NO");
            else sb.append("YES");

            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void solve(int start) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(start, 1));

        while (!q.isEmpty()) {
            Node cur = q.poll();

            color[cur.E] = cur.C;

            if (visited[cur.E]) continue;
            visited[cur.E] = true;

            for (Node n : list[cur.E]) {
                if (visited[n.E]) continue;

                if (cur.C == 1) q.add(new Node(n.E, 2));
                else q.add(new Node(n.E, 1));
            }
        }
    }
}