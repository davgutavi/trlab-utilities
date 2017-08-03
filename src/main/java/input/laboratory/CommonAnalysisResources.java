package input.laboratory;

import general.Tricluster;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommonAnalysisResources {

	
	//b: biological, c: common, s:synthetic
	private char analysisType;
	
	private String experimentAlias;
	
	private List<Tricluster> solutions;

	public CommonAnalysisResources(char analysisType, String experimentAlias,
			List<Tricluster> solutions) {
		super();
		
		this.analysisType = analysisType;
		this.experimentAlias = experimentAlias;
		this.solutions = solutions;
	}
	
	//************************LegacyTriGen constructor methods
	public CommonAnalysisResources(char analysisType, String experimentAlias) {
		super();
		
		this.analysisType = analysisType;
		this.experimentAlias = experimentAlias;
		this.solutions = new ArrayList<Tricluster>();
	}
	
	public void loadOneSolution (Collection<Integer> genes, Collection<Integer> samples, Collection<Integer> times){
		
		Tricluster tri = new Tricluster();
		
		List<Integer> lgenes = new ArrayList<Integer>(genes.size());
		
		List<Integer> lsamples = new ArrayList<Integer>(samples.size());
		
		List<Integer> ltimes = new ArrayList<Integer>(times.size());
				
		for(Integer g:genes)
			lgenes.add(new Integer(g.intValue()));
		
		for(Integer s:samples)
			lsamples.add(new Integer(s.intValue()));
		
		for(Integer t:times)
			ltimes.add(new Integer(t.intValue()));		
		
		tri.loadCoordinates(lgenes, lsamples, ltimes);
		
		solutions.add(tri);
				
	}	
	//************************LegacyTriGen constructor methods

	public char getAnalysisType() {
		return analysisType;
	}

	public String getExperimentAlias() {
		return experimentAlias;
	}

	public List<Tricluster> getSolutions() {
		return solutions;
	}

	public String toString (){
		
		String r = "";
				
		r +="Analysis type = "+analysisType+
			"\nExperiment alias = "+experimentAlias+
			"\nFirst solution = "+solutions.get(0)+
			"\nLast solution = "+solutions.get(solutions.size()-1);
				
		return r;
		
	}
	

}
