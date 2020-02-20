package com.bjsxt.test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 加载
 * @author A
 *
 */


public class DecrptClassLoader extends ClassLoader{

	// com.bjsxt.test.user    -->   //d:/myjava/com/bjsxt/test.class
	private String rootDir;
	
	public DecrptClassLoader(String rootDir) {
		this.rootDir=rootDir;
		
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		
		Class<?> c = findLoadedClass(name);
		
		//应该要先查询有没有加载过这个类。如果已经加载，则直接返回加载好的类。如果没有，则加载新的类
		if (c!=null) {
			return c;
		}else {
			ClassLoader parent = this.getParent();
			try {
			c = parent.loadClass(name);   //委派给父类加载
			}catch(Exception e) {
			//	e.printStackTrace();
			}
			if (c!=null) {
				return c;
			}else {
              byte[]  classData = getClassData(name);
              if (classData==null) {
				throw new ClassNotFoundException();
			}else {
				c=defineClass(name,classData,0,classData.length);			
			   }
	    	}
		}
		
		return c;
		
	}
	private byte[]  getClassData(String classname) {  // com.bjsxt.test.user    -->   //d:/myjava/com/bjsxt/test.class
	   String path = rootDir +"/"+classname.replace('.', '/')+".class";
	   
	 //  IOUtils,可以使用他将流中的数据转成字节数组
	   InputStream is =null;
	   ByteArrayOutputStream baos = new ByteArrayOutputStream();
	   try {
	      is = new FileInputStream(path);
	 
	      int temp =-1;
			while((temp=is.read())!=-1) {
				baos.write(temp^0xff);//取反操作	
			}   
	return baos.toByteArray();
	   }catch(Exception e){
		   e.printStackTrace();
		   return null;
	   }finally {
		   try {
		   if (is!=null) {	
			is.close();
			}
		   }catch (IOException e) {
				
				e.printStackTrace();
			}
		   try {
			   if (baos!=null) {	
				baos.close();
				}
			   }catch (IOException e) {
					
					e.printStackTrace();
				} 
		   
		  }
	   }
}
