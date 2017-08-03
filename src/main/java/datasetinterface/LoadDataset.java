package datasetinterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;


import general.DatasetModelServer;
import interfaceutils.PathPicker;
import interfaceutils.SourceFilesPicker;
import utils.ReadTable;
import utils.SystemUtilities;
import utils.TextUtilities;

@SuppressWarnings("serial")
public class LoadDataset extends JDialog {

	private static final Logger LOG = LoggerFactory.getLogger(LoadDataset.class);

	private final JScrollPane scrollPanel;
	private final JPanel contentPanel;
	private int currentCard;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private int timeSize;
	private JTextField datasetNameError;
	private JTextPane summary;

	// First card values
	private JTextField nameValue;
	private JTextField descriptionValue;
	private JSpinner timePointsValue;
	private DatasetTypeRadioButtons datasetType;

	// Second card values
	private PathPicker genesFile;
	private PathPicker samplesFile;
	private PathPicker timesFile;
	private SepCharRadioButtons sepRadio;
	private SourceFilesPicker sourceFiles;
	
	// Third card values
	private int geneSize;
	private int sampleSize;
	private JSpinner maxG_v;
	private JSpinner maxC_v;
	private JSpinner maxT_v;
	private JSpinner minG_v;
	private JSpinner minC_v;
	private JSpinner minT_v;
		
	private String id;
	
	/**
	 * Create the dialog.
	 */
	public LoadDataset() {

		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 672, 493);

		contentPanel = new JPanel();
		contentPanel.setLayout(new CardLayout(0, 0));
		scrollPanel = new JScrollPane(contentPanel);

		getContentPane().add(scrollPanel, BorderLayout.CENTER);
		getContentPane().add(scrollPanel);

		initFirstCard();
		initSecondCard();
		initThirdCard();
		initFourthCard();
		initButtonPanel();

		CardLayout c = (CardLayout) contentPanel.getLayout();

