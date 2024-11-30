import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, arr[][], ans;

    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int i = 0;

        while (true) {
            int cnt = checkArea();

            if (cnt == 0) return;
            else if (cnt >= 2) {
                ans = i;
                return;
            }

            i++;
        }
    }

    static int checkArea() {
        int cnt = 0;

        boolean visited[][] = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0 || visited[i][j]) continue;

                bfs(i, j, visited);
                cnt++;
            }
        }

        return cnt;
    }

    static void bfs(int y, int x, boolean visited[][]) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(y, x));

        visited[y][x] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            int cnt = 0;

            for (int k = 0; k < 4; k++) {
                int ny = dy[k] + cur.y;
                int nx = dx[k] + cur.x;

                if (!inRange(ny, nx) || visited[ny][nx]) continue;

                if (arr[ny][nx] == 0) {
                    cnt++;
                    continue;
                }

                visited[ny][nx] = true;
                q.add(new Node(ny, nx));
            }

            if (arr[cur.y][cur.x] - cnt < 0) arr[cur.y][cur.x] = 0;
            else arr[cur.y][cur.x] -= cnt;
        }
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < M);
    }
}

class Node {
    int y, x;

    Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}