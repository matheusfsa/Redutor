package redutores;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import arquivos.ArffToKeel;
import arquivos.ResultsKell;
import keel.Algorithms.Preprocess.Feature_Selection.nonevolutionary_algorithms.ABB_IEP.ABB;
import redutor.Redutor;

public class ABB_IEP extends Redutor {

	public ABB_IEP(String arquivo) {
		super(arquivo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[] reduz() {
		ArffToKeel ak = new ArffToKeel(arquivo);
		ak.transformaArffToKeel();
		ABB abb = new ABB(criaConfig(ak, 1286082570, 0.5));
		abb.ejecutar();
		ResultsKell rk = new ResultsKell(ak.getResult());	
		return rk.atributosSelecionados();
	}
	public String criaConfig(ArffToKeel ak,int seed, double beta){
		try {
			File arquivo = new File("config.txt");
			FileWriter fw = new FileWriter(arquivo);
			fw.write("algorithm = ABB_IEP\n"
					+ "inputData = \"" + ak.getTrain_in() + "\" \""+ ak.getTest_in() + "\"\n"
					+ "outputData = \"" + ak.getTrain_out() + "\" \"" + ak.getTest_out() + "\" \""+ak.getResult() +  "\"\nseed = "+ seed
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
