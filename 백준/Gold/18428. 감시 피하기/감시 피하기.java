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

        N = Integer.parseInt(br.readLine());

        arr = new char[N][N];

        for (int i = 0; i < N; i++) {
            String str[] = br.readLine().split(" ");

            for (int j = 0; j < N; j++) {
                arr[i][j] = str[j].charAt(0);

                if (arr[i][j] == 'T') list.add(new Teacher(i, j));
            }
        }

        solve(3);

        System.out.println("NO");
    }

    static void solve(int cnt) {

        if (cnt == 0) {
            if (check()) {
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 'X') {
                    arr[i][j] = 'O';
                    solve(cnt - 1);
                    arr[i][j] = 'X';
                }
            }
        }
    }

    static boolean check() {

        for (Teacher t : list) {
            for (int k = 0; k < 4; k++) {
                int ny = dy[k] + t.y;
                int nx = dx[k] + t.x;

                while (true) {
                    if (!inRange(ny, nx) || arr[ny][nx] == 'O') break;

                    if (arr[ny][nx] == 'S') return false;

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