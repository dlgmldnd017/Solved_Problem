import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, wallCnt, ans;
    static int[][] arr;
    static List<Node> list = new ArrayList<>();

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] == 0) wallCnt++;
            }
        }

        ans = Integer.MAX_VALUE;

        solve();

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void solve() {
        dfs(0, 0, 0);
    }

    static void dfs(int depth, int y, int x) {
        if (depth == M) {
            check();
            return;
        }

        if (x == N) {
            dfs(depth, y + 1, 0);
            return;
        }

        if (y == N) return;

        if (arr[y][x] == 2) {
            list.add(new Node(y, x, 0));
            dfs(depth + 1, y, x + 1);
            list.remove(list.size() - 1);
        }

        dfs(depth, y, x + 1);
    }

    static void check() {
        boolean[][] visited = new boolean[N][N];

        for (Node n : list) visited[n.y][n.x] = true;

        Queue<Node> pq = new ArrayDeque<>(list);

        int max = 0, cnt = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (arr[cur.y][cur.x] == 0) cnt++;

            if (arr[cur.y][cur.x] != 2 && max < cur.t) max = cur.t;

            for (int k = 0; k < 4; k++) {
                int ny = cur.y + dy[k];
                int nx = cur.x + dx[k];

                if (!inRange(ny, nx) || visited[ny][nx] || arr[ny][nx] == 1) continue;

                visited[ny][nx] = true;
                pq.add(new Node(ny, nx, cur.t + 1));
            }
        }

        if (wallCnt == cnt && ans > max) ans = max;
    }

    static boolean inRange(int y, int x) {
        return (0 <= y && y < N) && (0 <= x && x < N);
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