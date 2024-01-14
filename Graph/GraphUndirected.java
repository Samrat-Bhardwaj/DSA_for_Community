import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GraphUndirected {
    public static void addEdge(ArrayList<Integer> graph[], int u, int v){
        graph[u].add(v);
    }

    public static void topoDFS(int src, ArrayList<Integer> ans, boolean[] vis,ArrayList<Integer>[] graph){
        vis[src] = true;

        for(int nbr: graph[src]){
            if(!vis[nbr]){
                topoDFS(nbr, ans, vis, graph);
            }
        }

        ans.add(src);
    }


    public static ArrayList<Integer> topologicalSort(ArrayList<Integer>[] graph){
        ArrayList<Integer> ans = new ArrayList<>();

        int N = graph.length;
        
        boolean[] vis = new boolean[N];
        for(int i=0; i<N; i++){
            if(!vis[i]){
                topoDFS(i,ans,vis, graph);
            }
        }

        Collections.reverse(ans);
        return ans;
    }

    public static void topoDFS2(int src, ArrayList<Integer> ans, int[] vis,ArrayList<Integer>[] graph){
        if(vis[src] == 1){
            System.out.println("Cycle is present, no possible Ans!!!");
            return;
        } else if(vis[src]==2){
            return;
        }

        vis[src] = 1;

        for(int nbr: graph[src]){
            topoDFS2(nbr, ans, vis, graph);
        }

        vis[src] = 2;
        ans.add(src);
    }

    // we will know if there is any cycle
    public static ArrayList<Integer> topologicalSortCycle(ArrayList<Integer>[] graph){
        ArrayList<Integer> ans = new ArrayList<>();

        int N = graph.length;
        
        int[] vis = new int[N];
        for(int i=0; i<N; i++){
            if(vis[i] == 0){
                topoDFS2(i,ans,vis, graph);
            }
        }

        Collections.reverse(ans);
        return ans;
    }
    public static void main(String[] args) {
        int N = 8;
        ArrayList<Integer>[] graph = new ArrayList[N];

        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
        }

        addEdge(graph,0,3);
        addEdge(graph,3,5);
        addEdge(graph,1,3);
        addEdge(graph,1,4);
        addEdge(graph,2,4);
        addEdge(graph,4,6);
        addEdge(graph,3,6);
        addEdge(graph,3,7);
        addEdge(graph,7,2);
        addEdge(graph,6,7);

        System.out.println(topologicalSortCycle(graph));
    }
}
