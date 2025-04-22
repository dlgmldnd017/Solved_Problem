import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int ans;
    static char[][] ch;
    static boolean[][] visited;
    static Queue<Node> q;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ch = new char[12][6];

        for (int i = 0; i < 12; i++) {
            String input = br.readLine();
            for (int j = 0; j < 6; j++) ch[i][j] = input.charAt(j);
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        while (true) {
            boolean flag = false;

            visited = new boolean[12][6];

            q = new ArrayDeque<>();

            for (int i = 11; i >= 0; i--) {
                for (int j = 0; j < 6; j++) {
                    if (ch[i][j] == '.' || visited[i][j]) continue;

                    dfs(i, j, ch[i][j]);

                    if (q.size() <= 3) q.clear();
                    else {
                        flag = true;

                        while (!q.isEmpty()) {
                            Node cur = q.poll();

                            ch[cur.y][cur.x] = '.';
                        }
                    }
                }
            }

            if (!flag) return;

            ans++;

            for (int j = 0; j < 6; j++) down(j);
        }
    }

    static void dfs(int y, int x, char target) {
        visited[y][x] = true;

        q.add(new Node(y, x));

        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];

            if (!inRange(ny, nx) || visited[ny][nx] || ch[ny][nx] == '.' || ch[ny][nx] != target) continue;

            dfs(ny, nx, target);
        }
    }

    static void down(int j) {
        Queue<Character> tmp = new ArrayDeque<>();

        for (int i = 11; i >= 0; i--) {
            if (ch[i][j] == '.') continue;
            tmp.add(ch[i][j]);
            ch[i][j] = '.';
        }

        int row = 11;

        while (!tmp.isEmpty()) {
            char target = tmp.poll();

            ch[row--][j] = target;
        }
    }

    static boolean inRange(int y, int x) {
        return (0 <= y && y < 12) && (0 <= x && x < 6);
    }
}

class Node {
    int y, x;

    Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}