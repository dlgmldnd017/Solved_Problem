import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, arr[][], wallCnt, ans;
    static List<Node> list, virus;
    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0) wallCnt++;
                else if (arr[i][j] == 2) list.add(new Node(i, j, 0));
            }
        }

        ans = Integer.MAX_VALUE;

        solve();

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void solve() {
        if (wallCnt == 0) {
            ans = 0;
            return;
        }

        virus = new ArrayList<>();

        comb(0, 0);
    }

    static void comb(int depth, int idx) {
        if (depth == M) {
            check(wallCnt);
            return;
        }

        for (int i = idx; i < list.size(); i++) {
            virus.add(list.get(i));
            comb(depth + 1, i + 1);
            virus.remove(virus.size() - 1);
        }
    }

    static void check(int cnt) {
        Queue<Node> pq = new ArrayDeque<>();

        boolean visited[][] = new boolean[N][N];

        for (Node n : virus) {
            pq.add(new Node(n.y, n.x, n.t));
            visited[n.y][n.x] = true;
        }

        int max = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (arr[cur.y][cur.x] != 2 && max < cur.t) max = cur.t;

            for (int k = 0; k < 4; k++) {
                int ny = cur.y + dy[k];
                int nx = cur.x + dx[k];

                if (!inRange(ny, nx) || visited[ny][nx] || arr[ny][nx] == 1) continue;

                pq.add(new Node(ny, nx, cur.t + 1));
                visited[ny][nx] = true;

                if (arr[ny][nx] == 0) cnt--;
            }
        }

        if (cnt == 0) ans = Math.min(ans, max);
    }

    static boolean inRange(int y, int x) {
        return (0 <= y && y < N) && (0 <= x && x < N);
    }
}

class Node {
    int y, x, t;

    Node(int y, int x, int t) {
        this.y = y;
        this.x = x;
        this.t = t;
    }
}