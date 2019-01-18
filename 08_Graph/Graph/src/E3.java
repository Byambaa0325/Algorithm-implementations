import java.io.*;
import java.util.*;

public class E3 {
    private int V; //vertices
    private int time;
    private LinkedList<Integer> adj[]; //Adj lists

    // Constructor
    E3(int v)
    {
        V = v;
        adj = new LinkedList[v];
        time = 0;
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    // add an edge into the graph
    void addEdgeUD(int v,int w)
    {
        adj[v].add(w);
        adj[w].add(v);
    }
    void addEdgeD(int v,int w)
    {
        adj[v].add(w);

    }

    void printGraph(){
        for(int i=1;i<adj.length;i++) {
            System.out.println("Edge "+i+":"+adj[i].toString());
        }
    }
    
    void DFS(int u, boolean visited[], int disc[],
                int low[], int parent[], boolean ap[])
    {

        int children = 0;

        visited[u] = true;
        time++;

        disc[u] = time;
        low[u] = time;

        Iterator<Integer> i = adj[u].iterator();
        while (i.hasNext())
        {
            int v = i.next();

            if (!visited[v])
            {
                children++;
                parent[v] = u;
                DFS(v, visited, disc, low, parent, ap);

                low[u]  = Math.min(low[u], low[v]);

                // Case 1
                if (parent[u] == -1 && children > 1)
                    ap[u] = true;

                // Case 2
                if (parent[u] != -1 && low[v] >= disc[u])
                    ap[u] = true;
            }

            else if (v != parent[u])
                low[u]  = Math.min(low[u], disc[v]);
        }
    }


    void AP()
    {

        boolean visited[] = new boolean[V];
        int disc[] = new int[V];
        int parent[] = new int[V];
        int low[] = new int[V];
        boolean ap[] = new boolean[V];

        for (int i = 0; i < V; i++)
        {
            parent[i] = -1;
            visited[i] = false;
            ap[i] = false;
        }

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                DFS(i, visited, disc, low, parent, ap);
            }
        }

        System.out.print("Articulation points: ");
        for (int i = 0; i < V; i++) {
            if (ap[i]) {
                System.out.print(i + ", ");
            }
        }
    }
    public static void main(String args[])
    {
        File file = new File("input.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int t=sc.nextInt();
        int size= sc.nextInt();
        int source= sc.nextInt();

        E3 g = new E3(size+1);


        for(int ca=1;ca<=t;ca++) {


            while(sc.hasNextInt()){
                g.addEdgeUD(sc.nextInt(), sc.nextInt());
            }

            g.printGraph();
            g.AP();
            System.out.println();

        }
    }
}
