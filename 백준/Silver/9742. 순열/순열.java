import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static String input;
    static char arr[];
    static int idx, cnt;
    static boolean check, visited[];

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String line;
        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            input = st.nextToken();
            idx = Integer.parseInt(st.nextToken());

            cnt = 0;
            check = false;
            arr = new char[input.length()];
            visited = new boolean[input.length()];

            solve(0);

            if (!check) sb.append(input + " " + idx + " = No permutation\n");
        }

        System.out.println(sb);
    }

    static void solve(int depth) {
        if (check) return;

        if (depth == input.length()) {
            cnt++;
            if (cnt == idx) {
                sb.append(input + " " + idx + " = " + new String(arr) + "\n");
                check = true;
            }

            return;
        }

        for (int i = 0; i < input.length(); i++) {
            if (visited[i]) continue;

            visited[i] = true;
            arr[depth] = input.charAt(i);
            solve(depth + 1);
            visited[i] = false;
        }
    }
}