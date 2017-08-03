package general;

import java.io.FileNotFoundException;
import java.io.IOException;

import input.algorithm.InvalidImplementationException;
import input.algorithm.WrongContolException;
import input.laboratory.ReducedAnalysisResources;

public abstract class LegacySolLoadWorkFlow extends LabWorkFlow {
	
	public abstract ReducedAnalysisResources loadLegacySolution (String pathToSol, String datasetName) 
			throws FileNotFoundException, IOException, WrongContolException, InvalidImplementationException;

}
