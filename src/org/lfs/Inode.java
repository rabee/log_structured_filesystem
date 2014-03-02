package org.lfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Inode implements Serializable{
	
	private int[]  blockPointers = new int[1001];
	
	public Inode() throws IOException
	{
		
		
		for(int i = 0; i < 1001; i++){
			blockPointers[i] = i * 10;
		}
		
		
		FileOutputStream fos = new FileOutputStream("object");
		ObjectOutputStream o = new ObjectOutputStream(fos);
		o.writeObject(blockPointers);
		//o.writeObject(b);
		//o.writeObject(11);
		o.close();

		File file = new File("object");
		FileInputStream fin = new FileInputStream(file);
		byte[] fileContent = new byte[(int) file.length()];
		
		fin.read(fileContent);
		ObjectInputStream ois = new ObjectInputStream(fin);
		ois.read(fileContent, 0, 200);
		try {
			int[] readData = (int[]) ois.readObject();
			System.out.println(readData.length);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ois.close();
	//	fin.read;
	//	ArrayList<Integer> list = ;
	//	System.out.println(file.length());*/
	}
}
