package general;

import java.io.FileNotFoundException;
import java.io.IOException;

import input.InputFacade;
import input.laboratory.Options;
import input.laboratory.WrongOptionsException;

public abstract class LabWorkFlow {
	
	public Options loadOptions(String pathToOptions) throws FileNotFoundException, IOException, WrongOptionsException{
		
		Options options = InputFacade.buildOptions(pathToOptions);
		
		return options;
		
	}

}
