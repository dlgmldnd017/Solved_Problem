import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr, result;

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(sb);
    }

    static void solve() {
        result = new int[N];

        Stack<Integer> st = new Stack();

        for (int i = 0; i < N; i++) {
            while (!st.isEmpty() && arr[st.peek()] < arr[i]) result[st.pop()] = arr[i];

            st.push(i);
        }

        while (!st.isEmpty()) result[st.pop()] = -1;

        for (int i : result) sb.append(i).append(" ");
    }
}