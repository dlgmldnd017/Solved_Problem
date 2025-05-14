import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static String input;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = br.readLine();

        visited = new boolean[input.length()];

        solve();

        System.out.println(sb);
    }

    static void solve() {
        recur(0, input.length() - 1);
    }

    static void recur(int start, int end) {
        if (start > end) return;

        int mid = start;

        for (int i = start; i <= end; i++) {
            if (input.charAt(i) < input.charAt(mid)) mid = i;
        }

        visited[mid] = true;

        for (int i = 0; i < input.length(); i++) {
            if (visited[i]) sb.append(input.charAt(i));
        }

        sb.append("\n");

        recur(mid + 1, end);
        recur(start, mid - 1);
    }
}