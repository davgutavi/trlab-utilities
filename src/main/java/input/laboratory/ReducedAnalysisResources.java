package input.laboratory;

import general.Tricluster;
import input.datasets.Common;

import java.util.List;

public class ReducedAnalysisResources extends CommonAnalysisResources {
		
	private Common dataset;

	public ReducedAnalysisResources(char analysisType,String experimentAlias, List<Tricluster> solutions, Common dataset) {
		super(analysisType,experimentAlias,solutions);
		this.dataset = dataset;
	}

	
	public Common getDataset() {
		return dataset;
	}
	
	public String toString (){
		
		String r = super.toString();
				
		r += "\nDataset = "+dataset.getDatasetName()+" (ID="+dataset.getDatasetID()+")";
				
		return r;
		
	}
	
}