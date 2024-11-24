import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Teacher {
    int y, x;

    Teacher(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int N;
    static char arr[][];
    static List<Teacher> list = new ArrayList<>();

    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new char[N][N];

        for (int i = 0; i < N; i++) {
            String str[] = br.readLine().split(" ");

            for (int j = 0; j < N; j++) {
                arr[i][j] = str[j].charAt(0);

                if (arr[i][j] == 'T') list.add(new Teacher(i, j));
            }
        }

        solve(0, 0, 0);

        System.out.println("NO");
    }

    static void solve(int y, int x, int cnt) {
        if (x == N) {
            solve(y + 1, 0, cnt);
            return;
        }

        if (cnt == 3) {
            if (check()) {
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }

        if (y == N) return;

        if (arr[y][x] == 'X') {
            arr[y][x] = 'O';
            solve(y, x + 1, cnt + 1);
            arr[y][x] = 'X';
        }

        solve(y, x + 1, cnt);
    }

    static boolean check() {
        for (Teacher t : list) {
            boolean visited[][] = new boolean[N][N];

            for (int k = 0; k < 4; k++) {
                int ny = dy[k] + t.y;
                int nx = dx[k] + t.x;

                while (true) {
                    if (!inRange(ny, nx) || visited[ny][nx] || arr[ny][nx] == 'O' || arr[ny][nx] == 'T') break;

                    if (arr[ny][nx] == 'S') return false;

                    visited[ny][nx] = true;
                    ny += dy[k];
                    nx += dx[k];
                }
            }
        }

        return true;
    }

    static boolean inRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < N);
    }
} 