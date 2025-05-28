import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[E];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(from, to, weight);
        }

        Arrays.sort(edges);
        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) parent[i] = i;

        int totalWeight = 0;
        int count = 0;

        for (Edge edge : edges) {
            if (union(edge.from, edge.to)) {
                totalWeight += edge.weight;
                count++;
                if (count == V - 1) break;
            }
        }

        System.out.println(totalWeight);
    }

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return false;
        parent[rootB] = rootA;
        return true;
    }
}