import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static String str, target;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();

        target = br.readLine();

        solve();

        System.out.println(sb.length() == 0 ? "FRULA" : sb);
    }

    static void solve() {
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));

            if (sb.length() >= target.length()) {
                boolean flag = true;

                for (int j = 0; j < target.length(); j++) {
                    if (sb.charAt(sb.length() - target.length() + j) != target.charAt(j)) {
                        flag = false;
                        break;
                    }
                }

                if (!flag) continue;

                sb.delete(sb.length() - target.length(), sb.length());
            }
        }
    }
}