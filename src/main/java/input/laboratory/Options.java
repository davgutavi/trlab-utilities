package input.laboratory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import input.Parameters;
import input.algorithm.WrongContolException;
import utils.SystemUtilities;
import utils.TextUtilities;

/**
 * It stores all options that work out of a <b>triGen</b> algorithm run. 
 * 
 * @author David Gutiérrez Avilés
 *
 */
public class Options extends Parameters {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(Options.class);
	
	private static final double MIN_EVAL = 0.0;
	
	private static final double MAX_EVAL = 1000000000000000000000000.0;
	
	private static final boolean DEFAULT_GORESOURCES = false;
	
	/**
	 * Yes ignore option for GO analysis.  
	 */
	private static final String GORESOURCES_YES = "yes";
	
	/**
	 * No ignore option for GO analysis.  
	 */
	private static final String GORESOURCES_NO = "no";
		
	/**
	 * Default value.
	 */
	private static final String DEFAULT = "default";
	
	//MTC
	
	private static final String DEFAULT_MTC = "Bonferroni";
	
	/**
	* Bonferroni Multiple Testing Correction (MTC) option for GO analysis. 
	*/
	private static final String BONFERRONI = "Bonferroni";
		
	/**
	* None Multiple Testing Correction (MTC) option for GO analysis. 
	*/
	private static final String NONE = "None";
		
	/**
	* Westfall young single step Multiple Testing Correction (MTC) option for GO analysis. 
	*/
	private static final String WESTFALL = "Westfall-Young-Single-Step";
		
	//CALCULATION
	
	private static final String DEFAULT_CALCULATION = "Term-For-Term";
	
	/**
	* Term for term calculation option for GO analysis.  
	*/
	private static final String TFT = "Term-For-Term";
		
	/**
	* Parent child union calculation option for GO analysis.  
	*/
	private static final String PCU = "Parent-Child-Union";
		
	/**
	* Parent child intersection calculation option for GO analysis.  
	*/
	private static final String PCI = "Parent-Child-Intersection";
			
	//DOT
	
	private static final String DEFAULT_DOT = "";
	
	/**
	 * Minimum pi-value bias considered for GO analysis.
	 */
	private static final double MIN_DOT = 0.0;
	
	/**
	 * Maximum pi-value bias considered for GO analysis.
	 */
	private static final double MAX_DOT = 0.5;
	
	//FILTER
	
	private static final String DEFAULT_FILTER = "";
	
	//IGNORE
	
	private static final boolean DEFAULT_IGNORE = false;
	
	/**
	 * Yes ignore option for GO analysis.  
	 */
	private static final String IGNORE_YES = "yes";
	
	/**
	 * No ignore option for GO analysis.  
	 */
	private static final String IGNORE_NO = "no";
	
	//ANNOTATION
	
	private static final boolean DEFAULT_ANNOTATION = false;
	
	private static final String ANNOTATION_YES = "yes";
		
	private static final String ANNOTATION_NO = "no";
	
	//RESAMPLING STEPS
	
	private static final String DEFAULT_STEPS = "";
	
	/**
	 * Minimum resampling steps option for GO analysis.  
	 */
	private static final int MIN_STEPS = 0;
	
	/**
	 * Maximun resampling steps option for GO analysis.  
	 */
	private static final int MAX_STEPS = 10000;
	
	
	
	//BIOLOGICAL
	
	/**
	 * Current GO Multiple Testing Correction (MTC) option.
	 */
	private String goMtc;
	
	/**
	 * Current GO calculation method.
	 */
	private String goCalculation;
	
	/**
	 * Current GO pi-value bias.
	 */
	private String goDot;
	
	private String goFilter;
	
	/**
	 * Current GO ignore option.
	 */
	private boolean goIgnore;
	
	private boolean goAnnotation;
	
	/**
	 * True if you want go result files
	 */
	private boolean goResources;
	
	/**
	 * Current GO resampling steps.
	 */
	private String goResamplingSteps;
		
