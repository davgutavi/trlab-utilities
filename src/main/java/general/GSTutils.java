package general;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class GSTutils {
	
	
	public static Set<Integer> getComponentSet (Collection<Integer> component){
		
		Set<Integer> set = new HashSet<Integer> ();
		
		for(Integer num:component){
		
			set.add(new Integer(num.intValue()));			
		}
				
		return set;
		
	}
	
	public static int componentUnionCount (Set<Integer> s1, Set<Integer> s2){
		
		Set<Integer> union = new HashSet<Integer>(s1);
		
		union.addAll(s2);
				
		return union.size();
		
	}
	
	public static int componentIntersectionCount (Set<Integer> s1, Set<Integer> s2){
		
		Set<Integer> intersection = new HashSet<Integer>(s1);
		
		intersection.retainAll(s2);
				
		return intersection.size();
				
	}
	
	public static int cellUnionCount (List<Tricluster> group){
				
		Set<GST> union = new TreeSet<GST>();
		
		for (Tricluster tri:group){
			union.addAll(getCells(tri));
		}
					
		return union.size();
		
	}
	
	public static int cellUnionCount (Set<GST> s1, Set<GST> s2){
		
		Set<GST> union = new TreeSet<GST>(s1);
		
		union.addAll(s2);
				
		return union.size();
		
	}
	
	public static int cellIntersectionCount (List<Tricluster> group){
		
		Set<GST> intersection = new TreeSet<GST>();
		
		for (Tricluster tri:group){
			intersection.retainAll(getCells(tri));
		}
					
		return intersection.size();
		
	}
	
	
	public static int cellIntersectionCount (Set<GST> s1, Set<GST> s2){
		
		Set<GST> intersection = new TreeSet<GST>(s1);
		
		intersection.retainAll(s2);
				
		return intersection.size();
		
	}
		
	public static Set<GST> cellUnionSet (List<Tricluster> triclusters, Tricluster... excluding){
		
		Set<GST> set = new TreeSet<GST>();
				
		for (Tricluster tri:triclusters){
			
			if (!isIntoExcluding(tri,excluding)){
		
				set.addAll(getCells(tri));
			}
		
		}
				
		return set;
		
	}
	

	private static boolean isIntoExcluding(Tricluster tri, Tricluster[] excluding) {
		
		boolean enc = false;
		
		int i = 0;
		
		while (i<excluding.length&&!enc){
			if (excluding[i]==tri){
				enc = true;
			}
			i++;			
		}
			
		return enc;
	}
	
	
		
	public static Set<GST> getCells (Tricluster tri){
		
		Set<GST> set = new TreeSet<GST>();
		
		List<Integer> genes = tri.getGenes();
		
		List<Integer> samples = tri.getSamples();
		
		List<Integer> times = tri.getTimes();
		
		
		for(Integer ig:genes){
			
			for(Integer is:samples){
				
				for(Integer it:times){
					
					GST n = new GST(ig.intValue(), is.intValue(), it.intValue());
				
					set.add(n);
					
				}
								
			}
					
		}
				
		return set;
	}
	
	//Private
	
		
}