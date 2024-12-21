import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int r, c, k, A[][], rowLen, colLen, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());

        A = new int[100][100];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) A[i][j] = Integer.parseInt(st.nextToken());
        }

        rowLen = colLen = 3;

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int i = 0; i <= 100; i++) {
            if (A[r][c] == k) {
                ans = i;
                return;
            }

            if (rowLen >= colLen) {
                int maxCol = 0;
                for (int j = 0; j < rowLen; j++) {
                    R(j);
                    maxCol = Math.max(maxCol, getRowLength(j));
                }
                colLen = maxCol;
            } else {
                int maxRow = 0;
                for (int j = 0; j < colLen; j++) {
                    C(j);
                    maxRow = Math.max(maxRow, getColLength(j));
                }
                rowLen = maxRow;
            }
        }

        ans = -1;
    }

    static void R(int idx) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < colLen; i++) {
            if (A[idx][i] == 0) continue; // 0은 무시
            map.put(A[idx][i], map.getOrDefault(A[idx][i], 0) + 1);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        map.entrySet()
                .stream()
                .map(entry -> new Node(entry.getKey(), entry.getValue()))
                .forEach(pq::add);

        int i = 0;
        while (i < 100 && !pq.isEmpty()) {
            Node n = pq.poll();
            A[idx][i++] = n.num;
            A[idx][i++] = n.cnt;
        }

        while (i < 100) A[idx][i++] = 0;
    }

    static void C(int idx) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < rowLen; i++) {
            if (A[i][idx] == 0) continue; // 0은 무시
            map.put(A[i][idx], map.getOrDefault(A[i][idx], 0) + 1);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        map.entrySet()
                .stream()
                .map(entry -> new Node(entry.getKey(), entry.getValue()))
                .forEach(pq::add);

        int i = 0;
        while (i < 100 && !pq.isEmpty()) {
            Node n = pq.poll();
            A[i++][idx] = n.num;
            A[i++][idx] = n.cnt;
        }

        while (i < 100) A[i++][idx] = 0;
    }

    // 각 행의 유효한 길이를 계산
    static int getRowLength(int row) {
        int len = 0;
        for (int i = 0; i < 100; i++) {
            if (A[row][i] == 0) break;
            len++;
        }
        return len;
    }

    // 각 열의 유효한 길이를 계산
    static int getColLength(int col) {
        int len = 0;
        for (int i = 0; i < 100; i++) {
            if (A[i][col] == 0) break;
            len++;
        }
        return len;
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