import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BFS<E> {
    private boolean bidirectional;
    private boolean weighted;
    private final Graph<E> graph;

    private E startElement;
    private List<List<E>> layers= new ArrayList<>();

    public BFS(Graph<E> graph, E startElement){
        this.graph = graph;
        this.startElement = startElement;
        this.bidirectional = graph.isBidirectional();
        this.weighted = graph.isWeighted();
    }

    public BFS(Graph<E> graph){
        this.graph = graph;
        this.startElement = pickRandomVertex();
        this.bidirectional = graph.isBidirectional();
        this.weighted = graph.isWeighted();
    }

    //used when root vertex is not passed in constructor
    private E pickRandomVertex(){
        SecureRandom rnd = new SecureRandom();
        List<E> keys = new ArrayList<>(graph.getVertexes());
        E element = keys.get(rnd.nextInt(keys.size()));
        return element;
    }
}
