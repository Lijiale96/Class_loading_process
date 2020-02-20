package com.bjsxt.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 加密工具类
 * @author A
 *
 */
public class EncrptUtil {

	public static void main(String[] args) throws IOException {
		encrpt("d:/myjava/HelloWorld.class","d:/myjava/temp/HelloWorld.class");
		
	}
	public static void encrpt(String src,String dest) throws IOException {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream(src);
			fos = new FileOutputStream(dest);
		
			int temp =-1;
			while((temp=fis.read())!=-1) {
				fos.write(temp^0xff);//取反操作	
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	          try {
					if (fis!=null) {
					fis.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	try {
		if (fos!=null) {
		fos.close();
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
			}
			
		}
		
		
}
