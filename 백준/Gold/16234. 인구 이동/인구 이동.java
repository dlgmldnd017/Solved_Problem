import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, L, R, A[][], newA[][], ans;
    static boolean flag, visited[][];
    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        A = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) A[i][j] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        while (true) {
            flag = false;
            newA = new int[N][N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) continue;

                    bfs(i, j);
                }
            }

            if (!flag) return;

            for (int i = 0; i < N; i++) A[i] = newA[i].clone();

            ans++;
        }
    }

    static void bfs(int y, int x) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(y, x));

        Queue<Node> nq = new ArrayDeque<>();
        nq.add(new Node(y, x));

        int sum = A[y][x];

        visited[y][x] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int k = 0; k < 4; k++) {
                int ny = cur.y + dy[k];
                int nx = cur.x + dx[k];

                if (!inRange(ny, nx) || visited[ny][nx]) continue;

                int diff = Math.abs(A[cur.y][cur.x] - A[ny][nx]);

                if (L <= diff && diff <= R) {
                    sum += A[ny][nx];
                    visited[ny][nx] = true;
                    q.add(new Node(ny, nx));
                    nq.add(new Node(ny, nx));
                }
            }
        }

        if (nq.size() == 1) {
            Node n = nq.poll();
            newA[n.y][n.x] = A[n.y][n.x];
        } else {
            flag = true;

            int avg = sum / nq.size();

            while (!nq.isEmpty()) {
                Node cur = nq.poll();
                newA[cur.y][cur.x] = avg;
            }
        }
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < N);
    }
}

class Node {
    int y, x;

    Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}