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
			
			arr = UtilFunctions.fillArrayTwoAL(vet, foc);
			g.insertEdge(elem, arr);
			vet.clear();
			foc.clear();
			exchange = false;
		}
		//g.printGraph();
		return g;
	}

	public static void main(String[] args) throws IOException {
		args[0] = "/Users/gesteves/Documents/workspace/Paradigms/src/in100";
		args[1] = "/Users/gesteves/Documents/workspace/Paradigms/src/out100";
		//args[0] = "nothing";
		//Graph g = constructGraph(args[0]);
		//int[] greedy;
		//int[] f = g.returnFocuses(2);
		//for(int i : f)
			//System.out.println(i);
		//System.out.println(g.nextAdj(3));
		//greedy = Greedy.countConflictGreedy(g);
		//Greedy.RunGreedy(g, args[1], greedy);
		//ZikaZeroAnelDual.countConflictGreedy(g);
		//System.out.println(g.nextVertex(1));
		//System.out.println(g.previousVertex(1));
		//g.printGraph();
		
	}
}
