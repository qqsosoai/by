import java.util.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.text.*;
public class LocaleProgram {
	private final String c;
	public LocaleProgram(){
		c="abc";
	}
	public static void main(String[] args) {
		LocaleProgram a=new LocaleProgram();
		System.out.println(a.c);
	}
}
