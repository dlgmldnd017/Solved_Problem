import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Deque<Integer> dq;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            String command = br.readLine();

            N = Integer.parseInt(br.readLine());

            String number = br.readLine().replaceAll("\\[", "").replaceAll("\\]", "");

            dq = new ArrayDeque<>();

            if (!number.isEmpty()) {
                String[] numbers = number.split(",");
                for (String s : numbers) dq.addLast(Integer.parseInt(s));
            }

            solve(command);
        }

        System.out.println(sb);
    }

    static void solve(String command) {
        boolean isReversed = false;

        for (int i = 0; i < command.length(); i++) {
            char c = command.charAt(i);

            if (c == 'R') isReversed = !isReversed;
            else {
                if (dq.isEmpty()) {
                    sb.append("error").append("\n");
                    return;
                }

                if (isReversed) dq.removeLast();
                else dq.removeFirst();
            }
        }

        sb.append("[");

        if (isReversed) {
            while (!dq.isEmpty()) {
                sb.append(dq.removeLast());
                sb.append(",");
            }
        } else {
            while (!dq.isEmpty()) {
                sb.append(dq.removeFirst());
                sb.append(",");
            }
        }

        if (sb.charAt(sb.length() - 1) != '[') sb.deleteCharAt(sb.length() - 1);
        
        sb.append("]").append("\n");
    }
}