package test.classLoader;

public class Test {
    static {
        i=0;
        // System.out.println(i); //���������ᱨ��Illegal forward reference (���Ϸ�����ǰ����)
    }
    static int i=1;

  	public static void main(String args[]){
        System.out.println(i);
    }
}