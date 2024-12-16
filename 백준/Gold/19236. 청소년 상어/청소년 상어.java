import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int arr[][], ans;
    static Fish fish[] = new Fish[17];

    static int dy[] = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int dx[] = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr = new int[4][4];

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                int idx = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                arr[i][j] = idx;
                fish[idx] = new Fish(i, j, dir - 1, 1);
            }
        }

        solve(0, 0, fish[arr[0][0]].dir, arr[0][0]);

        System.out.println(ans);
    }

    static void solve(int y, int x, int dir, int cnt) {
        if (ans < cnt) ans = cnt;

        // 현재 상태 저장
        int[][] tempArr = copyArray(arr);
        Fish[] tempFish = copyFish(fish);

        fish[arr[y][x]].alive = 0;

        moveFish(y, x);

        for (int k = 1; k <= 3; k++) {
            int ny = dy[dir] * k + y;
            int nx = dx[dir] * k + x;

            if (!inRange(ny, nx) || fish[arr[ny][nx]].alive == 0) continue;

            solve(ny, nx, fish[arr[ny][nx]].dir, cnt + arr[ny][nx]);
        }

        // 이전 상태 복구
        arr = tempArr;
        fish = tempFish;
    }

    static int[][] copyArray(int[][] src) {
        int[][] copy = new int[4][4];
        for (int i = 0; i < src.length; i++) {
            copy[i] = src[i].clone();
        }
        return copy;
    }

    static Fish[] copyFish(Fish[] src) {
        Fish[] copy = new Fish[17];
        for (int i = 1; i < src.length; i++) {
            if (src[i] != null) {
                Fish f = src[i];
                copy[i] = new Fish(f.y, f.x, f.dir, f.alive);
            }
        }
        return copy;
    }

    static void moveFish(int y, int x) {
        for (int i = 1; i <= 16; i++) {
            Fish f = fish[i];

            if (f.alive == 0) continue;

            int ny, nx;

            while (true) {
                ny = dy[f.dir] + f.y;
                nx = dx[f.dir] + f.x;

                if (!inRange(ny, nx) || (ny == y && nx == x)) f.dir = (f.dir + 1) % 8;
                else break;
            }

            fish[arr[ny][nx]].y = f.y;
            fish[arr[ny][nx]].x = f.x;

            int tmp = arr[f.y][f.x];
            arr[f.y][f.x] = arr[ny][nx];
            arr[ny][nx] = tmp;

            f.y = ny;
            f.x = nx;
        }
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < 4) && (x >= 0 && x < 4);
    }
}

class Fish {
    int y, x, dir, alive;

    Fish(int y, int x, int dir, int alive) {
        this.y = y;
        this.x = x;
        this.dir = dir;
        this.alive = alive;
    }
}
