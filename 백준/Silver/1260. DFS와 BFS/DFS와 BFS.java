import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int E;

    Node(int E) {
        this.E = E;
    }
}

public class Main {
    static int N, M, V, map[][];
    static boolean visited[];

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            map[A][B] = 1;
            map[B][A] = 1;
        }

        solve();

        System.out.println(sb);
    }

    static void solve() {
        visited = new boolean[N + 1];
        dfs(0, V);

        sb.append("\n");

        visited = new boolean[N + 1];
        bfs();
    }

    static void dfs(int depth, int curV) {
        if (depth == N) return;

        if (visited[curV]) return;
        visited[curV] = true;

        sb.append(curV + " ");

        for (int i = 1; i <= N; i++) {
            if (visited[i] || map[curV][i] == 0) continue;
            dfs(depth + 1, i);
        }
    }

    static void bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(V));

        while (!q.isEmpty()) {
            Node curNode = q.poll();

            if (visited[curNode.E]) continue;
            visited[curNode.E] = true;

            sb.append(curNode.E + " ");

            for (int i = 1; i <= N; i++) {
                if (visited[i] || map[curNode.E][i] == 0) continue;
                q.add(new Node(i));
            }
        }
    }
}

