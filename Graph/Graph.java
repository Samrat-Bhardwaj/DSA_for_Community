import java.util.ArrayList;
import java.util.LinkedList;


class Graph {
    static class Edge {
        int v;
        int w;

        public Edge(int v, int w){
            this.v = v;
            this.w = w;
        }

        public String toString(){
            return "( " + this.v + "," + this.w + " )";
        }
    }

    public static void addEdge(ArrayList<Edge> graph[], int u, int v, int w){
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w)); // remove this line and this is now uni directional graph (directed)
    }

    public static void displayGraph(ArrayList<Edge> graph[]){
        for(int i=0; i<graph.length; i++){
            System.out.print(i + " -> ");
            for(Edge e: graph[i]){
                System.out.print(e);
            }
            System.out.println();
        }
    }

    public static int searchVtx(int u, int v, ArrayList<Edge> graph[]){
        for(int i=0; i<graph[u].size(); i++){
            Edge edge = graph[u].get(i);

            if(edge.v == v){
                return i;
            }
        }

        return -1;
    }

    public static boolean hasPath(int src, int des, ArrayList<Edge> graph[], boolean[] vis){
        if(src == des){
            return true;
        }

        vis[src] = true;

        for(Edge e: graph[src]){
            int nbr = e.v;
            if(!vis[nbr]){
                boolean canNbrReach = hasPath(nbr, des, graph, vis);
                if(canNbrReach == true){
                    return true;
                }
            }
        }

        return false;
    } 

    public static void allPaths(int src, int des, ArrayList<Edge> graph[], boolean[] vis, String psf, int wsf){
        if(src == des){
            System.out.println(psf + des +" @ " + wsf);
            return;
        }

        vis[src] = true;

        for(Edge e: graph[src]){
            int nbr = e.v;
            if(!vis[nbr]){
                allPaths(nbr, des, graph, vis, psf + src + " -> ", wsf + e.w);
            }
        }

        vis[src] = false;        
    } 

    // find path from src to des with maximum weight 
    public static void maxWeightPath(int src, int des, ArrayList<Edge> graph[], boolean[] vis, String psf, int wsf){
        
    } 

    public static int hamiltonianPath(int src, int osrc, ArrayList<Edge> graph[], boolean[] vis, int edgeCount, String psf){
        if(edgeCount == graph.length - 1){ // edgeCount = N-1
            // path or cycle
            if(searchVtx(src, osrc, graph)!=-1){ // hamiltonian cycle
                System.out.println("Hamiltonian Cycle: " + psf + src);
            } else {
                System.out.println("Hamiltonian Path: " + psf + src);
            }
            return 1;
        }

        vis[src] = true;
        int ans = 0;
        for(Edge e: graph[src]){
            int nbr = e.v;
            if(!vis[nbr]){
                ans+=hamiltonianPath(nbr, osrc, graph, vis, edgeCount+1, psf+src+" -> ");
            }
        }

        vis[src] = false;
        return ans;
    }


    public static void main(String[] args) {
        int N = 7;

        ArrayList<Edge> graph[] = new ArrayList[N];

        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
        }

        addEdge(graph,0,1,10);
        addEdge(graph,0,2,5);
        addEdge(graph,1,3,3);
        addEdge(graph,2,3,7);
        addEdge(graph,1,4,1);
        addEdge(graph,4,5,4);
        addEdge(graph,5,6,9);
        addEdge(graph,4,6,11);
        addEdge(graph,3,5,8);


        // displayGraph(graph);

        boolean[] vis = new boolean[N];
        // System.out.println("Has Path->"+hasPath(0,6,graph,vis));
        // allPaths(0,6,graph,vis,"",0);
        System.out.println("Total Hamiltonian Paths: "+ hamiltonianPath(0, 0, graph, vis, 0, ""));
    }
}
