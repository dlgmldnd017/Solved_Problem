import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, L, arr[][], cnt, ans;

    static Map<Integer, Character> map = new HashMap<>();

    static int dy[] = {0, 1, 0, -1};
    static int dx[] = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;

            arr[y][x] = 1;
        }

        L = Integer.parseInt(br.readLine());

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);

            map.put(X, C);
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, cnt++));

        int t = 0, dir = 0, y = 0, x = 0;
        arr[y][x] = -1;

        while (true) {
            if (map.containsKey(t)) {
                char C = map.get(t);

                if (C == 'L') {
                    dir--;
                    if (dir < 0) dir = 3;
                } else {
                    dir++;
                    if (dir > 3) dir = 0;
                }
            }

            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if (!inRange(ny, nx) || arr[ny][nx] == -1) {
                ans = t + 1;
                return;
            }

            if (arr[ny][nx] != 1) {
                Node n = pq.poll();
                arr[n.y][n.x] = 0;
            }

            t++;
            y = ny;
            x = nx;
            arr[ny][nx] = -1;
            pq.add(new Node(ny, nx, cnt++));
        }
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < N);
    }
}

class Node implements Comparable<Node> {
    int y, x, cnt;

    Node(int y, int x, int cnt) {
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }

    public int compareTo(Node n) {
        return this.cnt - n.cnt;
    }
}