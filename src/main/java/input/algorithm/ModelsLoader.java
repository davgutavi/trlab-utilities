package input.algorithm;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This class loads the xml model descriptor and provides methods to build and check implementations or load all valid models.
 * 
 * @author David Gutiérrez Avilés
 *
 */
public class ModelsLoader {

	private static final Logger LOG = LoggerFactory.getLogger(ModelsLoader.class);

//	/**
//	 * Path to xml model descriptor.
//	 */
//	private static String MODELS_PATH;
	
	//private static final String NOT = "not";
	
	/**
	 * None xml tag.
	 */
	private static final String NONE = "none";
		
	/**
	 * Model xml tag.
	 */
	private static final String MODEL_XML_TAG = "model";
	
	/**
	 * Name xml tag.
	 */
	private static final String NAME_XML_TAG = "name";
	
	/**
	 * Individual xml tag.
	 */
	@SuppressWarnings("unused")
	private static final String INDIVIDUAL_XML_TAG = "individual";
	
	/**
	 * Individuals xml tag.
	 */
	private static final String INDIVIDUALS_XML_TAG = "individuals";
	
	/**
	 * Fitness functions xml tag.
	 */
	private static final String FITNESSFUNCTIONS_XML_TAG = "fitnessfunctions";
	
	/**
	 * Fitness function xml tag.
	 */
	private static final String FITNESS_XML_TAG = "fitness";
	
	/**
	 * Data hierarchies xml tag.
	 */
	private static final String DATAHIERARCHIES_XML_TAG = "datahierarchies";
	
	/**
	 * Data hierarchy xml tag.
	 */
	private static final String STOPPINGCRITERIA_XML_TAG = "stoppingcriteria";
	
	/**
	 * Solution criteria xml tag.
	 */
	private static final String SOLUTIONCRITERIA_XML_TAG = "solutioncriteria";
	
	/**
	 * Initial population methods xml tag.
	 */
	private static final String INITIALPOPS_XML_TAG ="initialpops";
	
	/**
	 * Selection methods xml tag.
	 */
	private static final String SELECTIONS_XML_TAG = "selections";
	
	/**
	 * Crossover methods xml tag.
	 */
	private static final String CROSSOVERS_XML_TAG = "crossovers";
	
	/**
	 * Mutation methods xml tag.
	 */
	private static final String MUTATIONS_XML_TAG = "mutations";
	
	/**
	 * Current DOM root element.
	 */
	private Element root;

	/**
	 * It creates a Model Loader by loading the xml model descriptor.
	 * @throws URISyntaxException 
	 */
	public ModelsLoader()  {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder;
		
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		
		URL model_url = cl.getResource("models.xml");
		
		try {

			builder = factory.newDocumentBuilder();
			
			Document xmlDoc = builder.parse(model_url.openStream());

			root = xmlDoc.getDocumentElement();

		} catch (ParserConfigurationException e) {
			LOG.error("Parser error");
			e.printStackTrace();
		} catch (SAXException e) {
			LOG.error("XML format error");
			e.printStackTrace();
		} catch (IOException e) {
			LOG.error("File error");
			e.printStackTrace();
		}

	}

