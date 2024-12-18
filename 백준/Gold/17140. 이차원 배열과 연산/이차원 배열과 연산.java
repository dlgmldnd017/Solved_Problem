import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int r, c, k, arr[][], rowLen, colLen, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());

        arr = new int[100][100];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        rowLen = colLen = 3;

        solve();

        if (ans == 101) ans = -1;
        System.out.println(ans);
    }

    static void solve() {
        while (ans <= 100) {
            if (arr[r][c] == k) return;

            ans++;

            if (rowLen >= colLen) for (int i = 0; i < rowLen; i++) R(i);
            else for (int i = 0; i < colLen; i++) C(i);
        }
    }

    static void R(int idx) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < colLen; i++) {
            if (arr[idx][i] == 0) continue;
            map.compute(arr[idx][i], (num, cnt) -> cnt == null ? 1 : cnt + 1);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        map.forEach((k, v) -> pq.add(new Node(k, v)));

        int i = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            arr[idx][i++] = cur.num;
            arr[idx][i++] = cur.cnt;
        }

        colLen = Math.max(colLen, i);

        while (i <= 99) {
            arr[idx][i++] = 0;
            arr[idx][i++] = 0;
        }
    }

    static void C(int idx) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < rowLen; i++) {
            if (arr[i][idx] == 0) continue;
            map.compute(arr[i][idx], (num, cnt) -> cnt == null ? 1 : cnt + 1);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        map.forEach((k, v) -> pq.add(new Node(k, v)));

        int i = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            arr[i++][idx] = cur.num;
            arr[i++][idx] = cur.cnt;
        }

        rowLen = Math.max(rowLen, i);

        while (i <= 99) {
            arr[i++][idx] = 0;
            arr[i++][idx] = 0;
        }
    }
}

class Node implements Comparable<Node> {
    int num, cnt;

    Node(int num, int cnt) {
        this.num = num;
        this.cnt = cnt;
    }

    public int compareTo(Node n) {
        if (this.cnt == n.cnt) return this.num - n.num;
        return this.cnt - n.cnt;
    }
}