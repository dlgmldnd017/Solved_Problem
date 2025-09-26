import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, ans;
    static char[][] ch;

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ch = new char[N][M];

        int startY = -1, startX = -1;

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            for (int j = 0; j < M; j++) {
                ch[i][j] = input.charAt(j);

                if (ch[i][j] == '0') {
                    startY = i;
                    startX = j;
                }
            }
        }

        ans = -1;

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(startY, startX, 0, 0));

        boolean[][][] visited = new boolean[1 << 5 + 1][N][M];
        visited[0][startY][startX] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (ch[cur.y][cur.x] == '1') {
                ans = cur.cnt;
                break;
            }

            for (int k = 0; k < 4; k++) {
                int ny = cur.y + dy[k];
                int nx = cur.x + dx[k];

                if (!inRange(ny, nx) || ch[ny][nx] == '#' || visited[cur.key][ny][nx]) continue;

                if ('A' <= ch[ny][nx] && ch[ny][nx] <= 'F') {
                    if ((cur.key & (1 << ch[ny][nx] - 'A')) != 0) {
                        visited[cur.key][ny][nx] = true;
                        q.add(new Node(ny, nx, cur.cnt + 1, cur.key));
                    }
                } else if ('a' <= ch[ny][nx] && ch[ny][nx] <= 'f') {
                    int newKey = cur.key | (1 << ch[ny][nx] - 'a');
                    visited[newKey][ny][nx] = true;
                    q.add(new Node(ny, nx, cur.cnt + 1, newKey));
                } else {
                    visited[cur.key][ny][nx] = true;
                    q.add(new Node(ny, nx, cur.cnt + 1, cur.key));
                }
            }
        }

        System.out.println(ans);
    }

    static boolean inRange(int y, int x) {
        return (0 <= y && y < N) && (0 <= x && x < M);
    }

    static class Node {
        int y, x, cnt, key;

        Node(int y, int x, int cnt, int key) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.key = key;
        }
    }
}