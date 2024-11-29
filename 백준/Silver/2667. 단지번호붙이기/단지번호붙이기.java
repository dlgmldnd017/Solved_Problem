import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, ans;
    static char ch[][];
    static boolean visited[][];

    static List<Integer> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        ch = new char[N][N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            for (int j = 0; j < N; j++) ch[i][j] = input.charAt(j);
        }

        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && ch[i][j] == '1') solve(i, j);
            }
        }

        Collections.sort(list);

        sb.append(list.size() + "\n");

        for (int i : list) sb.append(i + "\n");

        System.out.println(sb);
    }

    static void solve(int y, int x) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(y, x));

        visited[y][x] = true;

        ans = 1;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int k = 0; k < 4; k++) {
                int ny = dy[k] + cur.y;
                int nx = dx[k] + cur.x;

                if (!inRange(ny, nx) || visited[ny][nx] || ch[ny][nx] == '0') continue;

                ans++;
                visited[ny][nx] = true;
                q.add(new Node(ny, nx));
            }
        }

        list.add(ans);
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