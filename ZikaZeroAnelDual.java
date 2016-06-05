import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ZikaZeroAnelDual {

	static int sizeFocuses = 0;
	
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
		//for (int i : allOccurence)
			//System.out.print(i + " ");
		greedyArray = generateGreedyArray(g, allOccurence);
		//for (int i : greedyArray)
			//System.out.print(i + " ");
		return greedyArray;
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

	public static ArrayList<Integer> fillArrayList(int[] arr){
		ArrayList<Integer> al = new ArrayList<Integer>();

		for(int i : arr)
			al.add(i);
		
		return al;
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
		int[] greedy;
		//int[] f = g.returnFocuses(2);
		//for(int i : f)
			//System.out.println(i);
		//System.out.println(g.nextAdj(3));
		greedy = countConflictGreedy(g);
		Greedy.RunGreedy(g, args[1], greedy);
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
