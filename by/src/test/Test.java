package test;

import util.MyMd5;

public class Test {
	@org.junit.Test
	public void test(){
		System.out.println(MyMd5.toMd5String("admin"));
	}
	@org.junit.Test
	public void test1(){
		System.out.println(Integer.toHexString(1).toUpperCase());
	}
}
