package input.algorithm;

import input.Parameters;
import input.datasets.Common;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.TextUtilities;

/**
 * It stores all options of a <b>triGen</b> algorithm run.
 * 
 * @author David Gutiérrez Avilés
 *
 */
@SuppressWarnings("unused")
public class Control extends Parameters {

	
	private static final Logger LOG = LoggerFactory.getLogger(Control.class);
	
	
	/**
	 * Minimun value of N.
	 */
	private static final int MIN_N = 1;
	
	/**
	 * Maximun value of N.
	 */
	private static final int MAX_N = 10000000;
	
	/**
	 * Minimun value of G.
	 */
	private static final int MIN_G = 1;
	
	/**
	 * Maximun value of G.
	 */
	private static final int MAX_G = 10000000;
	
	/**
	 * Minimun value of I.
	 */
	private static final int MIN_I = 3;
	
	/**
	 * Maximun value of I.
	 */
	private static final int MAX_I = 10000000;
	
	/**
	 * Minimun value of Ale.
	 */
	private static final double MIN_ALE = 0.0;
	
	/**
	 * Maximun value of Ale.
	 */
	private static final double MAX_ALE = 1.0;
	
	/**
	 * Minimun value of Sel.
	 */
	private static final double MIN_SEL = 0.0;
	
	/**
	 * Maximun value of Sel.
	 */
	private static final double MAX_SEL = 1.0;
	
	/**
	 * Minimun value of Mut.
	 */
	private static final double MIN_MUT = 0.0;
	
	/**
	 * Maximun value of Mut.
	 */
	private static final double MAX_MUT = 1.0;
	
	/**
	 * Minimun value of Wf.
	 */
	private static final double MIN_WF = 0.0;
	
	/**
	 * Maximun value of Wf.
	 */
	private static final double MAX_WF = 1.0;
	
	
	/**
	 * Minimun value of Wg.
	 */
	private static final double MIN_WG = 0.0;
	
	/**
	 * Maximun value of Wg.
	 */
	private static final double MAX_WG = 1.0;
	
	/**
	 * Minimun value of Wc.
	 */
	private static final double MIN_WC = 0.0;
	
	/**
	 * Maximun value of Wc.
	 */
	private static final double MAX_WC = 1.0;
	
	/**
	 * Minimun value of Wt.
	 */
	private static final double MIN_WT = 0.0;
	
	/**
	 * Maximun value of Wt.
	 */
	private static final double MAX_WT = 1.0;
	
	/**
	 * Minimun value of WOg.
	 */
	private static final double MIN_WOG = 0.0;
	
	/**
	 * Maximun value of WOg.
	 */
	private static final double MAX_WOG = 1.0;
	
	/**
	 * Minimun value of WOc.
	 */
	private static final double MIN_WOC = 0.0;
	
	/**
	 * Maximun value of WOc.
	 */
	private static final double MAX_WOC = 1.0;
	
	/**
	 * Minimun value of WOt.
	 */
	private static final double MIN_WOT = 0.0;
	
	/**
	 * Maximun value of WOt.
	 */
	private static final double MAX_WOT = 1.0;
	
	/**
	 * Minimun value of minG.
	 */
	private static final int MIN_MING = 2;
	
	/**
	 * Minimun value of minC.
	 */
	private static final int MIN_MINC = 2;
	
	/**
	 * Minimun value of minT.
	 */
	private static final int MIN_MINT = 2;
	
	/**
	 * Minimun value of threads.
	 */
	private static final int MIN_THREADS = 0;
	
	/**
	 * Maximun value of threads.
	 */
	private static final int MAX_THREADS = 12;
	
	//USER
	
	/**
	 * Current value of N.
	 */
	private int n;
	
	/**
	 * Current value of G.
	 */
	private int g;
	
	/**
	 * Current value of I.
	 */
	private int i;
	
	/**
	 * Current value of Ale.
	 */
	private double ale;
	
	/**
	 * Current value of Sel.
	 */
	private double sel;
	
	/**
	 * Current value of Mut.
	 */
	private double mut;
	
	/**
	 * Current value of Wf.
	 */
	private double wf;
		
	/**
	 * Current value of Wg.
	 */
	private double wg;
	
	/**
	 * Current value of Wc.
	 */
	private double wc;
	
