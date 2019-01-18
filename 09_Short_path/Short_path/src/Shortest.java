import java.io.*;
import java.util.*;


class Shortest
{
    private int V; //vertices
    private LinkedList<Integer> adj[]; //Adj lists
    private int[][] weights;

    // Constructor
    Shortest(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
        weights=new int[v][v];

    }

    // add an edge into the graph
    void addEdgeUD(int v,int w,int weigth)
    {
        adj[v].add(w);
        adj[w].add(v);
        weights[v][w] = weigth;
        weights[w][v] = weigth;
    }
    void addEdgeD(int v,int w, int weigth){
        adj[v].add(w);
        weights[v][w] = weigth;
    }


    void printGraph(){
        for(int i=1;i<V;i++) {
            System.out.println("Edge "+i+":"+adj[i].toString());
            System.out.println(Arrays.toString(weights[i]).replaceAll("0, ",""));
        }
    }
    void sortList(){
        for(int i=1;i<V;i++){
            Collections.sort(adj[i]);
        }
    }


    void djikstra(int source){
        ArrayList<Integer> Q = new ArrayList<>();
        int[] dist= new int[V];
        boolean[] pi = new boolean[V];
        for(int i=1;i<V;i++){
            dist[i]= Integer.MAX_VALUE;
            Q.add(i);
            pi[i]=false;
        }
        pi[source]=true;
        dist[source]=0;
        while(!Q.isEmpty()){
            int u=Q.get(0);
            Q.remove(0);

            Iterator<Integer> it = adj[u].listIterator();
            while(it.hasNext()) {
                int v = it.next();
                if(dist[u]!=Integer.MAX_VALUE && dist[u]+ weights[u][v]<dist[v]){
                    dist[v]=dist[u]+weights[u][v];
                    pi[v]=true;
                }

            }

        }
        System.out.println("Djikstra has yielded: "+Arrays.toString(dist).replaceAll("2147483647","NA"));

    }
    void bellman(int source){
        int[] dist= new int[adj.length];
        for(int i=1;i<adj.length;i++){
            dist[i]= Integer.MAX_VALUE;
        }
        dist[source]=0;
        for( int iteration=1; iteration<adj.length-1;iteration++){
            for(int u=1;u<adj.length;u++){
                Iterator<Integer> it = adj[u].listIterator();
                while(it.hasNext()){
                    int v= it.next();
                if( dist[u]!=Integer.MAX_VALUE && dist[u]+weights[u][v]<dist[v]){
                    dist[v]= dist[u] + weights[u][v];
                }
                }
            }
        }
        System.out.println("Bellman has yielded: "+Arrays.toString(dist).replaceAll("2147483647","NA"));


    }
    void floyd(int source, int target){
        int[][] dist= new int[V][V];
        for (int u=0;u<V;u++){
            for(int v=0;v<V;v++){
                    dist[u][v] = Integer.MAX_VALUE;
                    System.out.print(dist[u][v]+", ");
            }
            System.out.println();
        }
        for(int u=1;u<V;u++){
            Iterator<Integer> it = adj[u].listIterator();
            while(it.hasNext()) {
                int v = it.next();
                dist[u][v]=weights[u][v];
            }
        }
        for(int k=1; k<V;k++){
            for(int i=1; i<V;i++){
                for(int j=1; j<V;j++){
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                    //System.out.print(" Index "+i+","+j +" at k ="+k+" = "+dist[i][k]+" + "+dist[k][j]);
                   /* try{System.in.read();}
                    catch(Exception e){

                    }                   */
            }
               //// System.out.println();
            }
           // System.out.println();

        }
        System.out.println("Floyd has yielded: "+Arrays.toString(dist[source]));
    }
    void floydWarshall(int source)
    {
        int dist[][] = new int[V][V];
        int i, j, k;

        for (i=0;i<V;i++){
            for(j=0;j<V;j++){
                dist[i][j] = Integer.MAX_VALUE;
                if(i==j) {
                    dist[i][j] = 0;
                }
            }
        }


        for (i = 0; i < V; i++) {
            for (j = 0; j < V; j++) {
                if(weights[i][j]!=0) {
                    dist[i][j] = weights[i][j];
                }

            }
        }


        for (k = 0; k < V; k++){
            for (i = 0; i < V; i++){
                for (j = 0; j < V; j++){
                    if (dist[i][k] + dist[k][j] < dist[i][j]&& dist[i][k]!=Integer.MAX_VALUE&&dist[k][j]!=Integer.MAX_VALUE)
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        // Print the shortest distance matrix

        System.out.println("Floyd has yielded: "+Arrays.toString(dist[source]).replaceAll("2147483647","NA"));


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
        int size=sc.nextInt();
        int edgeCount= sc.nextInt();
        int source= sc.nextInt();
        int target=sc.nextInt();

        Shortest g = new Shortest(size+1);


        for(int i=0;i<edgeCount;i++){
            g.addEdgeUD(sc.nextInt(), sc.nextInt(),sc.nextInt());
        }
        int[][] temp=g.weights;
        g.sortList();
        g.printGraph();
        g.floydWarshall(source);
        g.djikstra(source);
        g.weights=temp;
        g.bellman(source);
        g.weights=temp;
       // g.floyd(source,target);
        g.weights=temp;
        System.out.println();



    }
}

