package input.workflows;

import general.ListLoadWorkFlow;
import general.Parser;
import input.InputFacade;
import input.algorithm.InvalidImplementationException;
import input.algorithm.WrongContolException;
import input.datasets.Common;
import input.laboratory.CommonAnalysisResources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.ReadTable;
import utils.WorkFlowUtilities;

public class LabListRun extends ListLoadWorkFlow{

	private static final Logger LOG = LoggerFactory.getLogger(LabListRun.class);
	
	private Map<String,Common> datasets;
	
	
	
	
	public LabListRun() {
		
		this.datasets = new HashMap<String,Common>();
	}




	@Override
	public List<CommonAnalysisResources> listLoad(String pathToList,char analysisType) 
			throws IOException, WrongContolException, InvalidImplementationException {
		
		List<CommonAnalysisResources> r = new ArrayList<CommonAnalysisResources>();
		
		ReadTable input = new ReadTable(pathToList, ";");
		
		List<List<String>> table = input.getTable();
		
		for (List<String> row:table){
			
			
			String expAlias = "";
			String solPath= "";
			String datasetName= "";
									
			if (row.size() == 2){//alias;sol
				
				expAlias = row.get(0);
				
				solPath = row.get(1);
				
				Properties solProp = new Properties();
				
				solProp.load(new FileInputStream(solPath));
				
				datasetName = solProp.getProperty("dataset");
				
				Common dataset = getDataset (datasetName);
				
				char anType = WorkFlowUtilities.getExperimentTypeFromDataset(dataset);
				
				if (anType!=analysisType){
					
					LOG.info(expAlias+" ignored -> his analysis type ("+anType+") does not coincide with input ("+analysisType+")");
				}
				else{
					
					CommonAnalysisResources ar = Parser.buildAnalysysResourcesFromList(analysisType, expAlias, solPath, dataset);
					
					r.add(ar);
					
				}
							
			}
			else if (row.size() == 3){//dataset;alias;norm
				
				datasetName = row.get(0);
				
				expAlias = row.get(1);
							
				solPath = row.get(2);
			
				Common dataset = getDataset (datasetName);
				
				char anType = WorkFlowUtilities.getExperimentTypeFromDataset(dataset);
				
				if (anType!=analysisType){
					
					LOG.info(expAlias+" ignored -> his analysis type ("+anType+") does not coincide with input ("+analysisType+")");
				}
				else{
					
					CommonAnalysisResources ar = Parser.buildReducedAnalysysResourcesFromList(analysisType,expAlias, solPath, dataset);
					
					r.add(ar);
					
				}
								
			}
			
		
		}
		
		return r;
	}




	private Common getDataset(String datasetName) throws FileNotFoundException, 
			IOException, WrongContolException, InvalidImplementationException {
		
		Common com = null;
		
		if(datasets.containsKey(datasetName)){
		
			com = datasets.get(datasetName);
		}
		else{
			
			com = InputFacade.buildDataset(datasetName);
			
			datasets.put(datasetName, com);
		}
		
		return com;
	}
	
	
	
	
	

}
