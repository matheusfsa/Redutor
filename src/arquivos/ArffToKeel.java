package arquivos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ArffToKeel {
	private String entrada;
	private String dir;
	private String train_in;
	private String test_in;
	private String train_out;
	private String test_out;
	private String result;
	public ArffToKeel(String entrada) {
		dir =System.getProperty("user.dir");
		this.entrada = entrada;
		train_in = dir + "/train_in.dat";
		test_in = dir + "/test_in.dat";
		train_out = dir + "/train_out.dat";
		test_out = dir + "/test_out.dat";
		result = dir + "/result.txt";
	}
	public void transformaArffToKeel(){
		
		try {
			DataSource source = new DataSource(entrada);
			Instances data = source.getDataSet();
			FileWriter ftrain_in = new FileWriter(train_in);
			FileWriter ftest_in = new FileWriter(test_in);
			FileWriter ftrain_out = new FileWriter(train_out);
			FileWriter ftest_out = new FileWriter(test_out);
			FileWriter fresult = new FileWriter(result);
			fresult.close();
			ftrain_out.close();
			ftest_out.close();
			int n_atributos = data.numAttributes();
			ftrain_in.write("@relation saida\n");
			ftest_in.write("@relation saida\n");
			for(int i = 0;i<n_atributos; i++){
				double maior = pegaValor(data, i, 0);
				double menor = pegaValor(data, i, 1);
				ftrain_in.write("@attribute " + i + " numeric [" + menor + ", " + maior +"]\n" );
				ftest_in.write("@attribute " + i + " numeric [" + menor + ", " + maior +"]\n" );
				
			}
			ftrain_in.write("@attribute class {0,1}\n" );
			ftest_in.write("@attribute class {0,1}\n" );
			ftrain_in.write("\n@data\n");
			ftest_in.write("\n@data\n");
			int n_inst= data.numInstances();
			
			for(int i = 0; i<n_inst; i++){
				ftrain_in.write(data.instance(i).toString() + ",1\n");
				if(i<=0.80*n_inst)
					ftest_in.write(data.instance(i).toString() + ",1\n");
			}
			ftrain_in.close();
			ftest_in.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getEntrada() {
		return entrada;
	}
	public String getDir() {
		return dir;
	}
	public String getTrain_in() {
		return train_in;
	}
	public String getTest_in() {
		return test_in;
	}
	public String getTrain_out() {
		return train_out;
	}
	public String getTest_out() {
		return test_out;
	}
	public String getResult() {
		return result;
	}
	public double pegaValor(Instances data, int att,int flag){
		double[] lista = data.attributeToDoubleArray(att);
		double res = lista[0];
		for(double v : lista){
			if(flag == 0){
				if(v>res)
					res = v;
			}else{
				if(v<res)
					res = v;
			}
		}
		return res;
		
	}
	
	/*public static void main(String[] args) {
		ArffToKeel arff = new ArffToKeel("C:/Users/Familia SA/Downloads/NSGAs_ND.arff");
		//"C:/Users/Familia SA/Downloads/"
		arff.transformaArffToKeel();
		//System.setProperty(, value)
		System.out.println(System.getProperty("user.dir"));
	}*/
	
}
