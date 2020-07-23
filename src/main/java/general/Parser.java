package general;

import input.InputFacade;
import input.algorithm.Control;
import input.algorithm.InvalidImplementationException;
import input.algorithm.WrongContolException;
import input.datasets.Common;
import input.laboratory.AnalysisResources;
import input.laboratory.ReducedAnalysisResources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import utils.SystemUtilities;
import utils.TextUtilities;
import utils.WorkFlowUtilities;

public class Parser {
	
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(Parser.class);

	private static final String SOLUTION_EXTENSION = ".sol";
	private static final String COORDINATE_SEPARATOR = ",";
	private static final String COMPONENT_SEPARATOR = ";";
	private static final String SOLUTION_SEPARATOR = "@";

	public static String getSolutionExtension() {
		return SOLUTION_EXTENSION;
	}

	public static String getCoordinateSeparator() {
		return COORDINATE_SEPARATOR;
	}

	public static String getComponentSeparator() {
		return COMPONENT_SEPARATOR;
	}

	public static String getSolutionSeparator() {
		return SOLUTION_SEPARATOR;
	}
	
	public static void buildSolutionFile(AnalysisResources resources, String pathToFolder)
			throws IOException {

		Control control = resources.getControl();

		Properties sf = new Properties();

		String auxExecTime = resources.getExecTime() + "";
		String auxOut = pathToFolder +SystemUtilities.getFileSep()+control.getOutName()
				+ SOLUTION_EXTENSION;
		
		//String auxOut = control.getOutFolder() +SystemUtilities.getFileSep()+control.getOutName()
			//	+ SOLUTION_EXTENSION;
		
		
		
		String auxDataset = control.getDataset().getDatasetName();
		String auxN = control.getN() + "";
		String auxG = control.getG() + "";
		String auxI = control.getI() + "";
		String auxAle = control.getAle() + "";
		String auxSel = control.getSel() + "";
		String auxMut = control.getMut() + "";
		String auxWf = control.getWf() + "";
		String auxWg = control.getWg() + "";
		String auxWc = control.getWc() + "";
		String auxWt = control.getWt() + "";
		String auxWog = control.getWog() + "";
		String auxWoc = control.getWoc() + "";
		String auxWot = control.getWot() + "";
		String auxMinG = control.getMinG() + "";
		String auxMinC = control.getMinC() + "";
		String auxMinT = control.getMinT() + "";
		String auxMaxG = control.getMaxG() + "";
		String auxMaxC = control.getMaxC() + "";
		String auxMaxT = control.getMaxT() + "";
		String auxThreads = control.getThreads() + "";
		String auxSol = buildSolString(resources.getSolutions());
		
		
		String auxGhi = buildDataHiearchyString(resources.getGhie());
		
		String auxChi = buildDataHiearchyString(resources.getChie());
		
		String auxThi = buildDataHiearchyString(resources.getThie());

		sf.setProperty("execTime", auxExecTime);
		sf.setProperty("out", auxOut);
		sf.setProperty("dataset", auxDataset);
		sf.setProperty("N", auxN);
		sf.setProperty("G", auxG);
		sf.setProperty("I", auxI);
		sf.setProperty("Ale", auxAle);
		sf.setProperty("Sel", auxSel);
		sf.setProperty("Mut", auxMut);
		sf.setProperty("Wf", auxWf);
		sf.setProperty("Wg", auxWg);
		sf.setProperty("Wc", auxWc);
		sf.setProperty("Wt", auxWt);
		sf.setProperty("WOg", auxWog);
		sf.setProperty("WOc", auxWoc);
		sf.setProperty("WOt", auxWot);
		sf.setProperty("minG", auxMinG);
		sf.setProperty("minC", auxMinC);
		sf.setProperty("minT", auxMinT);
		sf.setProperty("maxG", auxMaxG);
		sf.setProperty("maxC", auxMaxC);
		sf.setProperty("maxT", auxMaxT);
		sf.setProperty("threads", auxThreads);
		sf.setProperty("solutions", auxSol);
		sf.setProperty("g.datahierarchy", auxGhi);
		sf.setProperty("c.datahierarchy", auxChi);
		sf.setProperty("t.datahierarchy", auxThi);
		
		
		sf.setProperty("individual", control.getIndividualMethod());
		sf.setProperty("fitness", control.getFitnessMethod());
		sf.setProperty("datahierarchy", control.getDataHierarchyMethod());
		sf.setProperty("stoppingcriterion", control.getStoppingCriterionMethod());
		sf.setProperty("solutioncriterion", control.getSolutionCriterionMethod());
		sf.setProperty("initialpop", control.getInitialPopMethod());
		sf.setProperty("selection", control.getSelectionMethod());
		sf.setProperty("crossover", control.getCrossoverMethod());
		sf.setProperty("mutation", control.getMutationMethod());
		
		PrintStream ps = new PrintStream(auxOut);
		sf.store(ps, "");

	}
	

