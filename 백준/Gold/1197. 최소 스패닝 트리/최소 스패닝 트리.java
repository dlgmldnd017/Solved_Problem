import java.util.*;
import java.io.*;

class Node implements Comparable<Node>{
    int E, W;

    public Node(int E, int W){
        this.E = E;
        this.W = W;
    }

    public int compareTo(Node n){
        return this.W - n.W;
    }
}

public class Main{
    static int V, E, ans;
    static List<Node> list[];

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        list = new ArrayList[V+1];

        for(int i=1; i<=V; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=1; i<=E; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            list[A].add(new Node(B,C));
            list[B].add(new Node(A,C));
        }

        solve();

        System.out.println(ans);
    }

    static void solve(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        
        boolean visited[] = new boolean[V+1];

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(visited[cur.E]) continue;
            visited[cur.E] = true;
            ans += cur.W;

            for(Node next : list[cur.E]){
                pq.add(next);
            }
        }
    }
}