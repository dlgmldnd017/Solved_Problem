import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

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

public class Main {
    static int N, arr[][], ans;

    static int dy[] = {-1, 1, 0, 0};
    static int dx[] = {0, 0, -1, 1};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int visited[][] = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(visited[i], Integer.MAX_VALUE);
        visited[0][0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.y == N - 1 && cur.x == N - 1) {
                ans = visited[N - 1][N - 1];
                return;
            }

            for (int k = 0; k < 4; k++) {
                int ny = dy[k] + cur.y;
                int nx = dx[k] + cur.x;

                if (!inRange(ny, nx)) continue;

                int max = Math.max(visited[cur.y][cur.x], Math.abs(arr[ny][nx] - arr[cur.y][cur.x]));

                if (visited[ny][nx] <= max) continue;

                visited[ny][nx] = max;
                pq.add(new Node(ny, nx, visited[ny][nx]));
            }
        }
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < N);
    }
}