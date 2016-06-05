import java.util.ArrayList;

public class Greedy {
	
	public static void RunGreedy(Graph graph, String outputFile, int[] greedyArray){
		int vertex = 1;
		int size = (ZikaZeroAnelDual.sizeFocuses/2) - 1;

		ArrayList<Integer> solution = new ArrayList<Integer>();
		int[] partialSol = new int[2];
		int[] entireSolution, solVertex;
		Boolean done = false;

		for(int m = 0; m <= ZikaZeroAnelDual.sizeFocuses/2 && !done; m++){
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
	
}