	private double threshold;
	private double base;
	private double step;
	private double difference;
	private double bonus;
			
	
	//EVALUATION
	
	private double cgraphical;
	private double cpearson;
	private double cspearman;
	
	private double graphical;
	private double pearson;
	private double spearman;
	private double biological;


	
	public Options () throws WrongOptionsException{
		
		super();
		
		specificCheckings(SystemUtilities.getLaboratoryProperties());
		
		
	}
	
	
	
	public Options (File outFolder,String outName) throws WrongOptionsException{
				
		super(outFolder,outName);
		
		specificCheckings(SystemUtilities.getLaboratoryProperties());
		
		
	}
	
		
	/**
	 * It stores and checks all off-line options contained in {@link Properties} passed by input parameter.
	 * 
	 * @param prop {@link Properties} object that contains all input <b>triGen</b> parameters.
	 * @throws WrongContolException A parameter isn't properly set.
	 * @throws IOException 
	 */
	public Options (Properties prop, File outFolder, String outName) throws WrongOptionsException{
		
		super(outFolder, outName);		
			
		specificCheckings(prop);
					
		
	}
				
	
	
	public Options (Properties prop) throws WrongOptionsException{
		
		super(new File (TextUtilities.getRootPathWithSlash(prop.getProperty("out"))), 
				TextUtilities.getFileName(prop.getProperty("out")));		
		
		
		specificCheckings(prop);
					
		
	}
	
	
	
