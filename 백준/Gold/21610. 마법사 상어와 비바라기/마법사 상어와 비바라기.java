import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, A[][], ans;
    static List<Node> moveList = new ArrayList<>();
    static List<Cloud> cloudList = new ArrayList<>();

    static int dy[] = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int dx[] = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    static int diagonalY[] = {-1, -1, 1, 1};
    static int diagonalX[] = {-1, 1, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) A[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            moveList.add(new Node(d, s));
        }

        cloudList.add(new Cloud(N - 2, 0));
        cloudList.add(new Cloud(N - 2, 1));
        cloudList.add(new Cloud(N - 1, 0));
        cloudList.add(new Cloud(N - 1, 1));

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (Node cur : moveList) {
            int distY = (dy[cur.d] * cur.s) % N, distX = (dx[cur.d] * cur.s) % N;

            boolean visited[][] = new boolean[N][N];

            for (Cloud c : cloudList) {
                // (1)
                c.y = (c.y + distY) % N;
                c.x = (c.x + distX) % N;

                if (c.y < 0) c.y = N + c.y;
                if (c.x < 0) c.x = N + c.x;

                // (2)
                A[c.y][c.x]++;
                visited[c.y][c.x] = true;
            }

            for (Cloud c : cloudList) {
                int cnt = 0;

                // (4)
                for (int k = 0; k < 4; k++) {
                    int ny = diagonalY[k] + c.y;
                    int nx = diagonalX[k] + c.x;

                    if (!inRange(ny, nx) || A[ny][nx] == 0) continue;
                    cnt++;
                }

                A[c.y][c.x] += cnt;
            }

            // (3)
            cloudList.clear();

            // (4)
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] || A[i][j] < 2) continue;

                    A[i][j] -= 2;
                    cloudList.add(new Cloud(i, j));
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) ans += A[i][j];
        }
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < N);
    }
}

class Node {
    int d, s;

    Node(int d, int s) {
        this.d = d;
        this.s = s;
    }
}

class Cloud {
    int y, x;

    Cloud(int y, int x) {
        this.y = y;
        this.x = x;
    }
}