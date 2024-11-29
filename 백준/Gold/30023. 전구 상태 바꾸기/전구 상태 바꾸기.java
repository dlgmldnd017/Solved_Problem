import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        String input = br.readLine();

        char[] bulbs = input.toCharArray();

        ans = 1_000_000;

        for (int i = 0; i < 3; i++) {
            ans = Math.min(ans, solve(bulbs, bulbs[0]) + i);
            flipBulbs(bulbs, 0);
        }

        if (ans == 1_000_000) ans = -1;
        System.out.println(ans);
    }

    static int solve(char c[], char target) {
        char[] bulbs = Arrays.copyOf(c, N);
        int cnt = 0;

        for (int i = 1; i <= N - 3; i++) {
            while (target != bulbs[i]) {
                flipBulbs(bulbs, i);
                cnt++;
            }
        }

        for (int i = 0; i < N; i++) {
            if (target == bulbs[i]) continue;

            cnt = 1_000_000;
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