	private void specificCheckings(Properties prop) throws WrongOptionsException {
		//LOG.debug("go ="+goAppPath);
				//goAppPath = TextUtilities.getCorrectPathFromHome(aux2);
				
				//Get Go options
				goMtc = prop.getProperty("mtc");
				goCalculation = prop.getProperty("calculation");
				goDot = prop.getProperty("dot");
				goFilter = prop.getProperty("filter");
				String auxGoIgnore = prop.getProperty("ignore");
				String auxGoAnnotation = prop.getProperty("annotation");
				goResamplingSteps = prop.getProperty("resamplingsteps");
				String auxGoResources = prop.getProperty("go_resources");
				
				
				
				//Get evaluation options
				
				//EVALUATION
				cgraphical = Double.parseDouble(prop.getProperty("CGRQ"));
				cpearson = Double.parseDouble(prop.getProperty("CPEQ"));
				cspearman = Double.parseDouble(prop.getProperty("CSPQ"));		
				
				graphical = Double.parseDouble(prop.getProperty("GRQ"));
				pearson = Double.parseDouble(prop.getProperty("PEQ"));
				spearman = Double.parseDouble(prop.getProperty("SPQ"));
				biological = Double.parseDouble(prop.getProperty("BIOQ"));
					
				//BIOLOGICAL
				
				threshold = Double.parseDouble(prop.getProperty("threshold"));
				base = Double.parseDouble(prop.getProperty("base"));
				step = Double.parseDouble(prop.getProperty("step"));
				difference = Double.parseDouble(prop.getProperty("difference"));
				bonus = Double.parseDouble(prop.getProperty("bonus"));
									
				//Check values
				
				String errMessage = "";
				boolean error = false;
				
				
				try{
				
					//CHECK DOT
					
					if (!goDot.equalsIgnoreCase(DEFAULT)) {

						double dot = Double.parseDouble(goDot);
						
						errMessage += checkInterval("dot", dot, MIN_DOT, MAX_DOT);

						if (!errMessage.equalsIgnoreCase(""))
							
							error = true;
						
					} else {

						goDot = DEFAULT_DOT;
					}
				
					//CHECK RESAMPLINGSTEPS
					
					if (!goResamplingSteps.equalsIgnoreCase(DEFAULT)) {
						
						int rsteps = Integer.parseInt(goResamplingSteps);
						
						errMessage += checkInterval("resamplingsteps",rsteps,MIN_STEPS,MAX_STEPS);
						
						if (!errMessage.equalsIgnoreCase("")) 
							
							error = true;
					}
					else {

							goResamplingSteps = DEFAULT_STEPS;
					}
					
					//CHECK OUTPUT FILES
					
					//outFile.createNewFile();
					
					//outAnalysisFile.createNewFile();
								
				}
				catch(NumberFormatException e){
					errMessage += "Wrong input format number for dot and-or resamplingsteps paths\n";
				}
//				catch (IOException e) {
//					errMessage += "Wrong output paths\n";
//				}
				finally{
						
						//CHECK MTC
				
						errMessage += checkOptions ("mtc",goMtc,DEFAULT,BONFERRONI,NONE,WESTFALL);
				
						if (!errMessage.equalsIgnoreCase("")) error = true;
						
						if (goMtc.equalsIgnoreCase(DEFAULT)) goMtc = DEFAULT_MTC; 
						
						//CHECK CALCULATION
								
						errMessage += checkOptions ("calculation",goCalculation,DEFAULT,TFT,PCU,PCI);
				
						if (!errMessage.equalsIgnoreCase("")) error = true;
						
						if (goCalculation.equalsIgnoreCase(DEFAULT)) goCalculation = DEFAULT_CALCULATION; 
				
						//CHECK IGNORE
						
						errMessage += checkOptions ("ignore",auxGoIgnore,DEFAULT,IGNORE_YES,IGNORE_NO);
				
						if (!errMessage.equalsIgnoreCase("")) error = true;
						
						if (auxGoIgnore.equalsIgnoreCase(DEFAULT)) 
							
							goIgnore = DEFAULT_IGNORE;
						
						else if (auxGoIgnore.equalsIgnoreCase(IGNORE_NO)) 
							
							goIgnore = false;
						
						else 
							
							goIgnore = true;
										
						//CHECK FILTER
						
						if (goFilter.equalsIgnoreCase(DEFAULT)) goFilter = DEFAULT_FILTER;
						
						//CHECK ANOTATION
						
						errMessage += checkOptions ("annotation",auxGoAnnotation,DEFAULT,ANNOTATION_YES,ANNOTATION_NO);
						
						if (!errMessage.equalsIgnoreCase("")) error = true;
						
						if (auxGoAnnotation.equalsIgnoreCase(DEFAULT)) 
							
							goAnnotation = DEFAULT_ANNOTATION;
						
						else if (auxGoAnnotation.equalsIgnoreCase(IGNORE_NO)) 
							
							goAnnotation = false;
						
						else 
							
							goAnnotation = true;
						
						//CHECK GO RESOURCES
						
						errMessage += checkOptions ("go_resources",auxGoResources,DEFAULT,GORESOURCES_YES,GORESOURCES_NO);
						
						if (!errMessage.equalsIgnoreCase("")) error = true;
						
						if (auxGoResources.equalsIgnoreCase(DEFAULT)) 
							
							goResources = DEFAULT_GORESOURCES;
						
						else if (auxGoResources.equalsIgnoreCase(GORESOURCES_NO)) 
							
							goResources = false;
						
						else 
							
							goResources = true;
						
						//CHECK EVALUATION
						
						errMessage += checkInterval("CGRQ",cgraphical,MIN_EVAL,MAX_EVAL);
						if (!errMessage.equalsIgnoreCase("")) error = true;
						
						errMessage += checkInterval("CPEQ",cpearson,MIN_EVAL,MAX_EVAL);
						if (!errMessage.equalsIgnoreCase("")) error = true;
						
						errMessage += checkInterval("CSPQ",cspearman,MIN_EVAL,MAX_EVAL);
						if (!errMessage.equalsIgnoreCase("")) error = true;
						
						errMessage += checkInterval("GRQ",graphical,MIN_EVAL,MAX_EVAL);
						if (!errMessage.equalsIgnoreCase("")) error = true;
						
						errMessage += checkInterval("PEQ",pearson,MIN_EVAL,MAX_EVAL);
						if (!errMessage.equalsIgnoreCase("")) error = true;
						
						errMessage += checkInterval("SPQ",spearman,MIN_EVAL,MAX_EVAL);
						if (!errMessage.equalsIgnoreCase("")) error = true;
						
						errMessage += checkInterval("BIOQ",biological,MIN_EVAL,MAX_EVAL);
						if (!errMessage.equalsIgnoreCase("")) error = true;
						
						//THROWS ERRORS
						
						if (error) throw new WrongOptionsException(errMessage);
					}
		
	}



