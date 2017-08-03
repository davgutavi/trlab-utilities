package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * This class is a {@link BufferedReader} wrapper to facilitate the I/O file processing.
 * 
 * @author David Gutiérrez Avilés
 *
 */
public class InSerialTextFile implements Iterable<String> {

	/**
	 * The wrapped {@link BufferedReader}.
	 */
	private BufferedReader bf;
		
	/**
	 * A {@link String} for element separation. The default value is the newline character.
	 */
	private String sep = "\n";
	
	/**
	 * It builds the file manager from an input path.
	 * 
	 * @param path Path to input file.
	 * @throws IOException It throws when the input path is incorrect.
	 */
	public InSerialTextFile(String path) throws IOException {
		bf = new BufferedReader(new FileReader(path));
	}

	/**
	 * It builds the file manager from an input path with the option of set the separation element.
	 * 
	 * @param path Path to input file.
	 * @param sep Separator element.
	 * @throws IOException It throws when the input path is incorrect.
	 */
	public InSerialTextFile(String path, String sep) throws IOException {
		bf = new BufferedReader(new FileReader(path));
		this.sep = sep;
	}
		
	/**
	 * It close the file manager.
	 * 
	 * @throws IOException It throws when the close operation fails.
	 */
	public void close () throws IOException{
		bf.close();
	}

	public Iterator<String> iterator() {
		return new InSerialTextFileIterator(bf, sep);
	}
		
	/**
	 * Private class that provide iterator methods.
	 * 
	 * @author David Guriérrez Avilés
	 *
	 */
	private class InSerialTextFileIterator implements Iterator<String> {
		
		/**
		 * The wrapped {@link BufferedReader}.
		 */
		private BufferedReader bf;
		
		/**
		 * {@link String} {@link Iterator} to support the file iteration.
		 */
		private Iterator<String> wordIt;
		
		/**
		 * A {@link String} that represents each line of the file.
		 */
		private String line;
		
		/**
		 * A {@link String} for element separation.
		 */
		private String seps;

		/**
		 * It builds a new file iterator.
		 * 
		 * @param bf The wrapped {@link BufferedReader}.
		 * @param seps Separator element.
		 */
		public InSerialTextFileIterator(BufferedReader bf, String seps) {
			this.seps = seps;
			this.bf = bf;
			try {
				line = bf.readLine();
			} catch (IOException e) {
				;
			}
		}
		public boolean hasNext() {
			return line != null;
		}

		public String next() {
			String word = wordIt.next();

			if (!wordIt.hasNext()) {
				try {
					line = bf.readLine();
					if (line != null)
						wordIt = TextUtilities.splitElements(line,seps).iterator();
				} catch (IOException e) {
					;
				}
			}

			return word;
		}

		public void remove() {
		}
	}

}