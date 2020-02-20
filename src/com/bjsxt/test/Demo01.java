package com.bjsxt.test;

public class Demo01 {

	static {
		System.out.println("静态初始化Demo01");
	}
	
	
	public static void main(String[] args) throws Exception {
		System.out.println("Demo01的main方法！");
		
		//主动引用
		//new A(); //new一个类对象
		//System.out.println(A.width);//调用类的静态成员 除final
        //Class.forName("com.bjsxt.test.A");  //反射
		
		//被动引用
	    //System.out.println(A.MAX);  //引用常量
    	//A[]  as = new A[10];//数组
		System.out.println(B.width);  //通过子类引用父类静态变量
	}
}

class B extends A{
	static {
		System.out.println("静态初始化B");
	}
}
class A extends A_Father{
	public static int width =100;
	public static final int MAX=100;
	
	static {
		System.out.println("静态初始化类A");
		width=300;
		
	}
	public A(){
		System.out.println("创建A类的对象");
	}
}

class A_Father extends Object{
	static {
		System.out.println("静态初始化A_Father");
	}
}