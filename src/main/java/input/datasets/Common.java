package input.datasets;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.InTextFile;
import utils.TextUtilities;

/**
 * This class represents a group of common resources; it is the basic concept of resources that include all common
 * resurce's information.
 * 
 * @author David Gutiérrez Avilés
 *
 */
public class Common implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6939460461628804453L;

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(Common.class);
	
	/**
	 * Dataset id from xml resources descriptor.
	 */
	private String datasetID;
	
	/**
	 * Dataset name from xml resources descriptor.
	 */
	private String datasetName;
	
	/**
	 * Dataset type from xml resources descriptor: b = biological, e = earthquake, s = synthetic
	 */
	private char datasetType;
	
	/**
	 * Dataset gene size from xml resources descriptor.
	 */
	private int geneSize;
	
	/**
	 * Dataset condition or sample size from xml resources descriptor.
	 */
	private int sampleSize;
	
	/**
	 * Dataset time size from xml resources descriptor.
	 */
	private int timeSize;
	
	private int defMinG;
	private int defMaxG;
	private int defMinC;
	private int defMaxC;
	private int defMinT;
	private int defMaxT;
	
	
	/**
	 * Dataset values loaded from xml resouces descriptor.
	 */
	private double [][][] dataset;
	
	/**
	 * It builds the common resources from all required information.
	 * 
	 * @param datasetID Dataset id from xml resources descriptor.
	 * @param datasetName Dataset name from xml resources descriptor.
	 * @param datasetType Dataset type from xml resources descriptor.
	 * @param geneSize Dataset gene size from xml resources descriptor.
	 * @param sampleSize Dataset sample or condition size from xml resources descriptor.
	 * @param timeSize Dataset time size from xml resources descriptor.
	 * @param paths Dataset paths from xml resources descriptor.
	 * @param sep Dataset separator from xml resources descriptor.
	 */
	public Common (String datasetID, String datasetName, String datasetType, String geneSize, String sampleSize, String timeSize, String[] paths, String sep,
			String defMinG, String defMaxG, String defMinC, String defMaxC, String defMinT, String defMaxT){
		
		this.datasetID = datasetID;
		this.datasetName = datasetName;
		this.datasetType = datasetType.charAt(0);
		this.geneSize = Integer.parseInt(geneSize);
		this.sampleSize = Integer.parseInt(sampleSize);
		this.timeSize = Integer.parseInt(timeSize);
		this.dataset = buindDatasetFromFile (paths,sep);
		
		this.defMinG = Integer.parseInt(defMinG);
		this.defMaxG = Integer.parseInt(defMaxG);
		this.defMinC = Integer.parseInt(defMinC);
		this.defMaxC = Integer.parseInt(defMaxC);
		this.defMinT = Integer.parseInt(defMinT);
		this.defMaxT = Integer.parseInt(defMaxT);
				
	}
	
	/**
	 * It returns the dataset id. 
	 * @return Current dataset id.
	 */
	public String getDatasetID() {
		return datasetID;
	}

	/**
	 * It returns the dataset name. 
	 * @return Current dataset name.
	 */
	public String getDatasetName() {
		return datasetName;
	}

	/**
	 * It returns the dataset type. 
	 * @return Current dataset type: b = biological, e = earthquake, s = synthetic
	 */
	public char getDatasetType() {
		return datasetType;
	}

	/**
	 * It returns the dataset gene size. 
	 * @return Current dataset gene size. 
	 */
	public int getGeneSize() {
		return geneSize;
	}

	/**
	 * It returns the dataset condition or sample size. 
	 * @return Current dataset condition or sample size.
	 */
	public int getSampleSize() {
		return sampleSize;
	}
	
	/**
	 * It returns the dataset time size. 
	 * @return Current dataset time size. 
	 */
	public int getTimeSize() {
		return timeSize;
	}
	
	/**
	 * It returns the dataset values as a gene-sample-time array. 
	 * @return Current dataset values as a gene-sample-time array. 
	 */
	public double[][][] getDataset() {
		return dataset;
	}
	
	
	
	public int getDefMinG() {
		return defMinG;
	}

	public int getDefMaxG() {
		return defMaxG;
	}

	public int getDefMinC() {
		return defMinC;
	}

	public int getDefMaxC() {
		return defMaxC;
	}

	public int getDefMinT() {
		return defMinT;
	}

	public int getDefMaxT() {
		return defMaxT;
	}

	public String toString (){
		
		String r = "";
		
		r = "\nID = "+datasetID+
				"\nName = "+datasetName+
				"\nType = "+datasetType+
				"\nGenes = "+geneSize+
				"\nSamples = "+sampleSize+
				"\nTimes = "+timeSize+				
				"\ndefMinG ="+defMinG+
				"\ndefMaxG ="+defMaxG+
				"\ndefMinC ="+defMinC+
				"\ndefMaxC ="+defMaxC+
				"\ndefMinT ="+defMinT+
				"\ndefMaxT ="+defMaxT+			
				"\nel(0,0,0) ="+dataset[0][0][0]+
				"\nel(0,S,0) ="+dataset[0][sampleSize-1][0]+
				"\nel(G,0,0) ="+dataset[geneSize-1][0][0]+
				"\nel(G,S,0) ="+dataset[geneSize-1][sampleSize-1][0]+
				"\nel(0,0,T) ="+dataset[0][0][timeSize-1]+
				"\nel(0,S,T) ="+dataset[0][sampleSize-1][timeSize-1]+
				"\nel(G,0,T) ="+dataset[geneSize-1][0][timeSize-1]+
				"\nel(G,S,T) ="+dataset[geneSize-1][sampleSize-1][timeSize-1]
				;
			
		return r;
		
	}

	//Private methods
	
	

	/**
	 * This method builds the dataset values as a gene-sample-time array.
	 * 
	 * @param paths Dataset paths from xml resources descriptor.
	 * @param sep Dataset separator from xml resources descriptor.
	 * @return A gene-sample-time array containing the dataset values. 
	 */
	private double [][][] buindDatasetFromFile (String[] paths, String sep){
		
		double [][][] r  = new double[this.geneSize][this.sampleSize][this.timeSize];
		
		System.out.print("PATH LIST:");
		for (String e:paths)
			System.out.println(e);
		
		for (int t = 0; t < this.timeSize; t++) {
			
			String path = paths[t];
			
			InTextFile f;
			
			try {
				System.out.println("PATH = "+path);
				f = new InTextFile(path);
				
				int cont = 1;
				
				int g = 0;
				
				for(String it:f){
										
					List<Double> v = TextUtilities.stringToDouble(it,sep,cont);
					insertValues(v,g,t,r);
					g++;
					cont++;
									
				}				
				
				f.close();
				
			} 
			
			catch (IOException e) {
				e.printStackTrace();
			}
			
		}// for
		
		return r;
	
	}


	/**
	 * This method inserts a {@link List} of {@link Double} into a gene-sample-time array.
	 * 
	 * @param v {@link List} of {@link Double} with values for all conditions or samples.
	 * @param g Gene index.
	 * @param t Time index.
	 * @param r Gene-sample-time array.
	 */
	private void insertValues(List<Double> v, int g, int t, double[][][] r) {
		
		int s = 0;
		
		for(Double it:v){
	
			r[g][s][t] = it.doubleValue();
			
			s++;
	
		}
			
	}
		
	
}