	/**
	 * Current value of Wt.
	 */
	private double wt;
	
	/**
	 * Current value of WOg.
	 */
	private double wog;
	
	/**
	 * Current value of WOc.
	 */
	private double woc;
	
	/**
	 * Current value of WOt.
	 */
	private double wot;
	
	//ADVANCED
	
	/**
	 * Current value of minG.
	 */
	private int minG;
	
	/**
	 * Current value of minC.
	 */
	private int minC;
	
	/**
	 * Current value of minT.
	 */
	private int minT;
	
	/**
	 * Current value of maxG.
	 */
	private int maxG;
	
	/**
	 * Current value of maxC.
	 */
	private int maxC;
	
	/**
	 * Current value of maxT.
	 */
	private int maxT;
	
	/**
	 * Current value of threads.
	 */
	private int threads;
	
	/**
	 * Current triGen implementation.
	 */
	
	private Implementation implementation;
	
	private Common dataset;
	
	private String individualMethod;
	private String fitnessMethod;
	private String dataHierarchyMethod;
	private String stoppingCriterionMethod;
	private String solutionCriterionMethod;
	private String initialPopMethod;
	private String selectionMethod;
	private String crossoverMethod;
	private String mutationMethod;

	
	/**
	 * It stores and checks all parameters of a <b>triGen</b> run contained in {@link Properties} passed by input parameter.
	 * 
	 * @param prop {@link Properties} object that contains all input <b>triGen</b> parameters.
	 * @param geneSize Number of genes of the input dataset through {@link Common}.
	 * @param sampleSize Number of samples or conditions of the input dataset through {@link Common}.
	 * @param timeSize Number of times of the input dataset through {@link Common}.
	 * @throws WrongContolException A parameter isn't properly set.
	 * @throws InvalidImplementationException 
	 * @throws URISyntaxException 
	 */
	public Control (Common dataset, Properties prop) throws WrongContolException, InvalidImplementationException{
	
		super(new File (TextUtilities.getRootPathWithSlash(prop.getProperty("out"))), 
				TextUtilities.getFileName(prop.getProperty("out")));
				
		specificCheckings (dataset,prop);
			
	}
	
	public String toString (){
		
		String r = super.toString();
		
		r +=	"\nDataset = "+dataset.getDatasetName()+" (ID="+dataset.getDatasetID()+")"+
				"\nN = "+n+
				"\nG = "+g+
				"\nI = "+i+
				"\nAle = "+ale+
				"\nSel = "+sel+
				"\nMut = "+mut+
				"\nWf = "+wf+
				"\nWg = "+wg+
				"\nWc = "+wc+
				"\nWt = "+wt+
				"\nWOg = "+wog+
				"\nWOc = "+woc+
				"\nWOt = "+wot+
				"\nminG = "+minG+
				"\nminC = "+minC+
				"\nminT = "+minT+
				"\nmaxG = "+maxG+
				"\nmaxC = "+maxC+
				"\nmaxT = "+maxT+
				"\nthreads = "+threads+
				"\n"+implementation.toString()+
				"\nIndividual method = "+individualMethod+
				"\nFitness method = "+fitnessMethod+
				"\nData hierarchy method = "+dataHierarchyMethod+
				"\nStopping criterion method = "+stoppingCriterionMethod+	
				"\nSolution criterion method = "+solutionCriterionMethod+
				"\nInitial population method = "+initialPopMethod+
				"\nSelection method = "+selectionMethod+
				"\nCrossover method = "+crossoverMethod+
				"\nMutation method = "+mutationMethod;
	
		return r;
		
	}
		
	/**
	 * It returns the N parameter.
	 * @return N parameter.
	 */
	public int getN() {
		return n;
	}
	
	/**
	 * It returns the G parameter.
	 * @return G parameter.
	 */
	public int getG() {
		return g;
	}
	
	/**
	 * It returns the I parameter.
	 * @return I parameter.
	 */
	public int getI() {
		return i;
	}

	/**
	 * It returns the Ale parameter.
	 * @return Ale parameter.
	 */
	public double getAle() {
		return ale;
	}

	/**
	 * It returns the Sel parameter.
	 * @return Sel parameter.
	 */
	public double getSel() {
		return sel;
	}
	
