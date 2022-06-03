import java.util.*;

//only implemented for int weights
public class Graph<E>{
    private boolean bidirectional;
    private boolean weighted;
    private Map<E, HashMap<E, Integer>> graph = new HashMap<>();

    public Graph(){
        this(true, false);
    }

    public Graph(boolean bi, boolean w){
        this.bidirectional = bi;
        this.weighted = w;
    }

    public void addVertex(E v){
        if(!graph.containsKey(v))
            graph.put(v, new HashMap<>() );
    }

    public void addEdge(E from, E to){
        addEdge(from, to, 0);
    }

    public void addEdge(E from, E to, int w){
        if(!graph.containsKey(from)){
            addVertex(from);
        }
        if(!graph.containsKey(to)){
            addVertex(to);
        }
        graph.get(from).put(to, w);
        if(bidirectional)
            graph.get(to).put(from, w);
    }

    public void removeEdge(E from, E to){
        if(graph.containsKey(from)){
            graph.get(from).remove(to);
        }
        if(bidirectional){
            if(graph.containsKey(to)){
                graph.get(to).remove(from);
            }
        }
    }

    public void removeVertex(E v){
        if(graph.containsKey(v)){
            graph.remove(v);
            for(E e: graph.keySet()){
                graph.get(e).remove(v);
            }
        }
    }

    public List<E> getVertexes(){
        return new ArrayList<>(graph.keySet());
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(E e: graph.keySet()){
            str.append(e.toString() + ": ");
            for(E k: graph.get(e).keySet()){
                if(weighted)
                    str.append(k.toString() + "->" + graph.get(e).get(k) + " ");
                else
                    str.append(k.toString() +" ");
            }
            str.append("\n");
        }
        return str.toString();
    }

    public int edgeCount(){
        int count = 0;
        for(E e: graph.keySet()){
            count += graph.get(e).size();
        }
        if(bidirectional) return count / 2;
        return count;
    }

    public int vertexCount() {
        return graph.size();
    }

    public void copy(Graph<E> g){
        this.graph = new HashMap<>(g.getGraph());
        setDirection(g.isBidirectional());
        setWeighted(g.isWeighted());
    }

    public Map<E, HashMap<E, Integer>> getGraph(){
        return this.graph;
    }

    public boolean isBidirectional() {
        return bidirectional;
    }

    public void setDirection(boolean bidirectional) {
        this.bidirectional = bidirectional;
    }

    public boolean isWeighted() {
        return weighted;
    }

    public void setWeighted(boolean weighted) {
        this.weighted = weighted;
    }

    public void BFS(){

    }

    public void BFS(E e){

    }

    public void DFS(){

    }

    public void DFS(E e){

    }


}
