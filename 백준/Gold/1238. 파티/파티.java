import java.util.*;
import java.io.*;

class Node implements Comparable<Node>{
    int end, weight;

    public Node(int end, int weight){
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node node){
        return weight - node.weight;
    }
}

public class Main {
    static StringBuilder sb = new StringBuilder();

    static int N, M, X;

    static ArrayList<Node> map1[], map2[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken())+1;
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        map1 = new ArrayList[N];
        map2 = new ArrayList[N];

        for(int i=1; i<N; i++){
            map1[i] = new ArrayList<>();
            map2[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            map1[y].add(new Node(x, w));
            map2[x].add(new Node(y, w));
        }

        int[] dist1 = solve(map1);
        int[] dist2 = solve(map2);

        int max = Integer.MIN_VALUE;

        for(int i=1; i<N; i++){
            max = Math.max(max, dist1[i] + dist2[i]);
        }

        System.out.println(max);
    }

    static int[] solve(ArrayList<Node> map[]){
        int dist[] = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[X] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(X, 0));

        boolean[] visited = new boolean[N];

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(visited[cur.end]) continue;
            visited[cur.end] = true;

            for(Node next : map[cur.end]){
                if(!visited[next.end] && dist[next.end] > dist[cur.end] + next.weight){
                    dist[next.end] = dist[cur.end] + next.weight;
                    pq.add(new Node(next.end, dist[next.end]));
                }
            }
        }

        return dist;
    }
}