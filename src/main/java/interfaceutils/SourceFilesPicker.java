package interfaceutils;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SourceFilesPicker extends JPanel {

	
	private int timePoints;
	
	/**
	 * Create the panel.
	 */
	public SourceFilesPicker(int timePoints ) {

		this.timePoints = timePoints;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		for (int i = 0; i<this.timePoints; i++){
			
			PathPicker aux = new PathPicker("time "+i);
			
			add(aux);
			
		}	
		
	}
	
	public String getSelectedPath (int i){
		
		String r = "";
				
		Component[] comp = getComponents();
		
		r = ((PathPicker)comp[i]).getSelectedPath();		
		
		return r;
	}
	
	
	public String[] getAllSelectedPaths (){
		
		String[] r = new String[timePoints];
		
		Component[] comp = getComponents();
		
		for (int i=0;i<comp.length;i++){
			
			PathPicker aux = (PathPicker)comp[i];
			
			r[i] = aux.getSelectedPath();
			
		}		
		
		return r;
	}
	
	public String getReportString (){
		
		String r = "";
		
		Component[] comp = getComponents();
		
		for (int i=0;i<comp.length;i++){
			
			PathPicker aux = (PathPicker)comp[i];
			
			if (i==comp.length-1){
				r+= "T"+i+" path = "+aux.getSelectedPath();
			}
			else{
				r+= "T"+i+" path = "+aux.getSelectedPath()+"\n";
			}
							
		}		
		
		
		return r;
	}
	

}
