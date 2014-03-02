package org.junaid;

import org.lfs.Constants;

public class DataBlockManager {

	
	private int freeDataBlockIndex;
	
	
	public DataBlockManager(){
		
		freeDataBlockIndex = Constants.startingDataBlockPosition;		
	}
	
	
	
	int getFirstFreeDataBlock(){

		return freeDataBlockIndex;
	}
	
	int writeDataBlock(){
		
		freeDataBlockIndex++;
		
		
		return 0;
	}
	
	byte[] readDataBlock(){
		return null;}
	
	
}
