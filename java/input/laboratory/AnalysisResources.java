package input.laboratory;

import general.Tricluster;
import input.algorithm.Control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import utils.TextUtilities;
import utils.WorkFlowUtilities;

public class AnalysisResources extends CommonAnalysisResources {
	
	private long execTime;
	
	private Control control;
		
	//<K,V> = <level, number of coordinates>
	private Map<Integer, Integer> ghie;
	//<K,V> = <level, number of coordinates>
	private Map<Integer, Integer> chie;
	//<K,V> = <level, number of coordinates>
	private Map<Integer, Integer> thie;
	
	private List<String> slogs;

	
	public AnalysisResources(char analysisType,	String experimentAlias, List<Tricluster> solutions,
			 Control control, long execTime, Map<Integer, Integer> gh, Map<Integer, Integer> ch, Map<Integer, Integer> th) {
		
		super(analysisType,experimentAlias,solutions);
		
		assignments(control,execTime,gh,ch,th);
		
	}
	
	public AnalysisResources(char analysisType,	String experimentAlias, List<Tricluster> solutions,
			 Control control, long execTime, Map<Integer, Integer> gh, Map<Integer, Integer> ch, Map<Integer, Integer> th, List<String> slogs) {
		
		super(analysisType,experimentAlias,solutions);
		
		assignments(control,execTime,gh,ch,th,slogs);
			
	}
	
	private void assignments (Control control, long execTime, 
			Map<Integer, Integer> gh, Map<Integer, Integer> ch, Map<Integer, Integer> th){
		
		List<String> slogs = new ArrayList<String>();
		
		slogs.add("none");
		
		assignments(control,execTime,gh,ch,th,slogs);
		
	}
	
	private void assignments (Control control, long execTime, 
			Map<Integer, Integer> gh, Map<Integer, Integer> ch, Map<Integer, Integer> th, 
			List<String> slogs){
		
		this.control = control;
		
		this.execTime = execTime;
			
		this.ghie = gh;
		
		this.chie = ch;
		
		this.thie = th;
		
		this.slogs = slogs;
		
	}
	
	
	//************************LegacyTriGen constructor methods
	public AnalysisResources(Control control, long execTime) {
		
		super(WorkFlowUtilities.getExperimentTypeFromDataset(control.getDataset()),control.getOutName());
		
		this.control = control;
		
		this.execTime = execTime;
			
		this.slogs = new ArrayList<String>(control.getN());
			
	}
	
	
	public AnalysisResources(char analysisType,	String experimentAlias, Control control, long execTime) {
		
		super(analysisType,experimentAlias);
		
		this.control = control;
		
		this.execTime = execTime;
			
		this.slogs = new ArrayList<String>(control.getN());
			
	}
			
	public void loadLegacyDataHierarchy(Map<Integer, Integer> oldGh, Map<Integer, Integer> oldCh, Map<Integer, Integer> oldTh){
		
		this.ghie = changeDataHierarcchy (oldGh);
		
		this.chie = changeDataHierarcchy (oldCh);
		
		this.thie = changeDataHierarcchy (oldTh);
		
	}
	
	public void loadOneSlog (String log){
				
		slogs.add(log);
		
	}
	//************************LegacyTriGen constructor methods
	
	
	
	public Control getControl() {
		return control;
	}

	public long getExecTime() {
		return execTime;
	}

	public Map<Integer, Integer> getGhie() {
		return ghie;
	}

	public Map<Integer, Integer> getChie() {
		return chie;
	}

	public Map<Integer, Integer> getThie() {
		return thie;
	}
	
	public List<String> getSlogs() {
		return slogs;
	}
	
	
	public String toString (){
		
		String r = super.toString();
		
		r += 	"\nExecution time = "+TextUtilities.getTimeString(execTime)+
				"\nControl"+control+
				"\nGene data hierarcy = "+getDataHiearchyString(ghie)+
				"\nCondition data hierachy = "+getDataHiearchyString(chie)+
				"\nTime data hierarchy = "+getDataHiearchyString(thie)+
				"\nFirst slog = "+slogs.get(0)+
				"\nLast slog = "+slogs.get(slogs.size()-1);
			
		return r;
		
	}
	
	
	//PRIVATE METHODS
	
	private String getDataHiearchyString(Map<Integer, Integer> hierarchy) {
		
		String r = "";
		
		
		Set<Integer> auxlevels = hierarchy.keySet();
		
		List<Integer> auxLlevels = new ArrayList<Integer>(auxlevels);
		
		Collections.sort(auxLlevels);
				
		int iaux = 0;
		
		for (Integer auxL:auxLlevels){
			
			Integer count = hierarchy.get(auxL);
			
			String sufix = "";
			
			if (iaux!=auxLlevels.size()-1)
				sufix= " , ";	
				
			r+="Level "+auxL+": "+count+sufix;
						
			iaux++;
								
		}
		
		return r;
	}
	
	private Map<Integer, Integer> changeDataHierarcchy (Map<Integer, Integer> oldHierarchy) {
		
		//FROM <K,V> = <coordinate,level> to <K,V> = <level, number of coordinates>
		
		Map<Integer, Integer> newHierarchy = new HashMap<Integer,Integer>();;
		
		//K = level, V = Coordinates
		Map<Integer, List<Integer>> aux = new HashMap<Integer,List<Integer>>();
				
		Set<Integer> levels =  new HashSet<Integer> (oldHierarchy.values());
		
		Set<Entry<Integer,Integer>> entryset = oldHierarchy.entrySet();
		
		for (Integer level:levels){
		
			int levelValue = level.intValue();
			
			List<Integer> coordinates = new ArrayList<Integer>();
			
			for (Entry<Integer,Integer> en:entryset){
				
				int enKey = en.getKey().intValue();
				
				int enValue = en.getValue().intValue();
				
				if (levelValue==enValue)
					coordinates.add(new Integer(enKey));
				
			}
			
			Collections.sort(coordinates);
			
			aux.put(new Integer(levelValue), coordinates);
								
		}
	
		Set<Integer> auxlevels = aux.keySet();
		
		List<Integer> auxLlevels = new ArrayList<Integer>(auxlevels);
		
		Collections.sort(auxLlevels);
				
		for (Integer auxL:auxLlevels){
			
			List<Integer> co = aux.get(auxL);
						
			newHierarchy.put(new Integer(auxL.intValue()), new Integer (co.size()));

			
		}
		
		return newHierarchy;
		
	}

}
