import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        solve(0, 0);

        System.out.println(sb);
    }

    static void solve(int depth, int num) {
        if (depth == N) {
            if (isPrime(num)) sb.append(num + "\n");
            return;
        }

        for (int i = 1; i <= 9; i++) {
            int next = num * 10 + i;
            if (isPrime(next)) solve(depth + 1, next);
        }
    }

    static boolean isPrime(int num) {
        if (num < 2) return false;

        for (int i = 2; i <= Math.sqrt(num); i++) if (num % i == 0) return false;

        return true;
    }
}
