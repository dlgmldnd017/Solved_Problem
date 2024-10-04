import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int y, x, cnt;

    Node(int y, int x, int cnt) {
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }
}

public class Main {
    static int N, M, ans;
    static char map[][];

    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        int startY = -1, startX = -1;

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);

                if (map[i][j] == 'I') {
                    startY = i;
                    startX = j;
                }
            }
        }

        solve(startY, startX);

        if (ans == 0) System.out.println("TT");
        else System.out.println(ans);
    }

    static void solve(int startY, int startX) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(startY, startX, 0));

        boolean visited[][] = new boolean[N][M];
        visited[startY][startX] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int k = 0; k < 4; k++) {
                int ny = cur.y + dy[k];
                int nx = cur.x + dx[k];

                if (!inRange(ny, nx) || visited[ny][nx] || map[ny][nx] == 'X') continue;

                if (map[ny][nx] == 'P') {
                    q.add(new Node(ny, nx, cur.cnt + 1));
                    ans++;
                } else {
                    q.add(new Node(ny, nx, cur.cnt));
                }

                visited[ny][nx] = true;
            }
        }
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < M);
    }
}
