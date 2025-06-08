import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static String str;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            str = br.readLine();

            solve();
        }

        System.out.println(sb);
    }

    static void solve() {
        int left = 0, right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                if (check(left + 1, right) || check(left, right - 1)) sb.append("1").append("\n");
                else sb.append("2").append("\n");
                return;
            }

            left++;
            right--;
        }

        sb.append("0").append("\n");
    }

    static boolean check(int left, int right) {
        if (left > right) return false;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) return false;

            left++;
            right--;
        }

        return true;
    }
}