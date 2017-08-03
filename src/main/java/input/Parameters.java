package input;

import java.io.File;

public class Parameters {
	
	private File   outFolder;
	private String outName;
	
	
	public Parameters() {
		super();
		this.outFolder = new File(System.getProperty("user.home"));
		this.outName = "";
	}
	
	public Parameters(File outFolder, String outName) {
		super();
		this.outFolder = outFolder;
		this.outName = outName;
	}


	public File getOutFolder() {
		return outFolder;
	}


	public String getOutName() {
		return outName;
	}
	
	
	public void setOutFolder(File outFolder) {
		this.outFolder = outFolder;
	}

	public void setOutName(String outName) {
		this.outName = outName;
	}



	public String toString (){
		
		String r = "";
		
		r =		"\nOut Folder = "+outFolder.getAbsolutePath()+
				"\nOut Name = "+outName
				;
		
		return r;
		
	}
	

}
