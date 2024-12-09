import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, arr[][], cnt, ans;
    static Shark shark;

    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] == 0) continue;
                else if (arr[i][j] == 9) shark = new Shark(i, j, 2, 0);
                else cnt++;
            }
        }

        if (cnt != 0) solve();

        System.out.println(ans);
    }

    static void solve() {
        arr[shark.y][shark.x] = 0;

        while (true) {
            Queue<Shark> q = new ArrayDeque<>();
            q.add(new Shark(shark.y, shark.x, 0));

            boolean visited[][] = new boolean[N][N];
            visited[shark.y][shark.x] = true;

            List<Fish> fish = new ArrayList<>();

            while (!q.isEmpty()) {
                Shark cur = q.poll();

                for (int k = 0; k < 4; k++) {
                    int ny = dy[k] + cur.y;
                    int nx = dx[k] + cur.x;

                    if (!inRange(ny, nx) || visited[ny][nx] || shark.size < arr[ny][nx]) continue;

                    if (arr[ny][nx] != 0 && shark.size > arr[ny][nx]) fish.add(new Fish(ny, nx, cur.cnt + 1));
                    visited[ny][nx] = true;
                    q.add(new Shark(ny, nx, cur.cnt + 1));
                }
            }

            if (fish.isEmpty()) return;

            Collections.sort(fish);

            Fish f = fish.get(0);
            shark.y = f.y;
            shark.x = f.x;
            shark.cnt++;

            if (shark.size == shark.cnt) {
                shark.size++;
                shark.cnt = 0;
            }

            arr[f.y][f.x] = 0;

            ans += f.dist;
        }
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < N);
    }
}

class Fish implements Comparable<Fish> {
    int y, x, dist;

    Fish(int y, int x, int dist) {
        this.y = y;
        this.x = x;
        this.dist = dist;
    }

    public int compareTo(Fish f) {
        if (this.dist == f.dist) {
            if (this.y == f.y) return this.x - f.x;
            return this.y - f.y;
        }
        return this.dist - f.dist;
    }
}

class Shark {
    int y, x, size, cnt;

    Shark(int y, int x, int size, int cnt) {
        this.y = y;
        this.x = x;
        this.size = size;
        this.cnt = cnt;
    }

    Shark(int y, int x, int cnt) {
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }
}
