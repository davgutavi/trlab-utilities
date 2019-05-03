package general;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents a Tricluster as a group of gene coordinates, condition or sample coordinates, and time coordinates. Each coordinates corresponds
 * to a gene, condition or time in the natural order of input dataset.
 * 
 * @author David Gutiérrez Avilés
 *
 */
public class Tricluster {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(Tricluster.class);
	
	/**
	 * {@link List} of {@link Integer} that represents the gene coordinates of the Tricluster.
	 */
	private List<Integer> genes;
	
	/**
	 * {@link List} of {@link Integer} that represents the condition or sample coordinates of the Tricluster.
	 */
	private List<Integer> samples;
	
	/**
	 * {@link List} of {@link Integer} that represents the time coordinates of the Tricluster.
	 */
	private List<Integer> times;
	
	/**
	 * An empty constructor in order to load the fields afterwards.
	 */
	public Tricluster (){
				
	}

		
	/**
	 * It builds the Tricluster from input {@link List} of {@link Integer}.
	 * 
	 * @param genes {@link List} of {@link Integer} that represents the gene coordinates.
	 * @param samples {@link List} of {@link Integer} that represents the condition or sample coordinates.
	 * @param times {@link List} of {@link Integer} that represents the time coordinates.
	 */
	public Tricluster (List<String> genes, List<String> samples, List<String> times){
		
		this.genes = buildCoordinates(genes);
		this.samples = buildCoordinates(samples);
		this.times = buildCoordinates(times);
			
	}
	
	
	
	public void loadCoordinates (List<Integer> genes, List<Integer> samples, List<Integer> times){
		
		this.genes = genes;
		this.samples = samples;
		this.times = times;
		
	}
	
	
	
	/**
	 * It returns the gene coordinates.
	 * @return Gene coordinates.
	 */
	public List<Integer> getGenes() {
		return genes;
	}

	/**
	 * It returns the condition or sample coordinates.
	 * @return Condition or sample coordinates.
	 */
	public List<Integer> getSamples() {
		return samples;
	}

	/**
	 * It returns the time coordinates.
	 * @return Time coordinates.
	 */
	public List<Integer> getTimes() {
		return times;
	}

	public String toString () {
		
		String r = "";
		
		r = "["+genes.size()+","+samples.size()+","+times.size()+"] "
				+ "{"+genes.get(0)+"--"+genes.get(genes.size()-1)+"} "
				+ "{"+samples.get(0)+"--"+samples.get(samples.size()-1)+"} "
				+ "{"+times.get(0)+"--"+times.get(times.size()-1)+"}";
			
		return r;
		
	}
	
	public String completeToString () {
		
		String r = "";
		
		r = "["+genes.size()+","+samples.size()+","+times.size()+"]\n"
				+ "Genes: "+genes+"\n"+
				 "Samples: "+samples+"\n"+
				 "Times: "+times;
			
		return r;
		
	}

	//Private methods

	/**
	 * It builds a {@link List} of {@link Integer} from a {@link List} of {@link String} as input.
	 * 
	 * @param stringCoordinates {@link List} of {@link String}.
	 * @return {@link List} of {@link Integer}.
	 */
	private List<Integer> buildCoordinates (List<String> stringCoordinates){
		
		List<Integer> r = new ArrayList<Integer>(stringCoordinates.size());
		
		//LOG.debug(stringCoordinates.toString());
				
		for(String it:stringCoordinates)
			r.add(new Integer(it));			
		
		return r;
		
	}
	
	
	
}
