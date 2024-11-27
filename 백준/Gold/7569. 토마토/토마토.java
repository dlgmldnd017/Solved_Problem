import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, H, arr[][][], ans;
    static PriorityQueue<Node> q = new PriorityQueue<>();
    static boolean visited[][][];

    static int dh[] = {0, 0, 0, 0, 1, -1};
    static int dy[] = {0, 0, -1, 1, 0, 0};
    static int dx[] = {-1, 1, 0, 0, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        arr = new int[H][N][M];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    arr[i][j][k] = Integer.parseInt(st.nextToken());

                    if (arr[i][j][k] == 1) q.add(new Node(i, j, k, 1));
                }
            }
        }

        visited = new boolean[H][N][M];

        solve();

        if (ans == 1) System.out.println(0);
        else if(ans == -1) System.out.println(ans);
        else System.out.println(ans-1);
    }

    static void solve() {
        bfs();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (arr[i][j][k] == 0) {
                        ans = -1;
                        return;
                    }

                    ans = Math.max(ans, arr[i][j][k]);
                }
            }
        }
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int k = 0; k < 6; k++) {
                int nh = dh[k] + cur.h;
                int ny = dy[k] + cur.y;
                int nx = dx[k] + cur.x;

                if (!inRange(nh, ny, nx) || arr[nh][ny][nx] == -1 || visited[nh][ny][nx]) continue;

                if (arr[nh][ny][nx] == 0) {
                    visited[nh][ny][nx] = true;
                    arr[nh][ny][nx] = cur.cnt + 1;
                    q.add(new Node(nh, ny, nx, cur.cnt + 1));
                }
            }
        }
    }

    static boolean inRange(int h, int y, int x) {
        return (h >= 0 && h < H) && (y >= 0 && y < N) && (x >= 0 && x < M);
    }
}

class Node implements Comparable<Node> {
    int y, x, h, cnt;

    Node(int h, int y, int x, int cnt) {
        this.h = h;
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }

    public int compareTo(Node n) {
        return this.cnt - n.cnt;
    }
}
