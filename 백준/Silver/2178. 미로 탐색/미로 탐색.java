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
            String input = br.readLine();
            for (int j = 0; j < M; j++) ch[i][j] = input.charAt(j);
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0, 1));

        boolean visited[][] = new boolean[N][M];

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int k = 0; k < 4; k++) {
                int ny = dy[k] + cur.y;
                int nx = dx[k] + cur.x;

                if (!inRange(ny, nx) || visited[ny][nx] || ch[ny][nx] == '0') continue;

                if (ny == N - 1 && nx == M - 1) {
                    ans = cur.cnt + 1;
                    return;
                }

                visited[ny][nx] = true;
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