import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static char[] ch;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String input = br.readLine();

            ch = input.toCharArray();

            solve();
        }

        System.out.println(sb);
    }

    static void solve() {
        int left = 0, right = ch.length - 1;

        while (left < right) {
            if (ch[left] != ch[right]) {
                if (check(left + 1, right) || check(left, right - 1)) sb.append("1\n");
                else sb.append("2\n");

                return;
            }

            left++;
            right--;
        }

        sb.append("0\n");
    }

    static boolean check(int left, int right) {
        while(left < right) {
            if (ch[left] != ch[right]) return false;

            left++;
            right--;
        }

        return true;
    }
}