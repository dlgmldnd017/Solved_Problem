import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, arr[][], maxH, minH, ans;

    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        minH = 100;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (maxH < arr[i][j]) maxH = arr[i][j];
                if (minH > arr[i][j]) minH = arr[i][j];
            }
        }

        ans = 1;

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int h = minH; h < maxH; h++) {
            boolean visited[][] = new boolean[N][N];
            checkFloodedArea(visited, h);

            int cnt = checkAreaCnt(visited);

            if (ans < cnt) ans = cnt;
        }
    }

    static void checkFloodedArea(boolean visited[][], int h) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (h >= arr[i][j]) visited[i][j] = true;
            }
        }
    }

    static int checkAreaCnt(boolean visited[][]) {
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;

                cnt++;
                bfs(visited, i, j);
            }
        }

        return cnt;
    }

    static void bfs(boolean visited[][], int i, int j) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(i, j));

        visited[i][j] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int k = 0; k < 4; k++) {
                int ny = dy[k] + cur.y;
                int nx = dx[k] + cur.x;

                if (!inRange(ny, nx) || visited[ny][nx]) continue;

                visited[ny][nx] = true;
                q.add(new Node(ny, nx));
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