import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, arr[][], L, Y, X, dir, cnt, ans;
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

            arr[y][x] = 2;
        }

        L = Integer.parseInt(br.readLine());

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            String C = st.nextToken();

            map.put(X, C.charAt(0));
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        arr[0][0] = cnt = 1;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, cnt++));

        while (true) {
            if (map.containsKey(ans)) setDir(map.get(ans));

            int ny = Y + dy[dir];
            int nx = X + dx[dir];

            if (!inRange(ny, nx) || arr[ny][nx] == 1) {
                ans++;
                return;
            }

            Y = ny;
            X = nx;
            ans++;

            if (arr[ny][nx] == 2) {
                arr[ny][nx] = 1;
                pq.add(new Node(ny, nx, cnt++));
                continue;
            }

            arr[ny][nx] = 1;
            pq.add(new Node(ny, nx, cnt++));

            Node tail = pq.poll();
            arr[tail.y][tail.x] = 0;
        }
    }

    static void setDir(char c) {
        if (c == 'L') {
            if (--dir < 0) dir = 3;
        } else {
            dir = (dir + 1) % 4;
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