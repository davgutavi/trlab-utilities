package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

public class WriteCube {
	
	private double [][][] cube;	
	
	
	public WriteCube(double [][][] cube) throws IOException{
		this.cube = cube;
	}

	public void write (String path) throws IOException{
		
		write (path,";",null);
			
	}
	
	
	public void write (String path, String sep, DecimalFormat fr) throws IOException{
				
		int depthSize  = cube[0][0].length;
		
		for (int k=0;k<depthSize;k++){
		
			writeTable(k,path,sep,fr);
					
		}
			
	}

	private void writeTable(int k, String path, String sep, DecimalFormat fr) throws FileNotFoundException {
		
		OutTextFile f = new OutTextFile(path+"_"+k+".csv");
		
		int rowSize    = cube.length;
		int columnSize = cube[0].length;
		
		for(int i=0;i<rowSize;i++){
			
			for(int j=0;j<columnSize;j++){
				
				double value = cube[i][j][k];
				
				String str = "";
				
				if (fr!=null)
					str = fr.format(value);
				else{
					str = ""+value;
				}
								
				if (j==columnSize-1){
					f.print(str+"\n");
				}
				else{
					f.print(str+sep);
				}
								
			}
					
		}
		
		f.close();
		
	}
	
	
	
	
}
