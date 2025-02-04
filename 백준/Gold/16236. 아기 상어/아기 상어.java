import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, arr[][], ans;
    static Shark shark;
    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        int fishCnt = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] == 9) shark = new Shark(i, j, 2, 0);
                else if (1 <= arr[i][j] && arr[i][j] <= 6) fishCnt++;
            }
        }

        if (fishCnt != 0) solve();

        System.out.println(ans);
    }

    static void solve() {
        arr[shark.y][shark.x] = 0;

        while (true) {
            Queue<Shark> q = new ArrayDeque<>();
            q.add(new Shark(shark.y, shark.x, 0, 0));

            boolean visited[][] = new boolean[N][N];
            visited[shark.y][shark.x] = true;

            PriorityQueue<Fish> pq = new PriorityQueue<>();

            while (!q.isEmpty()) {
                Shark cur = q.poll();

                for (int k = 0; k < 4; k++) {
                    int ny = cur.y + dy[k];
                    int nx = cur.x + dx[k];

                    if (!inRange(ny, nx) || visited[ny][nx] || shark.size < arr[ny][nx]) continue;
                    visited[ny][nx] = true;

                    if (arr[ny][nx] != 0 && shark.size > arr[ny][nx]) pq.add(new Fish(ny, nx, cur.eatCnt + 1));
                    q.add(new Shark(ny, nx, 0, cur.eatCnt + 1));
                }
            }

            if (pq.isEmpty()) return;

            Fish fish = pq.poll();
            shark.y = fish.y;
            shark.x = fish.x;
            shark.eatCnt++;

            if (shark.size == shark.eatCnt) {
                shark.size++;
                shark.eatCnt = 0;
            }

            arr[shark.y][shark.x] = 0;

            ans += fish.dist;
        }
    }

    static boolean inRange(int y, int x) {
        return (0 <= y && y < N) && (0 <= x && x < N);
    }
}

class Shark {
    int y, x, size, eatCnt;

    Shark(int y, int x, int size, int eatCnt) {
        this.y = y;
        this.x = x;
        this.size = size;
        this.eatCnt = eatCnt;
    }
}

class Fish implements Comparable<Fish> {
    int y, x, size, dist;

    Fish(int y, int x, int dist) {
        this.y = y;
        this.x = x;
        this.dist = dist;
    }

    public int compareTo(Fish n) {
        if (this.dist == n.dist) {
            if (this.y == n.y) return this.x - n.x;
            return this.y - n.y;
        }
        return this.dist - n.dist;
    }
}