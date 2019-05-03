package input.datasets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents a group of biological resources; it extends {@link Real} with GO analysis information.
 * 
 * @author David Gutiérrez Avilés
 * @see Real
 */
public class Biological extends Real {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8700182723979926824L;

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(Biological.class);

	private String organism;
	
	private String genesPath;
	
	/**
	 * It builds the biological resources from all required information.
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
	public Biological(String datasetID, String datasetName, String datasetType, String geneSize, String sampleSize,String timeSize, String[] paths, String sep,
			String defMinG, String defMaxG, String defMinC, String defMaxC, String defMinT, String defMaxT,
			String gNamesPath, String sNamesPath, String tNamesPath, 
			String organism){
		
		super(datasetID, datasetName, datasetType, geneSize, sampleSize, timeSize,paths,sep, 
				defMinG,  defMaxG,  defMinC,  defMaxC,  defMinT,  defMaxT,
				gNamesPath, sNamesPath, tNamesPath);

		
		
		this.organism = organism;
		this.genesPath = gNamesPath;
		
	}

	
	public String getOrganism() {
		return organism;
	}
	
	
	
	
	public String getGenesPath() {
		return genesPath;
	}


	public String toString (){
		
		String r = super.toString();
			
		r+=  "\nOrganism = "+organism+
				"\nGenes path = "+genesPath;
		
		return r;
		
	}

	
	
}