import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, ans;
    static char[][] map;
    static boolean[][] visited;

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) map[i][j] = input.charAt(j);
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'W') continue;

                visited = new boolean[N][M];

                bfs(i, j);
            }
        }
    }

    static void bfs(int y, int x) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(y, x, 0));

        visited[y][x] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (ans < cur.t) ans = cur.t;

            for (int k = 0; k < 4; k++) {
                int ny = cur.y + dy[k];
                int nx = cur.x + dx[k];

                if (!inRange(ny, nx) || map[ny][nx] == 'W' || visited[ny][nx]) continue;

                visited[ny][nx] = true;
                q.add(new Node(ny, nx, cur.t + 1));
            }
        }
    }

    static boolean inRange(int y, int x) {
        return (0 <= y && y < N) && (0 <= x && x < M);
    }
}

class Node {
    int y, x, t;

    Node(int y, int x, int t) {
        this.y = y;
        this.x = x;
        this.t = t;
    }
}