package general;

import input.algorithm.InvalidImplementationException;
import input.algorithm.WrongContolException;
import input.laboratory.AnalysisResources;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class SolLoadWorkFlow extends LabWorkFlow{
	
	public abstract AnalysisResources loadSolution (String pathToSol) 
			throws FileNotFoundException, IOException, WrongContolException, InvalidImplementationException;
	
	

}