import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int S, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = Integer.parseInt(br.readLine());

        solve();

        System.out.println(ans);
    }

    static void solve() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(1, 0, 0));

        boolean[][] visited = new boolean[2001][2001];
        visited[1][0] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.screen == S) {
                ans = cur.time;
                return;
            }

            if (!visited[cur.screen][cur.screen]) {
                visited[cur.screen][cur.screen] = true;
                q.add(new Node(cur.screen, cur.screen, cur.time + 1));
            }

            if (cur.clipboard > 0 && cur.screen + cur.clipboard < 2000 && !visited[cur.screen + cur.clipboard][cur.clipboard]) {
                visited[cur.screen + cur.clipboard][cur.clipboard] = true;
                q.add(new Node(cur.screen + cur.clipboard, cur.clipboard, cur.time + 1));
            }

            if (cur.screen > 0 && !visited[cur.screen - 1][cur.clipboard]) {
                visited[cur.screen - 1][cur.clipboard] = true;
                q.add(new Node(cur.screen - 1, cur.clipboard, cur.time + 1));
            }
        }
    }
}

class Node {
    int screen, clipboard, time;

    public Node(int screen, int clipboard, int time) {
        this.screen = screen;
        this.clipboard = clipboard;
        this.time = time;
    }
}