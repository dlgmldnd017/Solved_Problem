import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    static int n;
    static String p;
    static Deque<Integer> q;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            q = new ArrayDeque<>();

            p = br.readLine();

            n = Integer.parseInt(br.readLine());

            String input = br.readLine().replaceAll("\\[", "").replaceAll("]", "");

            if (n != 0) {
                String[] arr = input.split(",");
                for (String str : arr) q.offer(Integer.parseInt(str));
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
                if (q.isEmpty()) {
                    sb.append("error").append("\n");
                    return;
                }

                if (isReversed) q.pollLast();
                else q.poll();
            }
        }

        sb.append("[");

        if (isReversed) {
            while (!q.isEmpty()) sb.append(q.pollLast()).append(",");
        } else {
            while (!q.isEmpty()) sb.append(q.poll()).append(",");
        }

        if (sb.charAt(sb.length() - 1) != '[') sb.deleteCharAt(sb.length() - 1);
        sb.append("]").append("\n");
    }
}