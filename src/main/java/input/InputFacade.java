package input;

import input.algorithm.Control;
import input.algorithm.Implementation;
import input.algorithm.InvalidImplementationException;
import input.algorithm.WrongContolException;
import input.datasets.Common;
import input.datasets.DatasetsLoader;
import input.laboratory.Options;
import input.laboratory.WrongOptionsException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import utils.SystemUtilities;
import utils.TextUtilities;

/**
 * It provides an facade for front-end's resources, parameters, options and implementation modules.
 * @author David Gutiérrez Avilés
 * @see Common
 * @see Control
 * @see Options
 * @see Implementation
 */
//@SuppressWarnings("unused")
public class InputFacade {

	
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(InputFacade.class);


	/**
	 * This method builds resources, parameters, options and implementation from an input properties file.
	 * @param entryPropPath Path to input properties file. 
	 * @return An four element array with {@link Common} at position zero, {@link Control} at position one, {@link Options} at position two and {@link Implementation} at position three. 
	 * @throws FileNotFoundException Wrong I/O path.
	 * @throws IOException Wrong I/O path.
	 * @throws WrongContolException Wrong control parameter.
	 * @throws InvalidImplementationException Wrong <b>triGen</b> algorithm implementation.
	 * @throws URISyntaxException 
	 */
	public static Control buildControl(String entryPropPath)
			throws FileNotFoundException, IOException,
			WrongContolException, InvalidImplementationException {

		Control r = null;

		Properties defaults = SystemUtilities.getAlgorithmProperties();
		
		Properties user = new Properties(defaults);

		user.load(new FileInputStream(entryPropPath));
		
		String rp = SystemUtilities.getResourcesXmlPath();
	
		DatasetsLoader n = new DatasetsLoader(rp);

		Common dataset = n.getResourcesByName(user.getProperty("dataset"));
				
		if (user.getProperty("out")==null){
		
			String aux = TextUtilities.removeExtensionFromPath(entryPropPath); 
			
			user.setProperty("out", aux);
			
		}
		
		r = new Control(dataset,user);
		
		return r;

	}
	
	public static Control buildControl(Properties solProp)
			throws FileNotFoundException, IOException,
			WrongContolException, InvalidImplementationException {

		Control r = null;
		
		String xmlRoot = SystemUtilities.getResourcesXmlPath();
		
		DatasetsLoader n = new DatasetsLoader(xmlRoot);
		
		Common dataset = n.getResourcesByName(solProp.getProperty("dataset"));

		r = new Control(dataset,solProp);
		
		return r;

	}
	
	public static Control buildControl(Properties solProp, Common dataset)
			throws FileNotFoundException, IOException,
			WrongContolException, InvalidImplementationException {

		Control r = null;

		r = new Control(dataset,solProp);
		
		return r;

	}
		
	public static Options buildOptions(String entryPropPath, File outFolder, String outName)
			throws FileNotFoundException, IOException,
			WrongOptionsException {
			
		Options r = null;

		Properties defaults = SystemUtilities.getLaboratoryProperties();
		
		Properties user = new Properties(defaults);
		
		user.load(new FileInputStream(entryPropPath));
		
		r = new Options(user, outFolder, outName);
				
		return r;
	
	}
	
	public static Options buildOptions(String entryPropPath)
			throws FileNotFoundException, IOException,
			WrongOptionsException {
			
		Options r = null;

		Properties defaults = SystemUtilities.getLaboratoryProperties();
		
		Properties user = new Properties(defaults);
		
		user.load(new FileInputStream(entryPropPath));
	
		r = new Options(user);
				
		return r;
	
	}
	
	public static Common buildDataset(String datasetName)
			throws FileNotFoundException, IOException,
			WrongContolException, InvalidImplementationException {
				
		String xmlRoot = SystemUtilities.getResourcesXmlPath();
		
		DatasetsLoader n = new DatasetsLoader(xmlRoot);
		
		Common dataset = n.getResourcesByName(datasetName);
				
		return dataset;
		
	}
	
}