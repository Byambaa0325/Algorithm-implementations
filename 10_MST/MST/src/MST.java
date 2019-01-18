import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.*;
import java.lang.*;
import java.io.*;

class MST {

    private final int V;
    private final ArrayList<Edge> edge;
    private final LinkedList<Integer>[] adj; //Adj lists
    private final int[][] weights;


    private final String styleSheet =
            "edge {" +
                    "	fill-color: black;" +
                    "}" +
                    "edge.mst {" +
                    "	fill-color: red;" +
                    "}";
    private final String styleSheet1 =
            "edge {" +
                    "	fill-color: black;" +
                    "}" +
                    "edge.sp {" +
                    "	fill-color: red;" +
                    "}";


    private MST(int v) {
        V = v;
        edge = new ArrayList<>();
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
        weights = new int[v][v];

    }

    private int addEdgeUd(int id, int from, int to, int weight) {
        edge.add(new Edge(id, from, to, weight));

        adj[from].add(to);
        adj[to].add(from);
        weights[from][to] = weight;
        weights[to][from] = weight;

        return edge.size() - 1;
    }

    private Edge findEdge(int from, int to) {
        for (int i = 0; i < edge.size(); i++) {
            if (edge.get(i).from == from) {
                if (edge.get(i).to == to) {
                    return edge.get(i);
                }
            }
            if (edge.get(i).to == to) {
                if (edge.get(i).from == from) {
                    return edge.get(i);
                }
            }
        }
        return null;
    }

