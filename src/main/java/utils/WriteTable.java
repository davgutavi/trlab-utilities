package utils;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class WriteTable {
	
	private double[][] table;
	
	public WriteTable(double [][] table) throws IOException{
		this.table  = table;
	}
	
	public void write (String path, DecimalFormat fr) throws IOException{
		write (new File(path), ";", fr);
	}
	
	public void write (File loc, DecimalFormat fr) throws IOException{
		write (loc, ";", fr);
	}
	
	public void write (String path) throws IOException{
		write (new File(path), ";", null);
	}
	
	public void write (File loc) throws IOException{
		write (loc, ";", null);
	}
	
	public void write (String path, String sep, DecimalFormat fr) throws IOException{
		write (new File(path), ";", null);
	}
		
	public void write (File loc, String sep, DecimalFormat fr) throws IOException{
	
		int rowSize    = table.length;
		int columnSize = table[0].length;
		
		OutTextFile f = new OutTextFile(loc);
	
		for(int i=0;i<rowSize;i++){
			
			for(int j=0;j<columnSize;j++){
				
				double value = table[i][j];
				
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
