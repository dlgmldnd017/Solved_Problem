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
    static int V, E, K, dist[];
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

        K = Integer.parseInt(br.readLine());

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[u].add(new Node(v, w));
        }

        solve();

        printDist();
    }

    static void solve(){
        dist = new int[V+1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(K, 0));

        boolean visited[] = new boolean[V+1];

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(visited[cur.E]) continue;
            visited[cur.E] = true;

            for(Node next : list[cur.E]){
                if(!visited[next.E] && dist[next.E] > next.W + dist[cur.E]){
                    dist[next.E] = next.W + dist[cur.E];
                    pq.add(new Node(next.E, dist[next.E]));
                }
            }
        }
    }

    static void printDist(){
        for(int i=1; i<=V; i++){
            if(dist[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(dist[i]);
        }
    }
}