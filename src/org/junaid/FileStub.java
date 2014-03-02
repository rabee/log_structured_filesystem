package org.junaid;

public class FileStub {
	
	private int inodeNumber;
	private String fileName;
	private boolean isDir;
	
	public FileStub(String fileNam, int inodeNum, boolean isDir ){
		
		fileName = fileNam;
		inodeNumber = inodeNum;
		this.isDir = isDir;
	}
	
	public String getName(){
		return fileName;
	}
	public void  setName(String name){
		fileName = name;
	}
	public int getinodeNumber(){
		return inodeNumber;
	}
	public void setinodeNumber(int inodeNum){
		inodeNumber = inodeNum;
	}
	public boolean isDir(){
		return this.isDir;
	}
	

}
