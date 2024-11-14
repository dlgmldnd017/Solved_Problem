import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        solve("");
    }

    static void solve(String str) {
        if (str.length() == N) {
            System.out.println(str);
            System.exit(0);
        }

        for (int i = 1; i <= 3; i++) {
            if (check(str + i)) solve(str + i);
        }
    }

    static boolean check(String str) {
        for (int i = 1; i <= str.length() / 2; i++) {
            String front = str.substring(str.length() - i * 2, str.length() - i);
            String back = str.substring(str.length() - i);

            if (front.equals(back)) return false;
        }

        return true;
    }
}
