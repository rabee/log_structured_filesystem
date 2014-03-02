package org.junaid;

import java.util.ArrayList;
import java.util.HashMap;


public class DirectoryManager {

	private HashMap<String, ArrayList<FileStub>> directoryMap;
	private String currentWorkingDirectory;
	
	public DirectoryManager(){
		
		directoryMap = new HashMap<>();
		currentWorkingDirectory = "/";
		
	}
	
	public static void main(String args[]){
		
		DirectoryManager dm = new DirectoryManager();
		System.out.println( dm.getWorkingDirectory() );
	}
	
	
	
	public int createNewFile(String filePath){
		
		String[] tokens = filePath.split("/");
		
		if(tokens.length == 0){
			//directoryMap
			
		}
		
		return 0;
	}
	
	public int createNewDirectory(){
		
		return 0;
	}
	
	public int deleteFile(){
		return 0;
		
	}
	
	public int deleteDirecory(){
		return 0;
		
	}
	
	public int changeWorkingDirectory(){
		
		return 0;
	}

	public String getWorkingDirectory(){
		
		return currentWorkingDirectory;
	}

	public int update(){
		
		return 0;
	}

	private boolean isValidPath(){
		
		return false;
	}
		
	private int getInodeNmber(){
		
		return 0;
	}

}
