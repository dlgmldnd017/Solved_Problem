import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static String p;
    static List<Integer> list;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            list = new ArrayList<>();

            p = br.readLine();

            n = Integer.parseInt(br.readLine());

            String input = br.readLine().replaceAll("\\[", "").replaceAll("]", "");

            if (n != 0) {
                String[] arr = input.split(",");
                for (String str : arr) list.add(Integer.parseInt(str));
            }

            solve();
        }

        System.out.println(sb);
    }

    static void solve() {
        boolean isReversed = false;

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == 'R') isReversed = !isReversed;
            else {
                if (list.isEmpty()) {
                    sb.append("error").append("\n");
                    return;
                }

                if (isReversed) list.remove(list.size() - 1);
                else list.remove(0);
            }
        }

        sb.append("[");

        if (isReversed) {
            for (int i = list.size() - 1; i >= 0; i--) sb.append(list.get(i)).append(",");
        } else {
            for (int i : list) sb.append(i).append(",");
        }

        if (sb.charAt(sb.length() - 1) != '[') sb.deleteCharAt(sb.length() - 1);
        sb.append("]").append("\n");
    }
}