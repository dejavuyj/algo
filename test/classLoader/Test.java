package test.classLoader;

public class Test {
    static {
        i=0;
        // System.out.println(i); //这句编译器会报错：Illegal forward reference (不合法的向前引用)
    }
    static int i=1;

  	public static void main(String args[]){
        System.out.println(i);
    }
}