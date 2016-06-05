import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BruteForce {
	
	public static void RunBruteForce(Graph graph, String outputFile){
		int vertex = 1;
		int size = (ZikaZeroAnelDual.sizeFocuses/2) - 1;

		ArrayList<Integer> solution = new ArrayList<Integer>();
		int[] partialSol = new int[2];
		int[] entireSolution, solVertex;
		Boolean done = false;

		for(int m = 0; m <= ZikaZeroAnelDual.sizeFocuses/2 && !done; m++){
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
	
	
	
}
