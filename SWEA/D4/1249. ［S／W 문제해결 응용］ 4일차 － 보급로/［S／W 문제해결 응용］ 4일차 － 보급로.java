import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[][] arr;

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");

            N = Integer.parseInt(br.readLine());

            arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < N; j++) arr[i][j] = str.charAt(j) - '0';
            }

            solve();

            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0));

        boolean[][] visited = new boolean[N][N];
        visited[0][0] = true;

        int[][] r = new int[N][N];

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int k = 0; k < 4; k++) {
                int ny = cur.y + dy[k];
                int nx = cur.x + dx[k];

                if (!inRange(ny, nx)) continue;

                if (!visited[ny][nx]) {
                    visited[ny][nx] = true;
                    q.add(new Node(ny, nx));
                    r[ny][nx] = r[cur.y][cur.x] + arr[ny][nx];
                } else {
                    if (r[ny][nx] > r[cur.y][cur.x] + arr[ny][nx]) {
                        q.add(new Node(ny, nx));
                        r[ny][nx] = r[cur.y][cur.x] + arr[ny][nx];
                    }
                }
            }
        }

        sb.append(r[N-1][N-1]);
    }

    static boolean inRange(int y, int x) {
        return (0 <= y && y < N) && (0 <= x && x < N);
    }
}

class Node {
    int y, x;

    Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}