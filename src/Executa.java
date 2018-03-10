import redutores.ReliefF;
import redutores.ABB_IEP;
import redutores.CFS;
import redutores.Focus;

public class Executa {

	public static void main(String[] args) {
		String arquivo = "D:/Matheus/Estudo/Computação/PIBIC/Dados/sumo600inst.arff";
		Focus focus = new Focus(arquivo);
		CFS cfs = new CFS(arquivo);
		ReliefF relieff = new ReliefF(arquivo);
		ABB_IEP abb = new ABB_IEP(arquivo);
		int[] features = focus.reduz();
		for (int i : features) {
				System.out.print(i + " ");
		}
		
	}
}
