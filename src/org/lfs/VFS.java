package org.lfs;

import static org.lfs.Constants.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class VFS {

	private String vfsFileName;
	private static VFS vfs = null;
	private static RandomAccessFile vfsFile = null;
	
	public static VFS getVFSInstance(String vfsFileName){

		if (vfs == null){	
			vfs = new VFS(vfsFileName);
		}

		return vfs;

	}

	private VFS(String fileName){

		vfsFileName = fileName;


		
		if(!new File(vfsFileName).exists()){
		
			System.out.println("here");
			try {
				vfsFile = new RandomAccessFile(fileName, "rw");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			initializeFile(vfsFile);
		}else{

			// read checkpoint, directory pointer and inodes and load into memory.
			BufferedReader vfsReader;
			try {
				vfsReader = new BufferedReader(new FileReader(vfsFileName));
				System.out.println("length:" + new File(vfsFileName).length());
				
				String firstLine = vfsReader.readLine();
				System.out.println(firstLine);
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
	private void initializeFile(RandomAccessFile vfsFile){


		try {

			//writing checkpoint
			vfsFile.writeBytes("1-");

			//writing inodes. First inode points to directory


			//		vfsFileWriter.write("1!");

			for(int i = 0; i < numberOfInodes ; i++){

				for(int j = 0 ; j< blockPointersPerInode; j++){

					if(i == 0 && j == blockPointersPerInode -1){
						vfsFile.writeBytes("0001!");
					}else{
						vfsFile.writeBytes("0000!");
					}
				}
				vfsFile.writeBytes("-");


			}


		} catch (IOException e) {
			e.printStackTrace();
		}



	}



	private List<List<Integer>> getInodesIntegers(List<String> inodesString){

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
	
	public int writeDataBlock(int dbNumber, byte[] data){

		if (data.length > 1024){
			return 0;
		}

		int seekPointer = (dbNumber * 1024) + startOfDataBlocks;

		try {
			vfsFile.seek(seekPointer);
			vfsFile.write(data);
			return 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}	

	}
	
	public int readDataBlock(int dbNumber , byte[] data) {

		if(data.length > 1024){
			return 0;
		}
		int seekPointer = (dbNumber * 1024)  + startOfDataBlocks;

		try {
			vfsFile.seek(seekPointer);
			vfsFile.readFully(data);
			return 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
			return 0;
		}
	} 

}

















//	 * dbNumber : data block number , starts from 0
//	 * bytes 	: array to read data into 
//	 * returns  : 1 on success, 0 on failure


//	



//	public void closeFile(){
//		try {
//			LFSfile.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
//	}

////	public void writeSomething() throws IOException{
////		Integer a = new Integer(1);
////		byte[] bytes = a.byteValue();
////		
////		System.out.println(bytes.length);
////		LFSfile.write(bytes);
////		
////	}

////	public  void readFromFile() throws IOException{
////		
////		LFSfile.seek(0);
////		
////		byte[] bytes = new byte[1];
////		//bytes[0] = LFSfile.readByte();
////		//bytes[1] = LFSfile.readByte();
////		
////		//int i = LFSfile.re
////		
////		//
////		
////		
////		for (int i=0;i<1000; i++){
////			
////			LFSfile.seek(i);
////			byte b[]=new byte[1];
////			b[0] = LFSfile.readByte();
////			
////			String charS = new String(b);
////			
////			System.out.println(i + " : " + charS);
////			
////		}
////		
////		
////	}
////	
////	public  void writeToFile(){	
////	}
////	
////	/*
////	 * byte[] buffer : size equal number of bytes you wants to read
////	 * int start     : start point in file measured from beginning of file
////	 */
////	public void readRangeOfBytes(byte[] buffer, int start ) throws IOException{
////		LFSfile.seek(start);
////		LFSfile.readFully(buffer);		
////	}
////	




//}
