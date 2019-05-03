package general;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.transform.TransformerException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import input.algorithm.Model;
import input.algorithm.Model.FitnessClass;
import input.algorithm.ModelsLoader;
import input.datasets.Common;
import input.datasets.DatasetsLoader;
import utils.SystemUtilities;

public class DatasetModelServer {
	
	private static final Logger LOG = LoggerFactory.getLogger(DatasetModelServer.class);
	
	private static DatasetsLoader datasetServer;
	
	private static ModelsLoader modelServer;
	
    private static final DatasetModelServer singleton = new DatasetModelServer();
    
    
    static {
    	
    	datasetServer = new DatasetsLoader(SystemUtilities.getResourcesXmlPath());
    	
    	modelServer = new ModelsLoader();
    	
    }
	
	private DatasetModelServer (){}
		
	
	public static DatasetModelServer getInstance() {
			
		return singleton;
	}

	
	public void updateServers(){
		
		datasetServer = new DatasetsLoader(SystemUtilities.getResourcesXmlPath());
    	
    	modelServer = new ModelsLoader();
		
	}
	
	public void updateDatasetServer (){
		datasetServer = new DatasetsLoader(SystemUtilities.getResourcesXmlPath());
	}
	
	public void updateModelServer (){
		modelServer = new ModelsLoader();
	}
		
	public DatasetsLoader getDatasetServer (){
		return datasetServer;
	}
	
	public ModelsLoader getModelServer (){
		return modelServer;
	}
	
	
	public Common getDatasetByName (String datasetName){
		
		return datasetServer.getResourcesByName(datasetName);
	
	}
	

	
	public boolean isRepeated (String datasetName){
		
		boolean enc = false;
		
		String [] items = getDatasetItems ();
		
		for (int i=0;i<items.length&&!enc;i++){
			
			String aux = items[i];
			
			if (aux.equalsIgnoreCase(datasetName))
				enc = true;
			
		}
		
		return enc;
	}
	
	
	public String getAnewID (){
		
		String r="";
	
		String [] ids =  getDatasetIDs ();
		
		int max = 0;
		
		for (int i = 0; i<ids.length;i++){
			
			int aux = Integer.parseInt(ids[i]);
			
			if (aux>max)
				max = aux;
			
		}
		
		max++;
		
		Integer aux1 = new Integer(max);
		
		int digits = (aux1.toString()).length();
		
		int zeros = 4-digits;
		
		for (int i = 0; i<zeros;i++){
			r+="0";
		}
		
		r+=max;	
		
		LOG.debug("Max = "+max);
		LOG.debug("Digits = "+digits);
		LOG.debug("Zeros = "+zeros);
		LOG.debug("ID = "+r);
		
		
		return r;
	
	}
	
	
	
	public String [] getDatasetIDs (){
		
		Map<String, String> datasets = datasetServer.getAllDatasetsNames();
		
		Set<String> keys1 = datasets.keySet();
		
		String [] dataset_ids = new String [keys1.size()];
		
		int i = 0;
		
		for (String k:keys1){
			
			dataset_ids[i] = datasets.get(k);
			
			i++;
		}
				
		return dataset_ids;		
		
	}
	
	
	
	public String [] getDatasetItems (){
						
		Map<String, String> datasets = datasetServer.getAllDatasetsNames();
		
		Set<String> keys1 = datasets.keySet();
		
		String [] dataset_items = new String [keys1.size()];
		
		int i = 0;
		
		for (String k:keys1){
			
			dataset_items[i] = k;
			
			i++;
		}
				
		return dataset_items;		
		
	}
	
	
	public String[]  getFitnessItems (){
					
		Model[] models = modelServer.getAvailableModels();
		
		Map<String, FitnessClass> fit = (models[0]).getFitness();
		
		Set<String> keys = fit.keySet();
		
		String[] fit_items = new String [keys.size()];
		
		int i = 0;
		
		for (String k:keys){
			
			fit_items[i] = k;
			
			i++;
			
		}
		
		return fit_items;
		
	}
	
	public List<String[]>  getImplementationItems (){
		
		List<String[]> r = new ArrayList<String[]>(9);
		
		Model[] models = modelServer.getAvailableModels();
		
		Map<String, FitnessClass> fit = (models[0]).getFitness();
		
		Set<String> keys = fit.keySet();
		
		String[] fit_items = new String [keys.size()];
		
		int i = 0;
		
		for (String k:keys){
			
			fit_items[i] = k;
			
			i++;
			
		}
		
		r.add(fit_items);
		
		Map<String, String> aux = (models[0]).getIndividuals();
		
		keys = aux.keySet();
		
		String[] ind_items = new String [keys.size()];
		
		i = 0;
		
		for (String k:keys){
			
			ind_items[i] = k;
			
			i++;
			
		}
		
		r.add(ind_items);
		
		aux = (models[0]).getDataHierarchies();
		
		keys = aux.keySet();
		
		String[] hie_items = new String [keys.size()];
		
		i = 0;
		
		for (String k:keys){
			
			hie_items[i] = k;
			
			i++;
			
		}
		
		r.add(hie_items);
		
		aux = (models[0]).getStoppingCriteria();
		
		keys = aux.keySet();
		
		String[] stp_items = new String [keys.size()];
		
		i = 0;
		
		for (String k:keys){
			
			stp_items[i] = k;
			
			i++;
			
		}
		
		r.add(stp_items);

		aux = (models[0]).getSolutionCriteria();
		
		keys = aux.keySet();
		
		String[] sol_items = new String [keys.size()];
		
		i = 0;
		
		for (String k:keys){
			
			sol_items[i] = k;
			
			i++;
			
		}
		
		r.add(sol_items);
		
		aux = (models[0]).getInitialPops();
		
		keys = aux.keySet();
		
		String[] ini_items = new String [keys.size()];
		
		i = 0;
		
		for (String k:keys){
			
			ini_items[i] = k;
			
			i++;
			
		}
		
		r.add(ini_items);
		
		aux = (models[0]).getSelections();
		
		keys = aux.keySet();
		
		String[] sel_items = new String [keys.size()];
		
		i = 0;
		
		for (String k:keys){
			
			sel_items[i] = k;
			
			i++;
			
		}
		
		r.add(sel_items);
		
		aux = (models[0]).getCrossovers();
		
		keys = aux.keySet();
		
		String[] cro_items = new String [keys.size()];
		
		i = 0;
		
		for (String k:keys){
			
			cro_items[i] = k;
			
			i++;
			
		}	
		
		r.add(cro_items);
		
		aux = (models[0]).getMutations();
		
		keys = aux.keySet();
		
		String[] mut_items = new String [keys.size()];
		
		i = 0;
		
		for (String k:keys){
			
			mut_items[i] = k;
			
			i++;
			
		}		
		
		r.add(mut_items);
		
		
		return r;
		
	}
	
	
	
	public void writeNewDataset (String id, String name,char type, int geneSize, int sampleSize, int timeSize,
			int minG, int maxG, int minC, int maxC, int minT, int maxT, String organism, String description, 
			String sep, String [] dataFileNames, String geneFileName, String sampleFileName, String timeFileName ) throws TransformerException {
		
		datasetServer.writeRecord(id, name, type, geneSize, sampleSize, timeSize, minG, maxG, minC, maxC, minT, maxT, 
				organism, description, sep, dataFileNames, geneFileName, sampleFileName, timeFileName);
					
		
	}
	
}
