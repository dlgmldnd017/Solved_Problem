import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int A, B, N;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        int At = 0, Bt = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            int cnt = Integer.parseInt(st.nextToken());

            for (int j = 0; j < cnt; j++) {
                if(c == 'B'){
                    if(At >= t){
                        pq.add(new Node(At, c));
                        At += A;
                    }else{
                        pq.add(new Node(t, c));
                        At = t + A;
                    }
                }else{
                    if(Bt >= t){
                        pq.add(new Node(Bt, c));
                        Bt += B;
                    }else{
                        pq.add(new Node(t, c));
                        Bt = t + B;
                    }
                }
            }
        }

        solve();

        System.out.println(sb);
    }

    static void solve() {
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        int i = 1;
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.c == 'B') list1.add(i++);
            else list2.add(i++);
        }

        sb.append(list1.size() + "\n");
        for (int j : list1) sb.append(j + " ");

        sb.append("\n");

        sb.append(list2.size() + "\n");
        for (int j : list2) sb.append(j + " ");
    }
}

class Node implements Comparable<Node> {
    int t;
    char c;

    Node(int t, char c) {
        this.t = t;
        this.c = c;
    }

    public int compareTo(Node n) {
        if (this.t == n.t) return this.c - n.c;
        return this.t - n.t;
    }
}