import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Virus {
    int y, x;

    Virus(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int N, M, arr[][], ans;

    static List<Virus> list = new ArrayList<>();

    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] == 0) cnt++;
                else if (arr[i][j] == 2) list.add(new Virus(i, j));
            }
        }


        solve(0, 0, 3, cnt);

        System.out.println(ans);
    }

    static void solve(int y, int x, int wall, int emptyCnt) {
        if (ans > emptyCnt) return;

        if (x == M) {
            solve(y + 1, 0, wall, emptyCnt);
            return;
        }

        if (wall == 0) {
            int cnt = emptyCnt - getRemovedCnt(arr);
            if (ans < cnt) ans = cnt;
            return;
        }

        if (y == N) return;

        if (arr[y][x] == 0) {
            arr[y][x] = 1;
            solve(y, x + 1, wall - 1, emptyCnt - 1);
            arr[y][x] = 0;
        }

        solve(y, x + 1, wall, emptyCnt);
    }

    static int getRemovedCnt(int tmp[][]) {
        int cnt = 0;

        boolean visited[][] = new boolean[N][M];

        for (Virus v : list) {
            Queue<Virus> q = new ArrayDeque<>();
            q.add(new Virus(v.y, v.x));

            while (!q.isEmpty()) {
                Virus cur = q.poll();

                for (int k = 0; k < 4; k++) {
                    int ny = dy[k] + cur.y;
                    int nx = dx[k] + cur.x;

                    if (!inRange(ny, nx) || visited[ny][nx] || tmp[ny][nx] != 0) continue;

                    cnt++;
                    visited[ny][nx] = true;
                    q.add(new Virus(ny, nx));
                }
            }
        }

        return cnt;
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < M);
    }
} 