import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static String input[], str;
    static int K;
    static List<Character> list = new ArrayList<>();
    static Set<String> set = new HashSet<>();
    static Set<String> ans = new TreeSet<>();
    static boolean visited[];

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = new String[3];

        for (int i = 0; i < 3; i++) input[i] = br.readLine();

        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < 3; i++) {
            str = input[i];
            visited = new boolean[str.length()];

            solve(0, 0);
        }

        if(ans.size()==0) System.out.println(-1);
        else for (String s : ans) System.out.println(s);
    }

    static void solve(int depth, int idx) {
        if (depth == K) {
            String s = "";
            for (char c : list) s += c;
            if (set.contains(s)) ans.add(s);
            else set.add(s);
            return;
        }

        for (int i = idx; i < str.length(); i++) {
            if (visited[i]) continue;

            list.add(str.charAt(i));
            visited[i] = true;
            solve(depth + 1, i);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }
}