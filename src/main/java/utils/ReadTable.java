package utils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ReadTable {
	
	
	List<List<String>> values;
	
	public ReadTable(String path, String sep) throws IOException{
		InTextFile f = new InTextFile(path);
		buildValues(f,sep);
	}
	
	public ReadTable(File file, String sep) throws IOException{
		InTextFile f = new InTextFile(file);
		buildValues(f,sep);
	}
		
	private void buildValues (InTextFile f, String sep){
	
		values = new LinkedList<List<String>> ();
		
		for (String s:f){
			
			List<String> v = TextUtilities.splitElements(s, sep);
			
			values.add(v);		
			
		}
		
	}
	
	public List<List<String>> getTable(){
		
		return values;		
		
	}

}