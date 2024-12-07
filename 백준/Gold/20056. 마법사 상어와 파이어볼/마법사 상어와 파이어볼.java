import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K, ans;
    static List<Node> list[][], fireBalls;

    static int dy[] = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int dx[] = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        list = new ArrayList[N][N];

        fireBalls = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            fireBalls.add(new Node(i, y, x, m, s, d, true));
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int k = 0; k < K; k++) {
            init();
            moveFireBall();
            checkFireBall();
            //System.out.println();
        }

        for (Node n : fireBalls) {
            if (!n.isLive) continue;
//            System.out.println(n.y + " : " + n.x + " : " + n.m + " : " + n.s + " : " + n.d);
            ans += n.m;
        }
    }

    static void init() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) list[i][j] = new ArrayList<>();
        }
    }

    static void moveFireBall() {
        for (Node n : fireBalls) {
            if (!n.isLive) continue;

            // System.out.println("이전 : [" + n.idx + "] " + n.y + " : " + n.x + " : " + n.m + " : " + n.s + " : " + n.d);
            int ny = (n.y + dy[n.d] * n.s) % N;
            int nx = (n.x + dx[n.d] * n.s) % N;

            if (ny < 0) ny += N;
            if (nx < 0) nx += N;

            // System.out.println("이후 : [" + n.idx + "] " + ny + " : " + nx + " : " + n.m + " : " + n.s + " : " + n.d);

            fireBalls.get(n.idx).y = ny;
            fireBalls.get(n.idx).x = nx;
            list[ny][nx].add(new Node(n.idx, ny, nx, n.m, n.s, n.d, true));
        }
    }

    static void checkFireBall() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (list[i][j].size() <= 1) continue;

                int m = 0, s = 0;

                for (Node n : list[i][j]) {
                    m += n.m;
                    s += n.s;
                    fireBalls.get(n.idx).isLive = false;
                }

                m = (int) Math.floor((double) m / 5);
                s = (int) Math.floor((double) s / list[i][j].size());

                if (m != 0) {
                    boolean allOdd = true, allEven = true;

                    for (Node n : list[i][j]) {
                        if (n.d % 2 == 0) allOdd = false;
                        else allEven = false;

                        if (!allOdd && !allEven) break;
                    }

                    if (allOdd || allEven) {
                        int nd = 0;
                        for (int k = 0; k < 4; k++) {
                            fireBalls.add(new Node(fireBalls.size(), i, j, m, s, nd, true));
                            nd += 2;
                        }
                    } else {
                        int nd = 1;
                        for (int k = 0; k < 4; k++) {
                            fireBalls.add(new Node(fireBalls.size(), i, j, m, s, nd, true));
                            nd += 2;
                        }
                    }
                }
            }
        }
    }
}

class Node {
    int idx, y, x, m, s, d;
    boolean isLive;

    Node(int idx, int y, int x, int m, int s, int d, boolean isLive) {
        this.idx = idx;
        this.y = y;
        this.x = x;
        this.m = m;
        this.s = s;
        this.d = d;
        this.isLive = isLive;
    }
}