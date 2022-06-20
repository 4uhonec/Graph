//TODO: Dijkstra works only for nonnegative weights
//for now it gets Integer as weights

import java.util.*;
//bfs first to find accessible vertices
public class Dijkstra<E>{
	private HashMap<E,HashMap<E,Integer>> graphMap;
	private Map<E,E> previous = new HashMap<>();
	private BFS<E, Integer> bfs;
	private E startElement;
	public Dijkstra(Graph<E, Integer> graph){
		if(!graph.isWeighted()){//TODO: return null in Graph's function
			throw new RuntimeException("graph is not weighted");
		}
		this.graphMap = graph.getGraph();
		this.bfs = new BFS<>(graph);
	}

	public Map<E,Integer> getDist(E start){
		if(!graphMap.containsKey(start)){
			System.out.println("no such element "+start);
			return null;
		}
		
		List<List<E>> layers = bfs.getBfs(start);
		startElement = start;
		
		Map<E,Integer> result= new HashMap<>();
		for(List<E> layer: layers){
			for(E el: layer){
				result.put(el, Integer.MAX_VALUE);
			}
		}
		result.put(start, 0);

		PriorityQueue<E> minHeap = new PriorityQueue<>(
			(a,b) -> result.get(a) - result.get(b)
		);

		minHeap.add(start);
		while(minHeap.size()>0){
			E current = minHeap.poll();
			for(E el: graphMap.get(current).keySet()){
				if(result.containsKey(el)&&//maybe add check !minHeap.contains(el)
				   result.get(current)+graphMap.get(current).get(el)<result.get(el)){
				   		result.put(el,result.get(current)+graphMap.get(current).get(el));
				   		previous.put(el,current);
				   		minHeap.add(el);
				   }
			}
		}
		
		return result;
	}

	//TODO: finish Dijkstra getPath - minimal weighted path
	public List<E> getPath(){
		List<E> res = new ArrayList<>();

		return res;
	}
}
