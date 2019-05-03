package general;

import java.io.FileNotFoundException;
import java.io.IOException;
import input.algorithm.Control;
import input.algorithm.InvalidImplementationException;
import input.algorithm.WrongContolException;

public interface AlgorithmWorkFlow  {
	
	public Control loadControl (String pathToControl) 
			throws FileNotFoundException, IOException, WrongContolException, InvalidImplementationException;

}