	/**
	 * It returns the Mut parameter.
	 * @return Mut parameter.
	 */
	public double getMut() {
		return mut;
	}
	
	/**
	 * It returns the Wf parameter.
	 * @return Wf parameter.
	 */
	public double getWf() {
		return wf;
	}
		
	/**
	 * It returns the Wg parameter.
	 * @return Wg parameter.
	 */
	public double getWg() {
		return wg;
	}
	
	/**
	 * It returns the Wc parameter.
	 * @return Wc parameter.
	 */
	public double getWc() {
		return wc;
	}
	
	/**
	 * It returns the Wt parameter.
	 * @return Wt parameter.
	 */
	public double getWt() {
		return wt;
	}
	
	/**
	 * It returns the WOg parameter.
	 * @return WOg parameter.
	 */
	public double getWog() {
		return wog;
	}
	
	/**
	 * It returns the WOc parameter.
	 * @return WOc parameter.
	 */
	public double getWoc() {
		return woc;
	}

	/**
	 * It returns the WOt parameter.
	 * @return WOt parameter.
	 */
	public double getWot() {
		return wot;
	}

	/**
	 * It returns the minG parameter.
	 * @return minG parameter.
	 */
	public int getMinG() {
		return minG;
	}

	/**
	 * It returns the minC parameter.
	 * @return minC parameter.
	 */
	public int getMinC() {
		return minC;
	}
	
	/**
	 * It returns the minT parameter.
	 * @return minT parameter.
	 */
	public int getMinT() {
		return minT;
	}
	
	/**
	 * It returns the maxG parameter.
	 * @return maxG parameter.
	 */
	public int getMaxG() {
		return maxG;
	}
	
	/**
	 * It returns the maxC parameter.
	 * @return maxC parameter.
	 */
	public int getMaxC() {
		return maxC;
	}
	
	/**
	 * It returns the maxT parameter.
	 * @return maxT parameter.
	 */
	public int getMaxT() {
		return maxT;
	}
	
	/**
	 * It returns the threads parameter.
	 * @return threads parameter.
	 */
	public int getThreads() {
		return threads;
	}
	
	public Implementation getImplementation(){
		
		return implementation;
		
	}
	
	public Common getDataset() {
		return dataset;
	}
	
	public String getIndividualMethod() {
		return individualMethod;
	}

	public String getFitnessMethod() {
		return fitnessMethod;
	}

	public String getDataHierarchyMethod() {
		return dataHierarchyMethod;
	}

	public String getStoppingCriterionMethod() {
		return stoppingCriterionMethod;
	}

	public String getSolutionCriterionMethod() {
		return solutionCriterionMethod;
	}

	public String getInitialPopMethod() {
		return initialPopMethod;
	}

	public String getSelectionMethod() {
		return selectionMethod;
	}

	public String getCrossoverMethod() {
		return crossoverMethod;
	}

	public String getMutationMethod() {
		return mutationMethod;
	}

