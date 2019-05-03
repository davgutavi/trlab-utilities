package input.algorithm;


/**
 * It represents a complete implementation of an <b>triGen</b> algorithm instance.
 * 
 * @author David Gutiérrez Avilés
 * 
 */
public class Implementation {
	
	/**
	 * Individual class name.
	 */
	private String individual;
	/**
	 * Fitness function class name.
	 */
	private String fitness;
	
	/**
	 * Data hierarchy class name.
	 */
	private String dataHierarchy;
	/**
	 * Stopping criterion class name.
	 */
	private String stoppingCriterion;
	/**
	 * Solution criterion class name.
	 */
	private String solutionCriterion;
	/**
	 * Initial population operator class name.
	 */
	private String initialPop;
	/**
	 * Selection operator class name.
	 */
	private String selection;
	/**
	 * Crossover operator class name.
	 */
	private String crossover;
	/**
	 * Mutation class name.
	 */
	private String mutation;
	
	
	/**
	 * It builds an implementation of <b>triGen</b> algorithm.
	 * @param individual Individual class name.
	 * @param fitness Fitness function class name.
	 * @param dataHierarchy Data hierarchy class name.
	 * @param stoppingCriterion Stopping criterion class name.
	 * @param solutionCriterion Solution criterion class name.
	 * @param initialPop Initial population operator class name.
	 * @param selection Selection operator class name.
	 * @param crossover Crossover operator class name.
	 * @param mutation Mutation class name.
	 */
	public Implementation(String individual, String fitness, String dataHierarchy,
			String stoppingCriterion, String solutionCriterion,
			String initialPop, String selection, String crossover,
			String mutation) {
		this.individual = individual;
		this.fitness = fitness;
		this.dataHierarchy = dataHierarchy;
		this.stoppingCriterion = stoppingCriterion;
		this.solutionCriterion = solutionCriterion;
		this.initialPop = initialPop;
		this.selection = selection;
		this.crossover = crossover;
		this.mutation = mutation;
	}

	/**
	 * It returns the Individual class name.
	 * @return Individual class name.
	 */
	public String getIndividual() {
		return individual;
	}

	/**
	 * It returns the Fitness function class name.
	 * @return Fitness function class name.
	 */
	public String getFitness() {
		return fitness;
	}

	/**
	 * It returns the Data hierarchy class name.
	 * @return Data hierarchy class name.
	 */
	public String getDataHierarchy() {
		return dataHierarchy;
	}
	
	/**
	 * It returns the Stopping criterion class name.
	 * @return Stopping criterion class name.
	 */
	public String getStoppingCriterion() {
		return stoppingCriterion;
	}
	
	/**
	 * It returns the Solution criterion class name.
	 * @return Solution criterion class name.
	 */
	public String getSolutionCriterion() {
		return solutionCriterion;
	}

	/**
	 * It returns the Initial population operator class name.
	 * @return Initial population operator class name.
	 */
	public String getInitialPop() {
		return initialPop;
	}
	
	/**
	 * It returns the Selection operator class name.
	 * @return Selection operator class name.
	 */
	public String getSelection() {
		return selection;
	}

	/**
	 * It returns the Crossover operator class name.
	 * @return Crossover operator class name.
	 */
	public String getCrossover() {
		return crossover;
	}

	/**
	 * It returns the Mutation operator class name.
	 * @return Mutation operator class name.
	 */
	public String getMutation() {
		return mutation;
	}
	
	public String toString (){
		String r = "";
		
		r = "Individual = "+individual+
				"\nFitness = "+fitness+
				"\nData Hierarchy = "+dataHierarchy+
				"\nStopping Criterion = "+stoppingCriterion+
				"\nSolution Criterion = "+solutionCriterion+
				"\nInitial Population = "+initialPop+
				"\nSelection = "+selection+
				"\nCrossover = "+crossover+
				"\nMutation = "+mutation;
		
		return r;
		
	}
	
}