	/**
	 * It checks the methods passed by parameter through the xml Model descriptor and build the corresponding {@link Implementation} object.
	 * @param ind Individual name.
	 * @param fit Fitness name.
	 * @param dat Data Hierarchy name.
	 * @param sto Stopping criterion name.
	 * @param sol Solution criterion name.
	 * @param ini Initial population method name.
	 * @param sel Selection method name.
	 * @param cro Crossover method name.
	 * @param mut Mutation method name.
	 * @return {@link Implementation} with the valid class name for every element.
	 * @throws InvalidImplementationException If names passed by parameter don't correspond to any valid model.
	 */
	public Implementation checkImplementation(String ind, String fit,
			String dat, String sto, String sol, String ini,
			String sel, String cro, String mut) throws InvalidImplementationException {

		Implementation r = null;

		String errMessage = "";
		String tail = "";
		
		boolean impFound = false;
		
		NodeList nLmodel = root.getElementsByTagName(MODEL_XML_TAG);

		for (int i = 0; i < nLmodel.getLength()&&!impFound; i++) {

			Node currNmodel = nLmodel.item(i);

			if (currNmodel.getNodeType() == Node.ELEMENT_NODE) {

				Element mod = (Element) currNmodel;
				
				String[] fitTr = null;
				
				String indImp = "",dhImp = "",stpCri = "",solCri = "",iniPop = "",select = "",cross = "",mute = "";
			
				boolean correctImp = true;
											
				//Check Individual
				//indImp = checkElementByName(mod,INDIVIDUAL_XML_TAG,ind);
				
				indImp = checkElementByName(mod,INDIVIDUALS_XML_TAG,ind);
				
				if (indImp.equalsIgnoreCase(NONE)){
					correctImp = false;
					tail += "Individual\n";
				}
								
				//Check Fitness
				fitTr = checkFitnessAndTransformation(mod.getElementsByTagName(FITNESSFUNCTIONS_XML_TAG), fit);
				
				if (fitTr[0].equalsIgnoreCase(NONE)){
					
					correctImp = false;
					tail += "Fitness\n";
														
				}
				
				//Check Data Hierarchy
				dhImp = checkElementByName(mod,DATAHIERARCHIES_XML_TAG,dat);
				
				if (dhImp.equalsIgnoreCase(NONE)){
					correctImp = false;
					tail += "Data Hierarchy\n";
				}
				
				//Check Stopping Criterion
				stpCri = checkElementByName(mod,STOPPINGCRITERIA_XML_TAG,sto);
				
				if (stpCri.equalsIgnoreCase(NONE)){
					correctImp = false;
					tail += "Stopping Criterion\n";
				}
				
				//Check Solution Criterion
				solCri = checkElementByName(mod,SOLUTIONCRITERIA_XML_TAG,sol);
				
				if (solCri.equalsIgnoreCase(NONE)){
					correctImp = false;
					tail += "Solution Criterion\n";
				}
				
				//Check Initial Pop
				iniPop = checkElementByName(mod,INITIALPOPS_XML_TAG,ini);
				
				if (iniPop.equalsIgnoreCase(NONE)){
					correctImp = false;
					tail += "Initial Population\n";
				}
				
				//Check Selection
				select = checkElementByName(mod,SELECTIONS_XML_TAG,sel);
				
				if (select.equalsIgnoreCase(NONE)){
					correctImp = false;
					tail += "Selection\n";
				}
				
				//Check Crossover
				cross = checkElementByName(mod,CROSSOVERS_XML_TAG,cro);
				
				if (cross.equalsIgnoreCase(NONE)){
					correctImp = false;
					tail += "Crossover\n";
				}
				
				//Check Mutation
				mute = checkElementByName(mod,MUTATIONS_XML_TAG,mut);
				
				if (mute.equalsIgnoreCase(NONE)){
					correctImp = false;
					tail += "Mutation\n";
				}				
				
				if (correctImp){
					
					r = new Implementation(indImp, fitTr[0], dhImp, stpCri, solCri, iniPop, select, cross, mute);
										
					impFound = true;
				}
				else{
					
					String modN = mod.getAttribute(NAME_XML_TAG);
					
					errMessage += "\nInvalid Implementation for "+modN+" model, failed elements:\n"+tail;
					
					tail = "";
					
				}
				
			}

		}
		
		if (!impFound)
			throw new InvalidImplementationException(errMessage);

		return r;

	}

	/**
	 * It provides every available models in order to consult programmatically. 
	 * 
	 * @return Array of {@link Model} with every available models
	 */
	public Model[] getAvailableModels() {
		
		NodeList nLmodel = root.getElementsByTagName(MODEL_XML_TAG);

		Model[] r = new Model[nLmodel.getLength()];
		
		for (int i = 0; i < nLmodel.getLength(); i++){
			
			Node currNmodel = nLmodel.item(i);

			if (currNmodel.getNodeType() == Node.ELEMENT_NODE) {

				Element mod = (Element) currNmodel;
			
				Model n = new Model(mod.getAttribute(NAME_XML_TAG));
				
				NodeList els = mod.getChildNodes();
				
				for (int j = 0; j < els.getLength(); j++) {

					Node currEl = els.item(j);
					
					if (currEl.getNodeType() == Node.ELEMENT_NODE) {
					
						//LOG.debug(currEl.getNodeName());
						putElements (currEl.getNodeName(), n,mod);
					
					}
				}
				
				r[i] = n;
			
			}
			
			
		}
		
		return r;

	}
	
