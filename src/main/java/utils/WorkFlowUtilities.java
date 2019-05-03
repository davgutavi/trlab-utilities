package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import general.LegacyParser;
import general.Tricluster;
import input.datasets.Biological;
import input.datasets.Common;
import input.datasets.Real;

public class WorkFlowUtilities {
	
	private static final Logger LOG = LoggerFactory.getLogger(WorkFlowUtilities.class);
	
	public static List<String> getTriclusters(String listOfExperiments, List<List<Tricluster>> experiments) throws IOException {
		
		InTextFile loe = new InTextFile(listOfExperiments);
		
		List<String> expNames = new ArrayList<String>();
		
		for (String l:loe){
			
			List<String> aux = TextUtilities.splitElements(l, ":");
			
			String name = aux.get(0);
		
			String path = aux.get(1);
			
			expNames.add(name);
			
			experiments.add(LegacyParser.parse(path));			
		}
		
		loe.close();
		
		return expNames;
	}
	
	public static void checkResources (Common res){
		
		char type = res.getDatasetType();
    	
    	if (type=='b'){
       		Biological r = (Biological) res;
    		LOG.debug("\nRESOURCES: \n"+r.toString()+"\n");
       	}
    	else if (type =='e'){
    		Real r = (Real) res;
    		LOG.debug("\nRESOURCES: \n"+r.toString()+"\n");
    	}
    	
	}
	
	public static char getExperimentType (String analysisType, Common res){
		
		char r = ' ';
		
		switch(analysisType){
			
		case (""):
			r = getExperimentTypeFromDataset(res);			
			break;
		
		case ("bio"):
			r = 'b';
			break;
		case ("com"):
			r = 'c';
			break;
		case ("syn"):
			r = 's';
			break;
		}
				
		return r;
	
	}


	public static char getExperimentTypeFromDataset(Common res) {
		
		char r = ' ';
		
		switch(res.getDatasetType()){
		
		case ('b'):
			r = 'b';
			break;
	
		case ('s'):
			r = 's';
			break;
			
		case ('e'):
			r = 'c';
			break;
		}
				
		return r;
	}

}
