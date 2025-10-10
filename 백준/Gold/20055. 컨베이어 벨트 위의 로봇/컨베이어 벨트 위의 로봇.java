import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, K, ans;
    static int[] map;
    static List<Integer> A;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1];

        A = new ArrayList<>();
        A.add(0);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N << 1; i++) {
            A.add(Integer.parseInt(st.nextToken()));
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        while (true) {
            ans++;

            rotate();

            moveRobot();

            loadRobot();

            if (!check()) return;
        }
    }

    static void rotate() {
        A.add(1, A.remove(N << 1));


        for (int i = N - 1; i >= 1; i--) {
            if (map[i] == 0) continue;

            map[i + 1] = 1;
            map[i] = 0;
        }
    }

    static void moveRobot() {
        map[N] = 0;

        for (int i = N - 1; i >= 1; i--) {
            if (map[i] == 0 || map[i + 1] == 1 || A.get(i + 1) == 0) continue;

            int durability = A.get(i + 1);
            A.set(i + 1, durability - 1);

            map[i] = 0;
            map[i + 1] = 1;
        }
    }

    static void loadRobot() {
        if (map[1] == 1) return;

        int durability = A.get(1);
        if (durability == 0) return;

        A.set(1, durability - 1);
        map[1] = 1;
    }

    static boolean check() {
        int cnt = 0;

        for (int i = 1; i <= N << 1; i++) {
            if (A.get(i) == 0) cnt++;
        }

        return cnt < K;
    }
}