package test.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class TestClassLoad {
    @Override
    public String toString() {
        return "����سɹ���";
    }
}
public class PathClassLoader extends ClassLoader {
    private String classPath;

    public PathClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getData(String className) {
        String path = classPath + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
        try {
            InputStream is = new FileInputStream(path);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int num = 0;
            while ((num = is.read(buffer)) != -1) {
                stream.write(buffer, 0, num);
            }
            return stream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String args[]) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException {
    	//ɾ��F:\YJ\project\test\bin\test\classLoaderĿ¼�µ�TestClassLoad.class,��AppClassLoader�Ҳ���
    	//F:\YJ\project\test\src\test\classLoader\test\classLoader\TestClassLoad.class
        ClassLoader pcl = new PathClassLoader("F:\\YJ\\project\\test\\src\\");
        Class c = pcl.loadClass("test.classLoader.TestClassLoad");//ע��Ҫ��������
        System.out.println(c.newInstance());//��ӡ����سɹ�.
    }
}