	private void specificCheckings (Common dataset, Properties prop) throws WrongContolException, InvalidImplementationException{
		
		//int geneSize, int sampleSize, int timeSize
		this.n = Integer.parseInt(prop.getProperty("N"));
		this.g = Integer.parseInt(prop.getProperty("G"));
		this.i = Integer.parseInt(prop.getProperty("I"));
		
		this.ale = Double.parseDouble(prop.getProperty("Ale"));
		this.sel = Double.parseDouble(prop.getProperty("Sel"));
		this.mut = Double.parseDouble(prop.getProperty("Mut"));
		
		this.wf = Double.parseDouble(prop.getProperty("Wf"));
		this.wg = Double.parseDouble(prop.getProperty("Wg"));
		this.wc = Double.parseDouble(prop.getProperty("Wc"));
		this.wt = Double.parseDouble(prop.getProperty("Wt"));
		this.wog = Double.parseDouble(prop.getProperty("WOg"));
		this.woc = Double.parseDouble(prop.getProperty("WOc"));
		this.wot = Double.parseDouble(prop.getProperty("WOt"));
		
		
		String a_minG = prop.getProperty("minG");
		String a_maxG = prop.getProperty("maxG");
		
		String a_minC = prop.getProperty("minC");
		String a_maxC = prop.getProperty("maxC");
		
		String a_minT = prop.getProperty("minT");
		String a_maxT = prop.getProperty("maxT");
		
		
		if (a_minG!=null){
			this.minG = Integer.parseInt(a_minG);
		}
		else{
			this.minG = dataset.getDefMinG();			
		}
		
		if (a_maxG!=null){
			this.maxG = Integer.parseInt(a_maxG);
		}
		else{
			this.maxG = dataset.getDefMaxG();			
		}
		
		if (a_minC!=null){
			this.minC = Integer.parseInt(a_minC);
		}
		else{
			this.minC = dataset.getDefMinC();			
		}
		
		if (a_maxC!=null){
			this.maxC = Integer.parseInt(a_maxC);
		}
		else{
			this.maxC = dataset.getDefMaxC();			
		}
		
		if (a_minT!=null){
			this.minT = Integer.parseInt(a_minT);
		}
		else{
			this.minT = dataset.getDefMinT();			
		}
		
		if (a_maxT!=null){
			this.maxT = Integer.parseInt(a_maxT);
		}
		else{
			this.maxT = dataset.getDefMaxT();			
		}
			
//		this.minC = Integer.parseInt(prop.getProperty("minC"));
//		this.minT = Integer.parseInt(prop.getProperty("minT"));
//		this.maxG = Integer.parseInt(prop.getProperty("maxG"));
//		this.maxC = Integer.parseInt(prop.getProperty("maxC"));
//		this.maxT = Integer.parseInt(prop.getProperty("maxT"));
				
		this.threads = Integer.parseInt(prop.getProperty("threads"));
		
		String errMessage = "";
		boolean error = false;
		
		errMessage += checkInterval("N",n,MIN_N,MAX_N);
		if (!errMessage.equalsIgnoreCase("")) error = true;
				
		errMessage += checkInterval("G",g,MIN_G,MAX_G);
		if (!errMessage.equalsIgnoreCase("")) error = true;
		
		errMessage += checkInterval("I",i,MIN_I,MAX_I);
		if (!errMessage.equalsIgnoreCase("")) error = true;
		
		errMessage += checkInterval("Ale",ale,MIN_ALE,MAX_ALE);
		if (!errMessage.equalsIgnoreCase("")) error = true;
		
		errMessage += checkInterval("Sel",sel,MIN_SEL,MAX_SEL);
		if (!errMessage.equalsIgnoreCase("")) error = true;
		
		errMessage += checkInterval("Mut",mut,MIN_MUT,MAX_MUT);
		if (!errMessage.equalsIgnoreCase("")) error = true;
		
		errMessage += checkInterval("Wf",wf,MIN_WF,MAX_WF);
		if (!errMessage.equalsIgnoreCase("")) error = true;
		
		errMessage += checkInterval("Wg",wg,MIN_WG,MAX_WG);
		if (!errMessage.equalsIgnoreCase("")) error = true;
		
		errMessage += checkInterval("Wc",wc,MIN_WC,MAX_WC);
		if (!errMessage.equalsIgnoreCase("")) error = true;
		
		errMessage += checkInterval("Wt",wt,MIN_WT,MAX_WT);
		if (!errMessage.equalsIgnoreCase("")) error = true;
		
		errMessage += checkInterval("WOg",wog,MIN_WOG,MAX_WOG);
		if (!errMessage.equalsIgnoreCase("")) error = true;
		
		errMessage += checkInterval("WOc",woc,MIN_WOC,MAX_WOC);
		if (!errMessage.equalsIgnoreCase("")) error = true;
		
		errMessage += checkInterval("WOt",wot,MIN_WOT,MAX_WOT);
		if (!errMessage.equalsIgnoreCase("")) error = true;
		
		errMessage += checkMin("minG",minG,MIN_MING);
		if (!errMessage.equalsIgnoreCase("")) error = true;
		
		errMessage += checkMin("minC",minC,MIN_MINC);
		if (!errMessage.equalsIgnoreCase("")) error = true;
		
		errMessage += checkMin("minT",minT,MIN_MINT);
		if (!errMessage.equalsIgnoreCase("")) error = true;
		
		if (maxG==-1)
			maxG = dataset.getGeneSize();
		if (maxC==-1)
			maxC = dataset.getSampleSize();
		if (maxT==-1)
			maxT = dataset.getTimeSize();
		
		errMessage += checkMax("maxG",maxG,dataset.getGeneSize());
		if (!errMessage.equalsIgnoreCase("")) error = true;
		
	
		errMessage += checkMax("maxC",maxC,dataset.getSampleSize());
		if (!errMessage.equalsIgnoreCase("")) error = true;
		
		errMessage += checkMax("maxT",maxT,dataset.getTimeSize());
		if (!errMessage.equalsIgnoreCase("")) error = true;
		
		errMessage += checkMaxMin("Gene Sizes",maxG,minG);
		if (!errMessage.equalsIgnoreCase("")) error = true;
		
		errMessage += checkMaxMin("Sample Sizes",maxC,minC);
		if (!errMessage.equalsIgnoreCase("")) error = true;
		
		errMessage += checkMaxMin("Time Sizes",maxT,minT);
		if (!errMessage.equalsIgnoreCase("")) error = true;
		
		errMessage += checkInterval("threads",threads,MIN_THREADS,MAX_THREADS);
		if (!errMessage.equalsIgnoreCase("")) error = true;
			
		
		if (error) throw new WrongContolException(errMessage);
		
		
		ModelsLoader ml = new ModelsLoader();
		
		individualMethod = prop.getProperty("individual");
		fitnessMethod = prop.getProperty("fitness");
		dataHierarchyMethod = prop.getProperty("datahierarchy");
		stoppingCriterionMethod = prop.getProperty("stoppingcriterion");
		solutionCriterionMethod = prop.getProperty("solutioncriterion");
		initialPopMethod = prop.getProperty("initialpop");
		selectionMethod = prop.getProperty("selection");
		crossoverMethod = prop.getProperty("crossover");
		mutationMethod =  prop.getProperty("mutation");
		

		this.implementation = ml.checkImplementation(
				individualMethod, fitnessMethod,
				dataHierarchyMethod,
				stoppingCriterionMethod, solutionCriterionMethod,
				initialPopMethod, selectionMethod,
				crossoverMethod, mutationMethod);
		
		
		
		this.dataset = dataset;
	
	}
	  	
