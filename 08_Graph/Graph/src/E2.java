import java.io.*;
import java.util.*;


class E2
{
    private int V; //vertices
    private LinkedList<Integer> adj[]; //Adj lists

    // Constructor
    E2(int v)
    {
        V = v;
        adj = new LinkedList[v];
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


    void topologicalSortUtil(int v, boolean visited[],
                             Stack stack)
    {
        visited[v] = true;
        Integer u;

        Iterator<Integer> i = adj[v].iterator();

        while (i.hasNext())
        {
            u = i.next();
            if (!visited[u])
                topologicalSortUtil(u, visited, stack);
        }

        stack.push(v);
    }

    void topologicalSort()
    {
        Stack build = new Stack();

        boolean visited[] = new boolean[V];
        for (int i = 1; i < V; i++) {
            visited[i] = false;
        }

        for (int i = 1; i < V; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, build);
            }
        }

        while (!build.empty()) {
            System.out.print(build.pop() + " ");
        }
    }


    public static void main(String args[])
    {
        File file = new File("input2.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int t=sc.nextInt();
        int size= sc.nextInt();
        int source= sc.nextInt();

        E2 g = new E2(size+1);


        for(int ca=1;ca<=t;ca++) {


            while(sc.hasNextInt()){
                g.addEdgeD(sc.nextInt(), sc.nextInt());
            }

            g.printGraph();
            g.topologicalSort();
            System.out.println();

        }
    }
}

