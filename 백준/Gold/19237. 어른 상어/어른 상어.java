import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K, dirs[][][], cnt, ans;
    static Map map[][];
    static Shark shark[];
    static List<Integer> list[][];

    static int dy[] = {0, -1, 1, 0, 0};
    static int dx[] = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new Map[N][N];

        shark = new Shark[M + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = new Map(0, 0);

                int idx = Integer.parseInt(st.nextToken());

                if (idx > 0) shark[idx] = new Shark(i, j, 1);
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) shark[i].dir = Integer.parseInt(st.nextToken());

        dirs = new int[M + 1][5][4];

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= 4; j++) {

                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    dirs[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        cnt = M;

        list = new ArrayList[N][N];

        solve();

        System.out.println(ans);
    }

    static void solve() {
        while (true) {
            if (ans >= 1000) {
                ans = -1;
                return;
            }

            init();

            // 자신의 위치에서 뿌리기
            spreadSmell();

            // 상어들 이동하기
            moveShark();

            // 한 공간에 여러 상어들이 존재하는지 확인하기
            checkSpace();

            // 냄새 1초 경과하기
            countOneSeconds();

            ans++;

            if (cnt == 1) return;
        }
    }

    static void init() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) list[i][j] = new ArrayList<>();
        }
    }

    static void spreadSmell() {
        for (int i = 1; i <= M; i++) {
            Shark s = shark[i];

            if (s.alive == 0) continue;

            map[s.y][s.x].idx = i;
            map[s.y][s.x].k = K;
        }
    }

    static void moveShark() {
        for (int i = 1; i <= M; i++) {
            Shark s = shark[i];

            if (s.alive == 0) continue;

            int ny = -1, nx = -1, nextDir = -1;
            boolean check = false;

            // 1. 빈 칸 탐색
            for (int k = 0; k < 4; k++) {
                int dir = dirs[i][s.dir][k];
                ny = dy[dir] + s.y;
                nx = dx[dir] + s.x;

                if (inRange(ny, nx) && map[ny][nx].idx == 0) {
                    check = true;
                    nextDir = dir;
                    break;
                }
            }

            // 2. 빈 칸이 없으면 자신의 냄새 칸 탐색
            if (!check) {
                for (int k = 0; k < 4; k++) {
                    int dir = dirs[i][s.dir][k];
                    ny = dy[dir] + s.y;
                    nx = dx[dir] + s.x;

                    if (inRange(ny, nx) && map[ny][nx].idx == i) {
                        nextDir = dir;
                        break;
                    }
                }
            }

            // 이동 및 방향 갱신
            s.y = ny;
            s.x = nx;
            s.dir = nextDir;
            list[ny][nx].add(i);
        }
    }

    static void checkSpace() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (list[i][j].size() > 1) {
                    Collections.sort(list[i][j]);

                    while (list[i][j].size() != 1) {
                        int idx = list[i][j].get(list[i][j].size() - 1);
                        shark[idx].alive = 0;
                        cnt--;
                        list[i][j].remove(list[i][j].size() - 1);
                    }
                }
            }
        }
    }

    static void countOneSeconds() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].idx == 0) continue;

                map[i][j].k--;

                if (map[i][j].k == 0) map[i][j].idx = 0;
            }
        }
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < N);
    }
}

class Map {
    int idx, k;

    Map(int idx, int k) {
        this.idx = idx;
        this.k = k;
    }
}

class Shark {
    int y, x, dir, alive;

    Shark(int y, int x, int alive) {
        this.y = y;
        this.x = x;
        this.alive = alive;
    }
}