	public static void buildSolutionFile(AnalysisResources resources)
			throws IOException {
			
		buildSolutionFile( resources, (resources.getControl()).getOutFolder()+
				SystemUtilities.getFileSep()+(resources.getControl()).getOutName()+SOLUTION_EXTENSION);
		
	}
	
	
	public static AnalysisResources buildOneAnalysysResources(String experimentAlias, String solPath)
			throws FileNotFoundException, IOException, WrongContolException,
			InvalidImplementationException {

		AnalysisResources r = null;

		Properties solProp = new Properties();

		solProp.load(new FileInputStream(solPath));

		Control control = InputFacade.buildControl(solProp);

		char analysisType = WorkFlowUtilities
				.getExperimentTypeFromDataset(control.getDataset());

		long execTime = Long.parseLong(solProp.getProperty("execTime"));

		List<Tricluster> solutions = buildSolutions(solProp
				.getProperty("solutions"));

		Map<Integer, Integer> gh = buildHierarchy(solProp
				.getProperty("g.datahierarchy"));

		Map<Integer, Integer> ch = buildHierarchy(solProp
				.getProperty("c.datahierarchy"));

		Map<Integer, Integer> th = buildHierarchy(solProp
				.getProperty("t.datahierarchy"));

		r = new AnalysisResources(analysisType, experimentAlias,
				solutions, control, execTime, gh, ch, th);

		return r;

	}

	public static AnalysisResources buildAnalysysResourcesFromList(char analysisType,String experimentAlias, String solPath, Common dataset)
			throws FileNotFoundException, IOException, WrongContolException,
			InvalidImplementationException {

		AnalysisResources r = null;

		Properties solProp = new Properties();

		solProp.load(new FileInputStream(solPath));

		Control control = InputFacade.buildControl(solProp, dataset);
	
		long execTime = Long.parseLong(solProp.getProperty("execTime"));

		List<Tricluster> solutions = buildSolutions(solProp
				.getProperty("solutions"));

		Map<Integer, Integer> gh = buildHierarchy(solProp
				.getProperty("g.datahierarchy"));

		Map<Integer, Integer> ch = buildHierarchy(solProp
				.getProperty("c.datahierarchy"));

		Map<Integer, Integer> th = buildHierarchy(solProp
				.getProperty("t.datahierarchy"));

		r = new AnalysisResources(analysisType, experimentAlias,
				solutions, control, execTime, gh, ch, th);

		return r;

	}
	
	
	public static ReducedAnalysisResources buildReducedAnalysysResources(String experimentAlias, String solNormalizedPath, Common dataset)
			throws FileNotFoundException, IOException, WrongContolException,
			InvalidImplementationException {

		char analysisType = WorkFlowUtilities.getExperimentTypeFromDataset(dataset);

		return buildReducedAnalysysResourcesFromList(analysisType,experimentAlias,solNormalizedPath,dataset);

	}
	

