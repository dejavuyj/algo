package test.classLoader;

/**
 * ���������������Ŀ¼
 */
public class TestClassLoaderPath {
    public static void main(String[] args)throws  Exception{
      	// BootStrap ClassLoader���ص��ļ�
        System.out.println(System.getProperty("sun.boot.class.path"));

      	// ExtClassLoader���ص��ļ�
      	System.out.println(System.getProperty("java.ext.dirs"));

      	// AppClassLoader���ص��ļ�
      	System.out.println(System.getProperty("java.class.path"));
    }
}