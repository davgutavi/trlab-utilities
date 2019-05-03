package general;

import input.algorithm.InvalidImplementationException;
import input.algorithm.WrongContolException;
import input.laboratory.CommonAnalysisResources;

import java.io.IOException;
import java.util.List;

public abstract class ListLoadWorkFlow extends LabWorkFlow {

	
	
	public  abstract List<CommonAnalysisResources> listLoad (String pathToList, char analysisType) 
			throws IOException, WrongContolException, InvalidImplementationException;
	
	
	
}
