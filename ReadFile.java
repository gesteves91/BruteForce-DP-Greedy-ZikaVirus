import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile {
	private String path;
	private Scanner sca;
	public int sizeV, sizeF;
	public int[] vertex, focuses;
	
	public ReadFile(String filePath){
		path = filePath;
	}
	
	public void InputFile() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(path));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
		    SetGraph(everything);
		    SetFocus();
		} finally {
		    br.close();
		}
	}
	private void SetGraph(String file){
		sca = new Scanner(file);
		
		sizeV = sca.nextInt();
		sizeV = sca.nextInt();
		
		vertex = new int[sizeV*2];
		
		for(int i = 0, j = 0; i < sizeV; i++, j+=2){
			vertex[j] = sca.nextInt();
			vertex[j+1] = sca.nextInt();
		}
	}
	private void SetFocus(){	
		sizeF = sca.nextInt();
		
		focuses = new int[sizeV*2];
		
		for(int i = 0, j = 0; i < sizeV; i++, j+=2){
			focuses[j] = sca.nextInt();
			focuses[j+1] = sca.nextInt();
		}
	}
}
