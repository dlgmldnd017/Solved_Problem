import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, N;
    static String op[] = {"+", "-", " "};
    static List<String> list = new ArrayList<>();
    static TreeSet<String> set;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            set = new TreeSet<>();

            solve(0);

            for (String s : set) sb.append(s + "\n");

            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void solve(int depth) {
        if (depth == N - 1) {
            if (!check()) return;
            return;
        }

        for (int i = 0; i < 3; i++) {
            list.add(op[i]);
            solve(depth + 1);
            list.remove(list.size() - 1);
        }
    }

    static boolean check() {
        String calc = "1", ans = "1";

        for (int i = 1; i < N; i++) {
            String s = list.get(i - 1);
            ans += list.get(i - 1) + (i + 1);

            if (s.equals(" ")) calc += (i + 1);
            else calc += s + (i + 1);
        }

        String operation = "", num = "";
        List<Integer> tmp = new ArrayList<>();

        for (int i = 0; i < calc.length(); i++) {
            char c = calc.charAt(i);

            switch (c) {
                case '+':
                    tmp.add(Integer.parseInt(num));
                    num = "";
                    operation += "+";
                    break;
                case '-':
                    tmp.add(Integer.parseInt(num));
                    num = "";
                    operation += "-";
                    break;
                default:
                    num += c;
                    break;
            }
        }

        tmp.add(Integer.parseInt(num));

        int sum = tmp.get(0);
        for (int i = 0; i < tmp.size() - 1; i++) {
            switch (operation.charAt(i)) {
                case '+':
                    sum += tmp.get(i + 1);
                    break;
                case '-':
                    sum -= tmp.get(i + 1);
                    break;
            }
        }

        if (sum == 0) set.add(ans);

        return true;
    }
}