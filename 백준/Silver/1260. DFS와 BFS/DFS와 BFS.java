import java.util.*;
import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    static int N, M, V;
    static int map[][];

    static boolean visited[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken())+1;
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            map[y][x] = 1;
            map[x][y] = 1;
        }

        visited = new boolean[N];
        dfs(V);

        sb.append("\n");

        visited = new boolean[N];
        bfs();

        System.out.println(sb);
    }

    static void dfs(int start){
        visited[start] = true;
        sb.append(start+" ");

        for(int i=1; i<N; i++){
            if(visited[i] || map[start][i]!=1) continue;
            dfs(i);
        }
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(V);

        visited[V] = true;
        sb.append(V + " ");

        while(!q.isEmpty()){
            int start = q.poll();

            for(int i=1; i<N; i++){
                if(visited[i] || map[start][i]!=1) continue;

                q.add(i);

                visited[i] = true;
                sb.append(i+" ");
            }
        }
    }
}