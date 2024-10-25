import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        System.out.println(solve(N, 0));
    }

    static int solve(int n, int cnt) {
        if (n < 2) return cnt;

        return Math.min(solve(n / 2, cnt + 1 + (n % 2)), solve(n / 3, cnt + 1 + (n % 3)));
    }
}