    void djikstra(int source) {
        ArrayList<Integer> Q = new ArrayList<>();
        int[] dist = new int[V];
        boolean[] pi = new boolean[V];
        for (int i = 1; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            Q.add(i);
            pi[i] = false;
        }
        pi[source] = true;
        dist[source] = 0;
        while (!Q.isEmpty()) {
            int u = Q.get(0);
            Q.remove(0);

            Iterator<Integer> it = adj[u].listIterator();
            while (it.hasNext()) {
                int v = it.next();
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weights[u][v] < dist[v]) {
                    dist[v] = dist[u] + weights[u][v];
                    pi[v] = true;
                }

            }

        }
        System.out.println("Djikstra has yielded: " + Arrays.toString(dist).replaceAll("2147483647", "NA"));

    }

    private Graph graphToLabyrith(int size, Graph cube, MST theGraph) {
        Graph Labyrith = new SingleGraph("The Labyrith");
        Labyrith.setStrict(false);
        Labyrith.setAutoCreate(true);
        Labyrith.addAttribute("ui.stylesheet", theGraph.styleSheet1);
        int verticlePerRow, verticlePerColumn;
        verticlePerRow = verticlePerColumn = (int) Math.sqrt(size);
        int CubeId = 0;
        int CubePerRow, CubePerColumn;
        CubePerColumn = CubePerRow = verticlePerRow + 1;
        int[][] cubeIds = new int[CubePerRow][CubePerColumn];

      /*  for(int i = 0;i < verticlePerRow+1; i++){
            for (int j = 0; j < verticlePerColumn+1; j++){
                cubeIds[i][j]=CubeId++;
                Labyrith.addNode(""+cubeIds[i][j]);
            }
        }
*/
        for (int i = 0; i <= verticlePerRow + 1; i++) {
            for (int j = 0; j <= verticlePerColumn + 1; j++) {
                int verticleId = (i - 1) * verticlePerRow + (j - 1);

                //Upper
                Edge edge1 = theGraph.findEdge(verticleId, verticleId + 1);
                //Left
                Edge edge2 = theGraph.findEdge(verticleId, verticleId + verticlePerRow);
                //Bottom
                Edge edge3 = theGraph.findEdge(verticleId + verticlePerRow, verticleId + verticlePerRow + 1);
                //Right
                Edge edge4 = theGraph.findEdge(verticleId + verticlePerRow + 1, verticleId + 1);

             /*  if(edge1!=null){
                    if(cube.getEdge(edge1.id).getAttribute("ui.class")!="mst"){
                        Labyrith.addEdge(CubeId+","+(CubeId-CubePerRow),""+CubeId,""+(CubeId-CubePerRow));
                        //System.out.println(Labyrith.getEdge(CubeId+","+(CubeId-CubePerRow)).toString());
                    }
                }
                if(edge2!=null){
                    if(cube.getEdge(edge2.id).getAttribute("ui.class")!="mst"){
                        Labyrith.addEdge(CubeId+","+(CubeId-1),""+CubeId,""+(CubeId-1));
                        //System.out.println(Labyrith.getEdge(CubeId+","+(CubeId-1)).toString());
                    }
                }*/
                if (edge3 != null) {
                    if (cube.getEdge(edge3.id).getAttribute("ui.class") != "mst") {
                        Labyrith.addEdge(CubeId + "," + (CubeId + 1), "" + CubeId, "" + (CubeId + 1));
                        System.out.println(Labyrith.getEdge(CubeId + "," + (CubeId + 1)).toString());
                    }
                } else if (i == verticlePerColumn || j == verticlePerColumn) {
                    Labyrith.addEdge(CubeId + "," + (CubeId + 1), "" + CubeId, "" + (CubeId + 1));
                    System.out.println(Labyrith.getEdge(CubeId + "," + (CubeId + 1)).toString());
                }
                if (edge4 != null) {
                    if (cube.getEdge(edge4.id).getAttribute("ui.class") != "mst") {
                        Labyrith.addEdge(CubeId + "," + ((CubeId + CubePerRow)), "" + CubeId, "" + (CubeId + CubePerColumn));
                        System.out.println(Labyrith.getEdge(CubeId + "," + (CubeId + CubePerRow)).toString());
                    }
                } else if (i == verticlePerColumn || j == verticlePerColumn) {
                    Labyrith.addEdge(CubeId + "," + ((CubeId + CubePerRow)), "" + CubeId, "" + (CubeId + CubePerColumn));
                    System.out.println(Labyrith.getEdge(CubeId + "," + ((CubeId + CubePerRow))).toString());
                }
                CubeId++;
            }
        }
        Labyrith.display();
        return Labyrith;


    }


    private int check(Subset[] subsets, int i) {

        if (subsets[i].parent != i) {
            subsets[i].parent = check(subsets, subsets[i].parent);
        }

        return subsets[i].parent;
    }

    private void Union(Subset[] subsets, int x, int y) {
        int xroot = check(subsets, x);
        int yroot = check(subsets, y);

        if (subsets[xroot].depth < subsets[yroot].depth)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].depth > subsets[yroot].depth)
            subsets[yroot].parent = xroot;

        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].depth++;
        }
    }

    private int getRandomWeight() {
        return (int) (Math.random() * Integer.MAX_VALUE);
    }

    private Edge[] KruskalMST(Graph graphs) {
        Graph graph = new SingleGraph("The MST");
        graph.setStrict(false);
        graph.setAutoCreate(true);


        Edge[] result = new Edge[V];
        int e = 0;
        int i = 0;
        for (i = 0; i < V; ++i) {
            result[i] = new Edge();
        }

        java.util.Collections.sort(edge);

        Subset[] subsets = new Subset[V];

        for (i = 0; i < V; ++i) {
            subsets[i] = new Subset();
        }

        for (int v = 0; v < V; ++v) {
            subsets[v].parent = v;
            subsets[v].depth = 0;
        }

        i = 0;

        while (e < V - 1) {

            Edge next_edge = new Edge();
            next_edge = edge.get(i++);

            int x = check(subsets, next_edge.from);
            int y = check(subsets, next_edge.to);

            if (x != y) {
                result[e] = next_edge;
                graphs.getEdge(next_edge.from + "," + next_edge.to).setAttribute("ui.class", "mst");
                Union(subsets, x, y);
                e++;
            }
        }

        System.out.println("Edges: ");
        for (i = 0; i < e; ++i) {
            System.out.println(result[i].from + " -> " + result[i].to + " (" + result[i].weight + ")");
            graph.addEdge("mst" + i, "" + result[i].from, "" + result[i].to);
        }
        graph.display();
        return result;

    }

    public static void main(String[] args) {
        int V = 25;
        int verticlePerRow = (int) Math.sqrt(V);
        int verticlePerColumn = (int) Math.sqrt(V);
        MST graphs = new MST(V);
        Graph graph = new SingleGraph("The Cube");
        graph.setStrict(false);
        graph.setAutoCreate(true);
        graph.addAttribute("ui.stylesheet", graphs.styleSheet);

        int edgeId = 0;


        for (int i = 0; i < verticlePerRow; i++) {
            for (int j = 0; j < verticlePerColumn; j++) {

                int verticleId = i * verticlePerRow + j;
                if (i + 1 < verticlePerColumn) {
                    edgeId = graphs.addEdgeUd(edgeId, verticleId, verticleId + verticlePerRow, graphs.getRandomWeight());
                    //graph.addEdge(""+edgeId,""+verticleId, ""+(verticleId+verticlePerRow));
                    graph.addEdge(verticleId + "," + (verticleId + verticlePerRow), "" + verticleId, "" + (verticleId + verticlePerRow));

                }
                if (j + 1 < verticlePerRow) {
                    edgeId = graphs.addEdgeUd(edgeId, verticleId, verticleId + 1, graphs.getRandomWeight());
                    //graph.addEdge(""+edgeId,""+verticleId, ""+(verticleId+1));
                    graph.addEdge(verticleId + "," + (verticleId + 1), "" + verticleId, "" + (verticleId + 1));


                }
            }
        }


        Edge[] MST = graphs.KruskalMST(graph);
        Graph Labyrith = graphs.graphToLabyrith(V, graph, graphs);


        graph.display().enableAutoLayout();

    }
}