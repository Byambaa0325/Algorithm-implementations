class Edge implements Comparable<Edge>
{
    int id;
    final int from;
    final int to;
    final int weight;


    Edge(int id_, int from_, int to_, int weight_){
        id=id_;
        from=from_;
        to=to_;
        weight=weight_;
    }
    Edge(){
        from=0;
        to=0;
        weight=0;
    }
    public int compareTo(Edge compareEdge)
    {
        return this.weight-compareEdge.weight;
    }
}