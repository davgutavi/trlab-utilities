package input.datasets;

/**
 * This class represents a group of earthquake resources; it extends {@link Common} with Earthquake information.
 * 
 * @author David Gutiérrez Avilés
 *
 */



public class Assorted extends Real {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2136629192023387798L;

	/**
	 * It builds the earthquake resources from all required information.
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
	public Assorted(String datasetID, String datasetName, String datasetType, String geneSize, String sampleSize,
			String timeSize, String[] paths, String sep, 
			String defMinG, String defMaxG, String defMinC, String defMaxC, String defMinT, String defMaxT,
			String gNamesPath, String sNamesPath, String tNamesPath) {
		
		
		
		super(datasetID, datasetName, datasetType, geneSize, sampleSize, timeSize,paths,sep, 
				defMinG,  defMaxG,  defMinC,  defMaxC,  defMinT,  defMaxT,
				gNamesPath, sNamesPath, tNamesPath);
	
		
	}

}
