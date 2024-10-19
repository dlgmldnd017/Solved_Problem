import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int idx;
    Node prev, next;

    Node(int idx) {
        this.idx = idx;
        this.prev = this.next = null;
    }
}

public class Main {
    static int N, M;
    static Node n[];
    static List<Integer> list = new ArrayList<>();
    static List<Integer> lis = new ArrayList<>();

    static StringBuilder sb = new StringBuilder();
    static int[] pos;  // 각 값이 LIS에서 들어간 위치 저장

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        n = new Node[N + 1];

        for (int i = 1; i < N + 1; i++) n[i] = new Node(i);
        for (int i = 1; i < N; i++) connectNode(n[i], n[i + 1]);

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            connectNode(n[i].prev, n[i].next);

            if (command.equals("A")) {
                connectNode(n[j].prev, n[i]);
                connectNode(n[i], n[j]);
            } else {
                connectNode(n[i], n[j].next);
                connectNode(n[j], n[i]);
            }
        }

        solve();
        System.out.println(sb);
    }

    static void solve() {
        Node head = n[1];
        while (head.prev != null) head = head.prev;
        while (head != null) {
            list.add(head.idx);
            head = head.next;
        }

        int[] arr = list.stream().mapToInt(i -> i).toArray();

        pos = new int[arr.length];
        findLISLength(arr);
        sb.append(N-lis.size()+"\n");

        // LIS 역추적하여 명령어 출력
        int num = 1;
        for (int i = 0; i < lis.size(); num++) {
            if (lis.get(i) == num) {
                i++;
                continue;
            }
            sb.append("A " + num + " " + lis.get(i) + "\n");
        }

        for (int i = num, j = lis.get(lis.size() - 1); i <= N; i++) {
            sb.append("B " + i + " " + j + "\n");
            j = i;
        }
    }

    // LIS 길이 구하는 함수
    static void findLISLength(int[] arr) {
        List<Integer> seq = new ArrayList<>();  // LIS 시퀀스 저장 리스트

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            int posIdx = Collections.binarySearch(seq, num);  // 이진 탐색으로 위치 찾기
            if (posIdx < 0) posIdx = -(posIdx + 1);

            if (posIdx < seq.size()) {
                seq.set(posIdx, num);
            } else {
                seq.add(num);
            }

            pos[i] = posIdx;  // 해당 값이 들어간 위치 기록
        }

        // LIS 수열 복원
        lis = new ArrayList<>();
        int currentPos = seq.size() - 1;

        for (int i = arr.length - 1; i >= 0; i--) {
            if (pos[i] == currentPos) {
                lis.add(arr[i]);
                currentPos--;
            }
        }

        Collections.reverse(lis);  // 수열이 역순이므로 다시 뒤집음
    }

    static void connectNode(Node start, Node end) {
        if (start != null) start.next = end;
        if (end != null) end.prev = start;
    }
}
