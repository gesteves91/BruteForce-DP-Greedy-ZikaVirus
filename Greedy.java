import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Greedy {

	public static void RunGreedy(Graph graph, String outputFile, int[] greedyArray){
		int vertex = 1;
		int size = (ZikaZeroAnelDual.sizeFocuses/2) - 1;

		ArrayList<Integer> solution = new ArrayList<Integer>();
		int[] partialSol = new int[2];
		int[] entireSolution, solVertex;
		Boolean done = false;

		for(int m = 0; m <= graph.Size()/2 && !done; m++){
			size++;
			solVertex = new int[size];
			for(int i = 0; i < greedyArray.length; i++){
				vertex = greedyArray[i];
				for(int j = 0; j < size; j++){
					partialSol = graph.returnFocuses(vertex);
					for(int ele : partialSol)
						solution.add(ele);
					solVertex[j] = vertex;
					vertex = graph.nextAdj(vertex);
				}
				entireSolution = UtilFunctions.fillArrayOneAL(solution);
				solution.clear();
				if(UtilFunctions.testSolution(entireSolution)){
					UtilFunctions.writeSolution(solVertex, outputFile);
					done = true;
					break;
				}
			}
		}
	}
	
	public static int[] generateGreedyArray(Graph g, int[] allOcurrences){
		ArrayList<Integer> al = new ArrayList<Integer>();
		int minIndex, vertex;
		int[] greedyArray = new int[g.Size()];
		
		for(int i : allOcurrences)
			al.add(i);
		
		int size = al.size();
		
		for(int i = 0; i < size; i++){
			minIndex = al.indexOf(Collections.min(al));
			vertex = minIndex + 1;
			greedyArray[i] = vertex;
			al.set(minIndex, 1000000);
		}
		return greedyArray;
	}
	
	public static int[] countConflictGreedy(Graph g){
		int[] focus = new int[2];
		int[] allFocus, countConflict, allOccurence, greedyArray;

		ArrayList<Integer> al = new ArrayList<Integer>();
		
		for(int i = 1; i <= g.Size(); i++){
			focus = g.returnFocuses(i);
			for(int e : focus)
				al.add(e);
		}
		
		allFocus = UtilFunctions.fillArrayOneAL(al);
		countConflict = createConflictRelation(allFocus);
		allOccurence = vertexConflictRelation(g, countConflict);

		greedyArray = generateGreedyArray(g, allOccurence);
		return greedyArray;
	}
	
	public static int[] createConflictRelation(int[] arr){
		int[] occ = new int[ZikaZeroAnelDual.sizeFocuses];
		for (int i = 1, k = 0; i <= occ.length; i++, k++)
			for (int j = 0; j < arr.length; j++)
				if(arr[j] == i)
					occ[k] += 1;
		return occ;
	}
	public static int returnIncidence(int[] occ, int[] focus){
		int sum = 0;
		
		for(int i = 0; i < focus.length; i++)
			for(int j = 0; j < occ.length; j++)
				if((j+1) == focus[i])
					sum += occ[j];
		
		return sum;
	}
	
	public static int[] vertexConflictRelation(Graph g, int[] occ){
		int[] focus = new int[2];
		int[] allOcc = new int[g.Size()];
		
		for(int i = 1, j = 0; i <= g.Size(); i++, j++){
			focus = g.returnFocuses(i);
			allOcc[j] = returnIncidence(occ, focus);
		}
			
		return allOcc;
	}
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();

		long total = 0;
		for (int i = 0; i < 10000000; i++) 
			total += i;
		
		//args[0] = "/Users/gesteves/Documents/workspace/Paradigms/src/in100";
		//args[1] = "/Users/gesteves/Documents/workspace/Paradigms/src/out100";
		
		Graph g = ZikaZeroAnelDual.constructGraph(args[0]);
		int[] greedy;
		
		greedy = Greedy.countConflictGreedy(g);
		Greedy.RunGreedy(g, args[1], greedy);
		
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}

}
