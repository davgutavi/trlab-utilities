package input.workflows;

import general.Parser;
import general.SolLoadWorkFlow;
import input.algorithm.InvalidImplementationException;
import input.algorithm.WrongContolException;
import input.laboratory.AnalysisResources;

import java.io.FileNotFoundException;
import java.io.IOException;

import utils.TextUtilities;

public class LabOneSolRun extends SolLoadWorkFlow{

	@Override
	public AnalysisResources loadSolution(String pathToSol) 
			throws FileNotFoundException, IOException, WrongContolException, InvalidImplementationException {
		
		
		String name = TextUtilities.getFileNameWithoutExtension(pathToSol);
		
		AnalysisResources ar = Parser.buildOneAnalysysResources(name,pathToSol);
		
		
		return ar;
	}

}
