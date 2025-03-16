import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, arr[][], es, ans;
    static List<Virus> list = new ArrayList<>();

    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0) es++;
            }
        }

        ans = Integer.MAX_VALUE;

        if (es == 0) ans = 0;
        else solve(0, 0, 0);

        if (ans == Integer.MAX_VALUE) ans = -1;
        System.out.println(ans);
    }

    static void solve(int y, int x, int cnt) {
        if (cnt == M) {
            check();
            return;
        }

        if (x == N) {
            solve(y + 1, 0, cnt);
            return;
        }

        if (y == N) return;

        if (arr[y][x] == 2) {
            list.add(new Virus(y, x, 0));
            solve(y, x + 1, cnt + 1);
            list.remove(list.size() - 1);
        }

        solve(y, x + 1, cnt);
    }

    static void check() {
        Queue<Virus> q = new ArrayDeque<>();

        boolean visited[][] = new boolean[N][N];

        for (Virus v : list) {
            visited[v.y][v.x] = true;
            q.add(new Virus(v.y, v.x, v.t));
        }

        int max = Integer.MIN_VALUE, cnt = 0;

        while (!q.isEmpty()) {
            Virus v = q.poll();

            if (arr[v.y][v.x] != 2 && max < v.t) max = v.t;

            for (int k = 0; k < 4; k++) {
                int ny = v.y + dy[k];
                int nx = v.x + dx[k];

                if (!inRange(ny, nx) || visited[ny][nx] || arr[ny][nx] == 1) continue;

                if (arr[ny][nx] == 0) cnt++;

                visited[ny][nx] = true;
                q.add(new Virus(ny, nx, v.t + 1));
            }
        }

        if (max != Integer.MIN_VALUE && es - cnt == 0) ans = Math.min(ans, max);
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < N);
    }
}

class Virus {
    int y, x, t;

    Virus(int y, int x, int t) {
        this.y = y;
        this.x = x;
        this.t = t;
    }
}