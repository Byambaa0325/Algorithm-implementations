import java.util.*;
import java.io.*;
import java.lang.*;


class MaxFlow {
    private static int V;
    private static int[] path;
    private static int[][] graph;
    private static int[][] residual;

    private static boolean BFS(int from, int to) {

        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; ++i) {
            visited[i] = false;
        }

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(from);
        visited[from] = true;
        path[from] = -1;

        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (!visited[v] && residual[u][v] > 0) {
                    queue.add(v);
                    path[v] = u;
                    visited[v] = true;
                }
            }
        }

        return (visited[to]);
    }


    private static int maxflow(int from, int to) {
        int u, v;

        residual = new int[V][V];

        for (u = 0; u < V; u++) {
            for (v = 0; v < V; v++) {
                residual[u][v] = graph[u][v];
            }
        }


        path = new int[V];

        int max_flow = 0;

        while (BFS(from, to)) {

            int current_flow = Integer.MAX_VALUE;

            for (v = to; v != from; v = path[v]) {
                u = path[v];

                current_flow = Math.min(current_flow, residual[u][v]);
            }


            for (v = to; v != from; v = path[v]) {
                u = path[v];

                residual[u][v] -= current_flow;
                residual[v][u] += current_flow;
            }

            max_flow += current_flow;
        }


        return max_flow;
    }

    private static int[] getIndexOfId(int[][] ids, int id) {
        int[] index = {-1, -1};
        for (int i = 0; i < ids.length; i++) {
            for (int j = 0; j < ids[i].length; j++) {
                if (ids[i][j] == id) {
                    index[0] = i;
                    index[1] = j;
                    return index;
                }
            }
        }
        return index;
    }


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        try {
            in = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
        }

        //Test case
        int t = in.nextInt();
        for (int ca = 1; ca <= t; ca++) {

            int n = in.nextInt();

            V = 26 + n + 2;
            /*
            node 0  = source
            node 1 = sink;
            node 2-27 = letters
            node 28-(28+n)= problems
            node (28+n)+  =  Names
            */

            String[][] names = new String[n][];
            int[][] namesId = new int[n][];
            int idCounter = V;

            for (int i = 0; i < n; i++) {

                in.nextInt();

                //Get input as a line
                String nameLine = in.nextLine();

                //Normalize the input
                nameLine = nameLine.toLowerCase();

                //Get rid of White Space
                if (nameLine.charAt(nameLine.length() - 1) == ' ') {
                    nameLine = nameLine.substring(0, nameLine.length() - 1);
                }
                if (nameLine.charAt(0) == ' ') {
                    nameLine = nameLine.substring(1);
                }

                //To input array
                names[i] = nameLine.split(" ");

                //Increase graph size
                int namesSize = names[i].length;
                V += namesSize;

                //Assign ids to strings
                namesId[i] = new int[namesSize];
                for (int j = 0; j < namesSize; j++) {
                    namesId[i][j] = ++idCounter;
                }
            }

            // Initialize the graph
            graph = new int[V + 1][V + 1];


            //Source to Letters
            for (int i = 2; i <= 27; i++) {
                graph[0][i] = 1;
            }
            //Problems to sink
            for (int i = 28; i < 28 + n; i++) {
                graph[i][1] = 1;
            }
            //names to problems
            for (int i = 0; i < names.length; i++) {
                for (int j = 0; j < names[i].length; j++) {

                    graph[namesId[i][j]][28 + i] = 1;
                }
            }
            //Letters to Names
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < names[i].length; j++) {
                    //Get the String
                    String name = names[i][j];
                    int id = namesId[i][j];

                    //Convert to ASCII
                    int ASCII_id = (int) name.charAt(0);
                    int letter_id = ASCII_id - 97;

                    //Convert for our graph
                    letter_id += 2;
                    graph[letter_id][id] = 1;
                }
            }

            //Solve
            /*
            Debugging code

            for(int i = 0; i < residual.length; i++) {
                int connection = 0;
                for( int j = 0; j < residual.length; j++)
                    if( residual[j][i] == 1)
                        connection++;
                System.out.println("Node " + i + " has " + connection + " incoming connections");
            }
            */

            maxflow(0, 1);
            for (int i = 2; i < 28; i++) {
                for (int j = 29 + n; j < V; j++) {
                    if (residual[j][i] == 1) {
                        int[] index = getIndexOfId(namesId, j);
                        System.out.println(names[index[0]][index[1]]);
                    }

                }
            }


        }


    }
}
