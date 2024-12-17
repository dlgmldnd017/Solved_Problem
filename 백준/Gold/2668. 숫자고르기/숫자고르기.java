import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, arr[];
    static List<Integer> list = new ArrayList<>();
    static boolean visited[];

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];

        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            visited[i] = true;
            solve(i, i);
            visited[i] = false;
        }

        Collections.sort(list);

        sb.append(list.size() + "\n");
        for (int i : list) sb.append(i + "\n");

        System.out.println(sb);
    }

    static void solve(int start, int target) {
        if (!visited[arr[start]]) {
            visited[arr[start]] = true;
            solve(arr[start], target);
            visited[arr[start]] = false;
        }

        if (arr[start] == target) list.add(target);
    }
}