import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static int X;
    static String input;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        X = Integer.parseInt(br.readLine());

        input = br.readLine();

        solve();

        System.out.println(sb);
    }

    static void solve() {
        StringBuilder head = new StringBuilder();
        StringBuilder tail = new StringBuilder();

        Map<String, Integer> map = new HashMap<>();

        List<String> list = new ArrayList<>();
        list.add(input);

        int cnt = 0, len = input.length(), x = X;

        while (x > 0) {
            head.setLength(0);
            tail.setLength(0);

            if (len % 2 == 0) {
                for (int i = len - 1; i >= 0; i -= 2) tail.append(input.charAt(i));
                for (int i = 0; i < len; i += 2) head.append(input.charAt(i));
            } else {
                for (int i = len - 2; i >= 0; i -= 2) tail.append(input.charAt(i));
                for (int i = 0; i < len; i += 2) head.append(input.charAt(i));
            }

            head.append(tail);
            input = head.toString();
            list.add(input);

            if (map.get(input) == null) map.put(input, cnt++);
            else break;

            x--;
        }

        if (x > 0) System.out.println(list.get(X % cnt));
        else System.out.println(input);
    }
}