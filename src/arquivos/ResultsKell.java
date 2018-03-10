package arquivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ResultsKell {
	private String result;
	
	public ResultsKell(String result){
		this.result = result;
	}
	public int[] atributosSelecionados(){
		int[] features = null;
		try {
			FileReader fr = new FileReader(result);
			BufferedReader br = new BufferedReader(fr);
			String sCurrentLine;
			int encontrou = 0;
			while ((sCurrentLine = br.readLine()) != null && encontrou == 0) {
				//System.out.println(sCurrentLine);
				if(sCurrentLine.contains("Features selected:")){
					
					encontrou = 1;
				}
			}
			if(encontrou == 1){
				String[] featuresStr = sCurrentLine.split(" - ");
				int n = featuresStr.length;
				features = new int[n];
				
				for (int i = 0;i<n;i++) {
					
					features[i] = Integer.parseInt(featuresStr[i]);
				
				}
						
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return features;
		
	}
}
