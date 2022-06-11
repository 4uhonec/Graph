import java.security.SecureRandom;
import java.util.*;

public class BFS<E> {
    private final boolean bidirectional;
    private final boolean weighted;
    private final Map<E, HashMap<E, Integer>> graph;
    //map prev? +speed, -space
    private final E startElement;
    private E endElement;
    private Set<E> visited = new HashSet<>();
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
        visited.add(startElement);
        List<E> layer = new ArrayList<>();
        layer.add(startElement);
        layers.add(new ArrayList<>(layer));
        List<E> neighbors = new ArrayList<>();
        while(!layer.isEmpty()){
            for(E el: layer){
                for(E neighbor: graph.get(el).keySet()){
                    if(!visited.contains(neighbor)){
                        neighbors.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            if(neighbors.isEmpty())
                break;
            layers.add(new ArrayList<>(neighbors));
            layer.clear();
            layer.addAll(neighbors);
            neighbors.clear();
        }
    }

    public List<List<E>> getBFS(){
        return layers;
    }

    @Override
    public String toString(){
        StringBuilder bld = new StringBuilder();
        for(List<E> list: layers){
            for(E el: list){
                bld.append(el);
                bld.append(" ");
            }
            bld.append("\n");
        }
        return bld.toString();
    }

    public List<E> getPath(){
        if(endElement == null)
            throw new RuntimeException("No end element specified, call getPath(E endElement)");
        if(!visited.contains(endElement))
            return null;
        List<E> result = new ArrayList<>();
        if(endElement == startElement){
            result.add(startElement);
            return result;
        }
        int len = layers.size();
        List<E> reversedRes = new ArrayList<>();
        reversedRes.add(endElement);
        E current = endElement;
        for(int i = len - 1; i>0; i--){
            for(E el: layers.get(i - 1)){
                if(graph.get(el).containsKey(current) && layers.get(i).contains(current)){
                    reversedRes.add(el);
                    current = el;
                    break;
                }
            }
        }

        //reverse rev->result


        for(int i = reversedRes.size() - 1; i>=0; i--){
            result.add(reversedRes.get(i));
        }
        if(result.isEmpty())
            return null;
        return result;
    }

    public List<E> getPath(E end){
        if(!graph.containsKey(end))
            throw new RuntimeException("Graph does not contain element " + end);
        endElement = end;
        return getPath();
    }
}
