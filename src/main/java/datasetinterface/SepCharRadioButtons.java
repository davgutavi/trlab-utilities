package datasetinterface;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import utils.SystemUtilities;

@SuppressWarnings("serial")
public class SepCharRadioButtons extends JPanel {

	private JRadioButton semicolonRadioButton;
	private JRadioButton commaRadioButton;
	private JRadioButton tabRadioButton;
	private JRadioButton blankRadioButton;

	public SepCharRadioButtons() {
		
		super();

		Properties labels = SystemUtilities.getLabelProperties();

		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("121px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("26px"),}));

		JLabel lblType = new JLabel(labels.getProperty("dataset_sep_tag"));
		lblType.setFont(new Font("Arial", Font.PLAIN, 12));
		add(lblType, "1, 2, left, center");

		semicolonRadioButton = new JRadioButton(labels.getProperty("dataset_semicolon_tag"));
		semicolonRadioButton.setFont(new Font("Arial", Font.PLAIN, 12));
		add(semicolonRadioButton, "3, 2, left, center");
		semicolonRadioButton.setSelected(false);

		commaRadioButton = new JRadioButton(labels.getProperty("dataset_comma_tag"));
		commaRadioButton.setFont(new Font("Arial", Font.PLAIN, 12));
		add(commaRadioButton, "5, 2, left, center");

		tabRadioButton = new JRadioButton(labels.getProperty("dataset_tab_tag"));
		tabRadioButton.setFont(new Font("Arial", Font.PLAIN, 12));
		add(tabRadioButton, "7, 2, left, center");

		blankRadioButton = new JRadioButton(labels.getProperty("dataset_blank_tag"));
		blankRadioButton.setFont(new Font("Arial", Font.PLAIN, 12));
		add(blankRadioButton, "9, 2, left, center");

		semicolonRadioButton.setSelected(true);
		commaRadioButton.setSelected(false);
		tabRadioButton.setSelected(false);
		blankRadioButton.setSelected(false);

		semicolonRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				commaRadioButton.setSelected(false);
				tabRadioButton.setSelected(false);
				blankRadioButton.setSelected(false);

			}
		});

		commaRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				semicolonRadioButton.setSelected(false);
				tabRadioButton.setSelected(false);
				blankRadioButton.setSelected(false);

			}
		});
		
		tabRadioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				semicolonRadioButton.setSelected(false);
				commaRadioButton.setSelected(false);
				blankRadioButton.setSelected(false);
				
			}
		});
		
		blankRadioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				semicolonRadioButton.setSelected(false);
				commaRadioButton.setSelected(false);
				tabRadioButton.setSelected(false);
				
			}
		});

	}
	
	
	public String getSelectedSep (){
		
		String r = "";
		
		if (semicolonRadioButton.isSelected()){
			r = ";";
		}		
		else if (commaRadioButton.isSelected()){
			r = ",";
		}
		else if (tabRadioButton.isSelected()){
			r = "\t";
			
		}
		else if (blankRadioButton.isSelected()){
			r = " ";
		}
			
		return r;
	}

}
