import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int L, C;
    static String s[], mos[] = {"a", "e", "i", "o", "u"};
    static List<String> list = new ArrayList<>();
    static boolean visited[];

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        s = new String[C];

        String str = br.readLine();

        s = str.split(" ");

        Arrays.sort(s);

        visited = new boolean[C];

        solve(0, 0);

        System.out.println(sb);
    }

    static void solve(int depth, int idx) {
        if (depth == L) {
            int mo = 0, ja = 0;

            for (String str : list) {
                if (str.equals("a") || str.equals("e") || str.equals("i") || str.equals("o") || str.equals("u")) mo++;
                else ja++;
            }

            if (mo < 1 || ja < 2) return;

            for (String str : list) sb.append(str);
            sb.append("\n");

            return;
        }

        for (int i = idx; i < C; i++) {
            if (visited[i]) continue;

            list.add(s[i]);
            visited[i] = true;
            solve(depth + 1, i);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }
}