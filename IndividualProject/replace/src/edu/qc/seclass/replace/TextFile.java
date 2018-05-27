package edu.qc.seclass.replace;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class TextFile {
	private String pathname , contents;
	private PrintWriter pw;
	public TextFile(String pathname , String contents){
		this.pathname = pathname;
		this.contents = contents;
		try {
			pw = new PrintWriter(new File(pathname));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	String getContents() {
		return contents;
	}
	
	void setContents(String contents) {
		this.contents = contents;
	}

	public String getPathname() {
		return pathname;
	}

	public void setPathname(String pathname) {
		this.pathname = pathname;
	}
	

	public void write() {
		// TODO Auto-generated method stub
		pw.print(contents);
		pw.flush();
		
		
	}
}
