package utils;

import general.Tricluster;
import java.text.Format;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormatUtils {
	
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(FormatUtils.class);
	
	public static String[] getExcelStrings(List<Tricluster> solutions,double[][][] dataset)  {
		return getExcelStings(solutions,dataset, TextUtilities.getDecimalFormat('.',"0.####"));
	}
	

    public static String[] getExcelStings(List<Tricluster> solutions,double[][][] dataset, Format format)  {
		
		String[] f = new String[solutions.size()];
	 
		int i = 0;
	
		Iterator<Tricluster> itr = solutions.iterator();
		
		while (itr.hasNext()) {
			
			Tricluster sol = itr.next();
			
			String str = "";

			List<Integer> times = sol.getTimes();
			List<Integer> genes = sol.getGenes();
			List<Integer> samples = sol.getSamples();

			int nt = times.size();
			int ng = genes.size();
			int nc = samples.size();
			
			str+=";";

			Iterator<Integer> ittimes = times.iterator();
			while (ittimes.hasNext()) {
				int t = (ittimes.next()).intValue();
				for (int j = 0; j < nc; j++) {
					if (j == nc - 1) {
						str+="t" + t + ";;";
					} else {
						str+="t" + t + ";";
					}
				}
			}
			
			str+="\n";
			
			str+=";";
			
			Iterator<Integer> ittimes1 = times.iterator();
			while (ittimes1.hasNext()) {
				ittimes1.next();
				for (int j = 0; j < nc; j++) {
					int c = (samples.get(j)).intValue();
					if (j == nc - 1) {
						str+="c" + c + ";;";
					} else {
						str+="c" + c + ";";
					}
				}
			}

		
			str+="\n";

			// CELDAS
			for (int g = 0; g < ng; g++) {// bloque genes
				for (int t = 0; t < nt; t++) {// bloque tiempos
					for (int c = -1; c < nc; c++) {// bloque condiciones
						int cg = (genes.get(g)).intValue();
						if (c != -1) {
							boolean cond1 = c == nc - 1;
							boolean cond2 = t == nt - 1;
							int ct = (times.get(t)).intValue();
							int cc = (samples.get(c)).intValue();
							
							String cad = format.format(dataset[cg][cc][ct]);
							
							
							if (cond1 == false && cond2 == false) {
								cad += ";";
							} else if (cond1 == true && cond2 == true) {
								cad += "\n";
							}
							else {
								cad += ";";
							}
							str+=cad;
						} else {
							str+="g" + cg + ";";
						}
					}// bloque condiciones
				}// bloque tiempos
			}// bloque genes
				// CELDAS

			
			str+="\n\n\n\n";

			// CABECERA 1
			str+=";";

			Iterator<Integer> itsamples = samples.iterator();
			while (itsamples.hasNext()) {
				int c = (itsamples.next()).intValue();
				for (int j = 0; j < nt; j++) {
					if (j == nt - 1) {
						str+="c" + c + ";;";
					} else {
						str+="c" + c + ";";
					}
				}
			}
			// CABECERA 1

			str+="\n";

			// CABECERA 2

			str+=";";
			Iterator<Integer> itsamples1 = samples.iterator();
			while (itsamples1.hasNext()) {
				itsamples1.next();
				for (int j = 0; j < nt; j++) {
					int t = (times.get(j)).intValue();
					if (j == nt - 1) {
						str+="t" + t + ";;";
					} else {
						str+="t" + t + ";";
					}
				}
			}

			// CABECERA 2

			str+="\n";

			// CELDAS
			for (int g = 0; g < ng; g++) {// bloque genes

				for (int c = 0; c < nc; c++) {// bloque condiciones

					for (int t = -1; t < nt; t++) {// bloque tiempos

						int cg = (genes.get(g)).intValue();

						if (t != -1) {
							boolean cond1 = t == nt - 1;
							boolean cond2 = c == nc - 1;
							int ct = (times.get(t)).intValue();
							int cc = (samples.get(c)).intValue();
														
							String cad = format.format(dataset[cg][cc][ct]);
														
							if (cond1 == false && cond2 == false) {
								cad += ";";
							} else if (cond1 == true && cond2 == true) {
								cad += "\n";
							}// es el ultimo tiempo y la ultima condicion
							else {
								cad += ";";
							}
							str+=cad;
						}

						else {
							str+="g" + cg + ";";
						}
					}
				}
			}
				
			f[i] = str;
			
			i++;
		}
		
		
		return f;

	}
    
    public static String[] getRstrings(List<Tricluster> solutions,double[][][] dataset)  {
    	return getRstrings(solutions,dataset, TextUtilities.getDecimalFormat('.',"0.####"));
    	
    }
	
	
	public static String[] getRstrings(List<Tricluster> solutions,double[][][] dataset, Format format)  {

		
		String[] f = new String[solutions.size()];
	 
		int i = 0;
		
		
		for (Tricluster sol:solutions){
			
			String str = "";
			
			List<Integer> times = sol.getTimes();
			List<Integer> genes = sol.getGenes();
			List<Integer> samples = sol.getSamples();
			
			str+="t;s;g;el\n";
						
			Iterator<Integer> it_t = times.iterator();
			while (it_t.hasNext()) {
				int v_t = (it_t.next()).intValue();
				Iterator<Integer> it_c = samples.iterator();
				while (it_c.hasNext()) {
					int v_c = (it_c.next()).intValue();
					Iterator<Integer> it_g = genes.iterator();
					while (it_g.hasNext()) {
						int v_g = (it_g.next()).intValue();
						double value = dataset[v_g][v_c][v_t];
						str+=v_t + ";" + v_c + ";" + v_g + ";"+format.format(value)+"\n";
					}
				}
			}
			
			f[i] = str;
			
			i++;
		}
		
		return f;

	}
	

	
}
