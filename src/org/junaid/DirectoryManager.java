package org.junaid;

import java.util.ArrayList;
import java.util.HashMap;


public class DirectoryManager {

	private HashMap<String, ArrayList<FileStub>> directoryMap;
	private String currentWorkingDirectory;
	
	public DirectoryManager(){
		
		directoryMap = new HashMap<>();
		currentWorkingDirectory = "root";
		
	}
	
	public static void main(String args[]){
		
		DirectoryManager dm = new DirectoryManager();
		System.out.println( dm.getWorkingDirectory() );
		
		String data = "root=0:file1:1+0:newFile:2+1:dirA:0\ndirA=0:asfs:4+0:file4:5\n";
		
		dm.loadHashMap(data);
		dm.writeHashMap();
		
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
	
	public int deleteFile(String fileName){
		
		try {
			ArrayList<FileStub> tempFs = directoryMap.get(currentWorkingDirectory);
			
			for (FileStub tempfs : tempFs){
				
				if (tempfs.getName().equals(fileName)){					
					tempFs.remove(tempfs);

				}
			}
			return 1;
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		
	}
	
	public int deleteDirecory(String directoryName){
		
		try {
			directoryMap.remove(directoryName);
			
			
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		
	}
	
	public int changeWorkingDirectory(String directoryName){
		
		try{
			directoryMap.get(directoryName);
			currentWorkingDirectory = directoryName;
			return 1;
		}catch(Exception e){
			
			return 0;
		}
		
	}

	public String getWorkingDirectory(){
		
		return currentWorkingDirectory;
	}

	public int update(){
		
		return 0;
	}

	private boolean isValidPath(String path){
		
		String pathToken[] = path.split("/");
		
		if (pathToken.length == 0){
			// current dir create here
			return true;
		}
		else{
			
			boolean state = false;
			for (int i=0;i<pathToken.length; i ++){
				
			    
				ArrayList<FileStub> tempFS = directoryMap.get(pathToken[i]);
				
				for (FileStub tempfs : tempFS){
					
					if (tempfs.getName() == pathToken[i+1]){
						
					}
					
				}
			
			
			}
			
		}
		
		
		return false;
	}
		
	private int getInodeNmber(){
		
		return 0;
	}
	
	private void loadHashMap(String data){
		
		String lines[] = data.split("\n");
		//System.out.println(lines.length);
		
		for (String line : lines){
			String directory[] = line.split("="); 
		
			//System.out.println("dir: " + directory[0]);
			//System.out.println("dir dat: "+ directory[1]);
			
			String files[] = directory[1].split("\\+");

			ArrayList<FileStub> fList = new ArrayList<>();
			
			//System.out.println(files.length);
			
			for (String file : files ){
				
				String fileEntry[] = file.split(":");
				//System.out.println("file :" + fileEntry[0]);
				
				if (fileEntry[0].equals("0")){
					//System.out.println("lFile: " + fileEntry[1]);
					//System.out.println("in:" + fileEntry[2]);
					int iNnumber = Integer.parseInt(fileEntry[2]);
					
					FileStub fs = new FileStub(fileEntry[1], iNnumber, false);
					fList.add(fs);
					
				}
				else if (fileEntry[0].equals("1")){

					//System.out.println("lDir: " + fileEntry[1]);
					FileStub fs = new FileStub(fileEntry[1], 0, true);
					fList.add(fs);
				}
				
			}
			
			
			directoryMap.put(directory[0], fList);
		
		}
		
		//System.out.println(directoryMap.get("/"));
	}
	
	private void writeHashMap(){
		
		StringBuilder sb = new StringBuilder();
		ArrayList<FileStub> tempFL;
		
		for (String dir : directoryMap.keySet()){
			
			sb.append(dir+"=");
			
			tempFL = directoryMap.get(dir);
			
			for (FileStub tempFS : tempFL){
				
				if(tempFS.isDir()){
					sb.append("1:"+tempFS.getName()+":"+0);
				}
				else if(!tempFS.isDir()){
					sb.append("0:"+tempFS.getName()+":"+tempFS.getinodeNumber());
				}
			}
			sb.append("\n");
			
			
		}
		
		String dirHashMap = sb.toString();
		System.out.println(dirHashMap);
		
	}
	

}
