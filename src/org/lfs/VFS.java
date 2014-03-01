package org.lfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class VFS {
	
	private static RandomAccessFile LFSfile;
	private static int StartOfDataBlockRegion;
	
	
	public VFS() throws FileNotFoundException{
		
		LFSfile  = new RandomAccessFile("fileSystem\\lsfFile.txt", "rw");
		StartOfDataBlockRegion = 0;
		
	}
	
	
	public static void main(String[] args) throws IOException{
		
		VFS vfs;
		try {
			vfs = new VFS();
			
			byte[] buffer = new byte[4];
			
			//vfs.readRangeOfBytes(buffer,2);
			
			//String charS = new String(buffer);
			
			//System.out.println(charS);
			
			byte[] b = new byte[1024];
			new Random().nextBytes(b);
//			
			
			System.out.println(vfs.writeDataBlock(0, b));

			byte[] data = new byte[1024];
			
			System.out.println(vfs.readDataBlock(0, data));
			

			String charS = new String(data);
						
			System.out.println(charS);
						
			
			vfs.closeFile();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		
	}

	

	
	/*
	 * dbNumber : data block number , starts from 0
	 * bytes 	: data to be written 
	 * returns  : 1 on success, 0 on failure
	 */
	
	public int writeDataBlock(int dbNumber, byte[] data){
		
		if (data.length > 1024){
			return 0;
		}
		
		int seekPointer = (dbNumber * 1024) + StartOfDataBlockRegion;
		
		try {
			LFSfile.seek(seekPointer);
			LFSfile.write(data);
			return 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}	
	
	}
	
	/*
	 * dbNumber : data block number , starts from 0
	 * bytes 	: array to read data into 
	 * returns  : 1 on success, 0 on failure
	 */
	
	public int readDataBlock(int dbNumber , byte[] data) {
		
		if(data.length > 1024){
			return 0;
		}
		
		int seekPointer = (dbNumber * 1024) + StartOfDataBlockRegion;
		
		try {
			LFSfile.seek(seekPointer);
			LFSfile.readFully(data);
			return 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	} 
	
	
	
	public void closeFile(){
		try {
			LFSfile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
//	public void writeSomething() throws IOException{
//		Integer a = new Integer(1);
//		byte[] bytes = a.byteValue();
//		
//		System.out.println(bytes.length);
//		LFSfile.write(bytes);
//		
//	}
	
//	public  void readFromFile() throws IOException{
//		
//		LFSfile.seek(0);
//		
//		byte[] bytes = new byte[1];
//		//bytes[0] = LFSfile.readByte();
//		//bytes[1] = LFSfile.readByte();
//		
//		//int i = LFSfile.re
//		
//		//
//		
//		
//		for (int i=0;i<1000; i++){
//			
//			LFSfile.seek(i);
//			byte b[]=new byte[1];
//			b[0] = LFSfile.readByte();
//			
//			String charS = new String(b);
//			
//			System.out.println(i + " : " + charS);
//			
//		}
//		
//		
//	}
//	
//	public  void writeToFile(){	
//	}
//	
//	/*
//	 * byte[] buffer : size equal number of bytes you wants to read
//	 * int start     : start point in file measured from beginning of file
//	 */
//	public void readRangeOfBytes(byte[] buffer, int start ) throws IOException{
//		LFSfile.seek(start);
//		LFSfile.readFully(buffer);		
//	}
//	

	
	
	
}
