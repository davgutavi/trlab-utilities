package utils;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * It is a class with a suite of methods to manipulate text {@link String}s.
 * 
 * @author David Gutiérrez Avilés
 *
 */
public class TextUtilities {
	
	
	private static final Logger LOG = LoggerFactory.getLogger(TextUtilities.class);
	
	public static String getFileName (String path){
		
		File aux = new File(path);
		
		return aux.getName();
		
	}
	
	public static String removeExtensionFromPath (String path){
		
		List<String> aux = splitElements(path, ".");
			
		return aux.get(0);
		
	}
	
	public static String getFileExtension (String path){
		
		String nameWith = getFileName(path);
		
		List<String> aux = splitElements(nameWith, ".");
		
		return aux.get(1);
	
	}
	
	
	public static String getFileNameWithoutExtension (String path){
		
		String nameWith = getFileName(path);
		
		List<String> aux = splitElements(nameWith, ".");
		
		return aux.get(0);
		
	}
	
	/**
	 * This method obtains the root path terminated by a slash character from a complete input path (path plus file name plus extension).
	 * 
	 * @param path Complete input path (path plus file name plus extension).
	 * @return Root path terminated by a slash character.
	 */
	public static String getRootPathWithSlash (String path){
		
		File aux = new File(path);
		
		return aux.getParentFile().getAbsolutePath()+SystemUtilities.getFileSep();
	
	}
	
	/**
	 * This method obtains the complete file path without extension from a complete input path (path plus file name plus extension).
	 * 
	 * @param path Complete input path (path plus file name plus extension).
	 * @return Complete path (path plus file name) without extension file.
	 */
	public static String removeExtensionFile (String path){
		
		String parentWithSlash = getRootPathWithSlash(path);
		
		String nameWithOut = getFileNameWithoutExtension(path);
		
		return parentWithSlash+nameWithOut;
		
	}
	
	
	/**
	 * This method appends the user's home path to the input path and builds a correct system complete path.
	 * 
	 * @param path Input path.
	 * @return A correct system complete path. 
	 */
	public static String getCorrectPathFromHome (String path){
		
		FileSystem fs = FileSystems.getDefault();
    	
		String homePath = System.getProperty("user.home");
		
    	String r = (fs.getPath(homePath,path)).toString();
    	
    	return r;
    	
	}
	
	/**
	 * This method appends the source input path to the rest of parameters and builds a correct system complete path.
	 * 
	 * @param source Source input path.
	 * @param tail Rest of {@link String} to be appended.
	 * @return A correct system complete path.
	 */
	public static String appendToPath (String source, String... tail){
		
		FileSystem fs = FileSystems.getDefault();
    	    	
    	String r = (fs.getPath(source,tail)).toString();
		
    	return r;
    	
	}
		
	/**
	 * This method splits the input {@link String} according to another separator {@link String}.
	 * 
	 * @param str Input {@link String} to be separated.
	 * @param sep Input separator {@link String}.
	 * @return A {@link List} of {@link String} with all separated elements excluding the separator one.
	 */
	public static List<String> splitElements(String str, String sep) {

		ArrayList<String> v = new ArrayList<String>();
		
		StringTokenizer st = new StringTokenizer(str, sep);
		
		while (st.hasMoreTokens())
			v.add(st.nextToken());
		
		v.trimToSize();
		
		return v;
	}
		
	/**
	 * This method splits the input {@link String} according to another separator {@link String}.
	 * 
	 * @param str Input {@link String} to be separated.
	 * @param sep Input separator {@link String}.
	 * @return A {@link List} of {@link String} with all separated elements including the separator one.
	 */
	public static List<String> splitElementsWithSep(String str, String sep) {
		
		ArrayList<String> v = new ArrayList<String>();
		
		StringTokenizer st = new StringTokenizer(str, sep, true);
		
		while (st.hasMoreTokens())
			v.add(st.nextToken());

		v.trimToSize();
		
		return v;
	}


	
	/**
	 * This method parse a separated {@link String} to a {@link List} of {@link Double} values.
	 * 
	 * @param s Input {@link String}.
	 * @param sep Separator element.
	 * @return {@link List} of {@link Double} values.
	 */
	public static List<Double> stringToDouble(String s, String sep) {
		
		ArrayList<Double> r = new ArrayList<Double>();
		
		List<String> c = splitElements(s, sep);
			
		for (String iv:c)
			r.add(new Double(iv));
		
		r.trimToSize();
		
		return r;
	}
	
