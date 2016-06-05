import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ZikaZeroAnelDual {

	static int sizeFocuses = 0;
	
	public static void countConflictGreedy(Graph g){
		int[] focus = new int[2];
		int[] allFocus, countConflict, allOccurence;

		ArrayList<Integer> al = new ArrayList<Integer>();
		
		for(int i = 1; i <= g.Size(); i++){
			focus = g.returnFocuses(i);
			for(int e : focus)
				al.add(e);
		}
		
		allFocus = fillArrayOneAL(al);
		countConflict = createConflictRelation(allFocus);
		allOccurence = vertexConflictRelation(g, countConflict);
		for (int i : allOccurence)
			System.out.print(i + " ");
	}
	
	public static int[] createConflictRelation(int[] arr){
		int[] occ = new int[sizeFocuses];
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
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		for(int i = 1, j = 0; i <= g.Size(); i++, j++){
			focus = g.returnFocuses(i);
			allOcc[j] = returnIncidence(occ, focus);
		}
			
		return allOcc;
		 
	}
	
	public static int[] fillArrayTwoAL(ArrayList<Integer> al1, ArrayList<Integer> al2){
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for (int i : al1)
			result.add(i);
		
		for (int i : al2)
			result.add(i);
		
		int[] arr = new int[result.size()];
		
		for(int i = 0; i < result.size(); i++)
			arr[i] = result.get(i);
		
		return arr;
	}
	
	public static void writeSolution(int[] solution, String outputFile){
		Writer w = null;
		
		try{
			w = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outputFile)));

			for (int i : solution){
				w.write(i + " ");
				System.out.print(i + " ");
			}
		
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {w.close();} catch (Exception ex) {}
		}
	}
	
	public static int[] fillArrayOneAL(ArrayList<Integer> al){
		int[] arr = new int[al.size()];
		
		for(int i = 0; i < al.size(); i++)
			arr[i] = al.get(i);
		
		return arr;
	}
	
	public static ArrayList<Integer> fillArrayList(int[] arr){
		ArrayList<Integer> al = new ArrayList<Integer>();

		for(int i : arr)
			al.add(i);
		
		return al;
	}
	
	public static int[] removeDuplicates(int[] arr) {
		  Set<Integer> alreadyPresent = new HashSet<Integer>();
		  int[] whitelist = new int[0];

		  for (int nextElem : arr) {
		    if (!alreadyPresent.contains(nextElem)) {
		      whitelist = Arrays.copyOf(whitelist, whitelist.length + 1);
		      whitelist[whitelist.length - 1] = nextElem;
		      alreadyPresent.add(nextElem);
		    }
		  }

		  return whitelist;
		}
	
	public static boolean testSolution(int[] arr){
		int[] solution = new int[sizeFocuses];
		Boolean same = false;
		int[] solutionWD;
		
		for(int i = 0; i < solution.length; i++)
			solution[i] = i+1;
		
		Quicksort qs = new Quicksort();
		
		qs.sort(arr);
		
		solutionWD = removeDuplicates(arr);
		
		if(Arrays.equals(solution, solutionWD)) return true;
		else return false;
		//for (int i = 0; i < solution.length; i++)
			//if(solution[i] == arr[i]) same = true;
			
		
		//return same;
	}

	public static void BruteForce(Graph graph, String outputFile){
		int vertex = 1;
		int size = (sizeFocuses/2) - 1;

		ArrayList<Integer> solution = new ArrayList<Integer>();
		int[] partialSol = new int[2];
		int[] entireSolution;
		int[] solVertex;
		Boolean done = false;
		//int[] solVertex = new int[sizeFocuses/2];

		for(int m = 0; m <= sizeFocuses/2 && !done; m++){
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
				entireSolution = fillArrayOneAL(solution);
				solution.clear();
				if(testSolution(entireSolution)){
					writeSolution(solVertex, outputFile);
					done = true;
					break;
				}
			}
		}
		//System.out.println("I'm done!");
	}
	
	public static Graph constructGraph(String inputFile) throws IOException{
		ReadFile rf = new ReadFile(inputFile);
		rf.InputFile();
		Graph g = new Graph(rf.sizeV);
		sizeFocuses = rf.sizeF;
		
		ArrayList<Integer> vet = new ArrayList<Integer>(); 
		ArrayList<Integer> foc = new ArrayList<Integer>();
		int elem = 0, size, index = 0;
		int[] arr;
		boolean exchange = false;
		
		for(int i = 1; i <= rf.sizeV; i++){
			for(int j = 0; j < rf.vertex.length; j+=2){
				if(rf.vertex[j] == i || rf.vertex[j+1] == i){
					vet.add(rf.vertex[j]);
					vet.add(rf.vertex[j+1]);
					elem = i;
				}
			}
			
			foc.add(rf.focuses[index]);
			index++;
			foc.add(rf.focuses[index]);
			index++;
			
			size = vet.size();
			for(int k = 0; k < size; k++)
				if(vet.get(k) == elem){
					if(k == 0)
						exchange = true;
					vet.remove(k);
					size = vet.size();
					k--;
				}	
			if(exchange){
				int aux = vet.get(0);
				vet.set(0, vet.get(1));
				vet.set(1, aux);
			}
			
			arr = fillArrayTwoAL(vet, foc);
			g.insertEdge(elem, arr);
			vet.clear();
			foc.clear();
			exchange = false;
		}
		
		//g.printGraph();
		return g;
	}

	public static void main(String[] args) throws IOException {
		args[0] = "/Users/gesteves/Documents/workspace/Paradigms/src/in_gt";
		args[1] = "/Users/gesteves/Documents/workspace/Paradigms/src/out_gt";
		//args[0] = "nothing";
		Graph g = constructGraph(args[0]);
		//int[] f = g.returnFocuses(2);
		//for(int i : f)
			//System.out.println(i);
		//System.out.println(g.nextAdj(3));
		BruteForce(g, args[1]);
		//ZikaZeroAnelDual.countConflictGreedy(g);
		//g.insertEdge(1, vector);
		//g.insertEdge(7, 2);
		//g.insertEdge(2, 5);
		//g.insertEdge(5, 6);
		//g.insertEdge(6, 4);
		//g.insertEdge(4, 1);
		//g.insertEdge(1, 3);
		//g.setFocuses(1, 5, 6);
		//g.setFocuses(2, 1, 2);
		//g.setFocuses(3, 1, 3);
		//g.setFocuses(4, 4, 6);
		//g.setFocuses(5, 2, 3);
		//g.setFocuses(6, 3, 5);
		//g.setFocuses(7, 2, 4);
		//BruteForce(g);
		//System.out.println(g.nextVertex(1));
		//System.out.println(g.previousVertex(1));
		//g.printGraph();
		
		//System.out.println(rf.sizeV);
		//System.out.println(rf.sizeF);
		//for(int i : rf.vertex)
			//System.out.print(i + " ");
		//System.out.println();
		//for(int i : rf.focuses)
			//System.out.print(i + " ");
		//System.out.println(g.proxAdj(1));
		//g.print();
	}
}
