import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Graph {
  
  HashMap<Integer, int[]> graph;
  private int numVertex;
  
  public Graph (int numVertex) {
	  this.numVertex = numVertex;
	  graph = new HashMap<Integer, int[]>(numVertex);
  }  
  
  public int Size(){
	  return numVertex;
  }
  
  public int[] returnFocuses(int key){
	  int[] fs = new int[2];
	  int[] values;
	  
	  values = graph.get(key);
	  
	  fs[0] = values[values.length-2];
	  fs[1] = values[values.length-1];
			
	  return fs;
  }
  
  public int nextAdj(int key){
	  int[] values = graph.get(key);
	  int var = values[1];
	  return var;
  }
  
  public void insertEdge(int v, int[] adj){
	  graph.put(v, adj);
  }
  public void printGraph(){
	  Iterator iterator = graph.keySet().iterator();
	  
	  while (iterator.hasNext()) {
	     int key = (Integer) iterator.next();
	     int[] value = graph.get(key);
	    
	     for (int v : value)
	    	 System.out.println(key + " " + v);
	  }
  }
}
