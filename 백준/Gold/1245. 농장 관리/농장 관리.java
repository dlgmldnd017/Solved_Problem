import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, ans;
    static int[][] arr;
    static boolean[][] visited;

    static int[] dy = {0, 0, -1, 1, -1, -1, 1, 1};
    static int[] dx = {-1, 1, 0, 0, -1, 1, -1, 1};

    public static void main(String args[]) throws Exception {
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
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) continue;
                visited[i][j] = true;

                if (isPeak(i, j)) ans++;
            }
        }
    }

    static boolean isPeak(int y, int x) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(y, x));

        boolean flag = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int k = 0; k < 8; k++) {
                int ny = dy[k] + cur.y;
                int nx = dx[k] + cur.x;

                if (!inRange(ny, nx)) continue;

                if (arr[cur.y][cur.x] < arr[ny][nx]) flag = false;

                if (!visited[ny][nx] && arr[cur.y][cur.x] == arr[ny][nx]) {
                    visited[ny][nx] = true;
                    q.add(new Node(ny, nx));
                }
            }
        }

        return flag;
    }

    static boolean inRange(int y, int x) {
        return (0 <= y && y < N) && (0 <= x && x < M);
    }
}

class Node {
    int y, x;

    Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}