	/**
	 * It checks a parameter with a current value is less than a maximun value.
	 * 
	 * @param parameter Current parameter.
	 * @param value Current value.
	 * @param max Maximun value.
	 * @return An error message if checking is not correct otherwise empty {@link String}.
	 */
	private String checkMax (String parameter, int value, int max){
		
		String r = "";
		
		if (value>max)
			r = parameter+" must be lower or equal to "+max+"\n";
		
		return r;
		
	}
	
	/**
	 * It checks a parameter with a current value is greater than a minimum value.
	 * 
	 * @param parameter Current parameter.
	 * @param value Current value.
	 * @param min Minimun value.
	 * @return An error message if checking is not correct otherwise empty {@link String}.
	 */
	private String checkMin (String parameter, int value, int min){
		
		String r = "";
		
		if (value<min)
			r = parameter+" must be greater or equal to "+min+"\n";
		
		return r;
		
	}
	
	private String checkMaxMin (String parameter, int max, int min){
		
		String r = "";
		
		if (min>max)
			r = parameter+" : min size ("+min+") is greater than max size ("+max+")\n";
		
		
		return r;
		
	}
	
	/**
	 * It checks a parameter with a current value through a minimun and maximun values. 
	 * 
	 * @param parameter Current parameter.
	 * @param value Current value.
	 * @param min Minimun value.
	 * @param max Maximun value.
	 * @return An error message if checking is not correct otherwise empty {@link String}.
	 */
	private String checkInterval (String parameter, int value, int min, int max){
		
		String r = "";
		
		if (value<min||value>max)
			r = parameter+" must be set to ["+min+","+max+"]\n";
		
		return r;
		
	}
	
	/**
	 * It checks a parameter with a current value through a minimun and maximun values. 
	 * 
	 * @param parameter Current parameter.
	 * @param value Current value.
	 * @param min Minimun value.
	 * @param max Maximun value.
	 * @return An error message if checking is not correct otherwise empty {@link String}.
	 */
	private String checkInterval (String parameter, double value, double min, double max){
		
		String r = "";
		
		if (value<min||value>max)
			r = parameter+" must be set to ["+min+","+max+"]\n";
		
		return r;
		
	}
	
}
