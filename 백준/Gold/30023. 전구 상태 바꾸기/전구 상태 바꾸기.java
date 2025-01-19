import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        char bulbs[] = br.readLine().toCharArray();

        ans = 200_000;

        for (int i = 0; i < 3; i++) {
            ans = Math.min(ans, solve(bulbs.clone(), bulbs[0]) + i);
            flipBulbs(bulbs, 0);
        }

        System.out.println(ans == 200_000 ? -1 : ans);
    }

    static int solve(char bulbs[], char target) {
        int cnt = 0;

        for (int i = 1; i <= N - 3; i++) {
            while (target != bulbs[i]) {
                flipBulbs(bulbs, i);
                cnt++;
            }
        }

        for (int i = 0; i < N; i++) {
            if (target == bulbs[i]) continue;

            cnt = 200_000;
            break;
        }

        return cnt;
    }

    static void flipBulbs(char[] bulbs, int start) {
        for (int i = start; i < start + 3; i++) bulbs[i] = getNextColor(bulbs[i]);
    }

    static char getNextColor(char c) {
        switch (c) {
            case 'R':
                return 'G';
            case 'G':
                return 'B';
            default:
                return 'R';
        }
    }
}