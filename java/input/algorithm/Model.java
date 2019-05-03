package input.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * It represents a valid model of <b>triGen</b> algorithm. A model include all valid class names for every implementation elements of an <b>triGen</b>
 * algorithm instance such as Individuals, Fitness, Data hierarchies, Stopping criteria, Solution criteria, Initial population operators, Selection 
 * operators, Crossover operators and Mutation operators.
 * 
 * @author David Gutiérrez Avilés
 *
 */
public class Model {

	/**
	 * Model name.
	 */
	private String name;

	// K = xml name attribute, V = class name
		
	/**
	 * Individual valid class names. Key: xml name attribute, Value: valid class name.
	 */
	private Map<String, String> individuals;
	
	/**
	 * Fitness valid class configurations. Key: xml name attribute, Value: {@link FitnessClass} object.
	 */
	private Map<String, FitnessClass> fitness;

	/**
	 * Data hierarchy valid class names. Key: xml name attribute, Value: valid class name.
	 */
	private Map<String, String> dataHierarchies;
	
	/**
	 * Stopping criterion valid class names. Key: xml name attribute, Value: valid class name.
	 */
	private Map<String, String> stoppingCriteria;
	
	/**
	 * Solution criterion valid class names. Key: xml name attribute, Value: valid class name.
	 */
	private Map<String, String> solutionCriteria;

	/**
	 * Initial population operator valid class names. Key: xml name attribute, Value: valid class name.
	 */
	private Map<String, String> initialPops;

	/**
	 * Selection operator valid class names. Key: xml name attribute, Value: valid class name.
	 */
	private Map<String, String> selections;

	/**
	 * Crossover operator valid class names. Key: xml name attribute, Value: valid class name.
	 */
	private Map<String, String> crossovers;

	/**
	 * Mutation operator valid class names. Key: xml name attribute, Value: valid class name.
	 */
	private Map<String, String> mutations;

	/**
	 * It builds am empty Model with the given name.
	 * @param name Model name.
	 */
	public Model(String name) {

		this.name = name;

		this.individuals = new HashMap<String, String>();
		this.fitness = new HashMap<String, FitnessClass>();
		this.dataHierarchies = new HashMap<String, String>();
		this.stoppingCriteria = new HashMap<String, String>();
		this.solutionCriteria = new HashMap<String, String>();
		this.initialPops = new HashMap<String, String>();
		this.selections = new HashMap<String, String>();
		this.crossovers = new HashMap<String, String>();
		this.mutations = new HashMap<String, String>();

	}

	/**
	 * Put a new Individual class name with the properly name.
	 * @param name Name of Individual (xml name attribute).
	 * @param className Valid Individual class name.
	 */
	public void putIndividual(String name, String className) {
		individuals.put(name, className);
	}
	
	/**
	 * Put a new Fitness class name with the properly name.
	 * @param name Name of Fitness (xml name attribute).
	 * @param className Valid Fitness class name.
	 */
	public void putFitness(String name, String className){
		
		FitnessClass fc = new FitnessClass(className);
		
		fitness.put(name, fc);
		
	}
		
	/**
	 * Put a new Transformation class name with the properly name on Fitness valid implementations.
	 * @param transformationName Name of Transformation (xml name attribute).
	 * @param trasnformationClassName Valid Transformation class name.
	 * @param fitnessName Name of Fitness (xml name attribute).
	 */
	public void putTransformationInFitness(String transformationName, String trasnformationClassName, String fitnessName){
		
		FitnessClass fc = fitness.get(fitnessName);
		
		fc.putTransformation(transformationName, trasnformationClassName);
				
	}
	
	/**
	 * Put a new Data hierarchy class name with the properly name.
	 * @param name Name of Data hierarchy (xml name attribute).
	 * @param className Valid Data hierarchy class name.
	 */
	public void putDataHierarchy(String name, String className) {
		dataHierarchies.put(name, className);
	}

	/**
	 * Put a new Stopping criterion class name with the properly name.
	 * @param name Name of Stopping criterion (xml name attribute).
	 * @param className Valid Stopping criterion class name.
	 */
	public void putStoppingCriterion(String name, String className) {
		stoppingCriteria.put(name, className);
	}

	/**
	 * Put a new Solution criterion class name with the properly name.
	 * @param name Name of Solution criterion (xml name attribute).
	 * @param className Valid Solution criterion class name.
	 */
	public void putSolutionCriterion(String name, String className) {
		solutionCriteria.put(name, className);
	}

	/**
	 * Put a new Initial population operator class name with the properly name.
	 * @param name Name of Initial population operator (xml name attribute).
	 * @param className Valid Initial population operator class name.
	 */
	public void putInitialPop(String name, String className) {
		initialPops.put(name, className);
	}

	/**
	 * Put a new Selection operator class name with the properly name.
	 * @param name Name of Selection operator (xml name attribute).
	 * @param className Valid Selection operator class name.
	 */
	public void putSelection(String name, String className) {
		selections.put(name, className);
	}

	/**
	 * Put a new Crossover operator class name with the properly name.
	 * @param name Name of Crossover operator (xml name attribute).
	 * @param className Valid Crossover operator class name.
	 */
	public void putCrossover(String name, String className) {
		crossovers.put(name, className);
	}
	