	/**
	 * It puts an algorithm element into current {@link Model}. 
	 * 
	 * @param element Algorithm element name.
	 * @param curr Current {@link Model}.
	 * @param model DOM element with loaded model.
	 */
	private void putElements (String element, Model curr,Element model){
		
		NodeList aux1 = model.getElementsByTagName(element);
		
		Node aux2 = aux1.item(0);
		
		NodeList listOfelements = aux2.getChildNodes();
				
		for (int i = 0; i < listOfelements.getLength(); i++) {

			Node currN = listOfelements.item(i);
			
			if (currN.getNodeType() == Node.ELEMENT_NODE) {

				Element currEl = (Element) currN;
				
				String item = currEl.getAttribute(NAME_XML_TAG);
				
				String cl = currEl.getFirstChild().getNodeValue();
				
				if (element.equalsIgnoreCase(INDIVIDUALS_XML_TAG)){
					
					curr.putIndividual(item, cl);
				
				}
				else if (element.equalsIgnoreCase(FITNESSFUNCTIONS_XML_TAG)){
					
					curr.putFitness(item, cl.trim());		
								
				}				
				else if (element.equalsIgnoreCase(DATAHIERARCHIES_XML_TAG)){
					
					curr.putDataHierarchy(item, cl);
					
				}
				else if (element.equalsIgnoreCase(STOPPINGCRITERIA_XML_TAG)){
					
					curr.putStoppingCriterion(item, cl);
					
				}
				else if (element.equalsIgnoreCase(SOLUTIONCRITERIA_XML_TAG)){
					
					curr.putSolutionCriterion(item, cl);
					
				}
				else if (element.equalsIgnoreCase(INITIALPOPS_XML_TAG)){
					
					curr.putInitialPop(item, cl);
					
				}
				else if (element.equalsIgnoreCase(SELECTIONS_XML_TAG)){
					
					curr.putSelection(item, cl);
					
				}
				else if (element.equalsIgnoreCase(CROSSOVERS_XML_TAG)){
					
					curr.putCrossover(item, cl);
					
				}
				else if (element.equalsIgnoreCase(MUTATIONS_XML_TAG)){
					
					curr.putMutation(item, cl);
					
				}
				
			}
			
		}
					
	}
	
	/**
	 * It checks if an algorithm element is into DOM element with loaded model.
	 * 
	 * @param model DOM element with loaded model.
	 * @param element Algorithm element type.
	 * @param name Name of specific algorithm element.
	 * @return Class name for specific algorithm element if it is a valid one otherwise NONE value.
	 */
	private String checkElementByName (Element model, String element, String name){
		
		String r = NONE;
		
		NodeList aux1 = model.getElementsByTagName(element);
		
		Node aux2 = aux1.item(0);
		
		NodeList listOfelements = aux2.getChildNodes();
		
		if (listOfelements.getLength()==1){
			
			Element el = (Element) aux2;

			String currName = el.getAttribute(NAME_XML_TAG);

			if (currName.equalsIgnoreCase(name)) {

				r = el.getFirstChild().getNodeValue();

			}
			
		}
		else{
			
			for (int i = 0; i < listOfelements.getLength(); i++) {

				Node currN = listOfelements.item(i);
				
				if (currN.getNodeType() == Node.ELEMENT_NODE) {

					Element currEl = (Element) currN;

					String currName = currEl.getAttribute(NAME_XML_TAG);
					
					if (currName.equalsIgnoreCase(name)) {

						r = currEl.getFirstChild().getNodeValue();

					}
				}
				
			}
			
		}
				
		return r;
		
	}	

	/**
	 * @param list {@link NodeList} of current loaded model.
	 * @param fit Fitness name method.
	 * @return Two-element Array, fitness class name in zero position and transformation class name in one position if both of them are valid ones otherwise NONE value. 
	 */
	private String[] checkFitnessAndTransformation(NodeList list, String fit) {

		String[] r = new String[2];

		r[0] = NONE;

		r[1] = NONE;

		Node node = list.item(0);

		NodeList listOfelements = ((Element) node).getElementsByTagName(FITNESS_XML_TAG);

		for (int i = 0; i < listOfelements.getLength(); i++) {

			Node currN = listOfelements.item(i);

			if (currN.getNodeType() == Node.ELEMENT_NODE) {

				Element el = (Element) currN;

				String name = el.getAttribute(NAME_XML_TAG);

				if (fit.equalsIgnoreCase(name)) {

					r[0] = (currN.getFirstChild().getNodeValue()).trim();

				}
			}
		}

		return r;
	}

}
