import java.io.*;
import java.util.*;


class E1
{
    private int V; //vertices
    private LinkedList<Integer> adj[]; //Adj lists

    // Constructor
    E1(int v)
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

    void DFS(int v,ArrayList<Integer> visited)
    {


        if(visited.size()==adj.length-1){
            System.out.println(visited.toString());

        }


        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext())
        {
            int n = i.next();

            if(visited.indexOf(n)==-1) {
                visited.add(n);
                DFS(n, visited);
                visited.remove(new Integer(n));
            }

        }
    }

    void printGraph(){
        for(int i=1;i<adj.length;i++) {
            System.out.println("Edge "+i+":"+adj[i].toString());
        }
    }


    void DFS_init(int v)
    {
        ArrayList<Integer> visited = new ArrayList<>();
        visited.add(v);

        for(int i=0;i<adj.length;i++){
           Collections.sort(adj[i]);
        }
        System.out.println("Sorted in alphabetical order:");
        this.printGraph();


        DFS(v, visited);
    }


    void BFS(int source)
    {

        boolean visited[] = new boolean[V];


        LinkedList<Integer> queue = new LinkedList<>();


        visited[source]=true;
        queue.add(source);

        while (queue.size() != 0)
        {

            source = queue.poll();
            System.out.print(source+" ");


            Iterator<Integer> i = adj[source].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
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

        E1 g = new E1(size+1);


        for(int ca=1;ca<=t;ca++) {


            while(sc.hasNextInt()){
            g.addEdgeUD(sc.nextInt(), sc.nextInt());
            }

            g.printGraph();
            g.BFS(source);
            System.out.println();
            g.DFS_init(source);
        }
    }
}

