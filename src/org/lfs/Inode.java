package org.lfs;

import java.util.ArrayList;
import java.util.List;
import static org.lfs.Constants.*;

public class Inode{

	private List<Integer> pointers;// = new ArrayList<Integer>();

	public void setInode(List<Integer> pointersPassed){
		
		assert pointersPassed.size() == 4 : "number of pointers passed is incorrect";
		
		pointers = pointersPassed;
		
	}
	
	public void allocate(int fileBlockNumber){
		
	}
	
	public int getPointer(int index){
		
		assert index < 4 && index >=0 : "Incorrect index passed";
		
		return pointers.get(index);
	}
}
