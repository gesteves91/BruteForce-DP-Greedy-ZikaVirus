import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Dynamic {

	public static HashMap<Integer, int[][]> dataTable = new HashMap<Integer, int[][]>();
	
	public static void RunDynamic(Graph graph, String outputFile){
		int nextEle;
		int[][] minimunVer; 
		int[] partialSol = new int[2];
		int[] previousSol, entireSolution, vertex, solVertex;
		Boolean done = false;
		//At this point the datatable has all the minimun solutions
		if(!Dynamic.testMinimun(graph, outputFile)){
			while(!done){
				for(int i = 1; i <= graph.Size(); i++){
					minimunVer = dataTable.get(i);
					nextEle = minimunVer[0][minimunVer.length-1];
					vertex = minimunVer[0];
					previousSol = minimunVer[1];
					nextEle = graph.nextAdj(nextEle);
					partialSol = graph.returnFocuses(nextEle);
					entireSolution = UtilFunctions.sumUpTwoArrays(previousSol, partialSol);
					solVertex = UtilFunctions.addNextEle(vertex, nextEle);
					if(UtilFunctions.testSolution(entireSolution)){
						UtilFunctions.writeSolution(solVertex, outputFile);
						done = true;
						break;
					}
					Dynamic.alterDataTable(i, solVertex, entireSolution);
				}
			}
		}
	}
	
	public static void dynamicDataTable(Integer vertice, int[] solution, int[] partialSol){
		int[][] sol = new int[solution.length][partialSol.length];
		
		sol[0] = solution;
		sol[1] = partialSol;
		
		dataTable.put(vertice, sol);
	}
	
	public static void alterDataTable(Integer vertice, int[] solution, int[] partialSol){
		dataTable.remove(vertice);
		Dynamic.dynamicDataTable(vertice, solution, partialSol);
	}
	
	public static Boolean testMinimun(Graph g, String outputFile){
		int vertex = 1;
		int size = (ZikaZeroAnelDual.sizeFocuses/2) - 1;
		Boolean done = false;

		ArrayList<Integer> solution = new ArrayList<Integer>();
		int[] partialSol = new int[2];
		int[] entireSolution, solVertex;
			
		for(int i = 1; i <= g.Size(); i++){
			solVertex = new int[ZikaZeroAnelDual.sizeFocuses/2];
			vertex = i;
			for(int j = 0; j < ZikaZeroAnelDual.sizeFocuses/2; j++){
				partialSol = g.returnFocuses(vertex);
				for(int ele : partialSol)
					solution.add(ele);
				solVertex[j] = vertex;
				vertex = g.nextAdj(vertex);
			}
			entireSolution = UtilFunctions.fillArrayOneAL(solution);
			solution.clear();
			Dynamic.dynamicDataTable(i, solVertex, UtilFunctions.removeDuplicates(entireSolution));
			if(UtilFunctions.testSolution(entireSolution)){
				UtilFunctions.writeSolution(solVertex, outputFile);
				done = true;
				break;
			}
		}
		return done;
	}
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();

		long total = 0;
		for (int i = 0; i < 10000000; i++) 
			total += i;
		
		//args[0] = "/Users/gesteves/Documents/workspace/Paradigms/src/in100";
		//args[1] = "/Users/gesteves/Documents/workspace/Paradigms/src/out100";

		Graph g = ZikaZeroAnelDual.constructGraph(args[0]);
		Dynamic.RunDynamic(g, args[1]);
		
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);

	}

}
