import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, N, X, Y, ans;
    static boolean visited[];
    static List<Node> store;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            store = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                store.add(new Node(x, y, 0));
            }

            st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());

            store.add(new Node(X, Y, 0));

            ans = 0;

            visited = new boolean[store.size()];

            solve(startX, startY);

            if (ans == 0) sb.append("sad\n");
            else sb.append("happy\n");
        }

        System.out.println(sb);
    }

    static void solve(int startX, int startY) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(startX, startY, 20));

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < store.size(); i++) {
                if (visited[i]) continue;

                Node next = store.get(i);
                if ((Math.abs(cur.x - next.x) + Math.abs(cur.y - next.y)) > cur.cnt * 50) continue;

                if (next.x == X && next.y == Y) {
                    ans = 1;
                    return;
                }

                visited[i] = true;
                q.add(new Node(next.x, next.y, 20));
            }
        }
    }
}

class Node {
    int x, y, cnt;

    Node(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}