	/**
	 * This method parse a separated {@link String} to a {@link List} of {@link Double} values reporting of NA values.
	 * 
	 * @param s Input {@link String}.
	 * @param sep Separator element.
	 * @param count NA value counter.
	 * @return {@link List} of {@link Double} values.
	 */
	public static List<Double> stringToDouble(String s, String sep,int count) {
		
		ArrayList<Double> r = new ArrayList<Double>();
		
		List<String> c = splitElements(s, sep);
		
		for (String iv:c){
			
			Double e;
			
			if (iv.equalsIgnoreCase("NA")){
				
				e = new Double (0);
				LOG.info("NA in gene "+count+" = 0.0");
			}
			else{
				e = new Double(iv);
			}
			
			r.add(e);
				
		}
		
		r.trimToSize();
		
		return r;
	}
	

	
	public static int getIndexFromGOfileName (String name){
		
		int r = -1;
		
		String snum = "";
		
		for (int i = 0; i<name.length();i++){
			
			char c = name.charAt(i);
			
			if (Character.isDigit(c))
				
				snum+=c;
						
		}
		
		r = Integer.parseInt(snum);
							
		return r;
		
	}
	
	
	public static DecimalFormat getDecimalFormat (char separator, String decimals){
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(separator);
		DecimalFormat format = new DecimalFormat(decimals);
		format.setDecimalFormatSymbols(symbols);
		return format;
	
	}
	
	public static DecimalFormat getDecimalFormat (char separator){
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(separator);
		DecimalFormat format = new DecimalFormat();
		format.setDecimalFormatSymbols(symbols);
		return format;
	
	}
	
	
	
	public static String vectorOfIntToString(int[] vector) {
		
		return vectorOfIntToString(vector,vector.length,",");
	}
	
	
	
	public static String vectorOfIntToString(int[] vector,int limit,String sep) {
		String r = "";
		int columns = vector.length;
		
		r = "";
		
		int i = 0;
		
		for (int j =0;j<columns;j++){
				
			if (i==(limit-1)){
				r+=vector[j]+"\n";
				i = 0;
			}
			else{
				r+=vector[j]+sep;
				i++;
			}
		}
		
		return r;
	}
	
	public static String vectorOfDoubleToString (double[] vector,int limit){
		return vectorOfDoubleToString (vector,limit,",", '.', "#.#");
	}
	
	public static String vectorOfDoubleToString (double[] vector,int limit,String sep, char decimalSeparator, String decimalPattern){
		
		String r = "";
		
		DecimalFormat format = getDecimalFormat(decimalSeparator,decimalPattern);
				
		int columns = vector.length;
		
//		r = columns+"\n";
		
		r = "";
		
		int i = 0;
		
		for (int j =0;j<columns;j++){
				
			if (i==(limit-1)){
				r+=format.format(vector[j])+"\n";
				i = 0;
			}
			else{
				r+=format.format(vector[j])+sep;
				i++;
			}
		}
			
		return r;		
		
	}
	
	
	public static String tableOfDoubleToString (double[][] table,String sep, char decimalSeparator, String decimalPattern){
		
		String r = "";
		
		DecimalFormat format = getDecimalFormat(decimalSeparator,decimalPattern);
		
		int rows = table.length;
		
		int columns = table[0].length;
		
		r = rows+" , "+columns+"\n";
		
		for (int i =0;i<rows;i++){
			
			for (int j =0;j<columns;j++){
				
				if (j==(columns-1))
					r+=format.format(table[i][j])+"\n";
				else
					r+=format.format(table[i][j])+sep;
				
			}
					
		}
		
		return r;		
		
	}
	
	public static String cubeOfDoubleToString(double[][][] cube,String sep,char decimalSeparator, String decimalPattern) {

		String r = "";
		
		int rsize = cube.length;
		int csize = cube[0].length;
		int dsize = cube[0][0].length;
		
		DecimalFormat format = getDecimalFormat(decimalSeparator,decimalPattern);
		
		String sufix = "";
		
		for (int l = 0; l < dsize; l++) {
									
			for (int i = 0; i < rsize; i++) {
		
				for (int j = 0; j < csize; j++) {
																	
					if(j==csize-1){
						
						sufix="\n";
						
					}
					else{
						
						sufix=sep;
					}
					
					r+=format.format(cube[i][j][l])+sufix;

				}
			
			}
			
			r+="\n";
		
		}
		
		return r;

	}
	
	public static String getTimeString (long t1, long t2){
		
		return getTimeString(t2-t1);
		
	}
	
	public static String getTimeString (long t){
		
		String r = "";
		
		double mill = (double) t;
		
		double seconds =  (mill / 1000.0) ;
						
		double hou = ((mill / 1000.0)/60.0)/60.0;	
		
		int ehou = (int) hou;
			
		double rhours = hou - ehou;
		
		double min = rhours*60.0;
				
		int emin = (int) min;
		
		double rmin = min - emin;
				
		double sec = rmin*60.0;
		
		int esec = (int) sec;
		
		double rsec = sec - esec;
						
		int mil = (int) Math.ceil((rsec)*1000) ;
		
		r = "Execution time: "+ehou+" hours, "+emin+" minutes and "+esec+" seconds with "+mil+" thousandths" 
				+ " ("+seconds+" seconds)";
			
		return r;
	}
	
}
	
