package input.laboratory;

import java.util.List;

import general.Tricluster;
import input.datasets.Biological;

public class OPTsolBatch {
	
	private List<Tricluster> triclusters;
	
	private Biological dataset;

	public OPTsolBatch(List<Tricluster> triclusters, Biological dataset) {
		super();
		this.triclusters = triclusters;
		this.dataset = dataset;
	}

	public List<Tricluster> getTriclusters() {
		return triclusters;
	}

	public Biological getDataset() {
		return dataset;
	}
		
}