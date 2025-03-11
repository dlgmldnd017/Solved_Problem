import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, ans;
    static int[][] arr;
    static List<Node> list = new ArrayList<>();

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] != 0) list.add(new Node(i, j, arr[i][j]));
            }
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int t = 0;

        while (!list.isEmpty()) {
            if (check()) {
                ans = t;
                return;
            }

            melt();

            t++;
        }
    }

    static void melt() {
        for (Node n : list) {
            int cnt = 0;

            for (int k = 0; k < 4; k++) {
                int ny = n.y + dy[k];
                int nx = n.x + dx[k];

                if (!inRange(ny, nx) || arr[ny][nx] != 0) continue;

                cnt++;
            }

            n.num = Math.max(n.num - cnt, 0);
        }

        int i = 0;

        while (i < list.size()) {
            Node n = list.get(i);

            arr[n.y][n.x] = n.num;

            if (n.num == 0) list.remove(i);
            else i++;
        }
    }

    static boolean check() {
        int cnt = 0;

        boolean[][] visited = new boolean[N][M];

        for (Node n : list) {
            if (visited[n.y][n.x]) continue;
            visited[n.y][n.x] = true;

            cnt++;

            Queue<Node> q = new ArrayDeque<>();
            q.add(new Node(n.y, n.x));

            while (!q.isEmpty()) {
                Node cur = q.poll();

                for (int k = 0; k < 4; k++) {
                    int ny = cur.y + dy[k];
                    int nx = cur.x + dx[k];

                    if (!inRange(ny, nx) || visited[ny][nx] || arr[ny][nx] == 0) continue;

                    visited[ny][nx] = true;
                    q.add(new Node(ny, nx));
                }
            }
        }

        return cnt >= 2;
    }

    static boolean inRange(int y, int x) {
        return (0 <= y && y < N) && (0 <= x && x < M);
    }
}

class Node {
    int y, x, num;

    Node(int y, int x) {
        this.y = y;
        this.x = x;
    }

    Node(int y, int x, int num) {
        this.y = y;
        this.x = x;
        this.num = num;
    }
}