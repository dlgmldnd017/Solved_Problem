import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int M;
    static TreeSet<Integer> set = new TreeSet<>();

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            String command;
            int x;

            st = new StringTokenizer(br.readLine());
            command = st.nextToken();

            switch (command) {
                case "add":
                    x = Integer.parseInt(st.nextToken());
                    set.add(x);
                    break;

                case "remove":
                    x = Integer.parseInt(st.nextToken());
                    if (set.contains(x)) set.remove(x);
                    break;

                case "check":
                    x = Integer.parseInt(st.nextToken());
                    if (set.contains(x)) sb.append("1\n");
                    else sb.append("0\n");
                    break;

                case "toggle":
                    x = Integer.parseInt(st.nextToken());
                    if (set.contains(x)) set.remove(x);
                    else set.add(x);
                    break;

                case "all":
                    set = new TreeSet<>();
                    for (int j = 1; j <= 20; j++) set.add(j);
                    break;

                case "empty":
                    set = new TreeSet<>();
                    break;
            }
        }

        System.out.println(sb);
    }
}