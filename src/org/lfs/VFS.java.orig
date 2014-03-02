package org.lfs;

import static org.lfs.Constants.blockPointersPerInode;
import static org.lfs.Constants.checkpointPosition;
import static org.lfs.Constants.directoryInodePosition;
import static org.lfs.Constants.numberOfInodes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class VFS {

	private String vfsFileName;

	public VFS(String fileName){

		vfsFileName = fileName;

		File vfsFile = new File(fileName);

		if(!vfsFile.exists()){
			initializeFile(vfsFile);
		}else{
			// read checkpoint, directory pointer and inodes and load into memory.
			BufferedReader vfsReader;
			try {
				vfsReader = new BufferedReader(new FileReader(vfsFileName));
				String firstLine = vfsReader.readLine();
				//System.out.println(firstLine);
				StringTokenizer firstLineTokenizer = new StringTokenizer(firstLine, "-");
				List<String> firstLineTokens = new ArrayList<String>();
				
				while(firstLineTokenizer.hasMoreTokens()){
					firstLineTokens.add(firstLineTokenizer.nextToken());
				}
				
				List<String> inodesString = firstLineTokens.subList(checkpointPosition, firstLineTokens.size());
				System.out.println("number of inodes = " + (inodesString.size()-1));
				
				int checkpoint = Integer.parseInt(firstLineTokens.get(checkpointPosition));
				
				System.out.println("checkpoint = " + checkpoint);
				
				List<List<Integer>> inodesList = getInodesIntegers(inodesString);
				System.out.println(inodesList.size());
				// TODO DirectoryManager.initialize(directoryInode);
				// TODO CheckpointManager.initialize(checkpoint);

					InodeManager.initialize(inodesList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	/*
	 * fills the file with default inode and checkpoint values
	 */
	private void initializeFile(File vfsFile){

		BufferedWriter vfsFileWriter;

		try {
			vfsFileWriter = new BufferedWriter(new FileWriter(vfsFile));

			//writing checkpoint
			vfsFileWriter.write("1-");

			//writing inodes. First inode points to directory


	//		vfsFileWriter.write("1!");

			for(int i = 0; i < numberOfInodes ; i++){

				for(int j = 0 ; j< blockPointersPerInode; j++){

					if(i == 0 && j == blockPointersPerInode -1){
						vfsFileWriter.write("0001!");
					}else{
						vfsFileWriter.write("0000!");
					}
				}
				vfsFileWriter.write("-");


			}

			vfsFileWriter.write("\n");
			vfsFileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}



	}



	public List<List<Integer>> getInodesIntegers(List<String> inodesString){

		List<List<Integer>> inodes = new ArrayList<List<Integer>>();
		
		int count = 0;
		for(String inodeString : inodesString){
			count++;
			if(count == 1){
				continue;
			}
			String[] inodeArrayForm = inodeString.split("!");
			assert inodeArrayForm.length == blockPointersPerInode : "incorrect inode size " + inodeArrayForm; 
			
			List<Integer> inode = new ArrayList<Integer>();
			for(String pointer : inodeArrayForm){
				inode.add(Integer.parseInt(pointer));
			}
			
			inodes.add(inode);
			inode = new ArrayList<Integer>();
		
		}
		
		return inodes;

	}
}
