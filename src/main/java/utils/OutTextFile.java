package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * This class is a {@link PrintStream} wrapper to facilitate the I/O file processing.
 * 
 * @author David Gutiérrez Avilés
 *
 */
public class OutTextFile {

	/**
	 * The wrapped {@link PrintStream}.
	 */
	private PrintStream ps;

	
	/**
	 * It builds the file manager from standard output.
	 */
	public OutTextFile() {
		ps = System.out;
	}

	
	public OutTextFile(File file) throws FileNotFoundException {
		ps = new PrintStream(file);
	}
	
	
	
	/**
	 * It builds the file manager from an input path.
	 * 
	 * @param path Path to output file.
	 * @throws FileNotFoundException It throws when the input path is incorrect.
	 */
	public OutTextFile(String path) throws FileNotFoundException {
		ps = new PrintStream(new File(path));
	}

	/**
	 * Print a {@link String} into output file.
	 * 
	 * @param str Input {@link String}.
	 */
	public void print(String str) {
		ps.print(str);
	}
	
	/**
	 * Print a {@link String} plus a new line into output file.
	 * 
	 * @param str Input {@link String}.
	 */
	public void println(String str) {
		ps.println(str);
	}
	
	/**
	 * It close the file manager.
	 */
	public void close (){
		ps.close();
	}
}
