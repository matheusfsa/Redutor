package redutores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import arquivos.ArffToKeel;
import arquivos.ResultsKell;
import keel.Algorithms.Preprocess.Feature_Selection.nonevolutionary_algorithms.FOCUS.FocusIncon;
import redutor.Redutor;





public class Focus extends Redutor {
	
	public Focus(String arquivo) {
		super(arquivo);
		// TODO Auto-generated constructor stub
	}
	@Override
	public int[] reduz() {
		ArffToKeel ak = new ArffToKeel(arquivo);
		ak.transformaArffToKeel();
		FocusIncon fi = new FocusIncon(criaConfig(ak,0,1));
		fi.ejecutar();
		ResultsKell rk = new ResultsKell(ak.getResult());	
		return rk.atributosSelecionados();
	}
	public String criaConfig(ArffToKeel ak,int knn, double inconAllow){
		try {
			File arquivo = new File("config.txt");
			FileWriter fw = new FileWriter(arquivo);
			fw.write("algorithm = FOCUS\n"
					+ "inputdata = \"" + ak.getTrain_in() + "\" \""+ ak.getTest_in() + "\"\n"
					+ "outputdata = \"" + ak.getTrain_out() + "\" \"" + ak.getTest_out() + "\" \""+ak.getResult() +  "\"\nparamKNN = "+ knn +"\ninconAllow = "+ inconAllow +"\n"
		);
			fw.close();
			return arquivo.getAbsolutePath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
