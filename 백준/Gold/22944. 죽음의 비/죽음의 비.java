import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, H, D, startY, startX, ans;
    static char[][] ch;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        ch = new char[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            for (int j = 0; j < N; j++) {
                ch[i][j] = str.charAt(j);

                if (ch[i][j] == 'S') {
                    startY = i;
                    startX = j;
                }
            }
        }

        solve();

        System.out.println(ans == 0 ? -1 : ans);
    }

    static void solve() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(startY, startX, H, 0, 0));

        int[][] visited = new int[N][N];
        visited[startY][startX] = H;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int k = 0; k < 4; k++) {
                int ny = cur.y + dy[k];
                int nx = cur.x + dx[k];

                if (!inRange(ny, nx)) continue;

                if (ch[ny][nx] == 'E') {
                    ans = cur.t + 1;
                    return;
                }

                int h = cur.h, d = cur.d;

                if (ch[ny][nx] == 'U') d = D;

                if (d != 0) d--;
                else h--;

                if (h == 0) continue;

                if (visited[ny][nx] < h + d) {
                    visited[ny][nx] = h + d;
                    q.add(new Node(ny, nx, h, d, cur.t + 1));
                }
            }
        }
    }

    static boolean inRange(int y, int x) {
        return (0 <= y && y < N) && (0 <= x && x < N);
    }
}

class Node {
    int y, x, h, d, t;

    Node(int y, int x, int h, int d, int t) {
        this.y = y;
        this.x = x;
        this.h = h;
        this.d = d;
        this.t = t;
    }
}