package input.datasets;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import utils.TextUtilities;

/**
 * It loads the xml resources descriptor and provides methods to retrieve all
 * required information from local file system.
 * 
 * @author David Gutiérrez Avilés
 *
 */
public class DatasetsLoader {

	private static final Logger LOG = LoggerFactory.getLogger(DatasetsLoader.class);

	/**
	 * Xml resources descriptor file name (with extension).
	 */
	// private static final String RESOURCES_FILE_NAME = "resources.xml";

	
	private String xmlPath;
	
	private Document xmlDoc;

	/**
	 * Root element from DOM model.
	 */
	private Element root;

	/**
	 * Complete root path to xml resources descriptor.
	 */
	private String rPath;

	/**
	 * It builds a resources loader from xml resources descriptor.
	 * 
	 * @param resourcesPath
	 *            Path to resources root folder. Without resources xml
	 *            descriptor name file.
	 */
	public DatasetsLoader(String resourcesPath) {

		// rPath = resourcesPath;
		
		rPath = TextUtilities.getRootPathWithSlash(resourcesPath);

		// String xmlPath = TextUtilities.appendToPath(resourcesPath,
		// RESOURCES_FILE_NAME);

		xmlPath = resourcesPath;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		
		
		DocumentBuilder builder = null;

		try {
			builder = factory.newDocumentBuilder();
			xmlDoc = builder.parse(new File(xmlPath));
			
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
	 * This method provides a {@link Map} with all dataset names.
	 * 
	 * @return {@link Map} with all dataset names. K = name, V = id.
	 */
	public Map<String, String> getAllDatasetsNames() {

		Map<String, String> r = new HashMap<String, String>();

		NodeList datasetNodes = root.getElementsByTagName("dataset");

		for (int i = 0; i < datasetNodes.getLength(); i++) {

			Node currentDatasetNode = datasetNodes.item(i);

			if (currentDatasetNode.getNodeType() == Node.ELEMENT_NODE) {

				NamedNodeMap attrib = currentDatasetNode.getAttributes();

				String id = (attrib.getNamedItem("id")).getNodeValue();

				String name = (attrib.getNamedItem("name")).getNodeValue();

				r.put(name, id);

			}

		}

		return r;

	}

	/**
	 * It provides a {@link Common} with all useful information of dataset
	 * consulted. Depending of type field, the returned object extends his class
	 * with {@link Biological} (type b) or {@link Assorted} (type e) 
	 * 
	 * @param dataset
	 *            Dataset xml name.
	 * @return {@link Common} with all useful information of dataset consulted.
	 */
	public Common getResourcesByName(String dataset) {

		Common r = null;

		NodeList datasetNodes = root.getElementsByTagName("dataset");

		// Find the specifically dataset throw XML document

		Node datasetNode = null;

		boolean found = false;

		for (int i = 0; i < datasetNodes.getLength() && !found; i++) {

			Node currentDatasetNode = datasetNodes.item(i);

			if (currentDatasetNode.getNodeType() == Node.ELEMENT_NODE) {

				NamedNodeMap attrib = currentDatasetNode.getAttributes();

				String name = (attrib.getNamedItem("name")).getNodeValue();

				if (name.equalsIgnoreCase(dataset)) {
					found = true;
					datasetNode = currentDatasetNode;
				}
			}

		}

		NamedNodeMap att = datasetNode.getAttributes();

		// Get dataset basics

		String dID = (att.getNamedItem("id")).getNodeValue();
		String gS = (att.getNamedItem("geneSize")).getNodeValue();
		String cS = (att.getNamedItem("sampleSize")).getNodeValue();
		String tS = (att.getNamedItem("timeSize")).getNodeValue();
		String or = (att.getNamedItem("organism")).getNodeValue();
		String ty = (att.getNamedItem("type")).getNodeValue();

		String minG = (att.getNamedItem("minG")).getNodeValue();
		String maxG = (att.getNamedItem("maxG")).getNodeValue();
		String minC = (att.getNamedItem("minC")).getNodeValue();
		String maxC = (att.getNamedItem("maxC")).getNodeValue();
		String minT = (att.getNamedItem("minT")).getNodeValue();
		String maxT = (att.getNamedItem("maxT")).getNodeValue();

		// LOG.debug(dN);
		// LOG.debug(dID);
		// LOG.debug(gS);
		// LOG.debug(cS);
		// LOG.debug(tS);
		// LOG.debug(or);

		// Get separator

		NodeList nodeResources = ((Element) datasetNode).getElementsByTagName("resources");

		Node rsc = nodeResources.item(0);

		String sp = ((rsc.getAttributes()).getNamedItem("separator")).getNodeValue();

		// LOG.debug(sp);

		// and value paths

		NodeList datasetResources = rsc.getChildNodes();

		String[] paths = new String[Integer.parseInt(tS)];

		int j = 0;

		for (int i = 0; i < datasetResources.getLength(); i++) {

			Node n = datasetResources.item(i);

			if (n.getNodeType() == Node.ELEMENT_NODE) {

				// paths[j] = rPath+dID+"/"+n.getFirstChild().getNodeValue();

				paths[j] = TextUtilities.appendToPath(rPath, dID, n.getFirstChild().getNodeValue());

				// LOG.debug(paths[j]);

				j++;
			}

		}

		// Distinguish between e (earthquake), s (synthetic), b (biological)

		if (ty.equalsIgnoreCase("e")) {

			// For earthquake, get genes, samples and times' tags

			NodeList gn = ((Element) datasetNode).getElementsByTagName("genes");
			// String genesPath =
			// rPath+dID+"/"+((gn.item(0)).getFirstChild()).getNodeValue();
			String genesPath = TextUtilities.appendToPath(rPath, dID, ((gn.item(0)).getFirstChild()).getNodeValue());

			NodeList sm = ((Element) datasetNode).getElementsByTagName("samples");
			// String samplesPath =
			// rPath+dID+"/"+((sm.item(0)).getFirstChild()).getNodeValue();
			String samplesPath = TextUtilities.appendToPath(rPath, dID, ((sm.item(0)).getFirstChild()).getNodeValue());

			NodeList tm = ((Element) datasetNode).getElementsByTagName("times");
			// String timesPath =
			// rPath+dID+"/"+((tm.item(0)).getFirstChild()).getNodeValue();
			String timesPath = TextUtilities.appendToPath(rPath, dID, ((tm.item(0)).getFirstChild()).getNodeValue());

			// LOG.debug(genesPath);
			// LOG.debug(samplesPath);
			// LOG.debug(timesPath);

			// and construct resources

			// r = new Real(dID,dataset,ty,gS,cS, tS, paths,
			// sp,minG,maxG,minC,maxC,minT,maxT,genesPath,samplesPath,timesPath);

			r = new Assorted(dID, dataset, ty, gS, cS, tS, paths, sp, minG, maxG, minC, maxC, minT, maxT, genesPath,
					samplesPath, timesPath);

		}

		else if (ty.equalsIgnoreCase("b")) {

			// For real, get genes, samples and times' tags

			NodeList gn = ((Element) datasetNode).getElementsByTagName("genes");
			// String genesPath =
			// rPath+dID+"/"+((gn.item(0)).getFirstChild()).getNodeValue();
			String genesPath = TextUtilities.appendToPath(rPath, dID, ((gn.item(0)).getFirstChild()).getNodeValue());

			NodeList sm = ((Element) datasetNode).getElementsByTagName("samples");
			// String samplesPath =
			// rPath+dID+"/"+((sm.item(0)).getFirstChild()).getNodeValue();
			String samplesPath = TextUtilities.appendToPath(rPath, dID, ((sm.item(0)).getFirstChild()).getNodeValue());

			NodeList tm = ((Element) datasetNode).getElementsByTagName("times");
			// String timesPath =
			// rPath+dID+"/"+((tm.item(0)).getFirstChild()).getNodeValue();
			String timesPath = TextUtilities.appendToPath(rPath, dID, ((tm.item(0)).getFirstChild()).getNodeValue());

			r = new Biological(dID, dataset, ty, gS, cS, tS, paths, sp, minG, maxG, minC, maxC, minT, maxT, genesPath,
					samplesPath, timesPath, or);

		}

		return r;

	}

	public void writeRecord(String id, String name, char type, int geneSize, int sampleSize, int timeSize, int minG,
			int maxG, int minC, int maxC, int minT, int maxT, String organism, String description, String sep,
			String[] dataFileNames, String geneFileName, String sampleFileName, String timeFileName) throws TransformerException {

		// DATASET

		Element newDataset = xmlDoc.createElement("dataset");

		newDataset.setAttribute("id", id);
		newDataset.setAttribute("name", name);
		newDataset.setAttribute("type", "" + type);
		newDataset.setAttribute("geneSize", "" + geneSize);
		newDataset.setAttribute("sampleSize", "" + sampleSize);
		newDataset.setAttribute("timeSize", "" + timeSize);
		newDataset.setAttribute("minG", "" + minG);
		newDataset.setAttribute("maxG", "" + maxG);
		newDataset.setAttribute("minC", "" + minC);
		newDataset.setAttribute("maxC", "" + maxC);
		newDataset.setAttribute("minT", "" + minT);
		newDataset.setAttribute("maxT", "" + maxT);
		newDataset.setAttribute("organism", organism);
		newDataset.setAttribute("description", description);
		root.appendChild(newDataset);

		// RESOURCES
		Element newResources = xmlDoc.createElement("resources");
		newResources.setAttribute("separator", sep);
		newDataset.appendChild(newResources);

		// RESOURCE
		for (int i = 0; i < dataFileNames.length; i++) {
			Element newResource = xmlDoc.createElement("resource");
			newResource.appendChild(xmlDoc.createTextNode(dataFileNames[i]));
			newResources.appendChild(newResource);
		}

		// GENES

		Element newGenes = xmlDoc.createElement("genes");
		newGenes.appendChild(xmlDoc.createTextNode(geneFileName));
		newDataset.appendChild(newGenes);

		// SAMPLES
		Element newSamples = xmlDoc.createElement("samples");
		newSamples.appendChild(xmlDoc.createTextNode(sampleFileName));
		newDataset.appendChild(newSamples);

		// TIMES
		Element newTimes = xmlDoc.createElement("times");
		newTimes.appendChild(xmlDoc.createTextNode(timeFileName));
		newDataset.appendChild(newTimes);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(xmlDoc);
		StreamResult result = new StreamResult(new File(xmlPath));

		transformer.transform(source, result);

		
	}

}