		c.first(contentPanel);
		
		
		
		
		

	}

	private void initButtonPanel() {

		CardLayout c = (CardLayout) contentPanel.getLayout();

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setFont(new Font("Arial", Font.PLAIN, 12));
		buttonPane.add(btnPrevious);

		JButton nextButton = new JButton("Next");
		nextButton.setFont(new Font("Arial", Font.PLAIN, 12));
		buttonPane.add(nextButton);
		getRootPane().setDefaultButton(nextButton);
		
		JButton btnLoad = new JButton("Load");
		buttonPane.add(btnLoad);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Arial", Font.PLAIN, 12));
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		currentCard = 1;
		nextButton.setVisible(true);
		btnPrevious.setVisible(false);
		btnLoad.setVisible(false);
		LOG.debug("INIT: currentCard = " + currentCard + " Config = card 1");

		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

	
				
				
				
				if (currentCard == 1) {

					datasetNameError.setText("");

					DatasetModelServer dataserver = DatasetModelServer.getInstance();

					boolean repeated = dataserver.isRepeated(nameValue.getText());
					
					boolean blank = (nameValue.getText()).equalsIgnoreCase("");

					if (!repeated&&!blank) {

						panel_2.remove(panel_2.getComponentCount() - 1);
						timeSize = ((Integer) timePointsValue.getValue()).intValue();
						sourceFiles = new SourceFilesPicker(timeSize);
						GridBagConstraints gbc_sourceFiles = new GridBagConstraints();
						gbc_sourceFiles.gridwidth = 2;
						gbc_sourceFiles.anchor = GridBagConstraints.WEST;
						gbc_sourceFiles.insets = new Insets(0, 0, 5, 5);
						gbc_sourceFiles.gridx = 0;
						gbc_sourceFiles.gridy = 4;
						panel_2.add(sourceFiles, gbc_sourceFiles);

						c.next(contentPanel);

						currentCard++;
						nextButton.setVisible(true);
						btnPrevious.setVisible(true);
						btnLoad.setVisible(false);
						LOG.debug("currentCard = " + currentCard + " Config = card 2");

					} else {

						datasetNameError.setText(SystemUtilities.getLabelProperty("dataset_name_error_text"));

					}

				}

				else {

					c.next(contentPanel);
					currentCard++;
					
					
					
					if (currentCard==3){
						checkDatasetSize();
						nextButton.setVisible(true);
						btnPrevious.setVisible(true);
						btnLoad.setVisible(false);
						LOG.debug("currentCard = " + currentCard + " Config = card 3");
					}	
					else if (currentCard==4){
						printSummary();
						nextButton.setVisible(false);
						btnPrevious.setVisible(true);
						btnLoad.setVisible(true);
						LOG.debug("currentCard = " + currentCard + " Config = card 4");
					}

					

				}

			}

			

		});

		btnPrevious.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				c.previous(contentPanel);
				currentCard--;

				String aux = "currentCard = " + currentCard;

				// POST
				if (currentCard == 1) {
					nextButton.setVisible(true);
					btnPrevious.setVisible(false);
					btnLoad.setVisible(false);
					aux += " Config = card 1";
				} else if (currentCard == 2) {
					nextButton.setVisible(true);
					btnPrevious.setVisible(true);
					btnLoad.setVisible(false);
					aux += " Config = card 2";
				} else if (currentCard == 3) {
//					nextButton.setEnabled(true);
					nextButton.setVisible(true);
					btnPrevious.setVisible(true);
					btnLoad.setVisible(false);
					aux += " Config = card 3";
				} else if (currentCard == 4) {
					nextButton.setVisible(false);
					btnPrevious.setVisible(true);
					btnLoad.setVisible(true);
					aux += " Config = card 4";
				}

				LOG.debug(aux);

			}
		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		
		btnLoad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				loadDatasetResources ();
				
			}

			
		});

	}
	
	private void initFourthCard (){
		panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_4.setForeground(Color.BLUE);
		contentPanel.add(panel_4, "name_1465892505241567000");
		
		summary = new JTextPane();
		summary.setBackground(SystemColor.window);
		summary.setForeground(Color.BLUE);
		summary.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		summary.setEditable(false);
		panel_4.add(summary);
	}

	private void initFirstCard() {

		Properties gui = SystemUtilities.getGuiProperties();

		Properties labels = SystemUtilities.getLabelProperties();

		panel_1 = new JPanel();
		contentPanel.add(panel_1, "name_1465550427543843000");
		panel_1.setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("191px"), FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(24dlu;default)"),
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));

		JLabel lblCard = new JLabel(labels.getProperty("dataset_name_tag"));
		lblCard.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_1.add(lblCard, "1, 3, right, center");

		nameValue = new JTextField();
		nameValue.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_1.add(nameValue, "4, 3, fill, default");
		nameValue.setColumns(10);

		datasetNameError = new JTextField();
		datasetNameError.setBackground(SystemColor.window);
		datasetNameError.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		datasetNameError.setForeground(Color.RED);
		panel_1.add(datasetNameError, "8, 3, fill, default");
		datasetNameError.setColumns(10);
		datasetNameError.setBorder(null);
		datasetNameError.setEditable(false);

		JLabel lblDescription = new JLabel(labels.getProperty("dataset_description_tag"));
		lblDescription.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_1.add(lblDescription, "1, 5, right, center");

		descriptionValue = new JTextField();
		descriptionValue.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_1.add(descriptionValue, "4, 5, fill, default");
		descriptionValue.setColumns(10);

		SpinnerModel times_model = new SpinnerNumberModel(new Integer(gui.getProperty("dataset_times_def")),
				new Integer(gui.getProperty("dataset_times_min")), new Integer(gui.getProperty("dataset_times_max")),
				new Integer(gui.getProperty("dataset_times_step")));

		JLabel lblNumberOfTime = new JLabel(labels.getProperty("dataset_time_points_tag"));
		lblNumberOfTime.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_1.add(lblNumberOfTime, "1, 7, right, center");

		timePointsValue = new JSpinner(times_model);
		timePointsValue.setFont(new Font("Arial", Font.PLAIN, 12));

		panel_1.add(timePointsValue, "4, 7, default, center");

		datasetType = new DatasetTypeRadioButtons();

		panel_1.add(datasetType, "4, 9, fill, fill");

	}

	private void initSecondCard() {

		panel_2 = new JPanel();
		contentPanel.add(panel_2, "name_1465550427551281000");
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 100, 210, 67, 100, 0 };
		gbl_panel.rowHeights = new int[] { 30, 30, 30, 30, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel);

		genesFile = new PathPicker("Genes file");
		GridBagConstraints gbc_genesFile = new GridBagConstraints();
		gbc_genesFile.anchor = GridBagConstraints.EAST;
		gbc_genesFile.insets = new Insets(0, 0, 5, 5);
		gbc_genesFile.gridx = 0;
		gbc_genesFile.gridy = 0;
		panel_2.add(genesFile, gbc_genesFile);

		samplesFile = new PathPicker("Samples file");
		GridBagConstraints gbc_samplesFile = new GridBagConstraints();
		gbc_samplesFile.anchor = GridBagConstraints.EAST;
		gbc_samplesFile.insets = new Insets(0, 0, 5, 5);
		gbc_samplesFile.gridx = 0;
		gbc_samplesFile.gridy = 1;
		panel_2.add(samplesFile, gbc_samplesFile);

		timesFile = new PathPicker("Times file");
		GridBagConstraints gbc_timesFile = new GridBagConstraints();
		gbc_timesFile.anchor = GridBagConstraints.EAST;
		gbc_timesFile.insets = new Insets(0, 0, 5, 5);
		gbc_timesFile.gridx = 0;
		gbc_timesFile.gridy = 2;
		panel_2.add(timesFile, gbc_timesFile);

		sepRadio = new SepCharRadioButtons();
		GridBagConstraints gbc_sepRadio = new GridBagConstraints();
		gbc_sepRadio.gridwidth = 2;
		gbc_sepRadio.anchor = GridBagConstraints.WEST;
		gbc_sepRadio.insets = new Insets(0, 0, 5, 5);
		gbc_sepRadio.gridx = 0;
		gbc_sepRadio.gridy = 3;
		panel_2.add(sepRadio, gbc_sepRadio);

		timeSize = ((Integer) timePointsValue.getValue()).intValue();
		sourceFiles = new SourceFilesPicker(timeSize);
		GridBagConstraints gbc_sourceFiles = new GridBagConstraints();
		gbc_sourceFiles.gridwidth = 2;
		gbc_sourceFiles.anchor = GridBagConstraints.WEST;
		gbc_sourceFiles.insets = new Insets(0, 0, 0, 5);
		gbc_sourceFiles.gridx = 0;
		gbc_sourceFiles.gridy = 4;
		panel_2.add(sourceFiles, gbc_sourceFiles);

	}

	private void initThirdCard() {

		panel_3 = new JPanel();
		contentPanel.add(panel_3, "name_1465550674496678000");
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 35, 85, 0, 75, 0 };
		gbl_panel_3.rowHeights = new int[] { 14, 14, 14, 14, 14, 14, 0 };
		gbl_panel_3.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_3.setLayout(gbl_panel_3);
						
								JLabel lblMinG = new JLabel("min G");
								lblMinG.setFont(new Font("Arial", Font.PLAIN, 12));
								GridBagConstraints gbc_lblMinG = new GridBagConstraints();
								gbc_lblMinG.insets = new Insets(0, 0, 5, 5);
								gbc_lblMinG.gridx = 0;
								gbc_lblMinG.gridy = 0;
								panel_3.add(lblMinG, gbc_lblMinG);
						
								minG_v = new JSpinner();
								minG_v.setFont(new Font("Arial", Font.PLAIN, 12));
								GridBagConstraints gbc_spinner_3 = new GridBagConstraints();
								gbc_spinner_3.fill = GridBagConstraints.HORIZONTAL;
								gbc_spinner_3.insets = new Insets(0, 0, 5, 5);
								gbc_spinner_3.gridx = 1;
								gbc_spinner_3.gridy = 0;
								panel_3.add(minG_v, gbc_spinner_3);
				
						JLabel lblCard_1 = new JLabel("max G");
						lblCard_1.setFont(new Font("Arial", Font.PLAIN, 12));
						GridBagConstraints gbc_lblCard_1 = new GridBagConstraints();
						gbc_lblCard_1.insets = new Insets(0, 0, 5, 5);
						gbc_lblCard_1.gridx = 2;
						gbc_lblCard_1.gridy = 0;
						panel_3.add(lblCard_1, gbc_lblCard_1);
		
				maxG_v = new JSpinner();
				maxG_v.setFont(new Font("Arial", Font.PLAIN, 12));
				GridBagConstraints gbc_spinner = new GridBagConstraints();
				gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
				gbc_spinner.insets = new Insets(0, 0, 5, 0);
				gbc_spinner.gridx = 3;
				gbc_spinner.gridy = 0;
				panel_3.add(maxG_v, gbc_spinner);
						
								JLabel lblMinC = new JLabel("min C");
								lblMinC.setFont(new Font("Arial", Font.PLAIN, 12));
								GridBagConstraints gbc_lblMinC = new GridBagConstraints();
								gbc_lblMinC.insets = new Insets(0, 0, 5, 5);
								gbc_lblMinC.gridx = 0;
								gbc_lblMinC.gridy = 1;
								panel_3.add(lblMinC, gbc_lblMinC);
						
								minC_v = new JSpinner();
								minC_v.setFont(new Font("Arial", Font.PLAIN, 12));
								GridBagConstraints gbc_spinner_4 = new GridBagConstraints();
								gbc_spinner_4.fill = GridBagConstraints.HORIZONTAL;
								gbc_spinner_4.insets = new Insets(0, 0, 5, 5);
								gbc_spinner_4.gridx = 1;
								gbc_spinner_4.gridy = 1;
								panel_3.add(minC_v, gbc_spinner_4);
				
						JLabel lblMaxC = new JLabel("max C");
						lblMaxC.setFont(new Font("Arial", Font.PLAIN, 12));
						GridBagConstraints gbc_lblMaxC = new GridBagConstraints();
						gbc_lblMaxC.insets = new Insets(0, 0, 5, 5);
						gbc_lblMaxC.gridx = 2;
						gbc_lblMaxC.gridy = 1;
						panel_3.add(lblMaxC, gbc_lblMaxC);
		
				maxC_v = new JSpinner();
				maxC_v.setFont(new Font("Arial", Font.PLAIN, 12));
				GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
				gbc_spinner_1.fill = GridBagConstraints.HORIZONTAL;
				gbc_spinner_1.insets = new Insets(0, 0, 5, 0);
				gbc_spinner_1.gridx = 3;
				gbc_spinner_1.gridy = 1;
				panel_3.add(maxC_v, gbc_spinner_1);
						
								JLabel lblMinT = new JLabel("min T");
								lblMinT.setFont(new Font("Arial", Font.PLAIN, 12));
								GridBagConstraints gbc_lblMinT = new GridBagConstraints();
								gbc_lblMinT.insets = new Insets(0, 0, 5, 5);
								gbc_lblMinT.gridx = 0;
								gbc_lblMinT.gridy = 2;
								panel_3.add(lblMinT, gbc_lblMinT);
						
								minT_v = new JSpinner();
								minT_v.setFont(new Font("Arial", Font.PLAIN, 12));
								GridBagConstraints gbc_spinner_5 = new GridBagConstraints();
								gbc_spinner_5.insets = new Insets(0, 0, 5, 5);
								gbc_spinner_5.fill = GridBagConstraints.HORIZONTAL;
								gbc_spinner_5.gridx = 1;
								gbc_spinner_5.gridy = 2;
								panel_3.add(minT_v, gbc_spinner_5);
				
						JLabel lblMaxT = new JLabel("max T");
						lblMaxT.setFont(new Font("Arial", Font.PLAIN, 12));
						GridBagConstraints gbc_lblMaxT = new GridBagConstraints();
						gbc_lblMaxT.insets = new Insets(0, 0, 5, 5);
						gbc_lblMaxT.gridx = 2;
						gbc_lblMaxT.gridy = 2;
						panel_3.add(lblMaxT, gbc_lblMaxT);
		
				maxT_v = new JSpinner();
				maxT_v.setFont(new Font("Arial", Font.PLAIN, 12));
				GridBagConstraints gbc_spinner_2 = new GridBagConstraints();
				gbc_spinner_2.fill = GridBagConstraints.HORIZONTAL;
				gbc_spinner_2.insets = new Insets(0, 0, 5, 0);
				gbc_spinner_2.gridx = 3;
				gbc_spinner_2.gridy = 2;
				panel_3.add(maxT_v, gbc_spinner_2);

	}

	private void checkDatasetSize() {

		String path0 = sourceFiles.getSelectedPath(0);
		
		String[] paths = sourceFiles.getAllSelectedPaths();
		
		for (int i=0;i<paths.length;i++){
			LOG.debug("path "+i+" = "+paths[i]);
		}
		

		try {
			ReadTable path0_table = new ReadTable(path0, sepRadio.getSelectedSep());

			List<List<String>> values = path0_table.getTable();

			geneSize = values.size();
			sampleSize = (values.get(0)).size();
			
			LOG.debug("sizes = [ "+geneSize+" , "+sampleSize+" , "+timeSize+" ]");
			
			if (geneSize>1000){
				maxG_v.setValue(new Integer(500));
			}
			else {
				maxG_v.setValue(new Integer(geneSize));
			}
			
			
			maxC_v.setValue(new Integer(sampleSize));
			maxT_v.setValue(new Integer(timeSize));
			
			
			if (geneSize<20){
				minG_v.setValue(new Integer(3));
			}
			else {
				minG_v.setValue(new Integer(20));
			}
			
			minC_v.setValue(new Integer(2));
			minT_v.setValue(new Integer(3));
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void printSummary() {
		
		id =  (DatasetModelServer.getInstance()).getAnewID();
		
		String sum = 
				"ID = "+id+"\n"+
				"Name = "+nameValue.getText()+"\n"+
				"Description = "+descriptionValue.getText()+"\n"+
				datasetType.getStringReport ()+"\n"+
				"Gene size = "+geneSize+"\n"+
				"Sample size = "+sampleSize+"\n"+
				"Time size = "+timeSize+"\n"+
				"Genes path = "+genesFile.getSelectedPath()+"\n"+
				"Samples path = "+samplesFile.getSelectedPath()+"\n"+
				"Times path = "+timesFile.getSelectedPath()+"\n"+
				"Separator = "+sepRadio.getSelectedSep()+"\n"+
				 sourceFiles.getReportString()+"\n"+
				"Min G = "+minG_v.getValue()+" , "+"Max G = "+maxG_v.getValue()+"\n"+
				"Min C = "+minC_v.getValue()+" , "+"Max C = "+maxC_v.getValue()+"\n"+
				"Min T = "+minT_v.getValue()+" , "+"Max T = "+maxT_v.getValue();
			
		
		
		summary.setText(sum);	
		
	}

	private void loadDatasetResources() {
		
		DatasetModelServer server = DatasetModelServer.getInstance();
		
		String[] sourcePaths = sourceFiles.getAllSelectedPaths();
		
		String[] sourceNames = new String [sourcePaths.length];
		
		for (int i = 0; i<sourcePaths.length; i++)
			sourceNames[i] = TextUtilities.getFileName(sourcePaths[i]);
			
		String genePath = genesFile.getSelectedPath();
		String geneName = TextUtilities.getFileName(genePath);
		
		String samplePath = samplesFile.getSelectedPath();
		String sampleName = TextUtilities.getFileName(samplePath);
		
		String timePath = timesFile.getSelectedPath();
		String timeName = TextUtilities.getFileName(timePath);
		
		//UPDATE XML
		
		try {
			server.writeNewDataset(id, nameValue.getText(), datasetType.getTypeSelected(), geneSize, sampleSize, timeSize, 
					((Integer)minG_v.getValue()).intValue(), ((Integer)maxG_v.getValue()).intValue(), ((Integer)minC_v.getValue()).intValue(), 
					((Integer)maxC_v.getValue()).intValue(), ((Integer)minT_v.getValue()).intValue(), ((Integer)maxT_v.getValue()).intValue(), 
					(datasetType.getOrganismText()).getText(), descriptionValue.getText(), sepRadio.getSelectedSep(), 
					sourceNames, geneName, sampleName, timeName);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		LOG.debug("New dataset written in Xml");
			
		//COPY FILES
		
		// build directory
		
		
		
		String dirP = TextUtilities.appendToPath(SystemUtilities.getResourcesFolderPath(), id);
		
		Path dirPath = Paths.get(dirP);
		
		LOG.debug("Build directory");
		LOG.debug(dirP);
		try {
			Files.createDirectory(dirPath);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		File dataDir = new File(SystemUtilities.getSystemProperty("resources_root")+id);
//		dataDir.mkdir();
			
		
		//copy data
			
		LOG.debug("Copying data files");
		
		for (int i = 0; i<sourcePaths.length; i++){
			
			Path source = Paths.get(sourcePaths[i]);
			
			Path target = Paths.get(dirP, sourceNames[i]);
//			Path target = Paths.get(dataDir.getAbsolutePath(), sourceNames[i]);
			
			try {
				Files.copy(source, target);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		LOG.debug("data copied");
		
		//copy genes, samples and times
			
		Path sourceGenes = Paths.get(genePath);
		Path targetGenes = Paths.get(dirP, geneName);
//		Path targetGenes = Paths.get(dataDir.getAbsolutePath(), geneName);
		Path sourceSamples = Paths.get(samplePath);
		Path targetSamples = Paths.get(dirP, sampleName);
//		Path targetSamples = Paths.get(dataDir.getAbsolutePath(), sampleName);
		Path sourceTimes = Paths.get(timePath);
		Path targetTimes = Paths.get(dirP, timeName);
//		Path targetTimes = Paths.get(dataDir.getAbsolutePath(), timeName);
		
		
		LOG.debug("Copying gene file");
		try {
			Files.copy(sourceGenes, targetGenes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.debug("Copied");
		
		LOG.debug("Copying sample file");
		try {
			Files.copy(sourceSamples, targetSamples);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.debug("Copied");
		
		LOG.debug("Copying time file");
		try {
			Files.copy(sourceTimes, targetTimes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.debug("Copied");
		
			
		LOG.debug("Dataset loaded!!");
		
		
		JOptionPane.showMessageDialog(null, "The dataset has been loaded!!","",JOptionPane.PLAIN_MESSAGE);
		
		
		(DatasetModelServer.getInstance()).updateDatasetServer();
		
		dispose();
		
		
		
	}
	
	
}
