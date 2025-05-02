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
        if (ch.length % 2 == 0) {
            int i = 0, j = ch.length - 1;

            while (i < j) {
                if (ch[i] != ch[j]) {
                    if (check(i, j)) sb.append("1\n");
                    else sb.append("2\n");
                    return;
                }
                i++;
                j--;
            }

            sb.append("0\n");
        } else {
            int i = 0, j = ch.length - 1;

            while (i != j) {
                if (ch[i] != ch[j]) {
                    if (check(i, j)) sb.append("1\n");
                    else sb.append("2\n");
                    return;
                }
                i++;
                j--;
            }

            sb.append("0\n");
        }
    }

    static boolean check(int i, int j) {
        int a = i + 1, b = j;

        while (a < b) {
            if (ch[a] != ch[b]) break;
            a++;
            b--;
        }

        if (a >= b) return true;

        a = i;
        b = j - 1;

        while (a < b) {
            if (ch[a] != ch[b]) return false;
            a++;
            b--;
        }

        return true;
    }
}