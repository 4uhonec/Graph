import java.security.SecureRandom;
import java.util.*;


public class BFS<E,K extends Number> {
    private final HashMap<E, HashMap<E,K>> graphMap;
    //map prev? +speed, -space
    private E startElement;
    private E endElement;
    private Set<E> visited = new HashSet<>();
    private List<List<E>> layers= new ArrayList<>();

    public BFS(Graph<E,K> graph){
        this.graphMap = graph.getGraph();
    }

    //used when root vertex is not passed in constructor
    private E pickRandomVertex(){
        SecureRandom rnd = new SecureRandom();
        List<E> keys = new ArrayList<>(graphMap.keySet());
        E element = keys.get(rnd.nextInt(keys.size()));
        return element;
    }

	private void clearVisited(){
		visited.clear();
	}

	public void setStart(E start){
		if(!graphMap.containsKey(start))
			throw new RuntimeException("No such start el "+start);
		this.startElement = start;
	}
	
    //build BFS from start element
    private void buildBfs(){
        visited.add(startElement);
        List<E> layer = new ArrayList<>();
        layer.add(startElement);
        layers.add(new ArrayList<>(layer));
        List<E> neighbors = new ArrayList<>();
        while(!layer.isEmpty()){
            for(E el: layer){
                for(E neighbor: graphMap.get(el).keySet()){
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
	
    public List<List<E>> getBfs(E start){
		startElement = start;
		layers.clear();
		buildBfs();
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

    public List<E> findPath(){
		if(startElement == null)
			throw new RuntimeException("No start element specified");
        if(endElement == null)
            throw new RuntimeException("No end element specified");
		layers.clear();
		buildBfs();
        if(!visited.contains(endElement))
            return null;
        List<E> result = new ArrayList<>();
        if(endElement == startElement){
            result.add(startElement);
            return result;
        }
        int len = layers.size();
        result.add(endElement);
        E current = endElement;
        for(int i = len - 1; i>0; i--){
            for(E el: layers.get(i - 1)){
                if(graphMap.get(el).containsKey(current) && layers.get(i).contains(current)){
                    result.add(el);
                    current = el;
                    break;
                }
            }
        }

		Collections.reverse(result);

        if(result.isEmpty())
            return null;
        return result;
    }

    public List<E> findPath(E start, E end){
		if(!graphMap.containsKey(start))
			throw new RuntimeException("Graph does not contain element " + start);
        if(!graphMap.containsKey(end))
            throw new RuntimeException("Graph does not contain element " + end);
        startElement = start;    
        endElement = end;
        return findPath();
    }
}