	/**
	 * Put a new Mutation operator class name with the properly name.
	 * @param name Name of Mutation operator (xml name attribute).
	 * @param className Valid Mutation operator class name.
	 */
	public void putMutation(String name, String className) {
		mutations.put(name, className);
	}

	
	/**
	 * It returns the Model's name.
	 * @return Model's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * It returns a {@link Map} with every {Key =  Name, Value = Class name} Individual pairs.
	 * @return {@link Map} with {Key =  Name, Value = Class name} Individual pairs.
	 */
	public Map<String, String> getIndividuals() {
		return individuals;
	}
	
	/**
	 * It returns a {@link Map} with every {Key =  Name, Value = {@link FitnessClass}} Fitness pairs.
	 * @return {@link Map} with {Key =  Name, Value = {@link FitnessClass}} Fitness pairs.
	 */
	public Map<String, FitnessClass> getFitness() {
		return fitness;
	}
	
	/**
	 * It returns a {@link Map} with every {Key =  Name, Value = Class name} Data hierarchy pairs.
	 * @return {@link Map} with {Key =  Name, Value = Class name} Data hierarchy pairs.
	 */
	public Map<String, String> getDataHierarchies() {
		return dataHierarchies;
	}

	/**
	 * It returns a {@link Map} with every {Key =  Name, Value = Class name} Stopping criterion pairs.
	 * @return {@link Map} with {Key =  Name, Value = Class name} Stopping criterion pairs.
	 */
	public Map<String, String> getStoppingCriteria() {
		return stoppingCriteria;
	}

	/**
	 * It returns a {@link Map} with every {Key =  Name, Value = Class name} Solution criterion pairs.
	 * @return {@link Map} with {Key =  Name, Value = Class name} Solution criterion pairs.
	 */
	public Map<String, String> getSolutionCriteria() {
		return solutionCriteria;
	}
	
	/**
	 * It returns a {@link Map} with every {Key =  Name, Value = Class name} Initial population operator pairs.
	 * @return {@link Map} with {Key =  Name, Value = Class name} Initial population operator pairs.
	 */
	public Map<String, String> getInitialPops() {
		return initialPops;
	}
	
	/**
	 * It returns a {@link Map} with every {Key =  Name, Value = Class name} Selection operator pairs.
	 * @return {@link Map} with {Key =  Name, Value = Class name} Selection operator pairs.
	 */
	public Map<String, String> getSelections() {
		return selections;
	}
	
	/**
	 * It returns a {@link Map} with every {Key =  Name, Value = Class name} Crossover operator pairs.
	 * @return {@link Map} with {Key =  Name, Value = Class name} Crossover operator pairs.
	 */
	public Map<String, String> getCrossovers() {
		return crossovers;
	}

	/**
	 * It returns a {@link Map} with every {Key =  Name, Value = Class name} Mutation operator pairs.
	 * @return {@link Map} with {Key =  Name, Value = Class name} Mutation operator pairs.
	 */
	public Map<String, String> getMutations() {
		return mutations;
	}

	public String toString (){
		
		String r = "";
				
		r = name.toUpperCase()+"\n"+
			"\nIndividuals: "+individuals.toString()+
			"\nFitness: "+fitness.toString()+			
			"\nData Hierarchies: "+dataHierarchies.toString()+
			"\nStopping Criteria: "+stoppingCriteria.toString()+
			"\nSolution Criteria: "+solutionCriteria.toString()+
			"\nInitial Populations: "+initialPops.toString()+
			"\nSelections: "+selections.toString()+
			"\nCrossovers: "+crossovers.toString()+
			"\nMutations: "+mutations.toString();	
				
		return r;
				
	}
	
	
	/**
	 * It represents all valid Transformation class names for a concrete Fitness function.
	 * 
	 * @author David Gutiérrez Aviléss
	 *
	 */
	public class FitnessClass{
		
		/**
		 * Fitness class name.
		 */
		private String fitnessClassName;
		
		/**
		 * Transformation method valid class names. Key: xml name attribute, Value: valid class name.
		 */
		private Map<String,String> transformations;
				

		/**
		 * It builds am empty FitnessClass object with the given Fitness class name.
		 * @param fitnessClassName Fitness class name.
		 */
		public FitnessClass(String fitnessClassName){
			
			this.fitnessClassName = fitnessClassName;
			
			this.transformations = new HashMap<String, String>();
		
		}
		
		/**
		 * It returns the FitnessClass' name.
		 * @return FitnessClass' name.
		 */
		public String getFitnessClassName() {
			return fitnessClassName;
		}
		
		/**
		 * It returns a {@link Map} with every {Key =  Name, Value = Class name} Transformation method pairs.
		 * @return {@link Map} with {Key =  Name, Value = Class name} Transformation method pairs.
		 */
		public Map<String, String> getTransformations() {
			return transformations;
		}
		
		/**
		 * Put a new Transformation method class name with the properly name.
		 * @param name Name of Transformation method (xml name attribute).
		 * @param className Valid Transformation method class name.
		 */
		public void putTransformation(String name, String className) {
			transformations.put(name, className);
		}
		
		public String toString (){
			
			String r = "";
			
			r = fitnessClassName+"--"+transformations;
						
			return r;
			
		}
				
	}
	
	
}
