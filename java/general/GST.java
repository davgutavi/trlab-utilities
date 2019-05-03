package general;

public class GST implements Comparable<GST>{

	private int g;
	private int s;
	private int t;
	
	public GST(int g, int s, int t) {
		this.g = g;
		this.s = s;
		this.t = t;
	}
	
	/**
	 * @return the g
	 */
	public int getG() {
		return g;
	}

	/**
	 * @return the s
	 */
	public int getS() {
		return s;
	}

	/**
	 * @return the t
	 */
	public int getT() {
		return t;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + g;
		result = prime * result + s;
		result = prime * result + t;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GST other = (GST) obj;
		if (g != other.g)
			return false;
		if (s != other.s)
			return false;
		if (t != other.t)
			return false;
		return true;
	}

	@Override
	public int compareTo(GST o) {
		
		int r = 0;
		
		int og = o.getG();
		
		int os = o.getS();
		
		int ot = o.getT();
		
		
		r = Integer.compare(g, og);
		
		if (r==0){
			
			r = Integer.compare(s, os);
			
			if (r==0){
			
				r = Integer.compare(t, ot);
				
			}
						
		}
				
		return r;
	
	}

	
	public String toString (){
		
		return "["+g+","+s+","+t+"]";
		
	}
		
}
