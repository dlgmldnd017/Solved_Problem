import java.util.*;
import java.io.*;

class Node implements Comparable<Node>{
    int E, W;

    public Node(int E, int W){
        this.E = E;
        this.W = W;
    }

    public int compareTo(Node n){
        return n.W - this.W;
    }
}

public class Main{
    static int N, maxIdx, ans;
    static List<Node> list[];
    static boolean visited[];

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        list = new ArrayList[N+1];

        for(int i=0; i<=N; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            list[A].add(new Node(B, C));
            list[B].add(new Node(A, C));
        }

        solve(1);
        solve(maxIdx);

        System.out.println(ans);
    }

    static void solve(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        visited = new boolean[N+1];

        int sum=0, idx=0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(visited[cur.E]) continue;
            visited[cur.E] = true;

            if(sum < cur.W) {
                sum = cur.W;
                idx = cur.E;
            }

            for(Node next : list[cur.E]){
                if(visited[next.E]) continue;

                pq.add(new Node(next.E, next.W+cur.W));
            }
        }

        maxIdx = idx;
        ans = sum;
    }
}