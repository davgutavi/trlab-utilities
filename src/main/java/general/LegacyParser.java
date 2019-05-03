package general;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.InTextFile;
import utils.OutTextFile;
import utils.TextUtilities;

/**
 * Older static class that provide methods to parse old experiments files to loaded {@link Tricluster}s.
 * 
 * @author David Guriérrez Avilés
 *
 */
public class LegacyParser {
	
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(LegacyParser.class);
	
	/**
	 * It loads in form of {@link List} of {@link Tricluster} the stored information of input file path.
	 * 
	 * 
	 * @param path Path to experiment file (normalized format) with stored Triclusters.
	 * @return {@link List} of {@link Tricluster}.
	 */
	public static List<Tricluster> parse (String path){
		
		ArrayList<Tricluster> r = new ArrayList<Tricluster> ();
				
		try {
			
			InTextFile f = new InTextFile(path);
		
			Iterator<String> it = f.iterator();
			
			while (it.hasNext()){
					
				String next1 = it.next();
				
				List<String> vg = TextUtilities.splitElements(next1, ";");
												
				String next2 = it.next();
				
				List<String> vc = TextUtilities.splitElements(next2, ";");
											
				String next3 = it.next();
				
				List<String> vt = TextUtilities.splitElements(next3, ";");
								
				Tricluster n = new Tricluster(vg,vc,vt);
				
				r.add(n);			
								
				it.next();
				
//				LOG.debug(vg.toString());
//				LOG.debug(vc.toString());
//				LOG.debug(vt.toString());
//				LOG.debug(n.toString());
				
			}
			
			f.close();
					
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//LOG.debug(r.toString());
		
		r.trimToSize();
		
		return r;
		
	}
	
	public static List<Tricluster> parse (File expFile){
		
		return parse(expFile.getAbsolutePath());
		
	}
	
	

	public static void buildLegacy (File file, List<Tricluster> triclusters) throws FileNotFoundException{
		
		OutTextFile f = new OutTextFile(file);
		
		for(Tricluster tri:triclusters){
			
			String str = getNormalizedString(tri);
			f.print(str+"\n;;\n");
							
		}
				
		f.close();
		
	}
	
	private static String getNormalizedString (Tricluster tricluster){
		
		String res = "";
				
		List<Integer> genes   = tricluster.getGenes();
		List<Integer> samples = tricluster.getSamples();
		List<Integer> times   = tricluster.getTimes();
		
		String sg = "";
			
		int i = 1;
		
		for(Integer ig:genes){
			
			int g = ig.intValue();
			
			if (i<genes.size()){
				sg+=g+";";
			}
			else{
				sg+=g;
			}			
			i++;
		}
		
		String sc = "";
		
		i = 1;
		
		for(Integer ic:samples){
			
			int c = ic.intValue();
			
			if (i<samples.size()){
				sc+=c+";";
			}
			else{
				sc+=c;
			}
			
			i++;
		}
		
		String st = "";
				
		i = 1;
				
		for(Integer it:times){
					
			int t = it.intValue();
					
			if (i<times.size()){
				st+=t+";";
			}
			else{
				st+=t;
			}
					
			i++;
		}
				
		res+= sg+"\n"+sc+"\n"+st;
		
		return res;
		
	}
		
}
