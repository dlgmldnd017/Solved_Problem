import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static char ch[][];
    static List<Node> teachers = new ArrayList<>();

    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        ch = new char[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ch[i][j] = st.nextToken().charAt(0);
                if (ch[i][j] == 'T') teachers.add(new Node(i, j));
            }
        }

        solve();

        System.out.println("NO");
    }

    static void solve() {
        dfs(0, 0, 3);
    }

    static void dfs(int y, int x, int cnt) {
        if (cnt == 0) {
            if (check()) {
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }

        if (x == N) {
            dfs(y + 1, 0, cnt);
            return;
        }

        if (y == N) return;

        if (ch[y][x] == 'X') {
            ch[y][x] = 'O';
            dfs(y, x + 1, cnt - 1);
            ch[y][x] = 'X';
        }

        dfs(y, x + 1, cnt);
    }

    static boolean check() {
        for (Node cur : teachers) {
            boolean visited[][] = new boolean[N][N];

            for (int k = 0; k < 4; k++) {
                int ny = cur.y, nx = cur.x;

                while (true) {
                    ny += dy[k];
                    nx += dx[k];

                    if (!inRange(ny, nx) || visited[ny][nx] || ch[ny][nx] == 'T' || ch[ny][nx] == 'O') break;

                    if (ch[ny][nx] == 'S') return false;

                    visited[ny][nx] = true;
                }
            }
        }

        return true;
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