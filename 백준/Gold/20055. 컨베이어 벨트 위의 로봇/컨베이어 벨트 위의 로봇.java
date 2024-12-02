import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, ans;
    static List<Integer> belt = new ArrayList<>();
    static List<Integer> robot = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) belt.add(Integer.parseInt(st.nextToken()));

        ans = 1;

        for (int i = 0; i < N; i++) robot.add(0);

        solve();

        System.out.println(ans);
    }

    static void solve() {
        while (true) {
            rotate();

            moveRobot();

            if (belt.get(0) != 0) {
                robot.set(0, 1);
                belt.set(0, belt.get(0) - 1);
            }

            if (check()) return;

            ans++;
        }
    }

    static void rotate() {
        int tmp = belt.get(belt.size() - 1);
        belt.remove(belt.size() - 1);
        belt.add(0, tmp);

        robot.remove(robot.size() - 1);
        robot.add(0, 0);

        if (robot.get(N - 1) == 1) robot.set(N - 1, 0);
    }

    static void moveRobot() {
        for (int i = robot.size() - 2; i >= 0; i--) {
            if (robot.get(i) == 0) continue;
            else {
                if (robot.get(i + 1) == 1 || belt.get(i + 1) == 0) continue;

                robot.set(i, 0);
                robot.set(i + 1, 1);
                belt.set(i + 1, belt.get(i + 1) - 1);
            }
        }
    }

    static boolean check() {
        int cnt = 0;

        for (int i : belt) {
            if (i == 0) cnt++;

            if (cnt >= K) return true;
        }

        return false;
    }
}