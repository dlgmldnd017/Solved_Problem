import java.util.*;
import java.io.*;

class Node{
    int end, color;

    public Node(int end, int color){
        this.end = end;
        this.color = color;
    }
}

public class Main {
    static StringBuilder sb = new StringBuilder();

    static int V, E;
    static ArrayList<Node> map[];
    static int c[];
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for(int T=1; T<=t; T++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken())+1;
            E = Integer.parseInt(st.nextToken());

            map = new ArrayList[V];

            for(int i=1; i<V; i++){
                map[i] = new ArrayList<>();
            }

            for(int i=0; i<E; i++){
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());

                map[y].add(new Node(x, -1));
                map[x].add(new Node(y, -1));
            }

            c = new int[V];
            visited = new boolean[V];

            int ans = 0;

            for(int i=1; i<V; i++){
                if(!visited[i]) solve(i);

                for(Node node : map[i]){
                    if(c[i] == c[node.end]){
                        ans=1;
                        break;
                    }
                }

                if(ans==1) break;
            }

            if(ans==1) sb.append("NO\n");
            else sb.append("YES\n");
        }

        System.out.println(sb);
    }

    static void solve(int start){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, 0));

        while(!q.isEmpty()){
            Node cur = q.poll();
            c[cur.end] = cur.color;

            if(visited[cur.end]) continue;
            visited[cur.end] = true;

            int color = -1;

            if(cur.color == 0) color = 1;
            else color = 0;

            for(Node node : map[cur.end]){
                if(visited[node.end]) continue;

                q.add(new Node(node.end, color));
            }
        }
    }
}