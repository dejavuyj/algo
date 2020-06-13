package test.classLoader;

/**
 * 测试类加载器加载目录
 */
public class TestClassLoaderPath {
    public static void main(String[] args)throws  Exception{
      	// BootStrap ClassLoader加载的文件
        System.out.println(System.getProperty("sun.boot.class.path"));

      	// ExtClassLoader加载的文件
      	System.out.println(System.getProperty("java.ext.dirs"));

      	// AppClassLoader加载的文件
      	System.out.println(System.getProperty("java.class.path"));
    }
}