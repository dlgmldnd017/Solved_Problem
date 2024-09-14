import java.util.*;
import java.io.*;

class Node{
    int E, C;

    public Node(int E, int C){
        this.E = E;
        this.C = C;
    }
}

public class Main{
    static int K, V, E, C[];
    static boolean visited[];
    static List<Node> list[];

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());

        for(int k=1; k<=K; k++){
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            list = new ArrayList[V+1];

            for(int i=1; i<=V; i++){
                list[i] = new ArrayList<>();
            }

            for(int i=0; i<E; i++){
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());

                list[A].add(new Node(B, -1));
                list[B].add(new Node(A, -1));
            }

            C = new int[V+1];
            visited = new boolean[V+1];

            int ans = 0;

            for(int i=1; i<=V; i++){
                if(!visited[i]) solve(i);

                for(Node n : list[i]){
                    if(C[i] == C[n.E]){
                        ans = 1;
                        break;
                    }
                }

                if(ans == 1) break;
            }

            if(ans == 1) sb.append("NO\n");
            else sb.append("YES\n");
        }

        System.out.println(sb);
    }

    static void solve(int startV){
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(startV, 0));

        while(!q.isEmpty()){
            Node cur = q.poll();
            C[cur.E] = cur.C;

            if(visited[cur.E]) continue;
            visited[cur.E] = true;

            int nextC;
            if(cur.C == 0) nextC = 1;
            else nextC = 0;

            for(Node n : list[cur.E]){
                if(visited[n.E]) continue;
                q.add(new Node(n.E, nextC));
            }
        }
    }
}