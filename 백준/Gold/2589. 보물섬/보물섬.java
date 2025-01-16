import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, ans;
    static char ch[][];
    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ch = new char[N][M];

        for (int i = 0; i < N; i++) {
            ch[i] = br.readLine().toCharArray();
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (ch[i][j] == 'W') continue;

                bfs(i, j);
            }
        }
    }

    static void bfs(int y, int x) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(y, x, 0));

        boolean visited[][] = new boolean[N][M];

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (visited[cur.y][cur.x]) continue;
            visited[cur.y][cur.x] = true;

            if (ans < cur.cnt) ans = cur.cnt;

            for (int k = 0; k < 4; k++) {
                int ny = cur.y + dy[k];
                int nx = cur.x + dx[k];

                if (!inRange(ny, nx) || visited[ny][nx] || ch[ny][nx] == 'W') continue;

                q.add(new Node(ny, nx, cur.cnt + 1));
            }
        }
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < M);
    }
}

class Node {
    int y, x, cnt;

    Node(int y, int x, int cnt) {
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }
}