	public String toString (){
		
		String r = super.toString();
		
		r +=	"\nMtc = "+goMtc+
				"\ncalculation = "+goCalculation+
				"\ndot = "+goDot+
				"\nfilter = "+goFilter+
				"\nignore = "+goIgnore+
				"\nannotation = "+goAnnotation+
				"\nresamplingsteps = "+goResamplingSteps+
				"\ngo resources = "+goResources+
				"\nCGRQ = "+cgraphical+
				"\nCPEQ = "+cpearson+
				"\nCSPQ = "+cspearman+
				"\nGRQ = "+graphical+
				"\nPEQ = "+pearson+
				"\nSPQ = "+spearman+
				"\nBIOQ = "+biological+
				"\nthreshold = "+threshold+
				"\nbase = "+base+
				"\nstep = "+step+
				"\ndifference = "+difference+
				"\nbonus = "+bonus;
	
		return r;
	
	}
		

	
	/**
	 * It returns the current GO Multiple Correction Testing (MTC) method.
	 * @return Current GO Multiple Correction Testing (MTC) method.
	 */
	public String getGoMtc() {
		return goMtc;
	}
	
	/**
	 * It returns the current GO calculation method.
	 * @return Current GO calculation method.
	 */
	public String getGoCalculation() {
		return goCalculation;
	}
	
	/**
	 * It returns the current GO pi-value bias.
	 * @return Current GO pi-value bias.
	 */
	public String getGoDot() {
		return goDot;
	}

	public String getGoFilter() {
		return goFilter;
	}
	
	/**
	 * It returns the current GO ignore option.
	 * @return Current GO ignore option.
	 */
	public boolean isGoIgnore() {
		return goIgnore;
	}

	public boolean isGoAnnotation() {
		return goAnnotation;
	}

	/**
	 * It returns the current GO resampling steps.
	 * @return Current GO resampling steps.
	 */
	public String getGOrsteps() {
		return goResamplingSteps;
	}

	/**
	 * @return the graphical
	 */
	public double getGraphical() {
		return graphical;
	}

	/**
	 * @return the pearson
	 */
	public double getPearson() {
		return pearson;
	}

	/**
	 * @return the spearman
	 */
	public double getSpearman() {
		return spearman;
	}

	/**
	 * @return the biological
	 */
	public double getBiological() {
		return biological;
	}
		

	public double getThreshold() {
		return threshold;
	}

	public double getBase() {
		return base;
	}
	
	public double getStep() {
		return step;
	}

	
	
	
	
	public double getDifference() {
		return difference;
	}


	public double getBonus() {
		return bonus;
	}

	/**
	 * @return the goResources
	 */
	public boolean isGoResources() {
		return goResources;
	}

	public double getCgrq() {
		return cgraphical;
	}

	public double getCpeq() {
		return cpearson;
	}

	public double getCspq() {
		return cspearman;
	}


	/**
	 * It checks a parameter with a current value through a set of valid options. 
	 * 
	 * @param parameter Current parameter.
	 * @param value Current value.
	 * @param options Valid options.
	 * @return An error message if checking is not correct otherwise empty {@link String}.
	 */
	private String checkOptions (String parameter, String value, String ... options){
		
		String r = "";
		
		boolean found = false;
		
		for (int i = 0;i<options.length;i++){
			
			if (options[i].equals(value)){
				found = true;
			}	
			
		}
		
		if (!found){
			r = parameter+" must be set to "+Arrays.toString(options) +"\n";
		}
								
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
