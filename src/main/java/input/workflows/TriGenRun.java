package input.workflows;

import general.AlgorithmWorkFlow;
import input.InputFacade;
import input.algorithm.Control;
import input.algorithm.InvalidImplementationException;
import input.algorithm.WrongContolException;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TriGenRun implements AlgorithmWorkFlow{
	
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(TriGenRun.class);


	@Override
	public Control loadControl(String pathToControl) 
			throws FileNotFoundException, IOException, WrongContolException, InvalidImplementationException {
		
		Control control = InputFacade.buildControl(pathToControl);
		
		return control;
	
	}

	
}
