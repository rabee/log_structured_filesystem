package org.lfs;

import java.util.ArrayList;
import java.util.List;
import static org.lfs.Constants.*;

public class InodeManager {
	
	private static List<Inode> inodes;
	
//	int allocateInode(int fileBlockNumber){
//		
//		int inodeNumber = getFirstFreeInode();
//		Inode inodeToAllocate = inodes.get(inodeNumber);
//		inodeToAllocate.allocate(fileBlockNumber);
//	}

	
	
	private int getFirstFreeInode(){
		
		for(int i = 0; i < inodes.size(); i++){
			
			if(inodes.get(i).getPointer(blockPointersPerInode - 1) == 0){
				return i;
			}
		}
		System.out.println("No free inode");
		return -1;
	}
	
	public static void initialize(List<List<Integer>> inodesList){
		
		assert inodesList.size() == Constants.numberOfInodes : "number of inodes not consistent";
		
		
		//inodes = inodesList;
	}

}
