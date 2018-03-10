package redutores;

import redutor.Redutor;
import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.Ranker;
import weka.attributeSelection.ReliefFAttributeEval;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ReliefF extends Redutor {

	public ReliefF(String arquivo) {
		super(arquivo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[] reduz() {
		DataSource source;
		try {
			source = new DataSource(arquivo);
			Instances data = source.getDataSet();
			if (data.classIndex() == -1)
				data.setClassIndex(data.numAttributes() - 1);
			ReliefFAttributeEval eval = new ReliefFAttributeEval();
			AttributeSelection attsel = new AttributeSelection();
			Ranker search = new Ranker();
			attsel.setEvaluator(eval);
			attsel.setSearch(search);
			attsel.SelectAttributes(data);
			return attsel.selectedAttributes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
