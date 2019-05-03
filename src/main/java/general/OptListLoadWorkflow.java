package general;


import java.io.IOException;

import input.algorithm.InvalidImplementationException;
import input.algorithm.WrongContolException;
import input.laboratory.OPTsolBatch;


public abstract class OptListLoadWorkflow {

	public  abstract OPTsolBatch listLoad (String pathToTriclustersFolder, String datasetName) throws IOException, WrongContolException, InvalidImplementationException;
	
}