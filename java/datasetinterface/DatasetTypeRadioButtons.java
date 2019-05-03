package datasetinterface;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import utils.SystemUtilities;

@SuppressWarnings("serial")
public class DatasetTypeRadioButtons extends JPanel {
	
	private JRadioButton bioRadioButton;
	private JRadioButton comRadioButton;
	private JTextField organismText;
	
	 
	
	public DatasetTypeRadioButtons (){
		super();		
		
		Properties labels = SystemUtilities.getLabelProperties();
		
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("62px"),
				ColumnSpec.decode("86px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("103px"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("26px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblType = new JLabel(labels.getProperty("dataset_type_tag"));
		lblType.setFont(new Font("Arial", Font.PLAIN, 12));
		add(lblType, "1, 2, left, center");
		
		
		bioRadioButton = new JRadioButton(labels.getProperty("dataset_biological_tag"));
		bioRadioButton.setFont(new Font("Arial", Font.PLAIN, 12));
		add(bioRadioButton, "2, 2, left, center");
			
		comRadioButton = new JRadioButton(labels.getProperty("dataset_common_tag"));
		comRadioButton.setFont(new Font("Arial", Font.PLAIN, 12));
		add(comRadioButton, "4, 2, left, center");
		
		JLabel lblOrganism = new JLabel(labels.getProperty("dataset_organism_tag"));
		lblOrganism.setFont(new Font("Arial", Font.PLAIN, 12));
		add(lblOrganism, "1, 4, left, center");
					
		organismText = new JTextField();
		organismText.setFont(new Font("Arial", Font.PLAIN, 12));
		organismText.setColumns(15);
		add(organismText, "2, 4, 3, 1, left, top");
					
		bioRadioButton.setSelected(true);
		comRadioButton.setSelected(false);
		lblOrganism.setVisible(true);
		organismText.setVisible(true);
		organismText.setText("");
		
		bioRadioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				comRadioButton.setSelected(false);
				lblOrganism.setVisible(true);
				organismText.setVisible(true);
				
			}
		});
		
		comRadioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				bioRadioButton.setSelected(false);
				lblOrganism.setVisible(false);
				organismText.setVisible(false);
				
			}
		});

	}
	
	public char getTypeSelected (){
		
		char r = ' ';
		
		if (bioRadioButton.isSelected()){
			r = 'b';
		}
		else if (comRadioButton.isSelected()){
			r = 'e';
		}
				
		return r;
	}
	
	public JTextField getOrganismText (){
		return organismText;
	}
	
	
	public String getStringReport (){
		
		String r = "";
		
		r = "Type = "+getTypeSelected();
		
		if (!(organismText.getText()).equalsIgnoreCase(""))
			r+=" , Organism = "+organismText.getText();
		
		return r;
	}
	
	
	
}
