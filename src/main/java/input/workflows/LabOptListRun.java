package input.workflows;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import general.OptListLoadWorkflow;
import general.Tricluster;
import input.InputFacade;
import input.algorithm.InvalidImplementationException;
import input.algorithm.WrongContolException;
import input.datasets.Biological;
import input.laboratory.OPTsolBatch;
import utils.ReadTable;
import utils.TextUtilities;

public class LabOptListRun extends OptListLoadWorkflow {
	
	private static final Logger LOG = LoggerFactory.getLogger(LabOptListRun.class);

	public LabOptListRun () {}
	
	
	@Override
	public OPTsolBatch listLoad(String pathToTriclustersFolder, String datasetName) throws IOException, WrongContolException, InvalidImplementationException {
		
		
		Biological dataset = (Biological)InputFacade.buildDataset(datasetName);
				
		File optFolder = new File(pathToTriclustersFolder);
		
		File[] optFileList = optFolder.listFiles();
		
		List<String> optPaths = new ArrayList<String>(optFileList.length);
		
		for (File path:optFileList) {
			
			LOG.debug(path.getAbsolutePath());
			
			if(!path.isHidden()) {
				optPaths.add(path.getAbsolutePath());
			}			
		}
				
		Collections.sort(optPaths);
		
		List<Tricluster> tl = new ArrayList<Tricluster>(optPaths.size());
		
		for(String path:optPaths) {
			
			ReadTable rt = new ReadTable(path, ";");
			
			List<List<String>> data = rt.getTable();
			
			Tricluster tr = processOneTricluster(data, dataset);	
			
			tl.add(tr);
		}
		
		
		OPTsolBatch r = new OPTsolBatch(tl, dataset);			
		
		return r;
		
	}
	
	
	private Tricluster processOneTricluster(List<List<String>> inputData, Biological dataset) {
		
		Tricluster r = new Tricluster();
		
		List<String> firstLine = inputData.get(0);
		
		List<List<String>> rest = inputData.subList(1, inputData.size());
		
		List<Integer> g = getTrGenes (rest);
		
		List<Integer>[] st = getSamplesTimesLists(firstLine, dataset);
		
//		checkLists(g,st[0],st[1],dataset);
		
		r.loadCoordinates(g, st[0], st[1]);
		
//		LOG.debug(r.completeToString());
				
		return r;
	
	}
	
	
	private List<Integer> getTrGenes(List<List<String>> rest) {
		
		@SuppressWarnings("unused")
		String check = "";
		
		List<Integer> r = new ArrayList<Integer>();
		
		int nr = 0;
		
		for (List<String> row:rest) {
			
			int gi = Integer.parseInt(row.get(0));
			
			if (nr==rest.size()-1) {
				
				check+=row.get(1);
			}
			else {
				check+=row.get(1)+", ";
				
			}
					
			r.add(new Integer(gi-1));		
			
			nr++;
			
		}
		
		Collections.sort(r);
		
//		LOG.debug("I g ["+nr+"] = "+check);
		
		return r;
	}


	private List<Integer>[] getSamplesTimesLists (List<String> firstLine, Biological dataset){
		
		@SuppressWarnings("unchecked")
		List<Integer>[] r = new List[2];
		
//		LOG.debug(firstLine.toString());
		
		firstLine.remove(0);
		
		firstLine.remove(0);
		
		Set<String> inputSamples = new HashSet<String>();
		
		Set<String> inputTimes = new HashSet<String>(); 
		
		for (String el:firstLine) {			
			
			List<String> t = TextUtilities.splitElements(el,"_");
						
			inputSamples.add(t.get(0));
			
			inputTimes.add(t.get(1));
		}
		
//		LOG.debug("I s ["+inputSamples.size()+"] = "+inputSamples);
//		LOG.debug("I t ["+inputTimes.size()+"] = "+inputTimes);
		
		List<Integer> trSamples = getTrGenesSamples (inputSamples,dataset.getSampleNames());
		List<Integer> trTimes = getTrTimes (inputTimes,dataset.getTimeNames());
				
		r[0] = trSamples;
		r[1] = trTimes;
		
		
		return r;
		
	}




	private List<Integer> getTrGenesSamples(Set<String> samples, String[] genesOrSampleNames) {
		
		List<Integer> r = new ArrayList<Integer> (samples.size());
		
		for(String e:samples) {
			
			
			boolean enc = false;
			int i = 0;
			int index = 0;
			
			while (i<genesOrSampleNames.length&&!enc) {
				
				if (genesOrSampleNames[i].equalsIgnoreCase(e)) {
					index=i;
					enc = true;					
				}
				
				i++;
			}
			
			r.add(new Integer(index));			
			
		}
		
		Collections.sort(r);
		
		return r;
	}
	
	
	

	private List<Integer> getTrTimes(Set<String> times, String[] timeNames) {
		
		List<Integer> r = new ArrayList<Integer> (times.size());
		
		for(String e:times) {
			
			
			boolean enc = false;
			int i = 0;
			int index = 0;
			
			while (i<timeNames.length&&!enc) {
				
				if (timeNames[i].contains(e)) {
					index=i;
					enc = true;					
				}
				
				i++;
			}
			
			r.add(new Integer(index));			
			
		}
		
		Collections.sort(r);
		
		return r;
	}
	
	
	@SuppressWarnings("unused")
	private void checkLists(List<Integer> genes, List<Integer> samples, List<Integer> times, Biological dataset) {
		
		
		
		LOG.debug("O g ["+genes.size()+"] = "+matchElementNames(genes,dataset.getGeneNames()));
		
		LOG.debug("O s ["+samples.size()+"] = "+matchElementNames(samples,dataset.getSampleNames()));
		
		LOG.debug("O t ["+times.size()+"] = "+matchElementNames(times,dataset.getTimeNames()));
		
		
	}
	
	private List<String> matchElementNames (List<Integer> elementIndexes, String[] elementNames){
		
		List<String> elements = new ArrayList<String>(elementIndexes.size());
		
		for(Integer genIndex:elementIndexes){
			
			elements.add(elementNames[genIndex.intValue()]);
					
		}
				
		return elements;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
