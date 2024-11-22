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


        solve(3, cnt);

        System.out.println(ans);
    }

    static void solve(int wall, int emptyCnt) {
        if (wall == 0) {
            int[][] tmp = new int[N][M];

            for (int i = 0; i < N; i++) tmp[i] = arr[i].clone();

            int cnt = emptyCnt - getRemovedCnt();

            if (ans < cnt) ans = cnt;

            arr = tmp;
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) {
                    arr[i][j] = 1;
                    solve(wall - 1, emptyCnt - 1);
                    arr[i][j] = 0;
                }
            }
        }
    }

    static int getRemovedCnt() {
        int cnt = 0;

        for (Virus v : list) {
            Queue<Virus> q = new ArrayDeque<>();
            q.add(new Virus(v.y, v.x));

            boolean visited[][] = new boolean[N][M];
            visited[v.y][v.x] = true;

            while (!q.isEmpty()) {
                Virus cur = q.poll();

                for (int k = 0; k < 4; k++) {
                    int ny = dy[k] + cur.y;
                    int nx = dx[k] + cur.x;

                    if (!inRange(ny, nx) || visited[ny][nx] || arr[ny][nx] != 0) continue;

                    arr[ny][nx] = -1;
                    visited[ny][nx] = true;
                    q.add(new Virus(ny, nx));
                    cnt++;
                }
            }
        }

        return cnt;
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < M);
    }
} 