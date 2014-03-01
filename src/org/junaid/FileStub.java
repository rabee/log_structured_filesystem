package org.junaid;

public class FileStub {
	
	private int inodeNumber;
	private String fileName;
	
	public FileStub(String fileNam, int inodeNum ){
		
		fileName = fileNam;
		inodeNumber = inodeNum;
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
	

}
