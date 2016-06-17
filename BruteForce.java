import java.io.IOException;
import java.util.ArrayList;

public class BruteForce {

	public static void RunBruteForce(Graph graph, String outputFile){
		int vertex = 1;
		int size = (ZikaZeroAnelDual.sizeFocuses/2) - 1;

		ArrayList<Integer> solution = new ArrayList<Integer>();
		int[] partialSol = new int[2];
		int[] entireSolution, solVertex;
		Boolean done = false;

		for(int m = 0; m <= graph.Size()/2 && !done; m++){
			size++;
			solVertex = new int[size];
			for(int i = 1; i <= graph.Size(); i++){
				vertex = i;
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
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();

		long total = 0;
		for (int i = 0; i < 10000000; i++) 
			total += i;
		
		//args[0] = "/Users/gesteves/Documents/workspace/Paradigms/src/in10000-2";
		//args[1] = "/Users/gesteves/Documents/workspace/Paradigms/src/out10000-2";
		
		Graph g = ZikaZeroAnelDual.constructGraph(args[0]);
		BruteForce.RunBruteForce(g, args[1]);

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);

	}

}
