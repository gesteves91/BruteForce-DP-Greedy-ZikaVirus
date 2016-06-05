import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UtilFunctions {
	
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
	
	public static boolean testSolution(int[] arr){
		int[] solution = new int[ZikaZeroAnelDual.sizeFocuses];
		Boolean same = false;
		int[] solutionWD;
		
		for(int i = 0; i < solution.length; i++)
			solution[i] = i+1;
		
		Quicksort qs = new Quicksort();
		
		qs.sort(arr);
		
		solutionWD = removeDuplicates(arr);
		
		if(Arrays.equals(solution, solutionWD)) return true;
		else return false;
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
	
	public static int[] fillArrayOneAL(ArrayList<Integer> al){
		int[] arr = new int[al.size()];
		
		for(int i = 0; i < al.size(); i++)
			arr[i] = al.get(i);
		
		return arr;
	}
}
