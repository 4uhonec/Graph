import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.SynchronousQueue;

public class BFS<E> {
    private final boolean bidirectional;
    private final boolean weighted;
    private final Map<E, HashMap<E, Integer>> graph;
    //map prev? +speed, -space
    private final E startElement;
    private E endElement;
    private List<List<E>> layers= new ArrayList<>();

    public BFS(Graph<E> graph, E startElement){
        if(!graph.getGraph().containsKey(startElement))
            throw new RuntimeException("no such start element in graph");
        this.graph = graph.getGraph();
        this.startElement = startElement;
        this.bidirectional = graph.isBidirectional();
        this.weighted = graph.isWeighted();
        init();
    }

    public BFS(Graph<E> graph, E startElement, E endElement){
        this(graph, startElement);
        if(!graph.getGraph().containsKey(endElement))
            throw new RuntimeException("no such end element in graph");
        this.endElement = endElement;
    }

    public BFS(Graph<E> graph){
        this.graph = graph.getGraph();
        this.startElement = pickRandomVertex();
        this.bidirectional = graph.isBidirectional();
        this.weighted = graph.isWeighted();
        init();
    }

    //used when root vertex is not passed in constructor
    private E pickRandomVertex(){
        SecureRandom rnd = new SecureRandom();
        List<E> keys = new ArrayList<>(graph.keySet());
        E element = keys.get(rnd.nextInt(keys.size()));
        return element;
    }

    //build BFS from start element
    public void init(){
        Queue<E> fifo = new LinkedList<>();
        E current = startElement;
        fifo.add(current);
        Set<E> visited = new HashSet<>();
        visited.add(current);

        while(!fifo.isEmpty()){
            current = fifo.poll();
            List<E> layer = new ArrayList<>();
            for(E e: graph.get(current).keySet()){
                if(!visited.contains(e)){
                    visited.add(e);
                    layer.add(e);
                    fifo.add(e);
                }
            }
            if(!layer.isEmpty())
                layers.add(layer);
        }
    }
}
