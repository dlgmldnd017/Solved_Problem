import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int y, x;

    Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int N, L, R, arr[][], ans;
    static List<Node> list[];
    static boolean visited[][], check;

    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        while (true) {
            int num = 0;
            check = false;
            list = new ArrayList[N * N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) continue;

                    list[num] = new ArrayList<>();
                    bfs(i, j, num++);
                }
            }

            if (!check) break;
            updateArr(num);
            ans++;
        }
    }

    static void bfs(int y, int x, int num) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(y, x));

        visited[y][x] = true;
        list[num].add(new Node(y, x));

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int k = 0; k < 4; k++) {
                int ny = dy[k] + cur.y;
                int nx = dx[k] + cur.x;

                if (!inRange(ny, nx) || visited[ny][nx]) continue;

                int value = Math.abs(arr[cur.y][cur.x] - arr[ny][nx]);

                if (L <= value && value <= R) {
                    check = true;
                    visited[ny][nx] = true;
                    q.add(new Node(ny, nx));
                    list[num].add(new Node(ny, nx));
                }
            }
        }
    }

    static void updateArr(int num) {
        for (int i = 0; i < num; i++) {
            if (list[i].size() == 1) continue;

            int sum = 0;

            for (Node n : list[i]) sum += arr[n.y][n.x];

            int value = sum / list[i].size();

            for (Node n : list[i]) arr[n.y][n.x] = value;
        }
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < N);
    }
}


