package org.lfs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.lfs.Constants.*;

public class VFS {
	
	public VFS(String fileName){
		
		File vfsFile = new File(fileName);
		
		if(!vfsFile.exists()){
			initializeFile(vfsFile);
		}
	
	}
	
	private void initializeFile(File vfsFile){
		
		BufferedWriter vfsFileWriter;
	
		try {
			vfsFileWriter = new BufferedWriter(new FileWriter(vfsFile));
			
			//writing checkpoint
			vfsFileWriter.write("1|");
			
			//writing inodes. First inode points to directory
			
			for(int i = 0 ; i< blockPointersPerInode-1; i++){

				vfsFileWriter.write("0|");
			}

			vfsFileWriter.write("1|");
			
			for(int i = 0; i < blockPointersPerInode * 1000 ; i++){
				
				vfsFileWriter.write("0|");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	

}
