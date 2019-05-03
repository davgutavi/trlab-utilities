package input.datasets;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.InTextFile;

/**
 * This class represents a group of real resources; it extends {@link Common} with gene names, condition or sample names and time names 
 * information.
 * 
 * @author David Gutiérrez Avilés
 * @see Common
 */
public class Real extends Common {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5056243918948791779L;

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(Real.class);
	
	/**
	 * Array of gene names.
	 */
	private String [] geneNames;
	
	/**
	 * Array of condition or sample names.
	 */
	private String [] sampleNames;
	
	/**
	 * Array of time names.
	 */
	private String [] timeNames;
	
	/**
	 * It builds the real resources from all required information.
	 * 
	 * @param datasetID Dataset id from xml resources descriptor.
	 * @param datasetName Dataset name from xml resources descriptor.
	 * @param datasetType Dataset type from xml resources descriptor.
	 * @param geneSize Dataset gene size from xml resources descriptor.
	 * @param sampleSize Dataset sample or condition size from xml resources descriptor.
	 * @param timeSize Dataset time size from xml resources descriptor.
	 * @param paths Dataset paths from xml resources descriptor.
	 * @param sep Dataset separator from xml resources descriptor.
	 * @param gNamesPath Dataset gene names path from xml resources descriptor.
	 * @param sNamesPath Dataset sample or condition names path from xml resources descriptor.
	 * @param tNamesPath Dataset time names path from xml resources descriptor.
	 */
	public Real(String datasetID, String datasetName, String datasetType, String geneSize, String sampleSize,
			String timeSize, String[] paths, String sep, 
			String defMinG, String defMaxG, String defMinC, String defMaxC, String defMinT, String defMaxT,
			String gNamesPath, String sNamesPath, String tNamesPath) {
		
		super(datasetID, datasetName, datasetType, geneSize, sampleSize, timeSize,paths,sep,
				defMinG,  defMaxG,  defMinC,  defMaxC,  defMinT,  defMaxT);
		
		this.geneNames = buindNamesFromFile(gNamesPath,'g');
		this.sampleNames = buindNamesFromFile(sNamesPath,'s');
		this.timeNames = buindNamesFromFile(tNamesPath,'t');
		
	}
	
	/**
	 * It returns the array of gene names.
	 * @return Array of gene names.
	 */
	public String[] getGeneNames() {
		return geneNames;
	}
	
	/**
	 * It returns the array of sample or condition names.
	 * @return Array of sample or condition names.
	 */
	public String[] getSampleNames() {
		return sampleNames;
	}
	
	/**
	 * It returns the array of time names.
	 * @return Array of time names.
	 */
	public String[] getTimeNames() {
		return timeNames;
	}
	
	public String toString (){
		
		String r = super.toString();
		
		r+= "\nFirst Gene Name = "+geneNames[0]+
			 "\nLast Gene Name = "+geneNames[getGeneSize()-1]+
			 "\nFirst Sample Name = "+sampleNames[0]+
			 "\nLast Sample Name = "+sampleNames[getSampleSize()-1]+
			 "\nFirst Time Name = "+timeNames[0]+
			 "\nLast Time Name = "+timeNames[getTimeSize()-1];
		
		return r;
		
	}

	//Private methods

	/**
	 * This method builds an array of {@link String} with the corresponding feature's names according to input.
	 * 
	 * @param path Dataset names path from xml resources descriptor.
	 * @param ty g = genes, s = samples or conditions, t = times
	 * @return Array of {@link String} with the corresponding feature's names.
	 */
	private String [] buindNamesFromFile (String path, char ty){
		
		int size = 0;
		
		if      (ty=='g') size = getGeneSize();
		else if (ty=='s') size = getSampleSize();
		else if (ty=='t') size = getTimeSize();
		
		String [] r  = new String[size];
		
		InTextFile f;
		
		try {
			
			f = new InTextFile(path);
			
			int i = 0;
			
			for (String it:f){
				
				r[i] = it.trim();
				i++;
								
			}
			
			f.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return r;
	
	}
	
}