	public static ReducedAnalysisResources buildReducedAnalysysResourcesFromList(char analysisType,
			String experimentAlias, String solNormalizedPath, Common dataset)
			throws FileNotFoundException, IOException, WrongContolException,
			InvalidImplementationException {

		ReducedAnalysisResources r = null;

		List<Tricluster> solutions = LegacyParser.parse(solNormalizedPath);
	
		r = new ReducedAnalysisResources(analysisType, experimentAlias,
				solutions, dataset);

		return r;

	}

	// ************************************************************private methods

	private static String buildSolString(List<Tricluster> solutions) {

		String r = "";

		int solCounter = 0;

		for (Tricluster tri : solutions) {

			List<Integer> genes = tri.getGenes();

			int ig = 0;

			for (Integer g : genes) {

				String sufix = COORDINATE_SEPARATOR;

				if (ig == genes.size() - 1)
					sufix = COMPONENT_SEPARATOR;

				r += g + sufix;

				ig++;

			}

			List<Integer> samples = tri.getSamples();

			int is = 0;

			for (Integer s : samples) {

				String sufix = COORDINATE_SEPARATOR;

				if (is == samples.size() - 1)
					sufix = COMPONENT_SEPARATOR;

				r += s + sufix;

				is++;

			}

			List<Integer> times = tri.getTimes();

			int it = 0;

			for (Integer t : times) {

				String sufix = COORDINATE_SEPARATOR;

				if (it == times.size() - 1)
					sufix = "";

				r += t + sufix;

				it++;

			}

			if (solCounter != solutions.size() - 1)
				r += SOLUTION_SEPARATOR;

			solCounter++;

		}

		return r;
	}

	private static String buildDataHiearchyString(
			Map<Integer, Integer> hierarchy) {

		String r = "";

		//<K,V> = <level, number of coordinates>
		
		Set<Integer> auxlevels = hierarchy.keySet();
		
//		LOG.debug("Levels = "+auxlevels.toString());

		List<Integer> auxLlevels = new ArrayList<Integer>(auxlevels);

		Collections.sort(auxLlevels);
		
//		for (Integer auxL : auxLlevels) {
//			
//			LOG.debug("L = "+auxL+" , Count = "+hierarchy.get(auxL)+"\n");
//						
//		}
		
		

		int iaux = 0;

		for (Integer auxL : auxLlevels) {

			Integer count = hierarchy.get(auxL);

			String sufix = SOLUTION_SEPARATOR;

			if (iaux == auxLlevels.size() - 1)
				sufix = "";
			

			r += auxL+COORDINATE_SEPARATOR + count + sufix;

			iaux++;

		}
		
//		LOG.debug("hierarchy = "+r+"\n");

		return r;
	}

	private static List<Tricluster> buildSolutions(String solutionsString) {

		List<Tricluster> r = new ArrayList<Tricluster>();

		List<String> auxSolutions = TextUtilities.splitElements(
				solutionsString, SOLUTION_SEPARATOR);

		for (String strSol : auxSolutions) {

			List<String> auxComponents = TextUtilities.splitElements(strSol,
					COMPONENT_SEPARATOR);

			List<String> auxG = TextUtilities.splitElements(
					auxComponents.get(0), COORDINATE_SEPARATOR);

			List<String> auxC = TextUtilities.splitElements(
					auxComponents.get(1), COORDINATE_SEPARATOR);

			List<String> auxT = TextUtilities.splitElements(
					auxComponents.get(2), COORDINATE_SEPARATOR);

			Tricluster tri = new Tricluster(auxG, auxC, auxT);

			r.add(tri);

		}

		return r;

	}

	private static Map<Integer, Integer> buildHierarchy(String hierarchyString) {

		Map<Integer, Integer> r = new HashMap<Integer, Integer>();

		List<String> auxLevels = TextUtilities.splitElements(hierarchyString,
				SOLUTION_SEPARATOR);

		

		for (String auxCount : auxLevels) {
			
			List<String> aux = TextUtilities.splitElements(auxCount,COORDINATE_SEPARATOR);

			r.put(new Integer(aux.get(0)), new Integer(aux.get(1)));


		}

		return r;

	}

}